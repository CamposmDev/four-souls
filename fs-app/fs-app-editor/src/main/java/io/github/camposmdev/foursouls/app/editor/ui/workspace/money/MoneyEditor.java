package io.github.camposmdev.foursouls.app.editor.ui.workspace.money;

import io.github.camposmdev.foursouls.app.editor.model.Model;
import io.github.camposmdev.foursouls.app.editor.ui.NotificationBar;
import io.github.camposmdev.foursouls.app.editor.ui.factory.DialogFactory;
import io.github.camposmdev.foursouls.app.editor.ui.workspace.BaseEditor;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import io.github.camposmdev.foursouls.core.card.attribute.CardSet;
import io.github.camposmdev.foursouls.core.card.attribute.CardType;
import io.github.camposmdev.foursouls.core.card.attribute.loot.MoneyEffect.MoneyEffect;
import io.github.camposmdev.foursouls.core.card.loot.MoneyCard;
import io.github.camposmdev.foursouls.core.ui.FXUtil;

public class MoneyEditor extends BaseEditor {
    private final GridPane root;
    private final ComboBox<CardSet> cbCardSet;
    private final TextField tfValue;
    private final ComboBox<MoneyEffect> effect;
    private final CardType cardType;

    public MoneyEditor(CardType type) {
        cardType = type;
        cbCardSet = new ComboBox<>();
        cbCardSet.setValue(CardSet.UNDEFINED);
        cbCardSet.getItems().addAll(CardSet.values());
        tfValue = new TextField();
        tfValue.setPromptText(FXUtil.getByteRange());
        FXUtil.initNumberFields(tfValue);
        effect = new ComboBox<>();
        effect.setValue(MoneyEffect.UNDEFINED);
        effect.getItems().addAll(MoneyEffect.values());

        var btCommit = new Button("Commit");
        btCommit.setOnAction(e -> commit());
        root = new GridPane(4, 4);
        root.setPadding(new Insets(2));
        root.addRow(0, new Label("cardSet"), cbCardSet);
        root.addRow(1, new Label("value"), tfValue);
        root.addRow(2, new Label("effect"), effect);
        root.addRow(3, btCommit);
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
            var card = new MoneyCard();
            card.setId(id()).setCardSet(cardSet).setCardType(cardType);
            card.setValue(value).setEffect(effect.getValue());
            Model.instance().appendCard(card);
            NotificationBar.instance().push(card.toString());
        } catch (Exception ex) {
            DialogFactory.instance().showErrorBox(ex);
        }
    }
}
