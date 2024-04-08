package org.camposmdev.editor.ui.workspace.soul;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.camposmdev.model.card.bonus_soul.BonusSoulCard;
import org.camposmdev.model.card.attribute.CounterType;
import org.camposmdev.util.FormController;

public class BonusSoulFormController extends FormController<BonusSoulCard> {
    @FXML
    TextField tfLoot, tfMoney, tfGuppyItems;
    @FXML
    ComboBox<String> cbHasCounter;
    @FXML
    TextField tfCounterLimit;
    @FXML
    ComboBox<String> cbCounterType;
    @FXML
    ComboBox<String> cbIsEnvy, cbIsSloth, cbIsStrawberry;

    @Override
    public BonusSoulCard submit() throws Exception {
        byte loot = Byte.parseByte(tfLoot.getText());
        byte money = Byte.parseByte(tfMoney.getText());
        byte guppyItems = Byte.parseByte(tfGuppyItems.getText());
        boolean hasCounter = Boolean.parseBoolean(cbHasCounter.getValue());
        byte counterLimit = Byte.parseByte(tfCounterLimit.getText());
        CounterType counterType = CounterType.valueOf(String.valueOf(cbCounterType.getValue()));
        boolean isEnvy = Boolean.parseBoolean(cbIsEnvy.getValue());
        boolean isSloth = Boolean.parseBoolean(cbIsSloth.getValue());
        boolean isStrawberry = Boolean.parseBoolean(cbIsStrawberry.getValue());
        return new BonusSoulCard().setLoot(loot).setMoney(money)
                .setGuppyItems(guppyItems).setHasCounter(hasCounter)
                .setCounterLimit(counterLimit).setCounterType(counterType)
                .setEnvy(isEnvy).setSloth(isSloth).setStrawBerry(isStrawberry);
    }

    private void f(ComboBox<Boolean> cb) {
        cb.setValue(false);
        cb.getItems().setAll(true, false);
    }
}
