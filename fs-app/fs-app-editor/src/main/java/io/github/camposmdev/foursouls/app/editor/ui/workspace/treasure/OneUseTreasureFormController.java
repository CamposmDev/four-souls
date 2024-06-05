package io.github.camposmdev.foursouls.app.editor.ui.workspace.treasure;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import io.github.camposmdev.foursouls.model.card.attribute.CardSet;
import io.github.camposmdev.foursouls.model.card.attribute.treasure.OneUseItem;
import io.github.camposmdev.foursouls.model.card.treasure.OneUseTreasureCard;
import io.github.camposmdev.foursouls.model.ui.FormController;

public class OneUseTreasureFormController extends FormController<OneUseTreasureCard> {
    @FXML
    ComboBox<CardSet> cbCardSet;
    @FXML
    CheckBox cbIsGuppy;
    @FXML
    ComboBox<OneUseItem> cbOneTimeUseItem;

    @Override
    public void init() {
        cbCardSet.setValue(CardSet.UNDEFINED);
        cbCardSet.getItems().addAll(CardSet.values());
        cbOneTimeUseItem.getItems().addAll(OneUseItem.values());
    }

    @Override
    public OneUseTreasureCard submit() throws Exception {
        return (OneUseTreasureCard) new OneUseTreasureCard()
                .setItem(cbOneTimeUseItem.getValue())
                .setGuppy(cbIsGuppy.isSelected())
                .setCardSet(cbCardSet.getValue());
    }
}
