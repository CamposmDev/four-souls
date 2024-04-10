package org.camposmdev.editor.ui.workspace.loot;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import org.camposmdev.editor.ui.factory.DialogFactory;
import org.camposmdev.model.card.attribute.EntityTarget;
import org.camposmdev.model.card.attribute.Reward;
import org.camposmdev.model.card.attribute.loot.PillEvent;
import org.camposmdev.util.FormController;

public class PillEventFormController extends FormController<PillEvent[]> {
    @FXML
    private TextField tfValues;
    @FXML
    private ComboBox<EntityTarget> cbRewardTo;
    @FXML
    private TextField tfDiscardCents;
    @FXML
    private TextField tfDiscardLoot;
    @FXML
    private TextField tfModPlayerDamage;
    @FXML
    private TextField tfModPlayerHitPoint;
    @FXML
    private TextField tfDamage;
    @FXML
    private ComboBox<EntityTarget> cbDamageTo;
    @FXML
    private CheckBox cbRechargeAllItems;
    @FXML
    private TextField tfModPlayerDiceRoll;
    @FXML
    private TextField tfModMonsterAttackRoll;
    @FXML
    private CheckBox cbCancelLootEffect;
    @FXML
    private TextField tfAllDiscardLoot;
    @FXML
    private CheckBox cbRerollPlayerItem;
    @FXML
    private CheckBox cbRerollItemInPlay;
    @FXML
    private CheckBox cbRerollAllItems;
    @FXML
    private TextField tfOtherPlayersDiscardLoot;
    @FXML
    private CheckBox cbRerollAnyItem;
    @FXML
    private TextField tfPutLoot;
    @FXML
    private ListView<PillEvent> lv;

    private Reward reward;

    @Override
    public void init() {
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

    public void modReward() {
        DialogFactory.instance().showRewardModifierBox(reward).ifPresent(x -> reward = x);
    }

    public void load(PillEvent... pillEvents) {
        lv.getItems().clear();
        lv.getItems().addAll(pillEvents);
    }

    public void commit() {
        try {
            var pillEvent = build();
            lv.getItems().add(pillEvent);
        } catch (Exception e) {
            DialogFactory.instance().showErrorBox(e);
        }
    }

    public PillEvent build() throws Exception {
        // Retrieve data from UI controls
        String[] tokens = tfValues.getText().split(",");
        byte[] values = new byte[tokens.length];
        for (int i = 0; i < tokens.length; i++)
            values[i] = Byte.parseByte(tokens[i]);
        EntityTarget rewardTo = cbRewardTo.getValue();
        byte discardCents = Byte.parseByte(tfDiscardCents.getText());
        byte discardLoot = Byte.parseByte(tfDiscardLoot.getText());
        byte modPlayerDamage = Byte.parseByte(tfModPlayerDamage.getText());
        byte modPlayerHitPoint = Byte.parseByte(tfModPlayerHitPoint.getText());
        byte damage = Byte.parseByte(tfDamage.getText());
        EntityTarget damageTo = cbDamageTo.getValue();
        boolean rechargeAllItems = cbRechargeAllItems.isSelected();
        byte modPlayerDiceRoll = Byte.parseByte(tfModPlayerDiceRoll.getText());
        byte modMonsterAttackRoll = Byte.parseByte(tfModMonsterAttackRoll.getText());
        boolean cancelLootEffect = cbCancelLootEffect.isSelected();
        byte allDiscardLoot = Byte.parseByte(tfAllDiscardLoot.getText());
        boolean rerollPlayerItem = cbRerollPlayerItem.isSelected();
        boolean rerollItemInPlay = cbRerollItemInPlay.isSelected();
        boolean rerollAllItems = cbRerollAllItems.isSelected();
        byte otherPlayersDiscardLoot = Byte.parseByte(tfOtherPlayersDiscardLoot.getText());
        boolean rerollAnyItem = cbRerollAnyItem.isSelected();
        byte putLoot = Byte.parseByte(tfPutLoot.getText());

        // Create a new PillEvent object and set its properties
        return new PillEvent()
                .setValues(values)
                .setRewardTo(rewardTo)
                .setDiscardCents(discardCents)
                .setDiscardLoot(discardLoot)
                .setModPlayerDamage(modPlayerDamage)
                .setModPlayerHitPoint(modPlayerHitPoint)
                .setDamage(damage)
                .setDamageTo(damageTo)
                .setRechargeAllItems(rechargeAllItems)
                .setModPlayerDiceRoll(modPlayerDiceRoll)
                .setModMonsterAttackRoll(modMonsterAttackRoll)
                .setCancelLootEffect(cancelLootEffect)
                .setAllDiscardLoot(allDiscardLoot)
                .setRerollYourItem(rerollPlayerItem)
                .setRerollItemInPlay(rerollItemInPlay)
                .setReollAllItems(rerollAllItems)
                .setOtherPlayersDiscardLoot(otherPlayersDiscardLoot)
                .setRerollAnyItem(rerollAnyItem)
                .setPutLoot(putLoot);
    }

    @Override
    public PillEvent[] submit() throws Exception {
        return lv.getItems().stream().toList().toArray(new PillEvent[]{});
    }
}
