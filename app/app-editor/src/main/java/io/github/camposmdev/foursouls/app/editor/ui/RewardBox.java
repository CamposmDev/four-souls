package io.github.camposmdev.foursouls.app.editor.ui;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import io.github.camposmdev.foursouls.model.card.attribute.Reward;
import io.github.camposmdev.foursouls.model.util.fx.FXUtil;
import io.github.camposmdev.foursouls.model.util.fx.FormController;

public class RewardBox extends FormController<Reward> {
    private final GridPane root;
    private final TextField tfLoot, tfTreasure, tfCents, tfHitPoints;
    private final CheckBox cbRechargeItem, cbStealShopItem, cbDeath, cbIsEqualToCounter;

    public RewardBox() {
        tfLoot = new TextField();
        tfTreasure = new TextField();
        tfCents = new TextField();
        tfHitPoints = new TextField();
        FXUtil.initNumberFields(tfLoot, tfTreasure, tfCents, tfHitPoints);
        cbRechargeItem = new CheckBox();
        cbStealShopItem = new CheckBox();
        cbDeath = new CheckBox();
        cbIsEqualToCounter = new CheckBox();
        root = new GridPane(4, 4);
        root.addRow(0, new Label("loot"), tfLoot);
        root.addRow(1, new Label("treasure"), tfTreasure);
        root.addRow(2, new Label("cents"), tfCents);
        root.addRow(4, new Label("hitPoints"), tfHitPoints);
        root.addRow(5, new Label("rechargeItem"), cbRechargeItem);
        root.addRow(6, new Label("stealShopItem"), cbStealShopItem);
        root.addRow(7, new Label("death"), cbDeath);
        root.addRow(8, new Label("equalToCounter"), cbIsEqualToCounter);
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

    public Node getContent() {
        return root;
    }

    @Override
    public Reward submit() throws Exception {
        return new Reward(Integer.parseInt(tfLoot.getText()),
                Integer.parseInt(tfTreasure.getText()),
                Integer.parseInt(tfCents.getText()),
                Integer.parseInt(tfHitPoints.getText()),
                cbRechargeItem.isSelected(),
                cbStealShopItem.isSelected(),
                cbDeath.isSelected(),
                cbIsEqualToCounter.isSelected());
    }
}
