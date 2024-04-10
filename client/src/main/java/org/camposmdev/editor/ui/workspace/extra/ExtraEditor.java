package org.camposmdev.editor.ui.workspace.extra;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.camposmdev.editor.ui.factory.DialogFactory;
import org.camposmdev.editor.ui.workspace.BaseEditor;
import org.camposmdev.model.card.attribute.CardSet;
import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.extra.ExtraCard;

public class ExtraEditor extends BaseEditor {
    private final GridPane root;
    private final ComboBox<CardSet> cbCardSet;
    private final CheckBox cbForceAttack, cbTheHarbingers, cbIndomitable, cbTheBeast;
    private final CardType cardType;

    public ExtraEditor(CardType type) {
        cardType = type;
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
        root.addColumn(0, new Label("Card Set"), new Label("Force Attack"), new Label("The Harbingers"), new Label("Indomitable"), new Label("The Beast"));
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
            var card = new ExtraCard()
                    .setForceAttack(cbForceAttack.isSelected())
                    .setTheHarbingers(cbTheHarbingers.isSelected())
                    .setIndomitable(cbIndomitable.isSelected())
                    .setTheBeast(cbTheBeast.isSelected());
            card.setId(super.id())
                    .setImage(super.image())
                    .setCardType(cardType)
                    .setCardSet(cbCardSet.getValue());
            /* TODO - add card to extra deck */
        } catch (Exception ex) {
            DialogFactory.instance().showErrorBox(ex);
        }
    }
}
