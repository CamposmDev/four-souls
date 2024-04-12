package org.camposmdev.editor.ui.workspace.loot;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import org.camposmdev.model.card.attribute.CardSet;
import org.camposmdev.model.card.attribute.loot.BatteryType;
import org.camposmdev.model.card.loot.BatteryCard;
import org.camposmdev.util.FormController;

public class BatteryFormController extends FormController<BatteryCard> {
    @FXML
    ComboBox<CardSet> cbCardSet;
    @FXML
    ComboBox<BatteryType> cbType;

    @Override
    public void init() {
        cbCardSet.setValue(CardSet.UNDEFINED);
        cbCardSet.getItems().addAll(CardSet.values());
        cbType.setValue(BatteryType.LIL_BATTERY);
        cbType.getItems().addAll(BatteryType.values());
    }

    @Override
    public BatteryCard submit() throws Exception {
        return (BatteryCard) new BatteryCard()
                .setType(cbType.getValue())
                .setCardSet(cbCardSet.getValue());
    }
}
