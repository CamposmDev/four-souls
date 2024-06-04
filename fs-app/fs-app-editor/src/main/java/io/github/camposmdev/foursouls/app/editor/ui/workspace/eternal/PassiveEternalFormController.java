package io.github.camposmdev.foursouls.app.editor.ui.workspace.eternal;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import io.github.camposmdev.foursouls.model.card.attribute.eternal.PassiveItem;
import io.github.camposmdev.foursouls.model.card.eternal.PassiveEternalCard;
import io.github.camposmdev.foursouls.model.card.attribute.*;
import io.github.camposmdev.foursouls.model.fx.FormController;

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
