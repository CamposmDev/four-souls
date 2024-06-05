package io.github.camposmdev.foursouls.app.editor.ui.workspace.treasure;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import io.github.camposmdev.foursouls.model.card.attribute.CardSet;
import io.github.camposmdev.foursouls.model.card.attribute.treasure.PassiveItem;
import io.github.camposmdev.foursouls.model.card.treasure.PassiveTreasureCard;
import io.github.camposmdev.foursouls.model.ui.FormController;

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
