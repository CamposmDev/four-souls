package io.github.camposmdev.foursouls.app.editor.ui.workspace.eternal;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import io.github.camposmdev.foursouls.core.card.attribute.CardSet;
import io.github.camposmdev.foursouls.core.card.eternal.SoulEternalCard;
import io.github.camposmdev.foursouls.core.ui.FormController;

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
