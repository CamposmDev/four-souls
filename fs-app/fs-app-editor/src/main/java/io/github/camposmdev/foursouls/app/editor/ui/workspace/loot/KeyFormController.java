package io.github.camposmdev.foursouls.app.editor.ui.workspace.loot;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import io.github.camposmdev.foursouls.model.card.attribute.CardSet;
import io.github.camposmdev.foursouls.model.card.attribute.loot.CardKind;
import io.github.camposmdev.foursouls.model.card.loot.KeyCard;
import io.github.camposmdev.foursouls.model.fx.FormController;

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
