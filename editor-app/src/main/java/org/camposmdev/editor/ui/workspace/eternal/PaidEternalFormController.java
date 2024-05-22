package org.camposmdev.editor.ui.workspace.eternal;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import org.camposmdev.model.card.attribute.CardSet;
import org.camposmdev.model.card.attribute.eternal.PaidItem;
import org.camposmdev.model.card.eternal.PaidEternalCard;
import org.camposmdev.util.FormController;

public class PaidEternalFormController extends FormController<PaidEternalCard> {
    @FXML ComboBox<CardSet> cbCardSet;
    @FXML ComboBox<PaidItem> cbItem;

    @Override
    public void init() {
        cbCardSet.setValue(CardSet.UNDEFINED);
        cbCardSet.getItems().addAll(CardSet.values());
        cbItem.setValue(PaidItem.UNDEFINED);
        cbItem.getItems().addAll(PaidItem.values());
    }

    @Override
    public PaidEternalCard submit() throws Exception {
        return (PaidEternalCard) new PaidEternalCard()
                .setItem(cbItem.getValue())
                .setCardSet(cbCardSet.getValue());
    }
}
