package io.github.camposmdev.foursouls.app.editor.ui.workspace.eternal;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import io.github.camposmdev.foursouls.core.card.attribute.*;
import io.github.camposmdev.foursouls.core.card.attribute.eternal.ActiveItem;
import io.github.camposmdev.foursouls.core.card.eternal.ActiveEternalCard;
import io.github.camposmdev.foursouls.core.ui.FormController;

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
