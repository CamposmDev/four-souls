package io.github.camposmdev.foursouls.app.editor.ui.workspace.loot;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import io.github.camposmdev.foursouls.core.card.attribute.CardSet;
import io.github.camposmdev.foursouls.core.card.loot.BlackHeartCard;
import io.github.camposmdev.foursouls.core.ui.FormController;

public class BlackHeartFormController extends FormController<BlackHeartCard> {
    @FXML
    ComboBox<CardSet> cbCardSet;

    @Override
    public void init() {
        cbCardSet.setValue(CardSet.UNDEFINED);
        cbCardSet.getItems().addAll(CardSet.values());
    }

    @Override
    public BlackHeartCard submit() throws Exception {
        return (BlackHeartCard) new BlackHeartCard()
                .setCardSet(cbCardSet.getValue());
    }
}
