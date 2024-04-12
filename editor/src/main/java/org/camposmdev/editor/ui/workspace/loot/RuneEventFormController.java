package org.camposmdev.editor.ui.workspace.loot;

import org.camposmdev.editor.ui.factory.DialogFactory;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import org.camposmdev.model.card.attribute.EntityTarget;
import org.camposmdev.model.card.attribute.Reward;
import org.camposmdev.model.card.attribute.loot.RuneEvent;
import org.camposmdev.util.FXUtil;
import org.camposmdev.util.FormController;

public class RuneEventFormController extends FormController<RuneEvent[]> {
    @FXML
    private ListView<RuneEvent> lv;
    @FXML
    private TextField tfValues;
    @FXML
    private TextField tfDamage;
    @FXML
    private ComboBox<EntityTarget> cbDamageTo;
    @FXML
    private ComboBox<EntityTarget> cbRewardTo;
    @FXML
    private CheckBox cbDestroyItemInPlayAndReplace;
    @FXML
    private CheckBox cbRerollAnyItem;
    @FXML
    private TextField tfDiscardHandThenLoot;
    private Reward reward;

    @Override
    public void init() {
        FXUtil.initNumberFields(tfValues, tfDamage, tfDiscardHandThenLoot);
        cbDamageTo.setValue(EntityTarget.UNDEFINED);
        cbDamageTo.getItems().addAll(EntityTarget.values());
        cbRewardTo.setValue(EntityTarget.UNDEFINED);
        cbRewardTo.getItems().addAll(EntityTarget.values());
        lv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        lv.setTooltip(new Tooltip("BACKSPACE or DEL to delete item"));
        lv.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.DELETE || e.getCode() == KeyCode.BACK_SPACE
                    && lv.getSelectionModel().getSelectedItem() != null) {
                lv.getItems().remove(lv.getSelectionModel().getSelectedItem());
            }
        });
    }

    public void load(RuneEvent... runeEvents) {
        lv.getItems().clear();
        lv.getItems().addAll(runeEvents);
    }

    public void modReward() {
        DialogFactory.instance().showRewardModifierBox(reward).ifPresent(x -> reward = x);
    }

    public RuneEvent build() throws Exception {
        String[] tokens = tfValues.getText().split(",");
        byte[] values = new byte[tokens.length];
        for (int i = 0; i < tokens.length; i++)
            values[i] = Byte.parseByte(tokens[i]);
        return new RuneEvent()
                .setValues(values)
                .setDamage(Byte.parseByte(tfDamage.getText()))
                .setDamageTo(cbDamageTo.getValue())
                .setReward(reward)
                .setRewardTo(cbRewardTo.getValue())
                .setDestroyItemInPlaceAndReplace(cbDestroyItemInPlayAndReplace.isSelected())
                .setRerollAnyItem(cbRerollAnyItem.isSelected())
                .setDiscardHandThenLoot(Byte.parseByte(tfDiscardHandThenLoot.getText()));
    }

    public void commit() {
        try {
            var runeEvent = build();
            lv.getItems().add(runeEvent);
        } catch (Exception ex) {
            DialogFactory.instance().showErrorBox(ex);
        }
    }

    @Override
    public RuneEvent[] submit() throws Exception {
        return lv.getItems().stream().toList().toArray(new RuneEvent[]{});
    }
}
