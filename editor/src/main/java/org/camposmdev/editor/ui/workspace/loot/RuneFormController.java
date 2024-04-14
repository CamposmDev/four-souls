package org.camposmdev.editor.ui.workspace.loot;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import org.camposmdev.model.card.attribute.CardSet;
import org.camposmdev.model.card.attribute.loot.RuneType;
import org.camposmdev.model.card.loot.RuneCard;
import org.camposmdev.util.FormController;

import java.util.LinkedList;
import java.util.List;

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
