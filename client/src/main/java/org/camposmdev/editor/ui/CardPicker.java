package org.camposmdev.editor.ui;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import org.camposmdev.editor.model.Model;
import org.camposmdev.editor.ui.workspace.CharacterEditor;
import org.camposmdev.editor.ui.workspace.IEditor;
import org.camposmdev.editor.ui.workspace.MoneyEditor;
import org.camposmdev.editor.ui.workspace.Workspace;
import org.camposmdev.editor.ui.workspace.eternal.EternalEditor;
import org.camposmdev.editor.ui.workspace.soul.SoulEditor;
import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.util.Log;

public class CardPicker {
    private final Workspace workspace;
    private final ObservableList<String> theList;
    private final GridPane root;
    private final ListView<String> lv;
    private final CardViewer cv;
    private IEditor editor;

    private CardType selectedCardType;
    private Image currentImage;

    public CardPicker(Workspace workspace) {
        this.workspace = workspace;
        theList = FXCollections.observableArrayList();
        root = new GridPane();
        cv = new CardViewer();
        lv = new ListView<>(theList);
        lv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        ChangeListener<String> itemSelectedListener = (ov, prevKey, key) -> {
            if (key == null) return;
            if (root.getChildren().isEmpty()) {
                root.addRow(0, lv, cv.getContent());
            }
            var data = Model.instance().images().getInfo(selectedCardType, key);
            editor.setId(data.name());
            editor.setImage(data.source2());
            currentImage = data.loadSource2();
            cv.setImage(currentImage);
        };
        /* add listener for when an item is selected from the list view */
        lv.getSelectionModel().selectedItemProperty().addListener(itemSelectedListener);
        /* set the cell factory to add a hover listener to preview cards */
        Callback<ListView<String>, ListCell<String>> cellFactory = (lv -> {
            var cell = new ListCell<String>() {
                @Override
                protected void updateItem(String s, boolean b) {
                    super.updateItem(s, b);
                    setText(s);
                    /* TODO - Implement check box to notify card has been implemented */
                    var cb = new CheckBox();
//                    cb.setDisable(true);
//                    cb.setSelected(Model.instance().isImplemented(selectedCardType, s));
//                    setGraphic(cb);
                }
            };
            cell.hoverProperty().addListener((ov, wasHovered, isHovered) -> {
                if (isHovered && !cell.isEmpty()) {
                    try {
                        var src = Model.instance().images().loadSource2(selectedCardType, cell.getItem());
                        cv.setImage(src);
                    } catch (NullPointerException ex) {
                        Log.warn("Failed to load " + selectedCardType + ":" + cell.getItem());
                    }
                }
                if (wasHovered) {
                    if (currentImage != null)
                        cv.setImage(currentImage);
                    else
                        cv.setImage(null);
                }
            });
            return cell;
        });
        lv.setCellFactory(cellFactory);
    }

    public void edit(String cardType) {
        cv.clear();
        var lst = Model.instance().getImageAtlas(cardType);
        theList.setAll(lst);
        selectedCardType = CardType.parse(cardType);
        workspace.clear();
        if (!theList.isEmpty()) {
            /* display the appropriate workspace */
            switch (cardType) {
                case "character" -> editor = new CharacterEditor();
                case "peternal", "aeternal", "seternal", "oeternal", "paideternal" ->
                        /* TODO - Implement EternalEditor */
                        editor = new EternalEditor(cardType);
                case "ptreasure", "atreasure", "paidtreasure", "otreasure", "streasure" ->
                        /* TODO - Implement TreasureEditor */
                        Log.fatal("NOT YET IMPLEMENTED");
//                        editor = new TreasureEditor(cardType);
                case "bmonster", "cmonster", "hmonster", "chamonster", "gevent", "bevent", "curse", "boss", "epic" ->
                        /* TODO - Implement MonsterEditor */
                        Log.fatal("NOT YET IMPLEMENTED");
//                        editor = new MonsterEditor(cardType);
                case "cards", "trinkets", "pills", "runes", "bombs", "butter", "batteries", "keys", "dice", "sheart", "bheart", "sack", "lsoul", "wildcard" ->
                        /* TODO - Implement LootEditor */
                        Log.fatal("NOT YET IMPLEMENTED");
//                        editor = new LootEditor();
                case "1c", "2c", "3c", "4c", "5c", "10c" ->
                        editor = new MoneyEditor(cardType);
                case "bsoul" ->
                        /* TODO - Implement SoulEditor */
                       editor = new SoulEditor();
                case "room" ->
                        /* TODO - Implement RoomEditor */
                        Log.fatal("NOT YET IMPLEMENTED");
//                        editor = new RoomEditor();
            }
            workspace.set(editor);
            lv.getSelectionModel().selectFirst();
        }
    }

    public GridPane getContent() {
        return root;
    }
}
