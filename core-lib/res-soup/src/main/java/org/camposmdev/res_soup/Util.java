package org.camposmdev.res_soup;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.camposmdev.model.json.ImageData;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.camposmdev.res_soup.Constants.JSON_DIR;

public class Util {
    static JsonObject object = new JsonObject();

    public static void saveTheObject() {
        File file = new File(JSON_DIR + "cards.json");
        try (var pw = new PrintWriter(file)) {
            Path path = Paths.get(file.getParent());
            Files.createDirectories(path);
            pw.println(object.encodePrettily());
            System.out.println("Saved: " + file);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void saveImageDataToJSON(String des, List<ImageData> list) {
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

            var type = new File(file.getParent()).getName();
            var src = des.substring(des.indexOf("json"));
            switch (type) {
                case "character" -> object.put("character", src);
                case "eternal" -> {
                    if (object.containsKey("eternal"))
                        object.getJsonArray("eternal").add(src);
                    else object.put("eternal", JsonArray.of(src));
                }
                case "treasure" -> {
                    if (object.containsKey("treasure"))
                        object.getJsonArray("treasure").add(src);
                    else object.put("treasure", JsonArray.of(src));
                }
                case "monster" -> {
                    if (object.containsKey("monster"))
                        object.getJsonArray("monster").add(src);
                    else object.put("monster", JsonArray.of(src));
                }
                case "loot" -> {
                    if (object.containsKey("loot"))
                        object.getJsonArray("loot").add(src);
                    else object.put("loot", JsonArray.of(src));
                }
                case "money" -> {
                    if (object.containsKey("money"))
                        object.getJsonArray("money").add(src);
                    else object.put("money", JsonArray.of(src));
                }
                case "bsoul" -> {
                    if (object.containsKey("bsoul"))
                        object.getJsonArray("bsoul").add(src);
                    else object.put("bsoul", JsonArray.of(src));
                }
                case "room" -> {
                    if (object.containsKey("room"))
                        object.getJsonArray("room").add(src);
                    else object.put("room", JsonArray.of(src));
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
