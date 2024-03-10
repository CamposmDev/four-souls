package org.camposmdev.editor;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import org.camposmdev.model.json.DeckAtlas;
import org.camposmdev.model.json.ImageDataAtlas;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;

public class EditorApp extends GameApplication {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1600);
        settings.setHeight(900);
    }

    private DeckAtlas atlas;

    @Override
    protected void onPreInit() {
        atlas = loadDeck();
    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initGame() {

    }

    private DeckAtlas loadDeck() {
        var deck = loadJSON("json/cards/cards.json", DeckAtlas.class);
        var characters = loadJSON(deck.character(), ImageDataAtlas.class);
        characters.images().values().forEach(data -> getAssetLoader().loadTexture(data.source2()));
        deck.eternal().forEach(x -> loadCards(x));
        deck.treasure().forEach(x -> loadCards(x));
        deck.monster().forEach(x -> loadCards(x));
        deck.loot().forEach(x -> loadCards(x));
        deck.money().forEach(x -> loadCards(x));
        deck.bsoul().forEach(x -> loadCards(x));
        deck.room().forEach(x -> loadCards(x));
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
}
