package org.camposmdev.editor.ui.workspace.soul;

import com.almasb.fxgl.ui.UI;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import org.camposmdev.editor.model.Model;
import org.camposmdev.editor.ui.NotificationBar;
import org.camposmdev.editor.ui.factory.DialogFactory;
import org.camposmdev.editor.ui.workspace.BaseEditor;
import org.camposmdev.model.card.BonusSoulCard;
import org.camposmdev.util.FXUtil;
import org.camposmdev.util.FormController;

public class SoulEditor extends BaseEditor {
    private final GridPane root;
    private final UI form;

    public SoulEditor() {
        form = FXUtil.loadUI("SoulForm.fxml");
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
            Model.instance().cards().addSoul(card);
            NotificationBar.instance().push(card.toString());
        } catch (Exception e) {
            DialogFactory.instance().showErrorBox(e);
        }
    }
}
