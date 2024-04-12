package org.camposmdev.editor.ui.workspace.treasure;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import org.camposmdev.model.card.attribute.CardSet;
import org.camposmdev.model.card.attribute.treasure.PassiveItem;
import org.camposmdev.model.card.treasure.PassiveTreasureCard;
import org.camposmdev.util.FormController;

public class PassiveTreasureFormController extends FormController<PassiveTreasureCard> {
    @FXML
    ComboBox<CardSet> cbCardSet;
    @FXML
    CheckBox cbIsGuppy;
    @FXML
    ComboBox<PassiveItem> cbPassiveItem;

    @Override
    public void init() {
        cbCardSet.setValue(CardSet.UNDEFINED);
        cbCardSet.getItems().addAll(CardSet.values());
        cbPassiveItem.getItems().addAll(PassiveItem.values());
    }

    @Override
    public PassiveTreasureCard submit() throws Exception {
        return (PassiveTreasureCard) new PassiveTreasureCard()
                .setItem(cbPassiveItem.getValue())
                .setGuppy(cbIsGuppy.isSelected())
                .setCardSet(cbCardSet.getValue());
    }
}
