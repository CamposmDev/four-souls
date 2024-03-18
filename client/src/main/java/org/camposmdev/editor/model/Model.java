package org.camposmdev.editor.model;

import com.almasb.fxgl.dsl.FXGL;
import io.vertx.core.json.Json;
import javafx.stage.FileChooser;
import org.camposmdev.editor.ui.NotificationBar;
import org.camposmdev.editor.ui.factory.DialogFactory;
import org.camposmdev.model.atlas.MasterCardAtlas;
import org.camposmdev.model.atlas.MasterImageAtlas;
import org.camposmdev.model.atlas.ImageAtlas;
import org.camposmdev.util.Log;

import java.io.*;
import java.util.List;
import java.util.TreeMap;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;

public class Model {
    private static Model model = null;

    public static Model instance() {
        if (model == null) model = new Model();
        return model;
    }

    private final MasterImageAtlas imageAtlas;
    private MasterCardAtlas cardAtlas;

    private Model() {
        imageAtlas = initImageAtlas();
        cardAtlas = new MasterCardAtlas(new TreeMap<>(), new TreeMap<>());
    }

    private MasterImageAtlas initImageAtlas() {
        var deck = loadJSON("json/cards/cards.json", MasterImageAtlas.class);
//        loadJSON(deck.character(), ImageDataAtlas.class).loadSource2All();
//        deck.eternal().forEach((x, y) -> loadCards(y));
//        deck.treasure().forEach((x, y) -> loadCards(y));
//        deck.monster().forEach((x, y) -> loadCards(y));
//        deck.loot().forEach((x, y) -> loadCards(y));
//        deck.money().forEach((x, y) -> loadCards(y));
//        loadJSON(deck.bsoul(), ImageDataAtlas.class).loadSource2All();
//        loadJSON(deck.room(), ImageDataAtlas.class).loadSource2All();
        return deck;
    }

    private <T> T loadJSON(String src, Class<T> type) {
        var result = getAssetLoader().loadJSON(src, type);
        assert result.isPresent();
        return result.get();
    }

    private List<String> loadImageAtlas(String src) {
        return loadJSON(src, ImageAtlas.class)
                .images().keySet().stream().toList();
    }

    public MasterImageAtlas images() {
        return imageAtlas;
    }

    public MasterCardAtlas cards() {
        return cardAtlas;
    }

    /**
     * Fetches key set of card type given by name
     * @param name Type of card
     * @return List of keys from its respective category
     */
    public List<String> getImageAtlas(String name) {
        return switch (name) {
            case "character" -> loadImageAtlas(imageAtlas.character());
            case "peternal", "aeternal", "paideternal", "oeternal", "seternal" ->
                    loadImageAtlas(imageAtlas.eternal().get(name));
            case "ptreasure", "atreasure", "paidtreasure", "otreasure", "streasure" ->
                    loadImageAtlas(imageAtlas.treasure().get(name));
            case "bmonster", "cmonster", "hmonster", "chamonster", "gevent", "bevent", "curse", "boss", "epic" ->
                    loadImageAtlas(imageAtlas.monster().get(name));
            case "cards", "trinkets", "pills", "runes", "bombs", "butter", "batteries", "keys", "dice", "sheart", "bheart", "sack", "lsoul", "wildcard" ->
                    loadImageAtlas(imageAtlas.loot().get(name));
            case "1c", "2c", "3c", "4c", "5c", "10c" -> loadImageAtlas(imageAtlas.money().get(name));
            case "bsoul" -> loadImageAtlas(imageAtlas.bsoul());
            case "room" -> loadImageAtlas(imageAtlas.room());
            default -> null;
        };
    }

    public void loadCards() {
        var filterJSON = new FileChooser.ExtensionFilter("JSON", "*.json");
        var fc = new FileChooser();
        fc.setTitle("Open File");
        fc.getExtensionFilters().add(filterJSON);
        fc.setSelectedExtensionFilter(filterJSON);
        var f = fc.showOpenDialog(FXGL.getPrimaryStage());
        if (f == null) return;
        /* otherwise open the file */
        Log.info("Load JSON file: " + f);
        try (var fis = new FileInputStream(f)) {
            var data = fis.readAllBytes();
            String payload = new String(data);
            cardAtlas = Json.decodeValue(payload, MasterCardAtlas.class);
            NotificationBar.instance().push("Loaded " + f);
        } catch (IOException ex) {
            DialogFactory.instance().showErrorBox(ex);
        }
    }

    public void saveCards() {
        var filterJSON = new FileChooser.ExtensionFilter("JSON", "*.json");
        var fc = new FileChooser();
        fc.setTitle("Open File");
        fc.getExtensionFilters().add(filterJSON);
        fc.setSelectedExtensionFilter(filterJSON);
        fc.setInitialFileName("cards");
        var f = fc.showSaveDialog(FXGL.getPrimaryStage());
        if (f == null) return;
        /* otherwise save the cards */
        try(var fos = new FileOutputStream(f)) {
            var payload = cards().toString();
            fos.write(payload.getBytes());
            fos.flush();
            NotificationBar.instance().push("Saved " + f);
        } catch (IOException ex) {
            DialogFactory.instance().showErrorBox(ex);
        }
    }
}
