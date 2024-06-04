package io.github.camposmdev.foursouls.app.editor.ui.workspace.loot;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import io.github.camposmdev.foursouls.model.card.attribute.CardSet;
import io.github.camposmdev.foursouls.model.card.loot.DiceShardCard;
import io.github.camposmdev.foursouls.model.util.fx.FormController;

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
