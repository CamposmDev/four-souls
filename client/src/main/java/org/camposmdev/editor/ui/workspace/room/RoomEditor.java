package org.camposmdev.editor.ui.workspace.room;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.camposmdev.editor.model.Model;
import org.camposmdev.editor.ui.NotificationBar;
import org.camposmdev.editor.ui.factory.DialogFactory;
import org.camposmdev.editor.ui.workspace.BaseEditor;
import org.camposmdev.model.card.attribute.CardSet;
import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.loot.MoneyCard;
import org.camposmdev.model.card.room.RoomCard;
import org.camposmdev.model.card.room.RoomType;

public class RoomEditor extends BaseEditor {
    private final GridPane root;
    private final ComboBox<CardSet> cbCardSet;
    private final ComboBox<RoomType> cbRoomType;
    private final CardType cardType;
    public RoomEditor(CardType type) {
        cardType = type;

        cbCardSet = new ComboBox<>();
        cbCardSet.setPrefWidth(150);
        cbCardSet.getItems().addAll(CardSet.values());
        cbRoomType = new ComboBox<>();
        cbRoomType.setPrefWidth(150);
        cbRoomType.getItems().addAll(RoomType.values());

        var btCommit = new Button("Commit");
        btCommit.setOnAction(e -> commit());

        root = new GridPane(4, 4);
        root.addColumn(0, new Label("Card Set"), new Label("RoomType"));
        root.addColumn(1, cbCardSet, cbRoomType);
        root.addRow(2, btCommit);
    }

    @Override
    public Node getContent() {
        return root;
    }

    @Override
    public void commit() {
        try {
            var card = new RoomCard();
            card.setRoomType(cbRoomType.getValue())
                    .setId(super.id())
                    .setImage(super.image())
                    .setCardType(cardType)
                    .setCardSet(cbCardSet.getValue());
            /* TODO - add card to room deck */
        } catch (Exception ex) {
            DialogFactory.instance().showErrorBox(ex);
        }
    }
}
