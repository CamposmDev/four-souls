package org.camposmdev.editor.ui.workspace.loot;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import org.camposmdev.model.card.attribute.CardSet;
import org.camposmdev.model.card.attribute.loot.CardKind;
import org.camposmdev.model.card.loot.TarotCard;
import org.camposmdev.util.FormController;

public class TarotFormController extends FormController<TarotCard> {
    @FXML private ComboBox<CardSet> cardSet;
    @FXML private ComboBox<CardKind> kind;

    @Override
    public void init() {
        cardSet.setValue(CardSet.UNDEFINED);
        cardSet.getItems().addAll(CardSet.values());
        kind.setValue(CardKind.O_THE_FOOL);
        kind.getItems().addAll(CardKind.values());
    }

    @Override
    public TarotCard submit() throws Exception {
        TarotCard card = new TarotCard();
        card.setKind(kind.getValue()).setCardSet(cardSet.getValue());
        return card;
    }
}
