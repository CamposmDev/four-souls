package org.camposmdev.editor.ui.workspace.bsoul;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.camposmdev.model.card.attribute.CardSet;
import org.camposmdev.model.card.bsoul.BonusSoulCard;
import org.camposmdev.model.card.attribute.CounterType;
import org.camposmdev.util.FXUtil;
import org.camposmdev.util.FormController;

public class BonusSoulFormController extends FormController<BonusSoulCard> {
    @FXML ComboBox<CardSet> cbCardSet;
    @FXML TextField tfLoot, tfMoney, tfGuppyItems;
    @FXML CheckBox cbHasCounter;
    @FXML TextField tfCounterLimit;
    @FXML ComboBox<CounterType> cbCounterType;
    @FXML CheckBox cbIsEnvy, cbIsSloth, cbIsStrawberry;

    @Override
    public void init() {
        FXUtil.initNumberFields(tfLoot, tfMoney, tfGuppyItems, tfCounterLimit);
        cbCardSet.setValue(CardSet.UNDEFINED);
        cbCardSet.getItems().addAll(CardSet.values());
        cbCounterType.setValue(CounterType.UNDEFINED);
        cbCounterType.getItems().addAll(CounterType.values());
    }

    @Override
    public BonusSoulCard submit() throws Exception {
        byte loot = Byte.parseByte(tfLoot.getText());
        byte money = Byte.parseByte(tfMoney.getText());
        byte guppyItems = Byte.parseByte(tfGuppyItems.getText());
        boolean hasCounter = cbHasCounter.isSelected();
        byte counterLimit = Byte.parseByte(tfCounterLimit.getText());
        CounterType counterType = CounterType.valueOf(String.valueOf(cbCounterType.getValue()));
        boolean isEnvy = cbIsEnvy.isSelected();
        boolean isSloth = cbIsSloth.isSelected();
        boolean isStrawberry = cbIsStrawberry.isSelected();
        return (BonusSoulCard) new BonusSoulCard().setLoot(loot).setMoney(money)
                .setGuppyItems(guppyItems).setCounter(hasCounter)
                .setCounterLimit(counterLimit).setCounterType(counterType)
                .setEnvy(isEnvy).setSloth(isSloth).setStrawberry(isStrawberry)
                .setCardSet(cbCardSet.getValue());
    }

    private void f(ComboBox<Boolean> cb) {
        cb.setValue(false);
        cb.getItems().setAll(true, false);
    }
}
