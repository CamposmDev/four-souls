package org.camposmdev.editor.model;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.FXGLForKtKt;
import org.camposmdev.editor.net.API;
import org.camposmdev.editor.ui.NotificationBar;
import org.camposmdev.editor.ui.factory.DialogFactory;
import javafx.stage.FileChooser;
import org.camposmdev.model.atlas.ImageAtlas;
import org.camposmdev.model.atlas.MasterCardAtlas;
import org.camposmdev.model.atlas.MasterImageAtlas;
import org.camposmdev.model.card.BaseCard;
import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.bsoul.BonusSoulCard;
import org.camposmdev.model.card.character.CharacterCard;
import org.camposmdev.model.card.eternal.EternalCard;
import org.camposmdev.model.card.extra.OutsideCard;
import org.camposmdev.model.card.loot.LootCard;
import org.camposmdev.model.card.monster.MonsterCard;
import org.camposmdev.model.card.room.RoomCard;
import org.camposmdev.model.card.treasure.TreasureCard;
import org.camposmdev.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Model {
    private static Model model = null;

    public static Model instance() {
        if (model == null) model = new Model();
        return model;
    }

    private final MasterImageAtlas masterImageAtlas;
    private MasterCardAtlas masterCardAtlas;

    private Model() {
        masterImageAtlas = initImageAtlas();
        masterCardAtlas = new MasterCardAtlas();
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
        switch (card.getCardType()) {
            case BSOUL -> API.instance().createBSoulCard((BonusSoulCard) card);
            case CHARACTER -> API.instance().createCharacterCard((CharacterCard) card);
            case PETERNAL, AETERNAL, PAIDETERNAL, OETERNAL, SETERNAL ->
                    API.instance().createEternalCard((EternalCard) card);
            case PTREASURE, ATREASURE, PAIDTREASURE, OTREASURE, STREASURE ->
                    API.instance().createTreasureCard((TreasureCard) card);
            case BMONSTER, CMONSTER, HMONSTER, CHAMONSTER, GEVENT, BEVENT, CURSE, BOSS, EPIC ->
                    API.instance().createMonsterCard((MonsterCard) card);
            case CARDS, TRINKETS, PILLS, RUNES, BOMBS, BUTTER, BATTERIES, KEYS, DICE, SHEART, BHEART, SACK, LSOUL, WILDCARD, MONEY, MONEY1C, MONEY2C, MONEY3C, MONEY4C, MONEY5C, MONEY10C ->
                    API.instance().createLootCard((LootCard) card);
            case ROOM -> API.instance().createRoomCard((RoomCard) card);
            case OUTSIDE -> API.instance().createOutsideCard((OutsideCard) card);
            default -> Log.fatal("Failed to upload card to server!");
        }
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
        var masterCardAtlas = API.instance().fetchMasterCardAtlas();
        if (masterCardAtlas == null) {
            NotificationBar.instance().push("Failed to load latest version of master card atlas");
        } else {
            NotificationBar.instance().push("Successfully loaded latest version of master card atlas");
            this.masterCardAtlas = masterCardAtlas;
        }
    }

    public void saveMasterCardAtlas() {
        DialogFactory.instance().saveMasterCardAtlas(masterCardAtlas);
    }

    public boolean isCardImplemented(CardType selectedCardType, String id) {
        return this.masterCardAtlas.contains(selectedCardType, id);
    }
}
