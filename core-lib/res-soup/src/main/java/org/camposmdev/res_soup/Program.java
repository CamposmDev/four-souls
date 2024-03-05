package org.camposmdev.res_soup;

import org.camposmdev.model.Timex;
import org.camposmdev.model.json.ImageData;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static org.camposmdev.res_soup.Util.*;

public class Program implements Constants {
    static final int N_THREADS = (int) (Runtime.getRuntime().availableProcessors() * 0.75d);

    static {
        System.out.println("Utilizing N_THREADS=" + N_THREADS);
    }
    public static void main(String[] args) {
        var timer = new Timex().start();
        mkdirs();
        downloadCardBacks(CARDS_DIR);
        downloadCards(CHARACTER_DIR, "character/" , CHARACTERS_URL);
        downloadCards(ETERNAL_DIR, "eternal/", ETERNAL_URLS);
        downloadCards(TREASURE_DIR, "treasure/", TREASURE_URLS);
        downloadCards(MONSTER_DIR, "monster/", MONSTER_URLS);
        downloadCards(LOOT_DIR, "loot/", LOOT_URLS);
        downloadCards(MONEY_DIR, "money/", MONEY_URLS);
        downloadCards(BSOUL_DIR, "bsoul/", BSOUL_URL);
        downloadCards(ROOM_DIR, "room/", ROOM_CARDS_URL);
        saveTheObject();
        System.out.printf("Finished Raiding (%s)\n", timer.stop());
    }

    /**
     * Creates directories to form the directory structure to store all data files and image files in
     * the client module.
     */
    public static void mkdirs() {
        /* create the root folders */
        for (var x : DIRS) mkdir(x);
        /* create the sub-folders */
        for (var x : ETERNAL_URLS) mkdir(ETERNAL_DIR + parseURLtoDIR(x));
        for (var x : TREASURE_URLS) mkdir(TREASURE_DIR + parseURLtoDIR(x));
        for (var x : MONSTER_URLS) mkdir(MONSTER_DIR + parseURLtoDIR(x));
        for (var x : LOOT_URLS) mkdir(LOOT_DIR + parseURLtoDIR(x));
        for (var x : MONEY_URLS) mkdir(MONEY_DIR + parseURLtoDIR(x));
    }

    /**
     * Downloads the back of cards of card types available in the tabletop game: Four Souls
     * @param dir The destination folder where the image file will be saved in
     */
    public static void downloadCardBacks(String dir) {
        /* download card backs */
        final var ORIGIN = "https://foursouls.com/cards/";
        var conn = Jsoup.connect(ORIGIN);
        try {
            var doc = conn.get();
            var divList = doc.getElementsByClass("CardTypeHover");
            for (var div : divList) {
                var list = div.getElementsByTag("img");
                for (var imgTag : list) {
                    if (!imgTag.attributes().get("src").startsWith("data:image")) {
                        var src = imgTag.attributes().get("src");
                        final var REGEX = "-110x150|-150x110";
                        var pattern = Pattern.compile(REGEX);
                        var lowResURL = ORIGIN.substring(0, ORIGIN.indexOf("/cards/")) + src;
                        var highResURL = ORIGIN.substring(0, ORIGIN.indexOf("/cards/")) + pattern.matcher(src).replaceAll("");
                        var record = new ImageData(ORIGIN, highResURL, lowResURL, dir.substring(dir.indexOf("cards/")));
                        var r = new ImageFetcherRunnable(dir, record);
                        r.run();
                    }
                }
            }
        } catch (IOException ex) {
            System.err.println(ex.getCause().toString());
        }
    }

    /**
     * Downloads info of the cards given from {urls} and stores the fetched images
     * to {imgdir} and data files to {datdir}
     * @param abs_dir Used as the path to save the image files
     * @param dir Used as the sub path to save the data files
     * @param args Used to extract all the card info
     * @see Constants
     */
    public static void downloadCards(String abs_dir, String dir, String... args) {
        for (var url : args) {
            var json_des = parseURLtoJSON(dir, url);
            var img_dir = buildDIR(abs_dir, url);
            var miser = new TheMiser(img_dir, url);
            /* time to download those cards... */
            downloadImages(img_dir, miser.getTheList());
            saveImageDataToJSON(json_des, miser.getTheList());
        }
    }


    /**
     * Since directories: character, bsoul, room are the only ones that have no subdirectories.
     * The program has to update the {imgdir} to re-direct the destination path to save the image file to
     * the appropriate subdirectory.
     * @param img_dir Path to parent directory of the potential image that has to be saved to a subdirectory
     * @param url URL to sub-category of the type of card
     * @return Updated path to save the image file, otherwise returns {imgdir}
     */
    public static String buildDIR(String img_dir, String url) {
        if (img_dir.equals(CHARACTER_DIR) || img_dir.equals(BSOUL_DIR) || img_dir.equals(ROOM_DIR)) {
            return img_dir;
        } else {
            return (img_dir + parseURLtoDIR(url));
        }
    }

    public static void downloadImages(String dir_img, List<ImageData> list) {
        var exe = Executors.newFixedThreadPool(N_THREADS);
        for (var x : list) {
            var r = new ImageFetcherRunnable(dir_img, x);
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
