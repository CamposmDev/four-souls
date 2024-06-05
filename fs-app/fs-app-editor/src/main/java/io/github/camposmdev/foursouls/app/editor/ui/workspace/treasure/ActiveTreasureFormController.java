package io.github.camposmdev.foursouls.app.editor.ui.workspace.treasure;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import io.github.camposmdev.foursouls.model.card.attribute.CardSet;
import io.github.camposmdev.foursouls.model.card.attribute.treasure.ActiveItem;
import io.github.camposmdev.foursouls.model.card.treasure.ActiveTreasureCard;
import io.github.camposmdev.foursouls.model.ui.FormController;

public class ActiveTreasureFormController extends FormController<ActiveTreasureCard> {
    @FXML
    ComboBox<CardSet> cbCardSet;
    @FXML
    CheckBox cbIsGuppy;
    @FXML
    ComboBox<ActiveItem> cbActiveItem;

    @Override
    public void init() {
        cbCardSet.setValue(CardSet.UNDEFINED);
        cbCardSet.getItems().addAll(CardSet.values());
        cbActiveItem.getItems().addAll(ActiveItem.values());
    }

    @Override
    public ActiveTreasureCard submit() throws Exception {
        return (ActiveTreasureCard) new ActiveTreasureCard()
                .setItem(cbActiveItem.getValue())
                .setGuppy(cbIsGuppy.isSelected())
                .setCardSet(cbCardSet.getValue());
    }
}
