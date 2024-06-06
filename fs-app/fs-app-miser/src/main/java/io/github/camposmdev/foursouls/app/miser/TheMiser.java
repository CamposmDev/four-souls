package io.github.camposmdev.foursouls.app.miser;

import io.github.camposmdev.foursouls.core.card.attribute.ImageInfo;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class TheMiser {
    static final String UA = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36";
    private static final int MAX_RETRIES = 10;
    private static final String REGEX = "-308x420|-420x308|-420x300";
    private final String des;
    private final List<ImageInfo> theList;

    /**
     * Fetch all the images of cards available in the given src parameter
     * @param des Destination directory to save the image file in
     * @param src Location to pillage for images of cards
     */
    public TheMiser(String des, String src) {
        /* Slice out the parent dir of cards since FXGL only the relative path starting from the textures dir */
        this.des = des.substring(des.indexOf("cards/"));
        this.theList = new LinkedList<>();
        raid(src, true);
    }

    public List<ImageInfo> getTheList() {
        return theList;
    }

    private Document ping(String src) {
        var conn = Jsoup.connect(src);
        Document doc = null;
        var retries = 0;
        while (retries < MAX_RETRIES) {
            try {
                doc = conn.userAgent(UA).get();
                System.out.println("Raided: " + src);
                break;
            } catch (HttpStatusException | SocketTimeoutException ex) {
                System.out.println("Retrying[" + ++retries + '/' + MAX_RETRIES + "]: " + src);
            } catch (IOException ex) {
                throw new RuntimeException();
            }
        }
        return doc;
    }

    private void raid(String src, boolean recursive) {
        var doc = ping(src);
        /* get all the cards in the grid */
        var cards = doc.getElementsByClass("cardGridCell");
        /* if there are elements, then fetch their data */
        if (!cards.isEmpty()) {
            this.pillage(cards);
        }
        var pageNumbers = doc.getElementsByClass("page-numbers");
        if (recursive) {
            for(var i = 1; i < pageNumbers.size(); i++) {
                var x = pageNumbers.get(i).attributes().get("href");
                this.raid(x, false);
            }
        }
    }

    private void pillage(Elements elements) {
        for (var div : elements) {
            var a_tags = div.getElementsByTag("a");
            if (a_tags.isEmpty()) return;
            var originURL = a_tags.getFirst().attributes().get("href");
            var img_tags = div.getElementsByTag("img");
            if (img_tags.isEmpty()) return;
            for (var img_tag: img_tags) {
                var imgURL = img_tag.attributes().get("src");
                if (!imgURL.startsWith("https")) continue;
                var pattern = Pattern.compile(REGEX);
                var matcher = pattern.matcher(imgURL);
                if (matcher.find()) {
                    var highResImgURL = matcher.replaceAll("");
                    var record = new ImageInfo(originURL, highResImgURL, imgURL, des);
                    theList.add(record);
                } else if (imgURL.contains("FlipCornerNote.png")) {
                    System.out.println("Skipping: " + imgURL);
                } else {
                    System.out.println("Outliar: " + imgURL);
                }
            }
        }
    }
}
