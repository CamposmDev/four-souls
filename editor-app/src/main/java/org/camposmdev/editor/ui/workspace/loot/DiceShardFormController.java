package org.camposmdev.editor.ui.workspace.loot;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import org.camposmdev.model.card.attribute.CardSet;
import org.camposmdev.model.card.loot.DiceShardCard;
import org.camposmdev.util.FormController;

public class DiceShardFormController extends FormController<DiceShardCard> {
    @FXML
    ComboBox<CardSet> cbCardSet;

    @Override
    public void init() {
        cbCardSet.setValue(CardSet.UNDEFINED);
        cbCardSet.getItems().addAll(CardSet.values());
    }

    @Override
    public DiceShardCard submit() throws Exception {
        return (DiceShardCard) new DiceShardCard()
                .setCardSet(cbCardSet.getValue());
    }
}
