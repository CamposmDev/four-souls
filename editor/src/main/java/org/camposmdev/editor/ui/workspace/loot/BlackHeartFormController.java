package org.camposmdev.editor.ui.workspace.loot;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import org.camposmdev.model.card.attribute.CardSet;
import org.camposmdev.model.card.attribute.CardVersion;
import org.camposmdev.model.card.loot.BlackHeartCard;
import org.camposmdev.util.FormController;

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