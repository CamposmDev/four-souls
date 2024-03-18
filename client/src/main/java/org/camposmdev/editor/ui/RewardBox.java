package org.camposmdev.editor.ui;

import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import org.camposmdev.model.game.Reward;

public class RewardBox {
    private VBox root;
    private TextField tfLoot;
    private TextField tfTreasure;
    private TextField tfCents;
    private TextField tfSouls;
    private TextField tfHitPoints;
    private ComboBox<Boolean> cbRechargeItem;

    public RewardBox() {
        init();
    }

    public void load(Reward reward) {
        tfLoot.setText(reward.loot().toString());
        tfTreasure.setText(reward.treasure().toString());
        tfCents.setText(reward.cents().toString());
        tfSouls.setText(reward.souls().toString());
        tfHitPoints.setText(reward.hitPoints().toString());
        cbRechargeItem.setValue(reward.rechargeItem());
    }

    public Reward build() throws NumberFormatException {
        return Reward.create()
                .loot(Byte.parseByte(tfLoot.getText()))
                .treasure(Byte.parseByte(tfTreasure.getText()))
                .cents(Byte.parseByte(tfCents.getText()))
                .souls(Byte.parseByte(tfSouls.getText()))
                .hitPoints(Byte.parseByte(tfHitPoints.getText()))
                .rechargeItem(cbRechargeItem.getValue() != null && cbRechargeItem.getValue())
                .build();
    }

    private void init() {
        tfLoot = new TextField();
        tfLoot.setPromptText("Loot [0, 127]");
        tfTreasure = new TextField();
        tfTreasure.setPromptText("Treasure [0, 127]");
        tfCents = new TextField();
        tfCents.setPromptText("Cents [0, 127]");
        tfSouls = new TextField();
        tfSouls.setPromptText("Souls [0, 127]");
        tfHitPoints = new TextField();
        tfHitPoints.setPromptText("Hit Points [0, 127]");
        cbRechargeItem = new ComboBox<>();
        cbRechargeItem.setPromptText("Recharge Item");
        cbRechargeItem.setPrefWidth(150);
        cbRechargeItem.getItems().addAll(true, false);
        root = new VBox(8, tfLoot, tfTreasure, tfCents, tfSouls, tfHitPoints, cbRechargeItem);
    }

    public Node getContent() {
        return root;
    }
}
