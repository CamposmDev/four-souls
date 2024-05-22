package org.camposmdev.editor.ui.workspace.loot;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import org.camposmdev.model.card.attribute.CardSet;
import org.camposmdev.model.card.loot.ButterBeanCard;
import org.camposmdev.util.FormController;

public class ButterBeanFormController extends FormController<ButterBeanCard> {
    @FXML
    private ComboBox<CardSet> cbCardSet;
    @FXML
    private CheckBox cbCancelActiveItem;
    @FXML
    private CheckBox cbCancelLoot;
    @FXML
    private CheckBox cbCancelPaidItem;

    @Override
    public void init() {
        cbCardSet.setValue(CardSet.UNDEFINED);
        cbCardSet.getItems().addAll(CardSet.values());
    }

    @Override
    public ButterBeanCard submit() throws Exception {
        return (ButterBeanCard) new ButterBeanCard()
                .setCancelActiveItemEffect(cbCancelActiveItem.isSelected())
                .setCancelLootEffect(cbCancelLoot.isSelected())
                .setCancelPaidItemEffect(cbCancelPaidItem.isSelected())
                .setCardSet(cbCardSet.getValue());

    }
}
