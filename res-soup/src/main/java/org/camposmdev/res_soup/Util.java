package org.camposmdev.res_soup;

import io.vertx.core.json.JsonObject;
import org.camposmdev.model.atlas.ImageInfo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.camposmdev.res_soup.Constants.JSON_DIR;

public class Util {
    static JsonObject theObject = new JsonObject();

    /**
     * Writes {theObject} as a JSON file to JSON_DIR as cards.json
     * @see Constants
     */
    public static void saveTheObject() {
        File file = new File(JSON_DIR + "cards.json");
        try (var pw = new PrintWriter(file)) {
            Path path = Paths.get(file.getParent());
            Files.createDirectories(path);
            pw.println(theObject.encodePrettily());
            System.out.println("Saved: " + file);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Writes {list} as a JSON file and appends {theObject} with the {des}
     * file name without '.json'
     * @param des Where to save the JSON
     * @param list Object to parse to JSON
     */
    public static void saveToJSON(String des, List<ImageInfo> list) {
        try {
            File file = new File(des);
            Path path = Paths.get(file.getParent());
            Files.createDirectories(path);
            /* write the json file */
            var map = new JsonObject();
            var obj = JsonObject.of("images", map);
            for (var x : list) {
                var name = x.name();
                if (map.containsKey(name)) {
                    /* if this occurs, then we got a problem */
                    System.err.println("OVERWRITING\n" + obj.getJsonObject(name).encodePrettily());
                } else {
                    map.put(name, JsonObject.of(
                            "origin", x.origin(),
                            "highResImgURL", x.highResImgURL(),
                            "lowResImgURL", x.lowResImgURL(),
                            "directory", x.directory()
                    ));
                }
            }
            var theProduct = obj.encodePrettily();
            var pw = new PrintWriter(file);
            pw.println(theProduct);
            pw.close();
            System.out.println("Saved: " + file);

            /* update the global JSON object */
            /* acquire the parent directory name */
            var type = file.getParentFile().getName();
            /* create a key based off the file name without '.json' */
            var key = file.getName().substring(0, file.getName().indexOf(".json"));
            /* acquire the relative path to the file from the view of FXGL */
            var src = des.substring(des.indexOf("json"));

            switch (type) {
                case "character", "bsoul", "room" -> theObject.put(type, src);
                case "eternal", "treasure", "monster", "loot", "money" -> {
                    if (theObject.containsKey(type))
                        theObject.getJsonObject(type).put(key, src);
                    else theObject.put(type, JsonObject.of(key, src));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void mkdir(final String DIR) {
        File file = new File(DIR);
        if (!file.exists()) {
            var x = file.mkdirs();
            if (!x) System.err.println("FAILED: " + file);
        }
    }

    public static String parseURLtoJSON(String dir, String url) {
        return JSON_DIR + dir + parseURL(url) + ".json";
    }

    /**
     * @brief Extracts URLs card_type attribute value
     * @param url URL to be parsed for its card_type attribute value
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
