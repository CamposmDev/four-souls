package org.camposmdev.editor.ui;

import com.almasb.fxgl.dsl.FXGL;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import org.camposmdev.editor.model.Model;

public class Editor {
    private ObservableList<String> observableList = FXCollections.observableArrayList();
    private final BorderPane root;
    private CardMenu menuBox;
    private CardViewer cv;
    private VBox centerBox;
    private ListView<String> listView;
    public Editor(int width, int height) {
        root = new BorderPane();
        root.setStyle(String.format("""
                -fx-background-color: black;
                -fx-pref-width: %d;
                -fx-pref-height: %d;
                -fx-padding: 0, 0, 0, 12;
                """, width, height));
        initControls();
        root.setTop(menuBox.getContent());
        root.setLeft(cv.getContent());
        root.setCenter(centerBox);
        root.setRight(listView);
    }

    private void initControls() {
        menuBox = new CardMenu(this);
        cv = new CardViewer();
        centerBox = new VBox(10);
        listView = FXGL.getUIFactoryService().newListView(observableList);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        ChangeListener<String> listener = (ov, x, y) -> {
            if (listView.getSelectionModel().getSelectedItem() != null) {
                String key = listView.getSelectionModel().getSelectedItem();
                Image img;
                switch (deckSelected) {
                    case "character" -> img = Model.instance().deck().loadCharacterJSON().source2(key);
                    case "peternal" -> img = Model.instance().deck().loadPeternalJSON().source2(key);
                    case "aeternal" -> img = Model.instance().deck().loadAeternalJSON().source2(key);
                    case "paideternal" -> img = Model.instance().deck().loadPaideternalJSON().source2(key);
                    case "oeternal" -> img = Model.instance().deck().loadOeternalJSON().source2(key);
                    case "seternal" -> img = Model.instance().deck().loadSeternalJSON().source2(key);
                    case "ptreasure" -> img = Model.instance().deck().loadPtreasureJSON().source2(key);
                    case "atreasure" -> img = Model.instance().deck().loadAtreasureJSON().source2(key);
                    case "paidtreasure" -> img = Model.instance().deck().loadPaidtreasureJSON().source2(key);
                    case "otreasure" -> img = Model.instance().deck().loadOtreasureJSON().source2(key);
                    case "streasure" -> img = Model.instance().deck().loadStreasureJSON().source2(key);
                    case "bmonster" -> img = Model.instance().deck().loadBmonsterJSON().source2(key);
                    case "cmonster" -> img = Model.instance().deck().loadCmonsterJSON().source2(key);
                    case "hmonster" -> img = Model.instance().deck().loadHmonsterJSON().source2(key);
                    case "chamonster" -> img = Model.instance().deck().loadChamonsterJSON().source2(key);
                    case "gevent" -> img = Model.instance().deck().loadGeventJSON().source2(key);
                    case "bevent" -> img = Model.instance().deck().loadBeventJSON().source2(key);
                    case "curse" -> img = Model.instance().deck().loadCurseJSON().source2(key);
                    case "boss" -> img = Model.instance().deck().loadMonsterBossJSON().source2(key);
                    case "epic" -> img = Model.instance().deck().loadMonsterEpicJSON().source2(key);
                    case "cards" -> img = Model.instance().deck().loadLootCardsJSON().source2(key);
                    case "trinkets" -> img = Model.instance().deck().loadLootTrinketsJSON().source2(key);
                    case "pills" -> img = Model.instance().deck().loadLootPillsJSON().source2(key);
                    case "runes" -> img = Model.instance().deck().loadLootRunesJSON().source2(key);
                    case "bombs" -> img = Model.instance().deck().loadLootBombsJSON().source2(key);
                    case "butter" -> img = Model.instance().deck().loadLootButterJSON().source2(key);
                    case "batteries" -> img = Model.instance().deck().loadLootBatteriesJSON().source2(key);
                    case "keys" -> img = Model.instance().deck().loadLootKeysJSON().source2(key);
                    case "dice" -> img = Model.instance().deck().loadLootDiceJSON().source2(key);
                    case "sheart" -> img = Model.instance().deck().loadLootSheartJSON().source2(key);
                    case "bheart" -> img = Model.instance().deck().loadLootBheartJSON().source2(key);
                    case "sack" -> img = Model.instance().deck().loadLootSackJSON().source2(key);
                    case "lsoul" -> img = Model.instance().deck().loadLootLsoulJSON().source2(key);
                    case "wildcard" -> img = Model.instance().deck().loadLootWildcardJSON().source2(key);
                    case "1c" -> img = Model.instance().deck().loadMoney1cJSON().source2(key);
                    case "2c" -> img = Model.instance().deck().loadMoney2cJSON().source2(key);
                    case "3c" -> img = Model.instance().deck().loadMoney3cJSON().source2(key);
                    case "4c" -> img = Model.instance().deck().loadMoney4cJSON().source2(key);
                    case "5c" -> img = Model.instance().deck().loadMoney5cJSON().source2(key);
                    case "10c" -> img = Model.instance().deck().loadMoney10cJSON().source2(key);
                    case "bsoul" -> img = Model.instance().deck().loadBsoulJSON().source2(key);
                    case "room" -> img = Model.instance().deck().loadRoomJSON().source2(key);
                    default -> throw new IllegalArgumentException("Invalid key: " + key);
                }
                cv.setImage(img);
            }
        };
        listView.getSelectionModel().selectedItemProperty().addListener(listener);

    }

    public BorderPane getContent() {
        return root;
    }

    private String deckSelected;

    public void edit(String name) {
        var lst = Model.instance().fetch(name);
        observableList.setAll(lst);
        deckSelected = name;
        if (!observableList.isEmpty()) {
            listView.getSelectionModel().selectFirst();
        }
    }
}
