package org.camposmdev.editor.ui.workspace.money;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.camposmdev.editor.model.Model;
import org.camposmdev.editor.ui.NotificationBar;
import org.camposmdev.editor.ui.factory.DialogFactory;
import org.camposmdev.editor.ui.workspace.BaseEditor;
import org.camposmdev.model.card.attribute.CardSet;
import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.loot.MoneyCard;
import org.camposmdev.util.FXUtil;

public class MoneyEditor extends BaseEditor {
    private final GridPane root;
    private final ComboBox<CardSet> cbCardSet;
    private final TextField tfValue;
    private final CheckBox cbRechargeItem;
    private final CheckBox cbDoubleReward;
    private final CheckBox cbIsSticky;
    private final CheckBox cbIsButt;
    private final CardType cardType;
    public MoneyEditor(CardType type) {
        cardType = type;
        cbCardSet = new ComboBox<>();
        cbCardSet.setValue(CardSet.UNDEFINED);
        cbCardSet.getItems().addAll(CardSet.values());
        tfValue = new TextField();
        tfValue.setPromptText(FXUtil.getByteRange());
        cbRechargeItem = new CheckBox();
        cbDoubleReward = new CheckBox();
        cbIsSticky = new CheckBox();
        cbIsButt = new CheckBox();

        var btSubmit = new Button("Submit");
        btSubmit.setOnAction(e -> commit());
        root = new GridPane(4, 4);
        root.addRow(0, new Label("Card Set"), cbCardSet);
        root.addRow(1, new Label("Value"), tfValue);
        root.addRow(2, new Label("Recharge Item"), cbRechargeItem);
        root.addRow(3, new Label("Double Reward"), cbDoubleReward);
        root.addRow(4, new Label("Is Sticky"), cbIsSticky);
        root.addRow(5, new Label("Is Butt"), cbIsButt);
        root.addRow(6, btSubmit);
    }

    @Override
    public Node getContent() {
        return root;
    }

    @Override
    public void commit() {
        try {
            var cardSet = cbCardSet.getValue();
            var value = Byte.parseByte(tfValue.getText());
            var rechargeItem = cbRechargeItem.isSelected();
            var doubleReward = cbDoubleReward.isSelected();
            var isSticky = cbIsSticky.isSelected();
            var isButt = cbIsButt.isSelected();
            var card = new MoneyCard();
            card.setId(id()).setImage(image()).setCardSet(cardSet).setCardType(cardType);
            card.setValue(value).setRechargeItem(rechargeItem).setDoubleReward(doubleReward).setSticky(isSticky).setButt(isButt);
            /* TODO - Maybe add this card to its own type of instance map */
            Model.instance().cards().add(card);
            NotificationBar.instance().push(card.toString());
        } catch (Exception ex) {
            DialogFactory.instance().showErrorBox(ex);
        }
    }
}
