package io.github.camposmdev.foursouls.app.editor.ui.workspace.loot;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import io.github.camposmdev.foursouls.model.card.attribute.CardSet;
import io.github.camposmdev.foursouls.model.card.loot.SackCard;
import io.github.camposmdev.foursouls.model.ui.FXUtil;
import io.github.camposmdev.foursouls.model.ui.FormController;

public class SackFormController extends FormController<SackCard> {
    @FXML
    ComboBox<CardSet> cbCardSet;
    @FXML
    TextField tfLoot;

    @Override
    public void init() {
        FXUtil.initNumberFields(tfLoot);
        cbCardSet.setValue(CardSet.UNDEFINED);
        cbCardSet.getItems().addAll(CardSet.values());
    }

    @Override
    public SackCard submit() throws Exception {
        return (SackCard) new SackCard()
                .setLoot(Byte.parseByte(tfLoot.getText()))
                .setCardSet(cbCardSet.getValue());
    }
}
