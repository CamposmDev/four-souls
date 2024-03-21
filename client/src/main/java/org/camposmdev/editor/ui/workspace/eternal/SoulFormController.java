package org.camposmdev.editor.ui.workspace.eternal;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.camposmdev.model.card.BonusSoulCard;
import org.camposmdev.model.card.attribute.CounterType;
import org.camposmdev.util.FormController;

public class SoulFormController extends FormController<BonusSoulCard> {
    @FXML
    TextField tfLoot, tfMoney, tfGuppyItems;
    @FXML
    ComboBox<Boolean> cbHasCounter;
    @FXML
    TextField tfCounterLimit;
    @FXML
    ComboBox<CounterType> cbCounterType;
    @FXML
    ComboBox<Boolean> cbIsEnvy, cbIsSloth, cbIsStrawberry;

    @Override
    public BonusSoulCard submit() throws Exception {
        var loot = Byte.parseByte(tfLoot.getText());
        var money = Byte.parseByte(tfMoney.getText());
        var guppyItems = Byte.parseByte(tfGuppyItems.getText());
        var hasCounter = cbHasCounter.getValue();
        var counterLimit = Byte.parseByte(tfCounterLimit.getText());
        var counterType = cbCounterType.getValue();
        var isEnvy = cbIsEnvy.getValue();
        var isSloth = cbIsSloth.getValue();
        var isStrawberry = cbIsStrawberry.getValue();
        return new BonusSoulCard().setLoot(loot).setMoney(money)
                .setGuppyItems(guppyItems).setHasCounter(hasCounter)
                .setCounterLimit(counterLimit).setCounterType(counterType)
                .setEnvy(isEnvy).setSloth(isSloth).setStrawBerry(isStrawberry);
    }
}
