package org.camposmdev.editor.ui;

import com.almasb.fxgl.dsl.FXGL;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.*;
import org.camposmdev.editor.model.Model;
import org.camposmdev.editor.ui.factory.DialogFactory;
import org.camposmdev.editor.ui.workspace.Workspace;

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
        miFullScreen.setOnAction(e -> {
            FXGL.getPrimaryStage().setFullScreen(miFullScreen.isSelected());
        });
        var menu = new Menu("View");
        menu.getItems().addAll(miDarkMode, miFullScreen);
        return menu;
    }

    private Menu buildSoulMenu() {
        var mi_bsoul = new MenuItem("bsoul");
        mi_bsoul.setAccelerator(new KeyCodeCombination(KeyCode.B, KeyCombination.SHORTCUT_DOWN, KeyCombination.SHIFT_DOWN));
        mi_bsoul.setOnAction(new Handler());
        var mSoul = new Menu("Bonus Soul");
        mSoul.getItems().addAll(mi_bsoul);
        return mSoul;
    }

    private Menu buildCharacterMenu() {
        var mi_character = new MenuItem("character");
        mi_character.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.SHORTCUT_DOWN, KeyCombination.SHIFT_DOWN));
        mi_character.setOnAction(new Handler());
        var mCharacter = new Menu("Character");
        mCharacter.getItems().addAll(mi_character);
        return mCharacter;
    }

    private Menu buildEternalMenu() {
        var mi_a_eternal = new MenuItem("aeternal");
        mi_a_eternal.setOnAction(new Handler());
        var mi_o_eternal = new MenuItem("oeternal");
        mi_o_eternal.setOnAction(new Handler());
        var mi_paid_eternal = new MenuItem("paideternal");
        mi_paid_eternal.setOnAction(new Handler());
        var mi_p_eternal = new MenuItem("peternal");
        mi_p_eternal.setOnAction(new Handler());
        var mi_s_eternal = new MenuItem("seternal");
        mi_s_eternal.setOnAction(new Handler());
        var mEternal = new Menu("Eternal");
        mEternal.getItems().addAll(mi_a_eternal, mi_o_eternal,
                mi_paid_eternal, mi_p_eternal, mi_s_eternal);
        return mEternal;
    }

    private Menu buildLootMenu() {
        var mi_loot_batteries = new MenuItem("batteries");
        mi_loot_batteries.setOnAction(new Handler());
        var mi_loot_bheart = new MenuItem("bheart");
        mi_loot_bheart.setOnAction(new Handler());
        var mi_loot_bombs = new MenuItem("bombs");
        mi_loot_bombs.setOnAction(new Handler());
        var mi_loot_butter = new MenuItem("butter");
        mi_loot_butter.setOnAction(new Handler());
        var mi_loot_cards = new MenuItem("cards");
        mi_loot_cards.setOnAction(new Handler());
        var mi_loot_dice = new MenuItem("dice");
        mi_loot_dice.setOnAction(new Handler());
        var mi_loot_keys = new MenuItem("keys");
        mi_loot_keys.setOnAction(new Handler());
        var mi_loot_lsoul = new MenuItem("lsoul");
        mi_loot_lsoul.setOnAction(new Handler());
        var mi_loot_pills = new MenuItem("pills");
        mi_loot_pills.setOnAction(new Handler());
        var mi_loot_runes = new MenuItem("runes");
        mi_loot_runes.setOnAction(new Handler());
        var mi_loot_sack = new MenuItem("sack");
        mi_loot_sack.setOnAction(new Handler());
        var mi_loot_sheart = new MenuItem("sheart");
        mi_loot_sheart.setOnAction(new Handler());
        var mi_loot_trinkets = new MenuItem("trinkets");
        mi_loot_trinkets.setOnAction(new Handler());
        var mi_loot_wildcard = new MenuItem("wildcard");
        mi_loot_wildcard.setOnAction(new Handler());
        var mLoot = new Menu("Loot");
        mLoot.getItems().addAll(mi_loot_batteries, mi_loot_bheart, mi_loot_bombs,
                mi_loot_butter, mi_loot_cards, mi_loot_dice, mi_loot_keys, mi_loot_lsoul,
                mi_loot_pills, mi_loot_runes, mi_loot_sack, mi_loot_sheart,
                mi_loot_trinkets, mi_loot_wildcard);
        return mLoot;
    }

    private Menu buildMoneyMenu() {
        var mi_1c = new MenuItem("1c");
        mi_1c.setOnAction(new Handler());
        var mi_2c = new MenuItem("2c");
        mi_2c.setOnAction(new Handler());
        var mi_3c = new MenuItem("3c");
        mi_3c.setOnAction(new Handler());
        var mi_4c = new MenuItem("4c");
        mi_4c.setOnAction(new Handler());
        var mi_5c = new MenuItem("5c");
        mi_5c.setOnAction(new Handler());
        var mi_10c = new MenuItem("10c");
        mi_10c.setOnAction(new Handler());
        var mMoney = new Menu("Money");
        mMoney.getItems().addAll(mi_1c, mi_2c, mi_3c,
                mi_4c, mi_5c, mi_10c);
        return mMoney;
    }

    private Menu buildMonsterMenu() {
        var mi_bevent = new MenuItem("bevent");
        mi_bevent.setOnAction(new Handler());
        var mi_bmonster = new MenuItem("bmonster");
        mi_bmonster.setOnAction(new Handler());
        var mi_boss = new MenuItem("boss");
        mi_boss.setOnAction(new Handler());
        var mi_chamonster = new MenuItem("chamonster");
        mi_chamonster.setOnAction(new Handler());
        var mi_cmonster = new MenuItem("cmonster");
        mi_cmonster.setOnAction(new Handler());
        var mi_curse = new MenuItem("curse");
        mi_curse.setOnAction(new Handler());
        var mi_epic = new MenuItem("epic");
        mi_epic.setOnAction(new Handler());
        var mi_gevent = new MenuItem("gevent");
        mi_gevent.setOnAction(new Handler());
        var mi_hmonster = new MenuItem("hmonster");
        mi_hmonster.setOnAction(new Handler());

        var mMonster = new Menu("Monster");
        mMonster.getItems().addAll(mi_bevent, mi_bmonster, mi_boss,
                mi_chamonster, mi_cmonster, mi_curse, mi_epic,
                mi_gevent, mi_hmonster);
        return mMonster;
    }

    public Menu buildRoomMenu() {
        var mi_room = new MenuItem("room");
        mi_room.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCombination.SHORTCUT_DOWN, KeyCombination.SHIFT_DOWN));
        mi_room.setOnAction(new Handler());
        var mRoom = new Menu("Room");
        mRoom.getItems().addAll(mi_room);
        return mRoom;
    }

    public Menu buildTreasureMenu() {
        var mi_atreasure = new MenuItem("atreasure");
        mi_atreasure.setOnAction(new Handler());
        var mi_otreasure = new MenuItem("otreasure");
        mi_otreasure.setOnAction(new Handler());
        var mi_paidtreasure = new MenuItem("paidtreasure");
        mi_paidtreasure.setOnAction(new Handler());
        var mi_ptreasure = new MenuItem("ptreasure");
        mi_ptreasure.setOnAction(new Handler());
        var mi_streasure = new MenuItem("streasure");
        mi_streasure.setOnAction(new Handler());
        var mTreasure = new Menu("Treasure");
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
            var name = ((MenuItem) node).getText();
            workspace.edit(name);
        }
    }
}
