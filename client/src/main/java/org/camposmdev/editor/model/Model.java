package org.camposmdev.editor.model;

import com.almasb.fxgl.dsl.FXGL;
import io.vertx.core.json.Json;
import javafx.stage.FileChooser;
import org.camposmdev.editor.ui.NotificationBar;
import org.camposmdev.editor.ui.factory.DialogFactory;
import org.camposmdev.model.atlas.ImageAtlas;
import org.camposmdev.model.atlas.MasterCardAtlas;
import org.camposmdev.model.atlas.MasterImageAtlas;
import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

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
        cardAtlas = new MasterCardAtlas();
    }

    private MasterImageAtlas initImageAtlas() {
        //        loadJSON(deck.character(), ImageDataAtlas.class).loadSource2All();
//        deck.eternal().forEach((x, y) -> loadCards(y));
//        deck.treasure().forEach((x, y) -> loadCards(y));
//        deck.monster().forEach((x, y) -> loadCards(y));
//        deck.loot().forEach((x, y) -> loadCards(y));
//        deck.money().forEach((x, y) -> loadCards(y));
//        loadJSON(deck.bsoul(), ImageDataAtlas.class).loadSource2All();
//        loadJSON(deck.room(), ImageDataAtlas.class).loadSource2All();
        return loadJSON("json/cards/cards.json", MasterImageAtlas.class);
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
     * @param cardType Type of card
     * @return List of keys from its respective category
     */
    public List<String> getImageAtlas(CardType cardType) {
        return switch (cardType) {
            case CHARACTER -> loadImageAtlas(imageAtlas.character());
            case PETERNAL, AETERNAL, SETERNAL, OETERNAL, PAIDETERNAL ->
                    loadImageAtlas(imageAtlas.eternal().get(cardType.key()));
            case PTREASURE, ATREASURE, PAIDTREASURE, OTREASURE, STREASURE ->
                    loadImageAtlas(imageAtlas.treasure().get(cardType.key()));
            case BMONSTER, CMONSTER, HMONSTER, CHAMONSTER, GEVENT, BEVENT, CURSE, BOSS, EPIC ->
                    loadImageAtlas(imageAtlas.monster().get(cardType.key()));
            case CARDS, TRINKETS, PILLS, RUNES, BOMBS, BUTTER, BATTERIES, KEYS, DICE, SHEART, BHEART, SACK, LSOUL, WILDCARD ->
                    loadImageAtlas(imageAtlas.loot().get(cardType.key()));
            case MONEY1C, MONEY2C, MONEY3C, MONEY4C, MONEY5C, MONEY10C ->
                    loadImageAtlas(imageAtlas.money().get(cardType.key()));
            case BSOUL -> loadImageAtlas(imageAtlas.bsoul());
            case ROOM -> loadImageAtlas(imageAtlas.room());
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

    public boolean isComplete(CardType selectedCardType, String s) {
        /* TODO - Implement this method */
        return false;
    }
}
