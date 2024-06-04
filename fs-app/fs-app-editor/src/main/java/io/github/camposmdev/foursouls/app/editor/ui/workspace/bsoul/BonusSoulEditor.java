package io.github.camposmdev.foursouls.app.editor.ui.workspace.bsoul;

import com.almasb.fxgl.ui.UI;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import io.github.camposmdev.foursouls.app.editor.model.Model;
import io.github.camposmdev.foursouls.app.editor.ui.NotificationBar;
import io.github.camposmdev.foursouls.app.editor.ui.factory.DialogFactory;
import io.github.camposmdev.foursouls.app.editor.ui.workspace.BaseEditor;
import io.github.camposmdev.foursouls.model.card.bsoul.BonusSoulCard;
import io.github.camposmdev.foursouls.model.fx.FXUtil;
import io.github.camposmdev.foursouls.model.fx.FormController;

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
            Model.instance().addCard(card);
            NotificationBar.instance().push(card);
        } catch (Exception e) {
            DialogFactory.instance().showErrorBox(e);
        }
    }
}
