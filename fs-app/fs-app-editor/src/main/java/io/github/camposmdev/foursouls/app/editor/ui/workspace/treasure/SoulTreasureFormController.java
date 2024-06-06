package io.github.camposmdev.foursouls.app.editor.ui.workspace.treasure;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import io.github.camposmdev.foursouls.core.card.attribute.CardSet;
import io.github.camposmdev.foursouls.core.card.attribute.treasure.SoulItem;
import io.github.camposmdev.foursouls.core.card.treasure.SoulTreasureCard;
import io.github.camposmdev.foursouls.core.ui.FormController;

public class SoulTreasureFormController extends FormController<SoulTreasureCard> {
    @FXML
    ComboBox<CardSet> cbCardSet;
    @FXML
    CheckBox cbIsGuppy;
    @FXML
    ComboBox<SoulItem> cbSoulItem;

    @Override
    public void init() {
        cbCardSet.setValue(CardSet.UNDEFINED);
        cbCardSet.getItems().addAll(CardSet.values());
        cbSoulItem.getItems().addAll(SoulItem.values());
    }

    @Override
    public SoulTreasureCard submit() throws Exception {
        return (SoulTreasureCard) new SoulTreasureCard()
                .setItem(cbSoulItem.getValue())
                .setGuppy(cbIsGuppy.isSelected())
                .setCardSet(cbCardSet.getValue());
    }
}
