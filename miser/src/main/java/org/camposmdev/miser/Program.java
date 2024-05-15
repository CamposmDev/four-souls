package org.camposmdev.miser;

import org.camposmdev.model.Timex;
import org.camposmdev.model.atlas.ImageInfo;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static org.camposmdev.miser.Util.*;

public class Program implements Constants {
    static final int N_THREADS = (int) (Runtime.getRuntime().availableProcessors() * 0.75d);

    static {
        System.out.println("Utilizing N_THREADS=" + N_THREADS);
    }
    public static void main(String[] args) {
        var timer = new Timex().start();
        mkdirs();
        /* this is where the fun begins */
        downloadCardBacks(CARDS_DIR);
        downloadCards(BSOUL_DIR, "bsoul/", BSOUL_URL);
        downloadCards(CHARACTER_DIR, "character/" , CHARACTERS_URL);
        downloadCards(ETERNAL_DIR, "eternal/", ETERNAL_URLS);
        downloadCards(TREASURE_DIR, "treasure/", TREASURE_URLS);
        downloadCards(MONSTER_DIR, "monster/", MONSTER_URLS);
        downloadCards(LOOT_DIR, "loot/", LOOT_URLS);
        downloadCards(MONEY_DIR, "money/", MONEY_URLS);
        downloadCards(ROOM_DIR, "room/", ROOM_URL);
        downloadCards(OUTSIDE_DIR, "outside/", OUTSIDE_URL);
        saveTheObject();
        System.out.printf("Finished Raiding (%s)\n", timer.stop());
    }

    /**
     * Creates directories to form the dir structure to store all data files and image files in
     * the client module.
     */
    public static void mkdirs() {
        /* create the root folders */
        for (var x : Directories) mkdir(x);
        /* create the sub-folders */
        for (var x : ETERNAL_URLS) mkdir(ETERNAL_DIR + parseURLtoDIR(x));
        for (var x : TREASURE_URLS) mkdir(TREASURE_DIR + parseURLtoDIR(x));
        for (var x : MONSTER_URLS) mkdir(MONSTER_DIR + parseURLtoDIR(x));
        for (var x : LOOT_URLS) mkdir(LOOT_DIR + parseURLtoDIR(x));
        for (var x : MONEY_URLS) mkdir(MONEY_DIR + parseURLtoDIR(x));
    }

    /**
     * Downloads the back of cards of base types available in the game: Four Souls
     * @param des Destination directory to save the images in
     */
    public static void downloadCardBacks(String des) {
        /* download base backs */
        final var url = "https://foursouls.com/cards/";
        var conn = Jsoup.connect(url);
        try {
            var doc = conn.get();
            var divs = doc.getElementsByClass("CardTypeHover");
            for (var div : divs) {
                var imgTags = div.getElementsByTag("img");
                for (var imgTag : imgTags) {
                    if (!imgTag.attributes().get("src").startsWith("data:image")) {
                        var src = imgTag.attributes().get("src");
                        final var REGEX = "-110x150|-150x110";
                        var pattern = Pattern.compile(REGEX);
                        var lowResURL = url.substring(0, url.indexOf("/cards/")) + src;
                        var highResURL = url.substring(0, url.indexOf("/cards/")) + pattern.matcher(src).replaceAll("");
                        var info = new ImageInfo(url, highResURL, lowResURL, des.substring(des.indexOf("cards/")));
                        new ImagePillagerRunnable(des, info).run();
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    /**
     * Downloads info of the cards given from {urls} and stores the fetched images
     * to {imgdir} and data files to {datdir}
     * @param abs_dir Used as the path to save the image files
     * @param dir Used as the sub path to save the data files
     * @param args Used to extract all the base info
     * @see Constants
     */
    public static void downloadCards(String abs_dir, String dir, String... args) {
        for (var url : args) {
            var json_des = parseURLtoJSON(dir, url);
            var img_dir = buildDIR(abs_dir, url);
            var miser = new TheMiser(img_dir, url);
            /* time to download those cards... */
            downloadImages(img_dir, miser.getTheList());
            saveToJSON(json_des, miser.getTheList());
        }
    }


    /**
     * Since directories: character, bsoul, room are the only ones that have no subdirectories.
     * The program has to update the {imgdir} to re-direct the destination path to save the image file to
     * the appropriate subdirectory.
     * @param img_dir Path to parent dir of the potential image that has to be saved to a subdirectory
     * @param url URL to sub-category of the type of base
     * @return Updated path to save the image file, otherwise returns {imgdir}
     */
    public static String buildDIR(String img_dir, String url) {
        if (img_dir.equals(CHARACTER_DIR) || img_dir.equals(BSOUL_DIR) || img_dir.equals(ROOM_DIR) || img_dir.equals(OUTSIDE_DIR)) {
            return img_dir;
        } else {
            return (img_dir + parseURLtoDIR(url));
        }
    }

    public static void downloadImages(String dir_img, List<ImageInfo> list) {
        var exe = Executors.newFixedThreadPool(N_THREADS);
        for (var x : list) {
            var r = new ImagePillagerRunnable(dir_img, x);
            exe.execute(r);
        }
        try {
            exe.shutdown();
            var rc = exe.awaitTermination(3, TimeUnit.MINUTES);
            if (!rc) {
                System.err.printf("DOWNLOADING IMAGES FOR %s TOOK TOO LONG\n", dir_img);
                exe.shutdownNow();
            }
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
