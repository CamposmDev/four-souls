package org.camposmdev.editor.ui;

import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import org.camposmdev.editor.net.API;
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

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

public class AppBar {
    private final MenuBar menuBar;
    private final Workspace workspace;

    public AppBar(Workspace workspace) {
        super();
        this.workspace = workspace;
        var mFile = buildFileMenu();
        var mEdit = buildEditMenu();
        var mView = buildViewMenu();
        var mHelp = buildHelpMenu();
        menuBar = new MenuBar(mFile, mEdit, mView, mHelp);
    }

    private Menu buildFileMenu() {
        var miOpen = new MenuItem("Load...");
        miOpen.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.SHORTCUT_DOWN));
        miOpen.setOnAction(e -> Model.instance().loadMasterCardAtlas());
        var miSave = new MenuItem("Save");
        miSave.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCodeCombination.SHORTCUT_DOWN));
        miSave.setOnAction(e -> Model.instance().saveMasterCardAtlas());
        var miPreview = new MenuItem("Preview");
        miPreview.setAccelerator(new KeyCodeCombination(KeyCode.P, KeyCodeCombination.SHORTCUT_DOWN));
        miPreview.setOnAction(e -> {
            try {
                Desktop.getDesktop().browse(new URI(API.base_url));
            } catch (IOException | URISyntaxException ex) {
                throw new RuntimeException();
            }
        });
        var miPref = new MenuItem("Preferences");
        miPref.setAccelerator(new KeyCodeCombination(KeyCode.COMMA, KeyCodeCombination.SHORTCUT_DOWN));
        miPref.setOnAction(e -> {
            DialogFactory.instance().showPreferences();
        });
        var miExit = new MenuItem("Exit");
        miExit.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCodeCombination.SHORTCUT_DOWN));
        miExit.setOnAction(e -> DialogFactory.instance().showExitBox());
        var menu = new Menu("File");
        menu.getItems().addAll(miOpen, miSave, miPreview, new SeparatorMenuItem(), miPref, new SeparatorMenuItem(), miExit);
        return menu;
    }

    private Menu buildEditMenu() {
        var bsoulMenu = buildBSoulMenu();
        var characterMenu = buildCharacterMenu();
        var eternalMenu = buildEternalMenu();
        var lootMenu = buildLootMenu();
        var monsterMenu = buildMonsterMenu();
        var roomMenu = buildRoomMenu();
        var treasureMenu = buildTreasureMenu();
        var outsideMenu = buildOutsideMenu();
        var menu = new Menu("Edit");
        menu.getItems().addAll(bsoulMenu, characterMenu,
                eternalMenu, treasureMenu, monsterMenu, lootMenu,
                roomMenu, outsideMenu);
        return menu;
    }

    private Menu buildViewMenu() {
        CheckMenuItem miDarkMode = new CheckMenuItem("Dark Mode");
        miDarkMode.setSelected(true);
        miDarkMode.setAccelerator(new KeyCodeCombination(KeyCode.D, KeyCombination.SHORTCUT_DOWN));
        miDarkMode.setOnAction(e -> {
            var css = FXGL.getAssetLoader().loadCSS("dark.css");
            var external = css.getExternalForm();
            var sheets = FXGL.getPrimaryStage().getScene().getStylesheets();
            if (miDarkMode.isSelected())
                sheets.add(external);
            else
                sheets.remove(external);
        });
        var miFullScreen = new CheckMenuItem("Full Screen");
        miFullScreen.setAccelerator(new KeyCodeCombination((KeyCode.F11)));
        miFullScreen.setOnAction(e -> FXGL.getPrimaryStage().setFullScreen(miFullScreen.isSelected()));
        var menu = new Menu("View");
        menu.getItems().addAll(miDarkMode, miFullScreen);
        return menu;
    }

    private Menu buildHelpMenu() {
        MenuItem miBug = new MenuItem("Submit a Bug Report");
        miBug.setOnAction(e -> {
            /* forward user to GitHub repo issues tab */
            try {
                Desktop.getDesktop().browse(new URI(API.issues));
            } catch (IOException | URISyntaxException ex) {
                throw new RuntimeException();
            }
        });
        MenuItem miAbout = new MenuItem("About");
        miAbout.setOnAction(e -> {
            /* show window to display info about this program */
            DialogFactory.instance().showAboutBox();
        });
        Menu m = new Menu("Help");
        m.getItems().addAll(miBug, miAbout);
        return m;
    }
    static final double ICON_SIZE = 16;
    private MenuItem buildBSoulMenu() {
        var iv = new ImageView(FXGL.image("cards/BonusSoulCardBack-110x150.png"));
        iv.setPreserveRatio(true);
        iv.setFitWidth(ICON_SIZE);
        var mi_bsoul = new MenuItem(CardType.BSOUL.pretty());
        mi_bsoul.setGraphic(iv);
        mi_bsoul.setAccelerator(new KeyCodeCombination(KeyCode.B, KeyCombination.SHORTCUT_DOWN, KeyCombination.SHIFT_DOWN));
        mi_bsoul.setOnAction(new MenuHandler());
        return mi_bsoul;
    }

    private MenuItem buildCharacterMenu() {
        var iv = new ImageView(FXGL.image("cards/CharacterCardBack-110x150.png"));
        iv.setPreserveRatio(true);
        iv.setFitWidth(ICON_SIZE);
        var mi_character = new MenuItem(CardType.CHARACTER.pretty());
        mi_character.setGraphic(iv);
        mi_character.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.SHORTCUT_DOWN, KeyCombination.SHIFT_DOWN));
        mi_character.setOnAction(new MenuHandler());
        return mi_character;
    }

    private Menu buildEternalMenu() {
        var iv = new ImageView(FXGL.image("cards/EternalCardBack-110x150.png"));
        iv.setPreserveRatio(true);
        iv.setFitWidth(ICON_SIZE);
        var mEternal = new Menu(CardType.ETERNAL.pretty());
        mEternal.setGraphic(iv);
        for (var x : CardType.eternals()) {
            var mi = new MenuItem(x.pretty());
            mi.setOnAction(new MenuHandler());
            mEternal.getItems().add(mi);
        }
        return mEternal;
    }

    private Menu buildLootMenu() {
        var iv = new ImageView(FXGL.image("cards/LootCardBack-110x150.png"));
        iv.setPreserveRatio(true);
        iv.setFitWidth(ICON_SIZE);
        var mLoot = new Menu(CardType.LOOT.pretty());
        mLoot.setGraphic(iv);
        for (var x : CardType.loot()) {
            if (x == CardType.MONEY) {
                var mMoney = new Menu(x.pretty());
                for (var y : CardType.money()) {
                    var mi = new MenuItem(y.pretty());
                    mi.setOnAction(new MenuHandler());
                    mMoney.getItems().add(mi);
                }
                mLoot.getItems().add(mMoney);
            } else {
                var mi = new MenuItem(x.pretty());
                mi.setOnAction(new MenuHandler());
                mLoot.getItems().add(mi);
            }
        }
        return mLoot;
    }

    private Menu buildMonsterMenu() {
        var iv = new ImageView(FXGL.image("cards/MonsterCardBack-110x150.png"));
        iv.setPreserveRatio(true);
        iv.setFitWidth(ICON_SIZE);
        var mMonster = new Menu(CardType.MONSTER.pretty());
        mMonster.setGraphic(iv);
        for (var type : CardType.monsters()) {
            var mi = new MenuItem(type.pretty());
            mi.setOnAction(new MenuHandler());
            mMonster.getItems().add(mi);
        }
        return mMonster;
    }

    public MenuItem buildRoomMenu() {
        var iv = new ImageView(FXGL.image("cards/RoomCardBack-150x110.png"));
        iv.setPreserveRatio(true);
        iv.setFitHeight(ICON_SIZE);
        var mi_room = new MenuItem(CardType.ROOM.pretty());
        mi_room.setGraphic(iv);
        mi_room.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCombination.SHORTCUT_DOWN, KeyCombination.SHIFT_DOWN));
        mi_room.setOnAction(new MenuHandler());
        return mi_room;
    }

    public Menu buildTreasureMenu() {
        var iv = new ImageView(FXGL.image("cards/TreasureCardBack-110x150.png"));
        iv.setPreserveRatio(true);
        iv.setFitWidth(ICON_SIZE);
        var mTreasure = new Menu(CardType.TREASURE.pretty());
        mTreasure.setGraphic(iv);
        for (var type : CardType.treasures()) {
            var mi = new MenuItem(type.pretty());
            mi.setOnAction(new MenuHandler());
            mTreasure.getItems().add(mi);
        }
        return mTreasure;
    }

    public MenuItem buildOutsideMenu() {
        var iv = new ImageView(FXGL.image("cards/OutsideCardBack-110x150.png"));
        iv.setPreserveRatio(true);
        iv.setFitWidth(ICON_SIZE);
        var mi_outside = new MenuItem(CardType.OUTSIDE.pretty());
        mi_outside.setGraphic(iv);
        mi_outside.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.SHORTCUT_DOWN, KeyCombination.SHIFT_DOWN));
        mi_outside.setOnAction(new MenuHandler());
        return mi_outside;
    }

    public MenuBar getContent() {
        return menuBar;
    }

    private class MenuHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            var node = event.getSource();
            var text = ((MenuItem) node).getText();
            workspace.edit(CardType.parse(text));
        }
    }
}
