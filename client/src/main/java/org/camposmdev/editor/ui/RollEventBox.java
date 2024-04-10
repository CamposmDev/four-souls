package org.camposmdev.editor.ui;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.camposmdev.editor.ui.factory.DialogFactory;
import org.camposmdev.model.card.attribute.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class RollEventBox {
    TextField valuesField;
    TextField multRewardField;
    CheckBox noMonsterDamageCheckBox;
    TextField modMonsterDamageField;
    TextField multMonsterDamageField;
    CheckBox cancelTurnCheckBox;
    CheckBox stealPlayerLootCheckBox;
    TextField damageField;
    ComboBox<EntityTarget> damageToComboBox;
    CheckBox attackAgainCheckBox;
    CheckBox forceAttackAgainCheckBox;
    CheckBox attackableCheckBox;
    CheckBox returnToDeckCheckBox;
    TextField modRollField;
    TextField modAttackField;
    TextField healMonsterField;
    CheckBox cancelAttackCheckBox;
    TextField expandMonsterField;
    TextField discardLootField;
    TextField discardCentsField;
    ComboBox<DeckType> peekDeckComboBox;
    TextField peekDeckAmountField;
    CheckBox peekDeckSortCheckBox = new CheckBox();
    TextField soulHitPointsField;
    CheckBox isSoulCheckBox = new CheckBox();
    CheckBox skipNextTurnCheckBox = new CheckBox();
    CheckBox isHeartItemCheckBox = new CheckBox();
    TextField rerollItemField;
    CheckBox guppyItemCheckBox = new CheckBox();
    ComboBox<EntityTarget> killComboBox;
    TextField modMonstersAttackRollField;
    CheckBox putOnTopMonsterDeckCheckBox;
    CheckBox damageLinkCheckBox;
    CheckBox discardRandomItemCheckBox;
    CheckBox putOnMonsterSlotCheckBox;

    private AttributeModifier modifier;
    private Reward reward;
    private List<RollEvent> rollEvents;
    private ListView<RollEvent> lv;


    public RollEventBox() {
        modifier = null;
        reward = null;
        rollEvents = new LinkedList<>();

        lv = new ListView<>();
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

    public void load(List<RollEvent> lst) {
        lv.getItems().setAll(lst);
    }

    public List<RollEvent> build() {
        return lv.getItems().stream().toList();
    }

    public Node getContent() {
        GridPane gridPane = new GridPane(4, 4);
        // Labels for variables
        Label valuesLabel = new Label("values");
        Label multRewardLabel = new Label("multReward");
        Label noMonsterDamageLabel = new Label("noMonsterDamage");
        Label modMonsterDamageLabel = new Label("modMonsterDamage");
        Label multMonsterDamageLabel = new Label("multMonsterDamage");
        Label cancelTurnLabel = new Label("cancelTurn");
        Label stealPlayerLootLabel = new Label("stealPlayerLoot");
        Label damageLabel = new Label("damage");
        Label damageToLabel = new Label("damageTo");
        Label attackAgainLabel = new Label("attackAgain");
        Label forceAttackAgainLabel = new Label("forceAttackAgain");
        Label modPlayerLabel = new Label("modPlayer");
        Label attackableLabel = new Label("attackable");
        Label returnToDeckLabel = new Label("returnToDeck");
        Label modRollLabel = new Label("modRoll");
        Label modAttackLabel = new Label("modAttack");
        Label healMonsterLabel = new Label("healMonster");
        Label cancelAttackLabel = new Label("cancelAttack");
        Label expandMonsterLabel = new Label("expandMonster");
        Label rewardLabel = new Label("reward");
        Label discardLootLabel = new Label("discardLoot");
        Label discardCentsLabel = new Label("discardCents");
        Label peekDeckLabel = new Label("peekDeck");
        Label peekDeckAmountLabel = new Label("peekDeckAmount");
        Label peekDeckSortLabel = new Label("peekDeckSort");
        Label soulHitPointsLabel = new Label("soulHitPoints");
        Label isSoulLabel = new Label("isSoul");
        Label skipNextTurnLabel = new Label("skipNextTurn");
        Label isHeartItemLabel = new Label("isHeartItem");
        Label rerollItemLabel = new Label("rerollItem");
        Label guppyItemLabel = new Label("guppyItem");
        Label killLabel = new Label("kill");
        Label modMonstersAttackRollLabel = new Label("modMonstersAttackRoll");
        Label putOnTopMonsterDeckLabel = new Label("putOnTopMonsterDeck");
        Label rollEventLabel = new Label("rollEvent");
        Label damageLinkLabel = new Label("damageLink");
        Label discardRandomItemLabel = new Label("discardRandomItem");
        Label putOnMonsterSlotLabel = new Label("putOnMonsterSlot");

        // Controls for variables
        valuesField = new TextField();
        valuesField.setPromptText("1,2,3,4,5,6");
        multRewardField = new TextField();
        multRewardField.setPromptText("[0,127]");
        noMonsterDamageCheckBox = new CheckBox();
        modMonsterDamageField = new TextField();
        modMonsterDamageField.setPromptText("[0,127]");
        multMonsterDamageField = new TextField();
        multMonsterDamageField.setPromptText("[0,127]");
        cancelTurnCheckBox = new CheckBox();
        stealPlayerLootCheckBox = new CheckBox();
        damageField = new TextField();
        damageField.setPromptText("[0,127]");
        damageToComboBox = new ComboBox<>();
        damageToComboBox.setValue(EntityTarget.UNDEFINED);
        damageToComboBox.getItems().addAll(EntityTarget.values());
        attackAgainCheckBox = new CheckBox();
        forceAttackAgainCheckBox = new CheckBox();
        Button modPlayerButton = new Button("Modify");
        modPlayerButton.setOnAction(e -> {
            DialogFactory.instance().showAttributeModifierBox(modifier);
        });
        attackableCheckBox = new CheckBox();
        returnToDeckCheckBox = new CheckBox();
        modRollField = new TextField();
        modRollField.setPromptText("[0,127]");
        modAttackField = new TextField();
        modAttackField.setPromptText("[0,127]");
        healMonsterField = new TextField();
        healMonsterField.setPromptText("[0,127]");
        cancelAttackCheckBox = new CheckBox();
        expandMonsterField = new TextField();
        expandMonsterField.setPromptText("[0,127]");
        Button rewardButton = new Button("Modify");
        rewardButton.setOnAction(e -> {
            DialogFactory.instance().showRewardModifierBox(reward).ifPresent(x -> {
                reward = x;
            });
        });
        discardLootField = new TextField();
        discardLootField.setPromptText("[0,127]");
        discardCentsField = new TextField();
        discardCentsField.setPromptText("[0,127]");
        peekDeckComboBox = new ComboBox<>();
        peekDeckComboBox.setValue(DeckType.UNDEFINED);
        peekDeckComboBox.getItems().addAll(DeckType.values());
        peekDeckAmountField = new TextField();
        peekDeckAmountField.setPromptText("[0,127]");
        peekDeckSortCheckBox = new CheckBox();
        soulHitPointsField = new TextField();
        soulHitPointsField.setPromptText("[0,127]");
        isSoulCheckBox = new CheckBox();
        skipNextTurnCheckBox = new CheckBox();
        isHeartItemCheckBox = new CheckBox();
        rerollItemField = new TextField();
        rerollItemField.setPromptText("[0,127]");
        guppyItemCheckBox = new CheckBox();
        killComboBox = new ComboBox<>();
        killComboBox.setValue(EntityTarget.UNDEFINED);
        killComboBox.getItems().addAll(EntityTarget.values());
        modMonstersAttackRollField = new TextField();
        modMonstersAttackRollField.setPromptText("[0,127]");
        putOnTopMonsterDeckCheckBox = new CheckBox();
        Button rollEventButton = new Button("Modify");
        rollEventButton.setOnAction(e -> {
            DialogFactory.instance().showRollEventModifierBox(rollEvents);
        });
        damageLinkCheckBox = new CheckBox();
        discardRandomItemCheckBox = new CheckBox();
        putOnMonsterSlotCheckBox = new CheckBox();

        var btSubmit = new Button("Submit");
        btSubmit.setOnAction(e -> {
            try {
                RollEvent rollEvent = new RollEvent();
                // Set values for each property
                String strValues = valuesField.getText();
                String[] valueTokens = strValues.split(",");
                byte[] values = new byte[valueTokens.length];
                for (int i = 0; i < valueTokens.length; i++)
                    values[i] = Byte.parseByte(valueTokens[i]);
                rollEvent.setValues(values);
                rollEvent.setMultReward(Byte.parseByte(multRewardField.getText()));
                rollEvent.setNoMonsterDamage(noMonsterDamageCheckBox.isSelected());
                rollEvent.setModMonsterDamage(Byte.parseByte(modMonsterDamageField.getText()));
                rollEvent.setMultMonsterDamage(Byte.parseByte(multMonsterDamageField.getText()));
                rollEvent.setCancelTurn(cancelTurnCheckBox.isSelected());
                rollEvent.setStealPlayerLoot(stealPlayerLootCheckBox.isSelected());
                rollEvent.setDamage(Byte.parseByte(damageField.getText()));
                rollEvent.setDamageTo(damageToComboBox.getValue());
                rollEvent.setAttackAgain(attackAgainCheckBox.isSelected());
                rollEvent.setForceAttackAgain(forceAttackAgainCheckBox.isSelected());
                rollEvent.setModPlayer(modifier);
                rollEvent.setAttackable(attackableCheckBox.isSelected());
                rollEvent.setReturnToDeck(returnToDeckCheckBox.isSelected());
                rollEvent.setModRoll(Byte.parseByte(modRollField.getText()));
                rollEvent.setModAttack(Byte.parseByte(modAttackField.getText()));
                rollEvent.setHealMonster(Byte.parseByte(healMonsterField.getText()));
                rollEvent.setCancelAttack(cancelAttackCheckBox.isSelected());
                rollEvent.setExpandMonster(Byte.parseByte(expandMonsterField.getText()));
                rollEvent.setReward(reward);
                rollEvent.setDiscardLoot(Byte.parseByte(discardLootField.getText()));
                rollEvent.setDiscardCents(Byte.parseByte(discardCentsField.getText()));
                rollEvent.setPeekDeck(peekDeckComboBox.getValue());
                rollEvent.setPeekDeckAmount(Byte.parseByte(peekDeckAmountField.getText()));
                rollEvent.setPeekDeckSort(peekDeckSortCheckBox.isSelected());
                rollEvent.setSoulHitPoints(Byte.parseByte(soulHitPointsField.getText()));
                rollEvent.setSoul(isSoulCheckBox.isSelected());
                rollEvent.setSkipNextTurn(skipNextTurnCheckBox.isSelected());
                rollEvent.setHeartItem(isHeartItemCheckBox.isSelected());
                rollEvent.setRerollItem(Byte.parseByte(rerollItemField.getText()));
                rollEvent.setGuppyItem(guppyItemCheckBox.isSelected());
                rollEvent.setKill(killComboBox.getValue());
                rollEvent.setModMonstersAttackRoll(Byte.parseByte(modMonstersAttackRollField.getText()));
                rollEvent.setPutOnTopMonsterDeck(putOnTopMonsterDeckCheckBox.isSelected());
                rollEvent.setRollEvents(rollEvents.toArray(new RollEvent[]{}));
                rollEvent.setDamageLink(damageLinkCheckBox.isSelected());
                rollEvent.setDiscardRandomItem(discardRandomItemCheckBox.isSelected());
                rollEvent.setPutOnMonsterSlot(putOnMonsterSlotCheckBox.isSelected());
                lv.getItems().add(rollEvent);
            }  catch (Exception ex) {
                DialogFactory.instance().showErrorBox(ex);
            }
        });

        // Add labels and controls to gridPane
        gridPane.addColumn(0, valuesLabel, multRewardLabel, noMonsterDamageLabel, modMonsterDamageLabel, multMonsterDamageLabel, cancelTurnLabel, stealPlayerLootLabel, damageLabel, damageToLabel, attackAgainLabel, forceAttackAgainLabel, modPlayerLabel, attackableLabel, returnToDeckLabel, modRollLabel, modAttackLabel, healMonsterLabel, cancelAttackLabel, expandMonsterLabel);
        gridPane.addColumn(1, valuesField, multRewardField, noMonsterDamageCheckBox, modMonsterDamageField, multMonsterDamageField, cancelTurnCheckBox, stealPlayerLootCheckBox, damageField, damageToComboBox, attackAgainCheckBox, forceAttackAgainCheckBox, modPlayerButton, attackableCheckBox, returnToDeckCheckBox, modRollField, modAttackField, healMonsterField, cancelAttackCheckBox, expandMonsterField);
        gridPane.addColumn(2, rewardLabel, discardLootLabel, discardCentsLabel, peekDeckLabel, peekDeckAmountLabel, peekDeckSortLabel, soulHitPointsLabel, isSoulLabel, skipNextTurnLabel, isHeartItemLabel, rerollItemLabel, guppyItemLabel, killLabel, modMonstersAttackRollLabel, putOnTopMonsterDeckLabel, rollEventLabel, damageLinkLabel, discardRandomItemLabel, putOnMonsterSlotLabel);
        gridPane.addColumn(3, rewardButton, discardLootField, discardCentsField, peekDeckComboBox, peekDeckAmountField, peekDeckSortCheckBox, soulHitPointsField, isSoulCheckBox, skipNextTurnCheckBox, isHeartItemCheckBox, rerollItemField, guppyItemCheckBox, killComboBox, modMonstersAttackRollField, putOnTopMonsterDeckCheckBox, rollEventButton, damageLinkCheckBox, discardRandomItemCheckBox, putOnMonsterSlotCheckBox);
        gridPane.add(new StackPane(btSubmit), 0, 19, 4, 1);
        return new HBox(4, lv, gridPane);
    }
}
