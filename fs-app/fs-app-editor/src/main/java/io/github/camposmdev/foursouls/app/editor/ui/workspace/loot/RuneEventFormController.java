package io.github.camposmdev.foursouls.app.editor.ui.workspace.loot;

import io.github.camposmdev.foursouls.app.editor.ui.RewardBox;
import io.github.camposmdev.foursouls.app.editor.ui.factory.DialogFactory;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import io.github.camposmdev.foursouls.core.card.attribute.EntityTarget;
import io.github.camposmdev.foursouls.core.card.attribute.loot.RuneEvent;
import io.github.camposmdev.foursouls.core.ui.FXUtil;
import io.github.camposmdev.foursouls.core.ui.FormController;

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
    @FXML GridPane root;
    private RewardBox rewardBox;

    @Override
    public void init() {
        rewardBox = new RewardBox();
        root.add(rewardBox.getContent(), 1, 3);
        FXUtil.initNumberFields(tfDamage, tfDiscardHandThenLoot);
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

    public RuneEvent build() throws Exception {
        String[] tokens = tfValues.getText().split(",");
        byte[] values = new byte[tokens.length];
        for (int i = 0; i < tokens.length; i++)
            values[i] = Byte.parseByte(tokens[i]);
        return new RuneEvent()
                .setValues(values)
                .setDamage(Byte.parseByte(tfDamage.getText()))
                .setDamageTo(cbDamageTo.getValue())
                .setReward(rewardBox.submit())
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
