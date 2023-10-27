package org.camposmdev.res_soup;

import org.camposmdev.model.card.meta.MetaCard;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.SocketTimeoutException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class CardFetcher {
    static final String UA = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36";
//    private String dir;
    private String url;
    private LinkedList<MetaCard> metaCards;

    public CardFetcher(String url) {
//        this.dir = dir;
        this.url = url;
        this.metaCards = new LinkedList<>();
    }

    private void fetchURLS(String url, boolean recursive) {
        var conn = Jsoup.connect(url);
        Document doc = null;
        var retries = 0;
        final var MAX_RETRIES = 5;
        while (retries < MAX_RETRIES) {
            try {
                doc = conn.userAgent(UA).get();
                System.out.println("Fetched: " + url);
                break;
            } catch (HttpStatusException | SocketTimeoutException ex) {
                System.out.println("Retrying[" + ++retries + '/' + MAX_RETRIES + "]: " + url);
            } catch (IOException ex) {
                throw new RuntimeException();
            }
        }
        assert doc != null : "NULL: " + url;
        var cards = doc.getElementsByClass("cardGridCell");
        if (!cards.isEmpty()) {
            parseCards(cards);
//            var t = new Thread(() -> downloadCards(cards));
//            try {
//                t.start();
//                t.join();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
        }
        var pageNumbers = doc.getElementsByClass("page-numbers");
        if (recursive) {
            for (int i = 1; i < pageNumbers.size(); i++) {
                var x = pageNumbers.get(i).attributes().get("href");
                fetchURLS(x, false);
            }
        }
    }

    private void parseCards(Elements cards) {
//        var executor = Executors.newFixedThreadPool(2);
        for (Element div : cards) {
            var aTags = div.getElementsByTag("a");
            assert !aTags.isEmpty() : "Failed to find <a> tags";
            var originURL = aTags.get(0).attributes().get("href");
            var imgTags = div.getElementsByTag("img");
            assert imgTags.size() > 1 : "Failed to find <img> tags";

            for (var img : imgTags) {
                var imgURL = img.attributes().get("src");
                if (!imgURL.startsWith("https")) continue;
                final var REGEX = "-308x420|-420x308|-420x300";
                var pattern = Pattern.compile(REGEX);
                imgURL = pattern.matcher(imgURL).replaceAll("");
                /* This image is not needed */
                if (imgURL.contains("FlipCornerNote.png")) {
                    System.out.println("Skipping: " + imgURL);
                    continue;
                }
//                var fileName = imgURL.substring(imgURL.lastIndexOf('/') + 1);
//                if (fileName.equals("FlipCornerNote.png")) {
//                    System.out.println("SKIPPING: " + imgURL);
//                    continue;
//                }
//                var file = new File(dir + fileName);
//                if (file.exists()) continue;
//                var r = new ImageFetcherRunnable(imgURL, file);
//                r.run();
//                executor.submit(new ImageFetcherRunnable(imgURL, file));
                var card = new MetaCard(originURL, imgURL, null);
                this.metaCards.add(card);
            }
        }
//        executor.shutdown();
    }
    public List<MetaCard> get() {
        fetchURLS(url, true);
        return this.metaCards;
    }
}
    