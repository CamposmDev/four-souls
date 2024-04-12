package org.camposmdev.editor.ui.workspace.eternal;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.camposmdev.model.card.attribute.eternal.PaidItem;
import org.camposmdev.model.card.attribute.eternal.PassiveItem;
import org.camposmdev.model.card.eternal.PassiveEternalCard;
import org.camposmdev.model.card.attribute.*;
import org.camposmdev.model.card.attribute.Reward;
import org.camposmdev.util.FormController;

public class PassiveEternalFormController extends FormController<PassiveEternalCard> {
    @FXML ComboBox<CardSet> cbCardSet;
    @FXML ComboBox<PassiveItem> cbItem;

    @Override
    public void init() {
        cbCardSet.setValue(CardSet.UNDEFINED);
        cbCardSet.getItems().addAll(CardSet.values());
        cbItem.setValue(PassiveItem.UNDEFINED);
        cbItem.getItems().addAll(PassiveItem.values());
    }

    @Override
    public PassiveEternalCard submit() throws NumberFormatException {
        return (PassiveEternalCard) new PassiveEternalCard()
                .setItem(cbItem.getValue())
                .setCardSet(cbCardSet.getValue());
    }
}
