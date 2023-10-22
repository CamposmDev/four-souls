package com.camposmdev.res_soup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class Main implements FileNames {
    public static void main(String[] args) throws Exception {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("goodbye world");
        }));
        setup();
        stealAssets(CARDS_URL, true);
    }

    public static void stealAssets(final String URL, boolean recursive) throws InterruptedException {
        System.out.println("Scraping " + URL + " ...");
        var conn = Jsoup.connect(URL);
        Document doc;
        try {
            doc = conn.get();
        } catch (IOException e) {
            System.out.println("Failed to get `" + URL + '`');
            return;
        }
        var cards = doc.getElementsByClass("cardGridCell");
        if (cards != null)
            downloadCards(cards);
        /* get all the page numbers */
        var pageNumbers = doc.getElementsByClass("page-numbers");
        if (pageNumbers != null && recursive) {
            for (int i = 1; i < pageNumbers.size(); i++) {
                var x = pageNumbers.get(i).attributes().get("href");
                stealAssets(x, false);
            }
        }
    }

    public static void downloadCards(Elements cards) throws InterruptedException {
        for (Element card : cards) {
            CardThreadHandler handler = new CardThreadHandler(card);
            handler.start();
            handler.join();
        }
    }

    public static void setup() {
        mkdir(RES_DIR);
        mkdir(RES_DIR + MONSTER_DIR);
        mkdir(RES_DIR + LOOT_DIR);
        mkdir(RES_DIR + TREASURE_DIR);
        mkdir(RES_DIR + PLAYER_DIR);
        mkdir(RES_DIR + ETERNAL_DIR);
        mkdir(RES_DIR + SOUL_DIR);
    }

    public static void mkdir(final String DIR) {
        File file = new File(DIR);
        if (!file.exists()) file.mkdir();
    }
}
