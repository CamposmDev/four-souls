package org.camposmdev.editor.ui.workspace.treasure;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import org.camposmdev.model.card.attribute.CardSet;
import org.camposmdev.model.card.attribute.treasure.OneTimeUseItem;
import org.camposmdev.model.card.attribute.treasure.PassiveItem;
import org.camposmdev.model.card.treasure.OneTimeUseTreasureCard;
import org.camposmdev.model.card.treasure.PassiveTreasureCard;
import org.camposmdev.util.FormController;

public class OneTimeUseTreasureFormController extends FormController<OneTimeUseTreasureCard> {
    @FXML
    ComboBox<CardSet> cbCardSet;
    @FXML
    CheckBox cbIsGuppy;
    @FXML
    ComboBox<OneTimeUseItem> cbOneTimeUseItem;

    @Override
    public void init() {
        cbCardSet.setValue(CardSet.UNDEFINED);
        cbCardSet.getItems().addAll(CardSet.values());
        cbOneTimeUseItem.getItems().addAll(OneTimeUseItem.values());
    }

    @Override
    public OneTimeUseTreasureCard submit() throws Exception {
        return (OneTimeUseTreasureCard) new OneTimeUseTreasureCard()
                .setItem(cbOneTimeUseItem.getValue())
                .setGuppy(cbIsGuppy.isSelected())
                .setCardSet(cbCardSet.getValue());
    }
}
