package org.camposmdev.editor.ui;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import org.camposmdev.editor.model.Model;
import org.camposmdev.editor.ui.workspace.*;
import org.camposmdev.editor.ui.workspace.character.CharacterEditor;
import org.camposmdev.editor.ui.workspace.eternal.EternalEditor;
import org.camposmdev.editor.ui.workspace.extra.ExtraEditor;
import org.camposmdev.editor.ui.workspace.loot.LootEditor;
import org.camposmdev.editor.ui.workspace.money.MoneyEditor;
import org.camposmdev.editor.ui.workspace.room.RoomEditor;
import org.camposmdev.editor.ui.workspace.bsoul.BonusSoulEditor;
import org.camposmdev.editor.ui.workspace.treasure.TreasureEditor;
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
            /*
                When an item is selected, fetch its image info and update the editors
                id, image, and display the image in the card viewer
            */
            if (key == null) return;
            if (root.getChildren().isEmpty())
                root.addRow(0, lv, cv.getContent());
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
                    cb.setDisable(true);
//                    cb.setSelected(Model.instance().isImplemented(selectedCardType, s));
                    setGraphic(cb);
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
        selectedCardType = CardType.parse(cardType);
        var lst = Model.instance().getImageAtlas(selectedCardType);
        theList.setAll(lst);
        workspace.clear();
//        if (!theList.isEmpty()) {
            /* display the appropriate workspace */
            switch (selectedCardType) {
                case CHARACTER -> editor = new CharacterEditor();
                case PETERNAL, AETERNAL, SETERNAL, OETERNAL, PAIDETERNAL ->
                        editor = new EternalEditor(selectedCardType);
                case PTREASURE, ATREASURE, PAIDTREASURE, OTREASURE, STREASURE ->
                        editor = new TreasureEditor(selectedCardType);
                case BMONSTER, CMONSTER, HMONSTER, CHAMONSTER, GEVENT, BEVENT, CURSE, BOSS, EPIC ->
                        /* TODO - Implement MonsterEditor */
                        Log.fatal("NOT YET IMPLEMENTED");
//                        editor = new MonsterEditor(cardType);
                case CARDS, TRINKETS, PILLS, RUNES, BOMBS, BUTTER, BATTERIES, KEYS, DICE, SHEART, BHEART, SACK, LSOUL, WILDCARD ->
                        editor = new LootEditor(selectedCardType);
                case MONEY1C, MONEY2C, MONEY3C, MONEY4C, MONEY5C, MONEY10C ->
                        editor = new MoneyEditor(selectedCardType);
                case BSOUL ->
                       editor = new BonusSoulEditor(selectedCardType);
                case ROOM ->
                        editor = new RoomEditor(selectedCardType);
                case EXTRA ->
                        editor = new ExtraEditor(selectedCardType);
            }
            workspace.set(editor);
            lv.getSelectionModel().selectFirst();
//        }
    }

    public GridPane getContent() {
        return root;
    }
}
