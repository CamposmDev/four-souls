package org.camposmdev.editor.ui;

import com.almasb.fxgl.dsl.FXGL;
import org.camposmdev.editor.ui.workspace.Workspace;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import org.camposmdev.editor.model.Model;
import org.camposmdev.editor.ui.factory.DialogFactory;
import org.camposmdev.model.card.attribute.CardType;

public class AppBar {
    private final MenuBar menuBar;
    private final Workspace workspace;

    public AppBar(Workspace workspace) {
        super();
        this.workspace = workspace;
        var mFile = buildFileMenu();
        var mEdit = buildEditMenu();
        var mView = buildViewMenu();
        menuBar = new MenuBar(mFile, mEdit, mView);
    }

    private Menu buildFileMenu() {
        var miOpen = new MenuItem("Load...");
        miOpen.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.SHORTCUT_DOWN));
        miOpen.setOnAction(e -> Model.instance().loadCards());
        var miSave = new MenuItem("Save");
        miSave.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCodeCombination.SHORTCUT_DOWN));
        miSave.setOnAction(e -> Model.instance().saveCards());
        var miPreview = new MenuItem("Preview");
        miPreview.setAccelerator(new KeyCodeCombination(KeyCode.P, KeyCodeCombination.SHORTCUT_DOWN));
        miPreview.setOnAction(e -> {
            var payload = Model.instance().cards().toString();
            DialogFactory.instance().showPreviewBox(payload);
        });
        var miExit = new MenuItem("Exit");
        miExit.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCodeCombination.SHORTCUT_DOWN));
        miExit.setOnAction(e -> DialogFactory.instance().showExitBox());
        var menu = new Menu("File");
        menu.getItems().addAll(miOpen, miSave, miPreview, new SeparatorMenuItem(), miExit);
        return menu;
    }

    private Menu buildEditMenu() {
        var soulMenu = buildSoulMenu();
        var characterMenu = buildCharacterMenu();
        var eternalMenu = buildEternalMenu();
        var lootMenu = buildLootMenu();
        var moneyMenu = buildMoneyMenu();
        var monsterMenu = buildMonsterMenu();
        var roomMenu = buildRoomMenu();
        var treasureMenu = buildTreasureMenu();
        var menu = new Menu("Edit");
        menu.getItems().addAll(soulMenu, characterMenu,
                eternalMenu, lootMenu, moneyMenu,
                monsterMenu, roomMenu, treasureMenu);
        return menu;
    }

    private Menu buildViewMenu() {
        CheckMenuItem miDarkMode = new CheckMenuItem("Dark Mode");
        miDarkMode.setAccelerator(new KeyCodeCombination(KeyCode.D, KeyCombination.SHORTCUT_DOWN));
        miDarkMode.setOnAction(e -> {
            var css = FXGL.getAssetLoader().loadCSS("dark.css");
            if (miDarkMode.isSelected()) {
                FXGL.getPrimaryStage().getScene().getStylesheets().add(css.getExternalForm());
            } else {
                FXGL.getPrimaryStage().getScene().getStylesheets().remove(css.getExternalForm());
            }
        });
        var miFullScreen = new CheckMenuItem("Full Screen");
        miFullScreen.setAccelerator(new KeyCodeCombination((KeyCode.F11)));
        miFullScreen.setOnAction(e -> FXGL.getPrimaryStage().setFullScreen(miFullScreen.isSelected()));
        var menu = new Menu("View");
        menu.getItems().addAll(miDarkMode, miFullScreen);
        return menu;
    }

    private Menu buildSoulMenu() {
        var mi_bsoul = new MenuItem(CardType.BSOUL.pretty());
        mi_bsoul.setAccelerator(new KeyCodeCombination(KeyCode.B, KeyCombination.SHORTCUT_DOWN, KeyCombination.SHIFT_DOWN));
        mi_bsoul.setOnAction(new Handler());
        var mSoul = new Menu(CardType.BSOUL.pretty());
        mSoul.getItems().addAll(mi_bsoul);
        return mSoul;
    }

    private Menu buildCharacterMenu() {
        var mi_character = new MenuItem(CardType.CHARACTER.pretty());
        mi_character.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.SHORTCUT_DOWN, KeyCombination.SHIFT_DOWN));
        mi_character.setOnAction(new Handler());
        var mCharacter = new Menu(CardType.CHARACTER.pretty());
        mCharacter.getItems().addAll(mi_character);
        return mCharacter;
    }

    private Menu buildEternalMenu() {
        var mi_a_eternal = new MenuItem(CardType.AETERNAL.pretty());
        mi_a_eternal.setOnAction(new Handler());
        var mi_o_eternal = new MenuItem(CardType.OETERNAL.pretty());
        mi_o_eternal.setOnAction(new Handler());
        var mi_paid_eternal = new MenuItem(CardType.PAIDETERNAL.pretty());
        mi_paid_eternal.setOnAction(new Handler());
        var mi_p_eternal = new MenuItem(CardType.PETERNAL.pretty());
        mi_p_eternal.setOnAction(new Handler());
        var mi_s_eternal = new MenuItem(CardType.SETERNAL.pretty());
        mi_s_eternal.setOnAction(new Handler());
        var mEternal = new Menu(CardType.ETERNAL.pretty());
        mEternal.getItems().addAll(mi_a_eternal, mi_o_eternal,
                mi_paid_eternal, mi_p_eternal, mi_s_eternal);
        return mEternal;
    }

    private Menu buildLootMenu() {
        var mi_loot_batteries = new MenuItem(CardType.BATTERIES.pretty());
        mi_loot_batteries.setOnAction(new Handler());
        var mi_loot_bheart = new MenuItem(CardType.BHEART.pretty());
        mi_loot_bheart.setOnAction(new Handler());
        var mi_loot_bombs = new MenuItem(CardType.BOMBS.pretty());
        mi_loot_bombs.setOnAction(new Handler());
        var mi_loot_butter = new MenuItem(CardType.BUTTER.pretty());
        mi_loot_butter.setOnAction(new Handler());
        var mi_loot_cards = new MenuItem(CardType.CARDS.pretty());
        mi_loot_cards.setOnAction(new Handler());
        var mi_loot_dice = new MenuItem(CardType.DICE.pretty());
        mi_loot_dice.setOnAction(new Handler());
        var mi_loot_keys = new MenuItem(CardType.KEYS.pretty());
        mi_loot_keys.setOnAction(new Handler());
        var mi_loot_lsoul = new MenuItem(CardType.LSOUL.pretty());
        mi_loot_lsoul.setOnAction(new Handler());
        var mi_loot_pills = new MenuItem(CardType.PILLS.pretty());
        mi_loot_pills.setOnAction(new Handler());
        var mi_loot_runes = new MenuItem(CardType.RUNES.pretty());
        mi_loot_runes.setOnAction(new Handler());
        var mi_loot_sack = new MenuItem(CardType.SACK.pretty());
        mi_loot_sack.setOnAction(new Handler());
        var mi_loot_sheart = new MenuItem(CardType.SHEART.pretty());
        mi_loot_sheart.setOnAction(new Handler());
        var mi_loot_trinkets = new MenuItem(CardType.TRINKETS.pretty());
        mi_loot_trinkets.setOnAction(new Handler());
        var mi_loot_wildcard = new MenuItem(CardType.WILDCARD.pretty());
        mi_loot_wildcard.setOnAction(new Handler());
        mi_loot_wildcard.setOnAction(new Handler());
        var mLoot = new Menu(CardType.LOOT.pretty());
        mLoot.getItems().addAll(mi_loot_batteries, mi_loot_bheart, mi_loot_bombs,
                mi_loot_butter, mi_loot_cards, mi_loot_dice, mi_loot_keys, mi_loot_lsoul,
                mi_loot_pills, mi_loot_runes, mi_loot_sack, mi_loot_sheart,
                mi_loot_trinkets, mi_loot_wildcard);
        return mLoot;
    }

    private Menu buildMoneyMenu() {
        var mi_1c = new MenuItem(CardType.MONEY1C.pretty());
        mi_1c.setOnAction(new Handler());
        var mi_2c = new MenuItem(CardType.MONEY2C.pretty());
        mi_2c.setOnAction(new Handler());
        var mi_3c = new MenuItem(CardType.MONEY3C.pretty());
        mi_3c.setOnAction(new Handler());
        var mi_4c = new MenuItem(CardType.MONEY4C.pretty());
        mi_4c.setOnAction(new Handler());
        var mi_5c = new MenuItem(CardType.MONEY5C.pretty());
        mi_5c.setOnAction(new Handler());
        var mi_10c = new MenuItem(CardType.MONEY10C.pretty());
        mi_10c.setOnAction(new Handler());
        var mMoney = new Menu(CardType.MONEY.pretty());
        mMoney.getItems().addAll(mi_1c, mi_2c, mi_3c,
                mi_4c, mi_5c, mi_10c);
        return mMoney;
    }

    private Menu buildMonsterMenu() {
        var mi_bevent = new MenuItem(CardType.BEVENT.pretty());
        mi_bevent.setOnAction(new Handler());
        var mi_bmonster = new MenuItem(CardType.BMONSTER.pretty());
        mi_bmonster.setOnAction(new Handler());
        var mi_boss = new MenuItem(CardType.BOSS.pretty());
        mi_boss.setOnAction(new Handler());
        var mi_chamonster = new MenuItem(CardType.CHAMONSTER.pretty());
        mi_chamonster.setOnAction(new Handler());
        var mi_cmonster = new MenuItem(CardType.CMONSTER.pretty());
        mi_cmonster.setOnAction(new Handler());
        var mi_curse = new MenuItem(CardType.CURSE.pretty());
        mi_curse.setOnAction(new Handler());
        var mi_epic = new MenuItem(CardType.EPIC.pretty());
        mi_epic.setOnAction(new Handler());
        var mi_gevent = new MenuItem(CardType.GEVENT.pretty());
        mi_gevent.setOnAction(new Handler());
        var mi_hmonster = new MenuItem(CardType.HMONSTER.pretty());
        mi_hmonster.setOnAction(new Handler());
        var mMonster = new Menu(CardType.MONSTER.pretty());
        mMonster.getItems().addAll(mi_bevent, mi_bmonster, mi_boss,
                mi_chamonster, mi_cmonster, mi_curse, mi_epic,
                mi_gevent, mi_hmonster);
        return mMonster;
    }

    public Menu buildRoomMenu() {
        var mi_room = new MenuItem(CardType.ROOM.pretty());
        mi_room.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCombination.SHORTCUT_DOWN, KeyCombination.SHIFT_DOWN));
        mi_room.setOnAction(new Handler());
        var mRoom = new Menu(CardType.ROOM.pretty());
        mRoom.getItems().addAll(mi_room);
        return mRoom;
    }

    public Menu buildTreasureMenu() {
        var mi_atreasure = new MenuItem(CardType.ATREASURE.pretty());
        mi_atreasure.setOnAction(new Handler());
        var mi_otreasure = new MenuItem(CardType.OTREASURE.pretty());
        mi_otreasure.setOnAction(new Handler());
        var mi_paidtreasure = new MenuItem(CardType.PAIDTREASURE.pretty());
        mi_paidtreasure.setOnAction(new Handler());
        var mi_ptreasure = new MenuItem(CardType.PTREASURE.pretty());
        mi_ptreasure.setOnAction(new Handler());
        var mi_streasure = new MenuItem(CardType.STREASURE.pretty());
        mi_streasure.setOnAction(new Handler());
        var mTreasure = new Menu(CardType.TREASURE.pretty());
        mTreasure.getItems().addAll(mi_atreasure, mi_otreasure,
                mi_paidtreasure, mi_ptreasure, mi_streasure);
        return mTreasure;
    }

    public MenuBar getContent() {
        return menuBar;
    }

    private class Handler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            var node = event.getSource();
            var text = ((MenuItem) node).getText();
            workspace.edit(CardType.parse(text));
        }
    }
}
