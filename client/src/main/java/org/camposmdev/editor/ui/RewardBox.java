package org.camposmdev.editor.ui;

import com.almasb.fxgl.core.collection.grid.Grid;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.camposmdev.model.card.attribute.Reward;

public class RewardBox {
    private GridPane root;
    private TextField tfLoot;
    private TextField tfTreasure;
    private TextField tfCents;
    private TextField tfHitPoints;
    private CheckBox cbRechargeItem, cbStealShopItem, cbDeath, cbIsEqualToCounter;

    public RewardBox() {
        tfLoot = new TextField();
        tfLoot.setPromptText("[-128, 127]");
        tfTreasure = new TextField();
        tfTreasure.setPromptText("[-128, 127]");
        tfCents = new TextField();
        tfCents.setPromptText("[-128, 127]");
        tfHitPoints = new TextField();
        tfHitPoints.setPromptText("Hit Points [-128, 127]");
        cbRechargeItem = new CheckBox();
        cbStealShopItem = new CheckBox();
        cbDeath = new CheckBox();
        cbIsEqualToCounter = new CheckBox();
        root = new GridPane(4, 4);
        root.addRow(0, new Label("Loot"), tfLoot);
        root.addRow(1, new Label("Treasure"), tfTreasure);
        root.addRow(2, new Label("Cents"), tfCents);
        root.addRow(4, new Label("Hit Points"), tfHitPoints);
        root.addRow(5, new Label("Recharge Item"), cbRechargeItem);
        root.addRow(6, new Label("Steal Shop Item"), cbStealShopItem);
        root.addRow(7, new Label("Death"), cbDeath);
        root.addRow(8, new Label("Is Equal to Counter"), cbIsEqualToCounter);
    }

    public void load(Reward reward) {
        tfLoot.setText(reward.loot().toString());
        tfTreasure.setText(reward.treasure().toString());
        tfCents.setText(reward.cents().toString());
        tfHitPoints.setText(reward.hitPoints().toString());
        cbRechargeItem.setSelected(reward.rechargeItem());
        cbStealShopItem.setSelected(reward.stealShopItem());
        cbDeath.setSelected(reward.death());
        cbIsEqualToCounter.setSelected(reward.isEqualToCounter());
    }

    public Reward build() throws NumberFormatException {
        return new Reward(Byte.parseByte(tfLoot.getText()),
                Byte.parseByte(tfTreasure.getText()),
                Byte.parseByte(tfCents.getText()),
                Byte.parseByte(tfHitPoints.getText()),
                cbRechargeItem.isSelected(),
                cbStealShopItem.isSelected(),
                cbDeath.isSelected(),
                cbIsEqualToCounter.isSelected());
    }

    public Node getContent() {
        return root;
    }
}
