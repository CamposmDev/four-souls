package org.camposmdev.editor.ui.workspace.outside;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.camposmdev.editor.model.Model;
import org.camposmdev.editor.ui.NotificationBar;
import org.camposmdev.editor.ui.factory.DialogFactory;
import org.camposmdev.editor.ui.workspace.BaseEditor;
import org.camposmdev.model.card.attribute.CardSet;
import org.camposmdev.model.card.extra.OutsideCard;

public class OutsideEditor extends BaseEditor {
    private final GridPane root;
    private final ComboBox<CardSet> cbCardSet;
    private final CheckBox cbForceAttack, cbTheHarbingers, cbIndomitable, cbTheBeast;

    public OutsideEditor() {
        cbCardSet = new ComboBox<>();
        cbCardSet.setValue(CardSet.UNDEFINED);
        cbCardSet.getItems().addAll(CardSet.values());
        cbForceAttack = new CheckBox();
        cbTheHarbingers = new CheckBox();
        cbIndomitable = new CheckBox();
        cbTheBeast = new CheckBox();
        var btCommit = new Button("Commit");
        btCommit.setOnAction(e -> commit());
        root = new GridPane(4, 4);
        root.addColumn(0, new Label("card Set"), new Label("forceAttack"), new Label("theHarbingers"), new Label("indomitable"), new Label("theBeast"));
        root.addColumn(1, cbCardSet, cbForceAttack, cbTheHarbingers, cbIndomitable, cbTheBeast);
        root.addRow(5, btCommit);
    }
    @Override
    public Node getContent() {
        return root;
    }

    @Override
    public void commit() {
        try {
            var card = new OutsideCard()
                    .setForceAttack(cbForceAttack.isSelected())
                    .setTheHarbingers(cbTheHarbingers.isSelected())
                    .setIndomitable(cbIndomitable.isSelected())
                    .setTheBeast(cbTheBeast.isSelected());
            card.setId(super.id())
                    .setImage(super.image())
                    .setCardSet(cbCardSet.getValue());
            Model.instance().addCard(card);
            NotificationBar.instance().push(card);
        } catch (Exception ex) {
            DialogFactory.instance().showErrorBox(ex);
        }
    }
}
