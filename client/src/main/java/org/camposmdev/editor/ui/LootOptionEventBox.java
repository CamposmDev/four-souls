package org.camposmdev.editor.ui;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.camposmdev.editor.ui.factory.DialogFactory;
import org.camposmdev.model.card.attribute.EntityTarget;
import org.camposmdev.model.card.attribute.Reward;
import org.camposmdev.model.card.attribute.loot.LootOptionEvent;
import org.camposmdev.util.FormController;

import java.util.List;

public class LootOptionEventBox extends FormController<List<LootOptionEvent>> {
    private final ListView<LootOptionEvent> lv;
    private final Label damageLabel;
    private final TextField damageTextField;

    private final Label damageToLabel;
    private final ComboBox<EntityTarget> damageToComboBox;

    private final Label rewardLabel;
    private final Button rewardButton;

    private final Label attackAgainLabel;
    private final CheckBox attackAgainCheckBox;

    private final Label summonMonsterLabel;
    private final CheckBox summonMonsterCheckBox;

    private final Label destroyCurseLabel;
    private final CheckBox destroyCurseCheckBox;

    private final Label preventDamageToPlayerLabel;
    private final TextField preventDamageToPlayerTextField;
    private Reward reward;

    public LootOptionEventBox() {
        reward = null;
        lv = new ListView<>();
        lv.setPrefSize(400, 600);
        lv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        lv.setTooltip(new Tooltip("BACKSPACE or DEL to delete item"));
        lv.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.DELETE || e.getCode() == KeyCode.BACK_SPACE
                    && lv.getSelectionModel().getSelectedItem() != null) {
                lv.getItems().remove(lv.getSelectionModel().getSelectedItem());
            }
        });
        damageLabel = new Label("Damage:");
        damageTextField = new TextField();
        damageTextField.setPromptText("[0,127]");

        damageToLabel = new Label("Damage To:");
        damageToComboBox = new ComboBox<>();
        damageToComboBox.setValue(EntityTarget.UNDEFINED);
        damageToComboBox.getItems().addAll(EntityTarget.values());

        rewardLabel = new Label("Reward:");
        rewardButton = new Button("Modify");
        rewardButton.setOnAction(e -> DialogFactory.instance().showRewardModifierBox(reward).ifPresent(x -> reward = x));

        attackAgainLabel = new Label("Attack Again:");
        attackAgainCheckBox = new CheckBox();

        summonMonsterLabel = new Label("Summon Monster:");
        summonMonsterCheckBox = new CheckBox();

        destroyCurseLabel = new Label("Destroy Curse:");
        destroyCurseCheckBox = new CheckBox();

        preventDamageToPlayerLabel = new Label("Prevent Damage To Player:");
        preventDamageToPlayerTextField = new TextField();
        preventDamageToPlayerTextField.setPromptText("[0,127]");
    }

    public void load(List<LootOptionEvent> lst) {
        lv.getItems().clear();
        lv.getItems().addAll(lst);
    }

    public Node getContent() {
        var btCommit = new Button("Commit");
        btCommit.setOnAction(e -> {
            try {
                var lootOptionEvent = new LootOptionEvent();
                lootOptionEvent.setDamage(Byte.parseByte(damageTextField.getText()));
                lootOptionEvent.setDamageTo(EntityTarget.UNDEFINED);
                lootOptionEvent.setReward(reward);
                lootOptionEvent.setAttackAgain(attackAgainCheckBox.isSelected());
                lootOptionEvent.setSummonMonster(summonMonsterCheckBox.isSelected());
                lootOptionEvent.setDestroyCurse(destroyCurseCheckBox.isSelected());
                lootOptionEvent.setPreventDamageToPlayer(Byte.parseByte(preventDamageToPlayerTextField.getText()));
                lv.getItems().add(lootOptionEvent);
            } catch (Exception ex) {
                DialogFactory.instance().showErrorBox(ex);
            }
        });
        // Initialize GridPane and add labels/controls
        GridPane gridPane = new GridPane(4,4);
        gridPane.addRow(0, damageLabel, damageTextField);
        gridPane.addRow(1, damageToLabel, damageToComboBox);
        gridPane.addRow(2, rewardLabel, rewardButton);
        gridPane.addRow(3, attackAgainLabel, attackAgainCheckBox);
        gridPane.addRow(4, summonMonsterLabel, summonMonsterCheckBox);
        gridPane.addRow(5, destroyCurseLabel, destroyCurseCheckBox);
        gridPane.addRow(6, preventDamageToPlayerLabel, preventDamageToPlayerTextField);
        gridPane.addRow(7, new StackPane(btCommit));
        gridPane.add(new StackPane(btCommit), 0, 7, 2, 1);
        return new HBox(4, lv, gridPane);
    }

    @Override
    public List<LootOptionEvent> submit() throws Exception {
        return lv.getItems().stream().toList();
    }
}
