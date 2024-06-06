package io.github.camposmdev.foursouls.app.miser;

import io.github.camposmdev.foursouls.core.card.attribute.ImageInfo;
import io.vertx.core.json.JsonObject;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static io.github.camposmdev.foursouls.app.miser.Constants.JSON_DIR;

public class Util {
    /**
     * Stores the locations of other json files
     */
    private final static JsonObject theObject = new JsonObject();

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
            for (ImageInfo x : list) {
                var name = x.id();
                var value = JsonObject.of("url", x.url(),
                        "hiResUrl", x.hiResUrl(),
                        "loResUrl", x.loResUrl(),
                        "dir", x.dir());
                if (map.containsKey(name)) {
                    /* if this occurs, then we got a problem */
                    System.err.println("OVERWRITING\n" + name);
                    map.put(name, JsonObject.of(
                            "url", x.url(),
                            "hiResUrl", x.hiResUrl(),
                            "loResUrl", x.loResUrl(),
                            "dir", x.dir()
                    ));
                } else {
                    map.put(name, JsonObject.of(
                            "url", x.url(),
                            "hiResUrl", x.hiResUrl(),
                            "loResUrl", x.loResUrl(),
                            "dir", x.dir()
                    ));
                }
            }
            var theProduct = obj.encodePrettily();
            var pw = new PrintWriter(file);
            pw.println(theProduct);
            pw.close();
            System.out.println("Saved: " + file);

            /* update the global JSON object */
            /* acquire the parent dir name */
            var type = file.getParentFile().getName();
            /* create a key based off the file name without '.json' */
            var key = file.getName().substring(0, file.getName().indexOf(".json"));
            /* acquire the relative path to the file from the view of FXGL */
            var src = des.substring(des.indexOf("json"));

            switch (type) {
                case "character", "bsoul", "room", "outside" -> theObject.put(type, src);
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
        var file_name = parseURL(url);
        return JSON_DIR + dir + file_name + ".json";
    }

    /**
     * @brief Extracts URLs card_type attribute value
     * @param url URL to be parsed for its card_type attribute value
     * @return Value of card_type attribute
     */
    public static String parseURL(String url) {
        final String x = "card_type=";
        return url.substring(url.indexOf(x) + x.length());
    }

    public static String parseURLtoDIR(String url) {
        return parseURL(url) + '/';
    }
}
