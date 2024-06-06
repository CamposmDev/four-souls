package io.github.camposmdev.foursouls.app.editor.ui.workspace.eternal;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import io.github.camposmdev.foursouls.core.card.attribute.CardSet;
import io.github.camposmdev.foursouls.core.card.attribute.eternal.PaidItem;
import io.github.camposmdev.foursouls.core.card.eternal.PaidEternalCard;
import io.github.camposmdev.foursouls.core.ui.FormController;

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
