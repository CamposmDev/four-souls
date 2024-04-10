package org.camposmdev.editor.ui.workspace.loot;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import org.camposmdev.model.card.attribute.CardSet;
import org.camposmdev.model.card.attribute.loot.TrinketType;
import org.camposmdev.model.card.loot.TrinketCard;
import org.camposmdev.util.FormController;

public class TrinketFormController extends FormController<TrinketCard> {
    @FXML
    ComboBox<CardSet> cbCardSet;
    @FXML
    ComboBox<TrinketType> cbTrinket;

    @Override
    public void init() {
        cbCardSet.setValue(CardSet.UNDEFINED);
        cbCardSet.getItems().addAll(CardSet.values());
        cbTrinket.getItems().addAll(TrinketType.values());
    }

    @Override
    public TrinketCard submit() throws Exception {
        return (TrinketCard) new TrinketCard()
                .setType(cbTrinket.getValue())
                .setCardSet(cbCardSet.getValue());
    }
}
