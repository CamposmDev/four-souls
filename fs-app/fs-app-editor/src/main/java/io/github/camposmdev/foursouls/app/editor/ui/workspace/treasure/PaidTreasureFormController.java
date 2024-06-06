package io.github.camposmdev.foursouls.app.editor.ui.workspace.treasure;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import io.github.camposmdev.foursouls.core.card.attribute.CardSet;
import io.github.camposmdev.foursouls.core.card.attribute.treasure.PaidItem;
import io.github.camposmdev.foursouls.core.card.treasure.PaidTreasureCard;
import io.github.camposmdev.foursouls.core.ui.FormController;

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
