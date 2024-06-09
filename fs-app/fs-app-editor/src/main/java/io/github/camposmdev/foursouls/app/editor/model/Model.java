package io.github.camposmdev.foursouls.app.editor.model;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.FXGLForKtKt;
import io.github.camposmdev.foursouls.app.editor.api.API;
import io.github.camposmdev.foursouls.app.editor.ui.EditorAppBar;
import io.github.camposmdev.foursouls.app.editor.ui.factory.DialogFactory;
import io.github.camposmdev.foursouls.core.atlas.MasterCardAtlas;
import io.github.camposmdev.foursouls.core.card.BaseCard;
import io.github.camposmdev.foursouls.core.card.attribute.CardType;
import io.github.camposmdev.foursouls.core.util.assets.CardAsset;
import io.github.camposmdev.foursouls.core.util.assets.MasterCardAssetsManager;
import javafx.application.Platform;

import java.util.Map;
import java.util.Stack;

public class Model {
    private static Model model = null;

    public static Model instance() {
        if (model == null) model = new Model();
        return model;
    }

    private final MasterCardAssetsManager assetsManager;
    private MasterCardAtlas cardAtlas;
    private final Stack<BaseCard> recentCommits;

    private Model() {
        assetsManager = initAssetsManager();
        cardAtlas = new MasterCardAtlas();
        recentCommits = new Stack<>();
    }

    private MasterCardAssetsManager initAssetsManager() {
        final var src = "card_assets.json";
        var result = FXGLForKtKt.getAssetLoader().loadJSON(src, MasterCardAssetsManager.class);
        if (result.isPresent()) {
            return result.get();
        } else throw new RuntimeException("Failed to load " + src);
    }

    public MasterCardAssetsManager assets() {
        return assetsManager;
    }

    public void appendCard(BaseCard card) {
        /* add the card to the local master card atlas */
        cardAtlas.add(card);
        /* then upload this card to the server if able */
        API.instance().appendDeck(card);
        /* then add the card to recent commits */
        recentCommits.addFirst(card);
        /* update the app bar */
        EditorAppBar.instance().setRecentCommits(recentCommits);
    }

    /**
     * Fetches key set of card type given by name
     * @param cardType Type of card
     * @return Map<String, CardAsset> of keys from its respective category
     */
    public Map<String, CardAsset> getAssetsByType(CardType cardType) {
        return assetsManager.getAssetsByType(cardType);
    }

    /**
     *
     */
    public void load() {
        API.instance().getAllDecks().onSuccess(it -> {
           this.cardAtlas = it;
            Platform.runLater(() ->
                    FXGL.getNotificationService().pushNotification("Loaded Master Card Atlas"));
        }).onFailure(err -> Platform.runLater(() ->
				FXGL.getNotificationService().pushNotification("Failed to connect to server")));
    }

    /**
     * Save MasterCardAtlas to a file.
     */
    public void export() {
        DialogFactory.instance().saveMasterCardAtlas(cardAtlas);
    }

    /**
     * Checks if a card is implemented by checking if it exists in the MasterCardAtlas.
     * @param selectedCardType Card type of the card
     * @param id ID of the card
     * @return true if the card exists, otherwise false
     */
    public boolean isCardImplemented(CardType selectedCardType, String id) {
        return this.cardAtlas.contains(selectedCardType, id);
    }

    public void clearRecentCommits() {
        recentCommits.clear();
        EditorAppBar.instance().setRecentCommits(recentCommits);
    }
}
