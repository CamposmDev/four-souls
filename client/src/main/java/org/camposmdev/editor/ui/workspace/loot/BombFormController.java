package org.camposmdev.editor.ui.workspace.loot;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.camposmdev.model.card.attribute.CardSet;
import org.camposmdev.model.card.attribute.CardVersion;
import org.camposmdev.model.card.attribute.EntityTarget;
import org.camposmdev.model.card.loot.BombCard;
import org.camposmdev.util.FormController;

public class BombFormController extends FormController<BombCard> {
    @FXML
    ComboBox<CardSet> cbCardSet;
    @FXML
    TextField tfDamage;
    @FXML
    ComboBox<EntityTarget> cbDamageTo;

    @Override
    public void init() {
        cbCardSet.setValue(CardSet.UNDEFINED);
        cbCardSet.getItems().addAll(CardSet.values());
        cbDamageTo.setValue(EntityTarget.UNDEFINED);
        cbDamageTo.getItems().addAll(EntityTarget.values());
    }

    @Override
    public BombCard submit() throws Exception {
        return (BombCard) new BombCard()
                .setDamage(Byte.parseByte(tfDamage.getText()))
                .setDamageTo(cbDamageTo.getValue())
                .setCardSet(cbCardSet.getValue());
    }
}
