package org.camposmdev.editor.ui.workspace.outside;

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
import org.camposmdev.model.card.attribute.outside.OutsideType.OutsideType;
import org.camposmdev.model.card.extra.OutsideCard;

public class OutsideEditor extends BaseEditor {
    private final GridPane root;
    private final ComboBox<CardSet> cardSet;
    private final ComboBox<OutsideType> outsideType;

    public OutsideEditor() {
        cardSet = new ComboBox<>();
        cardSet.setValue(CardSet.UNDEFINED);
        cardSet.getItems().addAll(CardSet.values());
        outsideType = new ComboBox<>();
        outsideType.setValue(OutsideType.UNDEFINED);
        outsideType.getItems().addAll(OutsideType.values());
        var btCommit = new Button("Commit");
        btCommit.setOnAction(e -> commit());
        root = new GridPane(4, 4);
        root.addRow(0, new Label("cardSet"), cardSet);
        root.addRow(1, new Label("outsideType"), outsideType);
        root.addRow(2, btCommit);
    }

    @Override
    public Node getContent() {
        return root;
    }

    @Override
    public void commit() {
        try {
            OutsideCard card = (OutsideCard) new OutsideCard()
                    .setOutsideType(outsideType.getValue())
                    .setId(super.id())
                    .setImage(super.image())
                    .setCardSet(cardSet.getValue());
            Model.instance().addCard(card);
            NotificationBar.instance().push(card);
        } catch (Exception ex) {
            DialogFactory.instance().showErrorBox(ex);
        }
    }
}
