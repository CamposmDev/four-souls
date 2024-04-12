package org.camposmdev.editor.ui.workspace.bsoul;

import com.almasb.fxgl.ui.UI;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import org.camposmdev.editor.model.Model;
import org.camposmdev.editor.ui.NotificationBar;
import org.camposmdev.editor.ui.factory.DialogFactory;
import org.camposmdev.editor.ui.workspace.BaseEditor;
import org.camposmdev.model.card.bsoul.BonusSoulCard;
import org.camposmdev.util.FXUtil;
import org.camposmdev.util.FormController;

public class BonusSoulEditor extends BaseEditor {
    private final GridPane root;
    private final UI form;

    public BonusSoulEditor() {
        form = FXUtil.loadUI("workspace/bsoul/BonusSoulForm.fxml");
        assert form != null;
        var btCommit = new Button("Commit");
        btCommit.setOnAction(e -> commit());
        root = new GridPane(4, 4);
        root.addColumn(0, form.getRoot(), btCommit);
    }

    @Override
    public Node getContent() {
        return root;
    }

    @Override
    public void commit() {
        try {
            var card = (BonusSoulCard) ((FormController<?>) form.getController()).submit();
            card.setId(super.id()).setImage(super.image());
            Model.instance().cards().add(card);
            NotificationBar.instance().push(card);
        } catch (Exception e) {
            DialogFactory.instance().showErrorBox(e);
        }
    }
}
