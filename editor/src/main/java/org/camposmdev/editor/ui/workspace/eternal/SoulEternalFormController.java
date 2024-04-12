package org.camposmdev.editor.ui.workspace.eternal;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import org.camposmdev.model.card.attribute.CardSet;
import org.camposmdev.model.card.attribute.CardVersion;
import org.camposmdev.model.card.eternal.SoulEternalCard;
import org.camposmdev.util.FormController;

public class SoulEternalFormController extends FormController<SoulEternalCard> {
    @FXML ComboBox<CardSet> cbCardSet;

    @Override
    public void init() {
        cbCardSet.setValue(CardSet.UNDEFINED);
        cbCardSet.getItems().addAll(CardSet.values());
    }

    @Override
    public SoulEternalCard submit() throws Exception {
        return (SoulEternalCard) new SoulEternalCard()
                .setCardSet(cbCardSet.getValue());
    }
}
