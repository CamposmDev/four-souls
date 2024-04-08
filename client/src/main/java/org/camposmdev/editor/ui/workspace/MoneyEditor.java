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
import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.loot.MoneyCard;
import org.camposmdev.util.FXUtil;

public class MoneyEditor extends BaseEditor {
    private final GridPane root;
    private final TextField tfValue;
    private final ComboBox<Boolean> cbRechargeItem;
    private final ComboBox<Boolean> cbDoubleReward;
    private final ComboBox<Boolean> cbIsSticky;
    private final ComboBox<Boolean> cbIsButt;
    private final CardType type;
    public MoneyEditor(String type) {
        this.type = CardType.parse(type);
        tfValue = new TextField();
        tfValue.setPromptText(FXUtil.getByteRange());

        cbRechargeItem = new ComboBox<>();
        cbRechargeItem.setValue(false);
        cbRechargeItem.getItems().addAll(true, false);
        cbRechargeItem.setPrefWidth(150);

        cbDoubleReward = new ComboBox<>();
        cbDoubleReward.setValue(false);
        cbDoubleReward.getItems().addAll(true, false);
        cbDoubleReward.setPrefWidth(150);

        cbIsSticky = new ComboBox<>();
        cbIsSticky.setValue(false);
        cbIsSticky.getItems().addAll(true, false);
        cbIsSticky.setPrefWidth(150);

        cbIsButt = new ComboBox<>();
        cbIsButt.setValue(false);
        cbIsButt.getItems().addAll(true, false);
        cbIsButt.setPrefWidth(150);

        var btSubmit = new Button("Submit");
        btSubmit.setOnAction(e -> commit());
        root = new GridPane(8, 12);
        root.addRow(0, new Label("Value"), tfValue);
        root.addRow(1, new Label("Recharge Item"), cbRechargeItem);
        root.addRow(2, new Label("Double Reward"), cbDoubleReward);
        root.addRow(3, new Label("Is Sticky"), cbIsSticky);
        root.addRow(4, new Label("Is Butt"), cbIsButt);
        root.addRow(5, btSubmit);
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
            var isButt = cbIsButt.getValue();
            var card = new MoneyCard();
            card.setId(id()).setImage(image());
            card.setValue(value).setRechargeItem(rechargeItem).setDoubleReward(doubleReward).setSticky(isSticky).setButt(isButt);

            Model.instance().cards().addLoot(card);
            NotificationBar.instance().push(card.toString());
        } catch (Exception ex) {
            DialogFactory.instance().showErrorBox(ex);
        }
    }
}