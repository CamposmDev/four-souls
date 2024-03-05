package org.camposmdev.res_soup;

import org.camposmdev.model.json.ImageData;

import java.util.LinkedList;
import java.util.List;

public class CardFetcher {
    static final String UA = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36";
    private static final int MAX_RETRIES = 10;
    private String src;
    private LinkedList<ImageData> highQualityInfoList;
    private LinkedList<ImageData> lowQualityInfoList;
    public CardFetcher(String src) {
        this.src = src;
        this.highQualityInfoList = new LinkedList<>();
        this.lowQualityInfoList = new LinkedList<>();
//        fetchURLS(src, true);
    }

    public List<ImageData> fetchHighQuality() {
        return this.highQualityInfoList;
    }

    public List<ImageData> fetchLowQuality() {
        return this.lowQualityInfoList;
    }

//    private void fetchURLS(String src, boolean recursive) {
//        var conn = Jsoup.connect(src);
//        Document doc = null;
//        var retries = 0;
//        while (retries < MAX_RETRIES) {
//            try {
//                doc = conn.userAgent(UA).get();
//                System.out.println("OK: " + src);
//                break;
//            } catch (HttpStatusException | SocketTimeoutException ex) {
//                System.out.println("Retrying[" + ++retries + '/' + MAX_RETRIES + "]: " + src);
//            } catch (IOException ex) {
//                throw new RuntimeException();
//            }
//        }
//        assert doc != null : "NULL: " + src;
//        var cards = doc.getElementsByClass("cardGridCell");
//        if (!cards.isEmpty()) {
//            this.parseCards(cards);
//        }
//        var pageNumbers = doc.getElementsByClass("page-numbers");
//        if (recursive) {
//            for (int i = 1; i < pageNumbers.size(); i++) {
//                var x = pageNumbers.get(i).attributes().get("href");
//                this.fetchURLS(x, false);
//            }
//        }
//    }

//    private void parseCards(Elements cards) {
//        for (Element div : cards) {
//            var aTags = div.getElementsByTag("a");
//            assert !aTags.isEmpty() : "Failed to find <a> tags";
//            var origin = aTags.get(0).attributes().get("href");
//            var imgTags = div.getElementsByTag("img");
//            assert imgTags.size() > 1 : "Failed to find <img> tags";
//            for (var img : imgTags) {
//                var imgURL = img.attributes().get("src");
//                if (!imgURL.startsWith("https")) continue;
//                final var REGEX = "-308x420|-420x308|-420x300";
//                var pattern = Pattern.compile(REGEX);
//                var matcher = pattern.matcher(imgURL);
//                if (matcher.find()) {
//                    var lowQualityInfo = new ImageRecord(origin, imgURL);
//                    this.lowQualityInfoList.add(lowQualityInfo);
//                    var imgURLHighQuality = matcher.replaceAll("");
//                    var highQualityInfo = new ImageRecord(origin, imgURLHighQuality);
//                    this.highQualityInfoList.add(highQualityInfo);
//                } else if (imgURL.contains("FlipCornerNote.png")) {
//                    System.out.println("Skipping: " + imgURL);
//                }
//            }
//        }
//    }
}
    