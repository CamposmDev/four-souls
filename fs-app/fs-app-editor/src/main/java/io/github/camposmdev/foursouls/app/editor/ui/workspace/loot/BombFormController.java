package io.github.camposmdev.foursouls.app.editor.ui.workspace.loot;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import io.github.camposmdev.foursouls.model.card.attribute.CardSet;
import io.github.camposmdev.foursouls.model.card.attribute.EntityTarget;
import io.github.camposmdev.foursouls.model.card.loot.BombCard;
import io.github.camposmdev.foursouls.model.ui.FXUtil;
import io.github.camposmdev.foursouls.model.ui.FormController;

public class BombFormController extends FormController<BombCard> {
    @FXML
    ComboBox<CardSet> cbCardSet;
    @FXML
    TextField tfDamage;
    @FXML
    ComboBox<EntityTarget> cbDamageTo;

    @Override
    public void init() {
        FXUtil.initNumberFields(tfDamage);
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
