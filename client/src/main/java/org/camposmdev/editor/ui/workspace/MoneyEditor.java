package org.camposmdev.editor.ui.workspace;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.camposmdev.editor.model.Model;
import org.camposmdev.editor.ui.NotificationBar;
import org.camposmdev.editor.ui.factory.DialogFactory;
import org.camposmdev.model.card.CardType;
import org.camposmdev.model.card.MoneyCard;
import org.camposmdev.util.FXUtil;

public class MoneyEditor extends BaseEditor {
    private final GridPane root;
    private final TextField tfValue;
    private final ComboBox<Boolean> cbRechargeItem;
    private ComboBox<Boolean> cbDoubleReward;
    private final ComboBox<Boolean> cbIsSticky;
    private final CardType type;
    public MoneyEditor(String type) {
        this.type = CardType.parse(type);
        tfValue = new TextField();
        tfValue.setPromptText(FXUtil.getByteRange());

        cbRechargeItem = new ComboBox<Boolean>();
        cbRechargeItem.setValue(false);
        cbRechargeItem.getItems().addAll(true, false);
        cbRechargeItem.setPrefWidth(150);

        cbIsSticky = new ComboBox<Boolean>();
        cbIsSticky.setValue(false);
        cbIsSticky.getItems().addAll(true, false);
        cbIsSticky.setPrefWidth(150);

        var cbDoubleReward = new ComboBox<Boolean>();
        cbDoubleReward.setValue(false);
        cbDoubleReward.getItems().addAll(true, false);
        cbDoubleReward.setPrefWidth(150);

        var btSubmit = new Button("Submit");
        btSubmit.setOnAction(e -> commit());
        root = new GridPane(8, 12);
        root.addRow(0, new Label("Value"), tfValue);
        root.addRow(1, new Label("Recharge Item"), cbRechargeItem);
        root.addRow(2, new Label("Double Reward"), cbDoubleReward);
        root.addRow(3, new Label("Is Sticky"), cbIsSticky);
        root.addRow(4, btSubmit);
    }

    @Override
    public Node getContent() {
        return root;
    }

    @Override
    public void commit() {
        try {
            var value = Byte.parseByte(tfValue.getText());
            var rechargeItem = cbRechargeItem.getValue();
            var doubleReward = cbDoubleReward.getValue();
            var isSticky = cbIsSticky.getValue();
            var card = new MoneyCard(id(), image(), type, value, rechargeItem, doubleReward, isSticky);
            Model.instance().cards().addLoot(card);
            NotificationBar.instance().push(card.toString());
        } catch (Exception ex) {
            DialogFactory.instance().showErrorBox(ex);
        }
    }
}
