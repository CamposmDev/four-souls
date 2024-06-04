package io.github.camposmdev.foursouls.app.editor.ui.workspace.loot;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import io.github.camposmdev.foursouls.model.card.attribute.CardSet;
import io.github.camposmdev.foursouls.model.card.attribute.loot.BatteryType;
import io.github.camposmdev.foursouls.model.card.loot.BatteryCard;
import io.github.camposmdev.foursouls.model.util.fx.FormController;

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
