package org.camposmdev.editor.ui.workspace.treasure;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import org.camposmdev.model.card.attribute.CardSet;
import org.camposmdev.model.card.attribute.treasure.PaidItem;
import org.camposmdev.model.card.treasure.PaidTreasureCard;
import org.camposmdev.util.FormController;

public class PaidTreasureFormController extends FormController<PaidTreasureCard> {
    @FXML
    ComboBox<CardSet> cbCardSet;
    @FXML
    CheckBox cbIsGuppy;
    @FXML
    ComboBox<PaidItem> cbPaidItem;

    @Override
    public void init() {
        cbCardSet.setValue(CardSet.UNDEFINED);
        cbCardSet.getItems().addAll(CardSet.values());
        cbPaidItem.getItems().addAll(PaidItem.values());
    }

    @Override
    public PaidTreasureCard submit() throws Exception {
        return (PaidTreasureCard) new PaidTreasureCard()
                .setItem(cbPaidItem.getValue())
                .setGuppy(cbIsGuppy.isSelected())
                .setCardSet(cbCardSet.getValue());
    }
}
