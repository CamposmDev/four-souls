package org.camposmdev.editor.model;

import org.camposmdev.model.json.CardAtlas;
import org.camposmdev.model.json.DeckAtlas;
import org.camposmdev.model.json.ImageDataAtlas;

import java.util.List;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;

public class Model {
    private static Model model = null;

    public static Model instance() {
        if (model == null) model = new Model();
        return model;
    }

    private DeckAtlas deckAtlas;
    private CardAtlas cardAtlas;

    private Model() {
        deckAtlas = load();
        cardAtlas = new CardAtlas();
    }

    private DeckAtlas load() {
        var deck = loadJSON("json/cards/cards.json", DeckAtlas.class);
        var characters = loadJSON(deck.character(), ImageDataAtlas.class);
        /* load all the character images */
        characters.images().values().forEach(data -> getAssetLoader().loadTexture(data.source2()));
//        deck.eternal().forEach(this::loadCards);
//        deck.treasure().forEach(this::loadCards);
//        deck.monster().forEach(this::loadCards);
//        deck.loot().forEach(this::loadCards);
//        deck.money().forEach(this::loadCards);
//        deck.bsoul().forEach(this::loadCards);
//        deck.room().forEach(this::loadCards);
        return deck;
    }

    private void loadCards(String src) {
        var atlas = loadJSON(src, ImageDataAtlas.class);
        atlas.images().values().forEach(data -> {
            getAssetLoader().loadTexture(data.source2());
        });
    }

    private <T> T loadJSON(String src, Class<T> type) {
        var result = getAssetLoader().loadJSON(src, type);
        assert result.isPresent();
        return result.get();
    }

    private List<String> loadImageDataAtlas(String src) {
        return loadJSON(src, ImageDataAtlas.class)
                .images().keySet().stream().toList();
    }

    public DeckAtlas deck() {
        return deckAtlas;
    }

    public CardAtlas cards() {
        return cardAtlas;
    }

    public List<String> fetch(String name) {
        return switch (name) {
            case "character" -> loadImageDataAtlas(deckAtlas.character());
            case "peternal", "aeternal", "paideternal", "oeternal", "seternal" ->
                    loadImageDataAtlas(deckAtlas.eternal().get(name));
            case "ptreasure", "atreasure", "paidtreasure", "otreasure", "streasure" ->
                    loadImageDataAtlas(deckAtlas.treasure().get(name));
            case "bmonster", "cmonster", "hmonster", "chamonster", "gevent", "bevent", "curse", "boss", "epic" ->
                    loadImageDataAtlas(deckAtlas.monster().get(name));
            case "cards", "trinkets", "pills", "runes", "bombs", "butter", "batteries", "keys", "dice", "sheart", "bheart", "sack", "lsoul", "wildcard" ->
                    loadImageDataAtlas(deckAtlas.loot().get(name));
            case "1c", "2c", "3c", "4c", "5c", "10c" -> loadImageDataAtlas(deckAtlas.money().get(name));
            case "bsoul" -> loadImageDataAtlas(deckAtlas.bsoul());
            case "room" -> loadImageDataAtlas(deckAtlas.room());
            default -> null;
        };
    }
}
