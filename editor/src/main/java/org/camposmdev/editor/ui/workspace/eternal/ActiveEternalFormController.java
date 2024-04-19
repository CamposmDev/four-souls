package org.camposmdev.editor.ui.workspace.eternal;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.camposmdev.model.card.attribute.*;
import org.camposmdev.model.card.attribute.eternal.ActiveItem;
import org.camposmdev.model.card.eternal.ActiveEternalCard;
import org.camposmdev.util.FXUtil;
import org.camposmdev.util.FormController;

public class ActiveEternalFormController extends FormController<ActiveEternalCard> {
    @FXML ComboBox<CardSet> cardSet;
    @FXML ComboBox<ActiveItem> item;

    @Override
    public void init() {
        cardSet.setValue(CardSet.UNDEFINED);
        cardSet.getItems().addAll(CardSet.values());
        item.setValue(ActiveItem.UNDEFINED);
        item.getItems().addAll(ActiveItem.values());
    }

    @Override
    public ActiveEternalCard submit() throws Exception {
        return (ActiveEternalCard) new ActiveEternalCard()
                .setItem(item.getValue())
                .setCardSet(cardSet.getValue());
    }
}
