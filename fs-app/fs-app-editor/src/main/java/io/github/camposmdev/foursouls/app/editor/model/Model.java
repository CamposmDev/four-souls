package io.github.camposmdev.foursouls.app.editor.model;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.FXGLForKtKt;
import io.github.camposmdev.foursouls.app.editor.ui.AppBar;
import io.github.camposmdev.foursouls.app.editor.ui.factory.DialogFactory;
import io.github.camposmdev.foursouls.app.editor.api.API;
import io.github.camposmdev.foursouls.model.atlas.ImageAtlas;
import io.github.camposmdev.foursouls.model.atlas.MasterCardAtlas;
import io.github.camposmdev.foursouls.model.atlas.MasterImageAtlas;
import io.github.camposmdev.foursouls.model.card.BaseCard;
import io.github.camposmdev.foursouls.model.card.attribute.CardType;
import javafx.application.Platform;

import java.util.List;
import java.util.Stack;

public class Model {
    private static Model model = null;

    public static Model instance() {
        if (model == null) model = new Model();
        return model;
    }

    private final MasterImageAtlas masterImageAtlas;
    private MasterCardAtlas masterCardAtlas;
    private final Stack<BaseCard> recentCommits;

    private Model() {
        masterImageAtlas = initImageAtlas();
        masterCardAtlas = new MasterCardAtlas();
        recentCommits = new Stack<>();
    }

    private MasterImageAtlas initImageAtlas() {
        return loadJSON("json/cards/cards.json", MasterImageAtlas.class);
    }

    private <T> T loadJSON(String src, Class<T> type) {
        var result = FXGLForKtKt.getAssetLoader().loadJSON(src, type);
        assert result.isPresent();
        return result.get();
    }

    private List<String> loadImageAtlas(String src) {
        return loadJSON(src, ImageAtlas.class)
                .images().keySet().stream().toList();
    }

    public MasterImageAtlas getImages() {
        return masterImageAtlas;
    }

    public void addCard(BaseCard card) {
        /* add the card to the local master card atlas */
        masterCardAtlas.add(card);
        /* then upload this card to the server if able */
        API.instance().appendDeck(card);
        /* then add the card to recent commits */
        recentCommits.addFirst(card);
        /* update the app bar */
        AppBar.instance().setRecentCommits(recentCommits);
    }

    /**
     * Fetches key set of card type given by name
     * @param cardType Type of card
     * @return List of keys from its respective category
     */
    public List<String> getImageAtlas(CardType cardType) {
        return switch (cardType) {
            case CHARACTER -> loadImageAtlas(masterImageAtlas.character());
            case PETERNAL, AETERNAL, SETERNAL, OETERNAL, PAIDETERNAL ->
                    loadImageAtlas(masterImageAtlas.eternal().get(cardType.key()));
            case PTREASURE, ATREASURE, PAIDTREASURE, OTREASURE, STREASURE ->
                    loadImageAtlas(masterImageAtlas.treasure().get(cardType.key()));
            case BMONSTER, CMONSTER, HMONSTER, CHAMONSTER, GEVENT, BEVENT, CURSE, BOSS, EPIC ->
                    loadImageAtlas(masterImageAtlas.monster().get(cardType.key()));
            case CARDS, TRINKETS, PILLS, RUNES, BOMBS, BUTTER, BATTERIES, KEYS, DICE, SHEART, BHEART, SACK, LSOUL, WILDCARD ->
                    loadImageAtlas(masterImageAtlas.loot().get(cardType.key()));
            case MONEY1C, MONEY2C, MONEY3C, MONEY4C, MONEY5C, MONEY10C ->
                    loadImageAtlas(masterImageAtlas.money().get(cardType.key()));
            case BSOUL -> loadImageAtlas(masterImageAtlas.bsoul());
            case ROOM -> loadImageAtlas(masterImageAtlas.room());
            case OUTSIDE -> loadImageAtlas(masterImageAtlas.outside());
            default -> null;
        };
    }

    public void loadMasterCardAtlas() {
        API.instance().getAllDecks().onSuccess(it -> {
           this.masterCardAtlas = it;
            Platform.runLater(() ->
                    FXGL.getNotificationService().pushNotification("Loaded Master Card Atlas"));
        }).onFailure(err -> Platform.runLater(() ->
				FXGL.getNotificationService().pushNotification("Failed to connect to server")));
    }

    public void saveMasterCardAtlas() {
        DialogFactory.instance().saveMasterCardAtlas(masterCardAtlas);
    }

    public boolean isCardImplemented(CardType selectedCardType, String id) {
        return this.masterCardAtlas.contains(selectedCardType, id);
    }

    public void clearRecentCommits() {
        recentCommits.clear();
        AppBar.instance().setRecentCommits(recentCommits);
    }
}
