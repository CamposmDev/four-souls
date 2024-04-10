package org.camposmdev.editor.ui.workspace.treasure;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import org.camposmdev.model.card.attribute.CardSet;
import org.camposmdev.model.card.attribute.treasure.PassiveItem;
import org.camposmdev.model.card.attribute.treasure.SoulItem;
import org.camposmdev.model.card.treasure.PassiveTreasureCard;
import org.camposmdev.model.card.treasure.SoulTreasureCard;
import org.camposmdev.util.FormController;

public class SoulTreasureFormController extends FormController<SoulTreasureCard> {
    @FXML
    ComboBox<CardSet> cbCardSet;
    @FXML
    CheckBox cbIsGuppy;
    @FXML
    ComboBox<SoulItem> cbSoulItem;

    @Override
    public void init() {
        cbCardSet.setValue(CardSet.UNDEFINED);
        cbCardSet.getItems().addAll(CardSet.values());
        cbSoulItem.getItems().addAll(SoulItem.values());
    }

    @Override
    public SoulTreasureCard submit() throws Exception {
        return (SoulTreasureCard) new SoulTreasureCard()
                .setItem(cbSoulItem.getValue())
                .setGuppy(cbIsGuppy.isSelected())
                .setCardSet(cbCardSet.getValue());
    }
}
