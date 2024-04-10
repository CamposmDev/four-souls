package org.camposmdev.editor.ui.workspace.loot;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.camposmdev.model.card.attribute.CardSet;
import org.camposmdev.model.card.attribute.CardVersion;
import org.camposmdev.model.card.loot.SackCard;
import org.camposmdev.util.FormController;

public class SackFormController extends FormController<SackCard> {
    @FXML
    ComboBox<CardSet> cbCardSet;
    @FXML
    TextField tfLoot;

    @Override
    public void init() {
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
