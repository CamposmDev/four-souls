package com.camposmdev.res_soup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class CardThreadHandler extends Thread implements FileNames {
    private Element card;

    public CardThreadHandler(Element card) {
        this.card = card;
    }

    @Override
    public void run() {
        super.run();

        var tags = card.getElementsByTag("a");
        assert tags.size() > 0 : "<a> tags less than zero";
        var href = tags.get(0).attributes().get("href");
        Document doc;
        try {
            doc = Jsoup.connect(href).get();
        } catch (IOException e1) {
            System.out.println("Failed to get " + href);
            return;
        }
        var cardLeft = doc.getElementById("CardLeft");
        var cardRight = doc.getElementById("CardRight");
        var cardBackEP = cardRight.getElementsByTag("a").get(0).attributes().get("href");
        var cardType = cardBackEP.substring(cardBackEP.lastIndexOf('/') + 1);
//        var div = doc.getElementById("OriginSet");
//        var val = div.getElementsByTag("p").get(1).text().toLowerCase();
        var srcImg = cardLeft.getElementsByTag("a").get(0).attributes().get("href");
        final String NAME = srcImg.substring(srcImg.lastIndexOf('/') + 1);
        var t = cardType.toLowerCase();
        if (t.contains("character")) {
            downloadImage(srcImg, RES_DIR + PLAYER_DIR + NAME);
        } else if (t.contains("eternal")) {
            downloadImage(srcImg, RES_DIR + ETERNAL_DIR + NAME);
        } else if (t.contains("treasure")) {
            downloadImage(srcImg, RES_DIR + TREASURE_DIR + NAME);
        } else if (t.contains("loot")) {
            downloadImage(srcImg, RES_DIR + LOOT_DIR + NAME);
        } else if (t.contains("monster")) {
            downloadImage(srcImg, RES_DIR + MONSTER_DIR + NAME);
        }  else if (t.contains("room")) {
            downloadImage(srcImg, RES_DIR + ROOM_DIR + NAME);
        } else {
            System.out.println("Not Supported: " + href);
        }
    }

    private void downloadImage(String src, String des) {
        try {
            File file = new File(des);
            if (file.exists()) return;
            URL url = new URL(src);
            BufferedImage buffImg = ImageIO.read(url);
            ImageIO.write(buffImg, "png", file);
            System.out.println("Downloaded " + des);
        } catch (IOException e) {
            System.out.println("Failed to write " + des);
        }
    }
}
