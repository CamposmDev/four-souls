package io.github.camposmdev.foursouls.app.editor.ui;

import com.almasb.fxgl.dsl.FXGL;
import io.github.camposmdev.foursouls.app.editor.ui.workspace.CardEditor;
import io.github.camposmdev.foursouls.app.editor.ui.workspace.Workspace;
import io.github.camposmdev.foursouls.app.editor.ui.workspace.bsoul.BonusSoulEditor;
import io.github.camposmdev.foursouls.app.editor.ui.workspace.eternal.EternalEditor;
import io.github.camposmdev.foursouls.app.editor.ui.workspace.loot.LootEditor;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import io.github.camposmdev.foursouls.app.editor.model.Model;
import io.github.camposmdev.foursouls.app.editor.ui.workspace.character.CharacterEditor;
import io.github.camposmdev.foursouls.app.editor.ui.workspace.outside.OutsideEditor;
import io.github.camposmdev.foursouls.app.editor.ui.workspace.money.MoneyEditor;
import io.github.camposmdev.foursouls.app.editor.ui.workspace.monster.MonsterEditor;
import io.github.camposmdev.foursouls.app.editor.ui.workspace.room.RoomEditor;
import io.github.camposmdev.foursouls.app.editor.ui.workspace.treasure.TreasureEditor;
import io.github.camposmdev.foursouls.core.card.attribute.CardType;
import io.github.camposmdev.foursouls.core.ui.Log;

public class CardPicker {
    private final Workspace workspace;
    private final ObservableList<String> observableList;
    private final VBox root;
    private final ListView<String> assetIdListView;
    private final CardViewer cv;
    private CardEditor editor;

    private CardType selectedCardType;
    private Image currentImage;

    public CardPicker(Workspace workspace) {
        this.workspace = workspace;
        observableList = FXCollections.observableArrayList();
        root = new VBox();
        cv = new CardViewer();
        assetIdListView = new ListView<>(observableList);
        assetIdListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        ChangeListener<String> itemSelectedListener = (ov, prevKey, key) -> {
            /*
                When an item is selected, fetch its image info and update the editors
                id, image, and display the image in the card viewer
            */
            if (key == null) return;
            if (root.getChildren().isEmpty()) {
                root.getChildren().addAll(cv.getContent(), assetIdListView);
                VBox.setVgrow(assetIdListView, Priority.ALWAYS);
            }
            var data = Model.instance().assets().getAssetById(selectedCardType, key);
			assert data != null;
			editor.setId(data.id());
            editor.setImage(data);

            currentImage = FXGL.image(data.hiResSrc());
            cv.setImage(currentImage);
        };
        /* add listener for when an item is selected from the list view */
        assetIdListView.getSelectionModel().selectedItemProperty().addListener(itemSelectedListener);
        /* set the cell factory to add a hover listener to preview cards */
        Callback<ListView<String>, ListCell<String>> cellFactory = (lv -> {
            var cell = new ListCell<String>() {
                @Override
                protected void updateItem(String s, boolean b) {
                    super.updateItem(s, b);
                    setText(s);
                    var cb = new CheckBox();
                    cb.setSelected(Model.instance().isCardImplemented(selectedCardType, s));
                    cb.setDisable(cb.isSelected());
                    setGraphic(cb);
                }
            };
            cell.hoverProperty().addListener((ov, wasHovered, isHovered) -> {
                if (isHovered && !cell.isEmpty()) {
                    try {
                        var src = Model.instance().assets().getAssetById(selectedCardType, cell.getItem());
                        assert src != null;
                        var img = FXGL.image(src.hiResSrc());
                        cv.setImage(img);
                    } catch (NullPointerException ex) {
                        Log.warnf("Failed to load [%s]: %s", selectedCardType, cell.getItem());
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
        assetIdListView.setCellFactory(cellFactory);
    }

    public void edit(CardType cardType) {
        cv.clear();
        selectedCardType = cardType;
        var map = Model.instance().getAssetsByType(selectedCardType);
        observableList.setAll(map.keySet());
        /* display the appropriate workspace */
        switch (selectedCardType) {
            case CHARACTER -> editor = new CharacterEditor();
            case PETERNAL, AETERNAL, SETERNAL, OETERNAL, PAIDETERNAL ->
                    editor = new EternalEditor(selectedCardType);
            case PTREASURE, ATREASURE, PAIDTREASURE, OTREASURE, STREASURE ->
                    editor = new TreasureEditor(selectedCardType);
            case BMONSTER, CMONSTER, HMONSTER, CHAMONSTER, GEVENT, BEVENT, CURSE, BOSS, EPIC ->
                    editor = new MonsterEditor(selectedCardType);
            case CARDS, TRINKETS, PILLS, RUNES, BOMBS, BUTTER, BATTERIES, KEYS, DICE, SHEART, BHEART, SACK, LSOUL, WILDCARD ->
                    editor = new LootEditor(selectedCardType);
            case MONEY1C, MONEY2C, MONEY3C, MONEY4C, MONEY5C, MONEY10C ->
                    editor = new MoneyEditor(selectedCardType);
            case BSOUL ->
                   editor = new BonusSoulEditor();
            case ROOM ->
                    editor = new RoomEditor(selectedCardType);
            case OUTSIDE ->
                    editor = new OutsideEditor();
        }
        workspace.set(editor);
        assetIdListView.getSelectionModel().selectFirst();
    }

    public Node getContent() {
        return root;
    }
}
