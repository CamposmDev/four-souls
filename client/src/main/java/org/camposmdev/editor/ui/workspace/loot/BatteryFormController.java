package org.camposmdev.editor.ui.workspace.loot;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import org.camposmdev.model.card.attribute.CardSet;
import org.camposmdev.model.card.attribute.CardVersion;
import org.camposmdev.model.card.loot.BatteryCard;
import org.camposmdev.util.FormController;

public class BatteryFormController extends FormController<BatteryCard> {
    @FXML
    ComboBox<CardSet> cbCardSet;
    @FXML
    ComboBox<CardVersion> cbVersion;

    @Override
    public void init() {
        cbCardSet.setValue(CardSet.UNDEFINED);
        cbCardSet.getItems().addAll(CardSet.values());
        cbVersion.setValue(CardVersion.UNDEFINED);
        cbVersion.getItems().addAll(CardVersion.values());
    }

    @Override
    public BatteryCard submit() throws Exception {
        return (BatteryCard) new BatteryCard()
                .setVersion(cbVersion.getValue())
                .setCardSet(cbCardSet.getValue());
    }
}
