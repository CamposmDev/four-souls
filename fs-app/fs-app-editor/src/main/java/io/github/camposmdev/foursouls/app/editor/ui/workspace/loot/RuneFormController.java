package io.github.camposmdev.foursouls.app.editor.ui.workspace.loot;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import io.github.camposmdev.foursouls.model.card.attribute.CardSet;
import io.github.camposmdev.foursouls.model.card.attribute.loot.RuneType;
import io.github.camposmdev.foursouls.model.card.loot.RuneCard;
import io.github.camposmdev.foursouls.model.ui.FormController;

public class RuneFormController extends FormController<RuneCard> {
    @FXML
    private ComboBox<CardSet> cbCardSet;
    @FXML
    private ComboBox<RuneType> runeType;

    @Override
    public void init() {
        cbCardSet.setValue(CardSet.UNDEFINED);
        cbCardSet.getItems().addAll(CardSet.values());
        runeType.setValue(RuneType.UNDEFINED);
        runeType.getItems().addAll(RuneType.values());
    }


    @Override
    public RuneCard submit() throws Exception {
        // Retrieve values from UI components
        RuneCard card = new RuneCard();
        card.setRuneType(runeType.getValue()).setCardSet(cbCardSet.getValue());
        return card;
    }
}
