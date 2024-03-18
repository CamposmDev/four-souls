package org.camposmdev.editor.ui;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import org.camposmdev.editor.model.Model;
import org.camposmdev.editor.ui.workspace.CharacterEditor;
import org.camposmdev.editor.ui.workspace.eternal.EternalEditor;
import org.camposmdev.editor.ui.workspace.IEditor;
import org.camposmdev.editor.ui.workspace.Workspace;
import org.camposmdev.model.card.CardType;
import org.camposmdev.util.Log;

public class CardPicker {
    private final Workspace workspace;
    private final ObservableList<String> theList;
    private final HBox root;
    private final ListView<String> lv;
    private final CardViewer cv;
    private IEditor editor;

    private CardType selectedCardType;
    private Image currentImage;

    public CardPicker(Workspace theWorkspace) {
        workspace = theWorkspace;
        theList = FXCollections.observableArrayList();
        root = new HBox(8);
        root.setAlignment(Pos.CENTER);
        cv = new CardViewer();
        lv = new ListView<>(theList);
        lv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        ChangeListener<String> itemSelectedListener = (ov, prevKey, key) -> {
            if (key == null) return;
            if (root.getChildren().isEmpty()) {
                root.getChildren().addAll(lv, cv.getContent());
            }
            var data = Model.instance().images().getInfo(selectedCardType, key);
            editor.setId(data.name());
            editor.setImage(data.source2());
            currentImage = data.loadSource2();
            cv.setImage(currentImage);
        };
        lv.getSelectionModel().selectedItemProperty().addListener(itemSelectedListener);
        lv.setCellFactory(lv -> {
            var cell = new ListCell<String>() {
                @Override
                protected void updateItem(String s, boolean b) {
                    super.updateItem(s, b);
                    setText(s);
                }
            };
            cell.hoverProperty().addListener((ov, wasHovered, isHovered) -> {
                if (isHovered) {
                    if (!cell.isEmpty()) {
                        cv.setImage(Model.instance().images().loadSource2(selectedCardType, cell.getItem()));
                    }
                }
                if (wasHovered) {
                    if (currentImage != null) {
                        cv.setImage(currentImage);
                    } else {
                        cv.setImage(null);
                    }
                }
            });
            return cell;
        });
        lv.getSelectionModel().selectFirst();
    }

    public void edit(String cardType) {
        var lst = Model.instance().getImageAtlas(cardType);
        theList.setAll(lst);
        selectedCardType = CardType.parse(cardType);
        workspace.clear();
        if (!theList.isEmpty()) {
            /* display the appropriate workspace */
            switch (cardType) {
                case "character" -> editor = new CharacterEditor();
                case "peternal", "aeternal", "seternal", "oeternal", "paideternal" ->
                        editor = new EternalEditor(cardType);
                case "ptreasure", "atreasure", "paidtreasure", "otreasure", "streasure" ->
                        Log.fatal("NOT YET IMPLEMENTED");
//                        editor = new TreasureEditor(cardType);
                case "bmonster", "cmonster", "hmonster", "chamonster", "gevent", "bevent", "curse", "boss", "epic" ->
                        Log.fatal("NOT YET IMPLEMENTED");
//                        editor = new MonsterEditor(cardType);
                case "cards", "trinkets", "pills", "runes", "bombs", "butter", "batteries", "keys", "dice", "sheart", "bheart", "sack", "lsoul", "wildcard" ->
                        Log.fatal("NOT YET IMPLEMENTED");
//                        editor = new LootEditor();
//                case "1c", "2c", "3c", "4c", "5c", "10c" -> editor = new MoneyEditor();
//                case "bsoul" -> editor = new SoulEditor();
//                case "room" -> editor = new RoomEditor();
            }
            workspace.set(editor);
            lv.getSelectionModel().selectFirst();
        } else {
            /* remove the current image */
            cv.setImage(null);
        }
    }

    public HBox getContent() {
        return root;
    }
}
