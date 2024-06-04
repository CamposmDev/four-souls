package io.github.camposmdev.foursouls.app.editor.ui;

import io.github.camposmdev.foursouls.app.editor.ui.factory.DialogFactory;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import io.github.camposmdev.foursouls.model.card.attribute.*;
import io.github.camposmdev.foursouls.model.util.fx.FXUtil;
import io.github.camposmdev.foursouls.model.util.fx.FormController;

import java.util.LinkedList;
import java.util.List;

public class RollEventFormController extends FormController<List<RollEvent>> {
    @FXML TextField values;
    @FXML TextField multReward;
    @FXML TextField multPlayersDamage;
    @FXML CheckBox noMonsterDamage;
    @FXML TextField modMonstersDamage;
    @FXML TextField multMonstersDamage;
    @FXML CheckBox endTurn;
    @FXML CheckBox stealPlayerLoot;
    @FXML TextField damage;
    @FXML ComboBox<EntityTarget> damageTo;
    @FXML CheckBox mayAttackAgain;
    @FXML CheckBox forceAttackAgain;
    @FXML CheckBox attackable;
    @FXML CheckBox returnToDeck;
    @FXML TextField modMonstersDC;
    @FXML TextField modMonstersAttack;
    @FXML TextField healMonster;
    @FXML CheckBox endAttack;
    @FXML TextField expandMonster;
    @FXML TextField discardLoot;
    @FXML TextField discardCents;
    @FXML ComboBox<DeckType> peekDeck;
    @FXML TextField peekDeckAmount;
    @FXML CheckBox peekDeckSort = new CheckBox();
    @FXML TextField soulHitPoints;
    @FXML CheckBox soul = new CheckBox();
    @FXML CheckBox skipNextTurn = new CheckBox();
    @FXML CheckBox heartItem = new CheckBox();
    @FXML TextField rerollItem;
    @FXML CheckBox guppyItem = new CheckBox();
    @FXML ComboBox<EntityTarget> kill;
    @FXML TextField modAllMonstersDC;
    @FXML CheckBox putOnTopOfMonsterDeck;
    @FXML CheckBox damageLink;
    @FXML CheckBox discardRandomItem;
    @FXML CheckBox putOnMonsterSlot;
    @FXML ListView<RollEvent> lv;

    private AttributeModifier modifier;
    private Reward reward;
    private List<RollEvent> rollEvents;

    @Override
    public void init() {
        FXUtil.initNumberFields(multReward, multPlayersDamage, modMonstersDamage, multMonstersDamage,
                damage, modMonstersDC, modMonstersAttack, healMonster, expandMonster,
                discardLoot, discardCents, peekDeckAmount, soulHitPoints,
                rerollItem, modAllMonstersDC);
        damageTo.setValue(EntityTarget.UNDEFINED);
        damageTo.getItems().addAll(EntityTarget.values());
        peekDeck.setValue(DeckType.UNDEFINED);
        peekDeck.getItems().addAll(DeckType.values());
        kill.setValue(EntityTarget.UNDEFINED);
        kill.getItems().addAll(EntityTarget.values());
        lv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        lv.setPrefSize(400, 600);
        lv.setTooltip(new Tooltip("BACKSPACE or DEL to delete item"));
        lv.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.DELETE || e.getCode() == KeyCode.BACK_SPACE
                    && lv.getSelectionModel().getSelectedItem() != null) {
                lv.getItems().remove(lv.getSelectionModel().getSelectedItem());
            }
        });
    }

    public RollEventFormController() {
        modifier = null;
        reward = null;
        rollEvents = new LinkedList<>();
    }

    public void load(List<RollEvent> lst) {
        lv.getItems().setAll(lst);
    }

    @Override
    public List<RollEvent> submit() {
        return lv.getItems().stream().toList();
    }

    public void modPlayer() {
        DialogFactory.instance().showAttributeModifierBox(modifier);
    }

    public void modReward() {
        DialogFactory.instance().showRewardModifierBox(reward);
    }

    public void modRollEvents() {
        DialogFactory.instance().showRollEventModifierBox(rollEvents);
    }

    public void commit() {
        try {
            RollEvent rollEvent = new RollEvent();
            // Set values for each property
            String strValues = values.getText();
            String[] valueTokens = strValues.split(",");
            List<Byte> values = new LinkedList<>();
            for (String valueToken : valueTokens)
                values.add(Byte.parseByte(valueToken));
            rollEvent.setValues(values);
            rollEvent.setMultReward(Byte.parseByte(multReward.getText()));
            rollEvent.setMultPlayersDamage(Byte.parseByte(multPlayersDamage.getText()));
            rollEvent.setNoMonsterDamage(noMonsterDamage.isSelected());
            rollEvent.setModMonsterDamage(Byte.parseByte(modMonstersDamage.getText()));
            rollEvent.setMultMonsterDamage(Byte.parseByte(multMonstersDamage.getText()));
            rollEvent.setEndTurn(endTurn.isSelected());
            rollEvent.setStealPlayerLoot(stealPlayerLoot.isSelected());
            rollEvent.setDamage(Byte.parseByte(damage.getText()));
            rollEvent.setDamageTo(damageTo.getValue());
            rollEvent.setAttackAgain(mayAttackAgain.isSelected());
            rollEvent.setForceAttackAgain(forceAttackAgain.isSelected());
            rollEvent.setModPlayer(modifier);
            rollEvent.setAttackable(attackable.isSelected());
            rollEvent.setReturnToDeck(returnToDeck.isSelected());
            rollEvent.setModMonstersDC(Byte.parseByte(modMonstersDC.getText()));
            rollEvent.setModMonstersAttack(Byte.parseByte(modMonstersAttack.getText()));
            rollEvent.setHealMonster(Byte.parseByte(healMonster.getText()));
            rollEvent.setEndAttack(endAttack.isSelected());
            rollEvent.setExpandMonster(Byte.parseByte(expandMonster.getText()));
            rollEvent.setReward(reward);
            rollEvent.setDiscardLoot(Byte.parseByte(discardLoot.getText()));
            rollEvent.setDiscardCents(Byte.parseByte(discardCents.getText()));
            rollEvent.setPeekDeck(peekDeck.getValue());
            rollEvent.setPeekDeckAmount(Byte.parseByte(peekDeckAmount.getText()));
            rollEvent.setPeekDeckSort(peekDeckSort.isSelected());
            rollEvent.setSoulHitPoints(Byte.parseByte(soulHitPoints.getText()));
            rollEvent.setSoul(soul.isSelected());
            rollEvent.setSkipNextTurn(skipNextTurn.isSelected());
            rollEvent.setHeartItem(heartItem.isSelected());
            rollEvent.setRerollItem(Byte.parseByte(rerollItem.getText()));
            rollEvent.setGuppyItem(guppyItem.isSelected());
            rollEvent.setKill(kill.getValue());
            rollEvent.setModAllMonstersDC(Byte.parseByte(modAllMonstersDC.getText()));
            rollEvent.setPutOnTopOfMonsterDeck(putOnTopOfMonsterDeck.isSelected());
            rollEvent.setRollEvents(rollEvents);
            rollEvent.setDamageLink(damageLink.isSelected());
            rollEvent.setDiscardRandomItem(discardRandomItem.isSelected());
            rollEvent.setPutOnMonsterSlot(putOnMonsterSlot.isSelected());
            lv.getItems().add(rollEvent);
            /* reset the fields for efficient data entry */
            modifier = null;
            reward = null;
            rollEvents = new LinkedList<>();
        }  catch (Exception ex) {
            DialogFactory.instance().showErrorBox(ex);
        }
    }
}
