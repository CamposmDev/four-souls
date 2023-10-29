package org.camposmdev.res_soup;

import org.camposmdev.model.card.meta.MetaCard;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static org.camposmdev.res_soup.Util.*;

public class Program implements FileNames {
    static final int N_THREADS = (int) (Runtime.getRuntime().availableProcessors() * 0.75d);

    static {
        System.out.println("N_THREADS=" + N_THREADS);
    }
    public static void main(String[] args) {
        final var START = System.currentTimeMillis();
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
        final var END = System.currentTimeMillis() - START;
        final var SEC = (END / 1000);
        final var MIN = (SEC / 60);
        System.out.printf("Done (%d min %d sec)\n", MIN, SEC);
    }

    /**
     * @brief Creates directories to form the directory structure to store all data files and image files in
     * the client module.
     */
    public static void mkdirs() {
        /* create the root folders */
        for (var x : ASS_DIRS) mkdir(x);
        /* create the sub-folders */
        for (var x : ETERNAL_URLS) mkdir(ETERNAL_DIR + parseURLtoDIR(x));
        for (var x : TREASURE_URLS) mkdir(TREASURE_DIR + parseURLtoDIR(x));
        for (var x : MONSTER_URLS) mkdir(MONSTER_DIR + parseURLtoDIR(x));
        for (var x : LOOT_URLS) mkdir(LOOT_DIR + parseURLtoDIR(x));
        for (var x : MONEY_URLS) mkdir(MONEY_DIR + parseURLtoDIR(x));
    }

    /**
     * @brief Downloads the back of cards of card types available in the tabletop game: Four Souls
     * @param dir The destination folder where the image file will be saved in
     */
    public static void downloadCardBacks(String dir) {
        /* download card backs */
        var conn = Jsoup.connect("https://foursouls.com/cards/");
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
                        src = pattern.matcher(src).replaceAll("");
                        var imgURL = "https://foursouls.com" + src;
                        var r = new ImageFetcherRunnable(dir, imgURL);
                        r.run();
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @brief Downloads info of the cards given from {urls} and stores the fetched images
     * to {imgdir} and data files to {datdir}
     * @param imgdir Used as the path to save the image files
     * @param datdir Used as the sub path to save the data files
     * @param urls Used to extract all the card info
     * @see FileNames
     */
    public static void downloadCards(String imgdir, String datdir, String... urls) {
        for (var x : urls) {
            var des = parseURLtoDAT(datdir, x);
            var fetcher = new CardFetcher(x);
            var cards = fetcher.get();
            saveMetaCards(des, cards);
            /* time to download those cards... */
            saveCardImages(buildDIR(imgdir, x), cards);
        }
    }

    /**
     * @brief Since directories: character, bsoul, room are the only ones that have no subdirectories.
     * The program has to update the {imgdir} to re-direct the destination path to save the image file to
     * the appropriate subdirectory.
     * @param imgdir Path to parent directory of the potential image that has to be saved to a subdirectory
     * @param url URL to sub-category of the type of card
     * @return Updated path to save the image file, otherwise returns {imgdir}
     */
    public static String buildDIR(String imgdir, String url) {
        if (imgdir.equals(CHARACTER_DIR) || imgdir.equals(BSOUL_DIR) || imgdir.equals(ROOM_DIR)) {
            return imgdir;
        } else {
            return (imgdir + parseURLtoDIR(url));
        }
    }

    public static void saveCardImages(String imgdir, List<MetaCard> cards) {
        var exe = Executors.newFixedThreadPool(N_THREADS);
        for (var c : cards) {
            var r = new ImageFetcherRunnable(imgdir, c.getImgURL());
            exe.execute(r);
        }
        try {
            exe.shutdown();
            var rc = exe.awaitTermination(5, TimeUnit.MINUTES);
            if (!rc) {
                System.err.println("DOWNLOADING TOOK TO LONG");
                exe.shutdownNow();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
