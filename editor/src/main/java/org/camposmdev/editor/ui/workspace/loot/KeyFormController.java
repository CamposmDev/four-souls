package org.camposmdev.editor.ui.workspace.loot;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import org.camposmdev.editor.ui.factory.DialogFactory;
import org.camposmdev.model.card.attribute.CardSet;
import org.camposmdev.model.card.attribute.loot.CardKind;
import org.camposmdev.model.card.attribute.loot.LootOption;
import org.camposmdev.model.card.loot.KeyCard;
import org.camposmdev.util.FormController;

import java.util.LinkedList;

public class KeyFormController extends FormController<KeyCard> {
    @FXML
    ComboBox<CardSet> cardSet;
    @FXML
    ComboBox<CardKind> kind;

    @Override
    public void init() {
        cardSet.setValue(CardSet.UNDEFINED);
        cardSet.getItems().addAll(CardSet.values());
        kind.setValue(CardKind.O_THE_FOOL);
        kind.getItems().addAll(CardKind.values());
    }

    @Override
    public KeyCard submit() throws Exception {
        return (KeyCard) new KeyCard()
                .setKind(kind.getValue())
                .setCardSet(cardSet.getValue());
    }
}
