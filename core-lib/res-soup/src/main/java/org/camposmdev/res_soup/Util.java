package org.camposmdev.res_soup;

import org.camposmdev.model.card.meta.ImageOriginInfo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.camposmdev.res_soup.Constants.DAT_DIR;

public class Util {
    public static void saveImageOriginInfo(String des, List<ImageOriginInfo> cards) {
        try {
            File file = new File(des);
            if (file.getName().equals("character.dat")) {
                var f = new File("./character.dat");
                writeObject(f, cards);
            }
            Path dir = Paths.get(file.getParent());
            Files.createDirectories(dir);
            writeObject(file, cards);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeObject(File f, List<ImageOriginInfo> cards) {
        try {
            var oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(cards);
            oos.close();
            System.out.println("Saved: " + f);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void mkdir(final String DIR) {
        File file = new File(DIR);
        if (!file.exists()) {
            var x = file.mkdirs();
            if (!x) System.err.println("FAILED: " + file);
        }
    }

    public static String parseURLtoDAT(String dir, String url) {
        return DAT_DIR + dir + parseURL(url) + ".dat";
    }

    /**
     * @brief Extracts URLs card_type attribute value
     * @param url
     * @return Value of card_type attribute
     */
    public static String parseURL(String url) {
        String x = "card_type=";
        return url.substring(url.indexOf("card_type=") + x.length());
    }

    public static String parseURLtoDIR(String url) {
        return parseURL(url) + '/';
    }
}
