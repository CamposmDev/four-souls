package org.camposmdev.editor.ui.workspace.bsoul;

import com.almasb.fxgl.ui.UI;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import org.camposmdev.editor.model.Model;
import org.camposmdev.editor.ui.NotificationBar;
import org.camposmdev.editor.ui.factory.DialogFactory;
import org.camposmdev.editor.ui.workspace.BaseEditor;
import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.bonus_soul.BonusSoulCard;
import org.camposmdev.util.FXUtil;
import org.camposmdev.util.FormController;

public class BonusSoulEditor extends BaseEditor {
    private final GridPane root;
    private final UI form;
    private final CardType cardType;

    public BonusSoulEditor(CardType type) {
        cardType = type;
        form = FXUtil.loadUI("BonusSoulForm.fxml");
        assert form != null;
        var btSubmit = new Button("Submit");
        btSubmit.setOnAction(e -> commit());
        root = new GridPane(8, 12);
        root.addColumn(0, form.getRoot(), btSubmit);
    }

    @Override
    public Node getContent() {
        return root;
    }

    @Override
    public void commit() {
        try {
            var card = (BonusSoulCard) ((FormController<?>) form.getController()).submit();
            card.setId(id()).setImage(image()).setCardType(cardType);
            Model.instance().cards().add(card);
            NotificationBar.instance().push(card.toString());
        } catch (Exception e) {
            DialogFactory.instance().showErrorBox(e);
        }
    }
}
