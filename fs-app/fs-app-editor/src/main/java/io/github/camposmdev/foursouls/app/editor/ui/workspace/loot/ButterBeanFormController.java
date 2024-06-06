package io.github.camposmdev.foursouls.app.editor.ui.workspace.loot;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import io.github.camposmdev.foursouls.core.card.attribute.CardSet;
import io.github.camposmdev.foursouls.core.card.loot.ButterBeanCard;
import io.github.camposmdev.foursouls.core.ui.FormController;

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
