package io.github.camposmdev.foursouls.app.editor.ui.workspace.character;

import com.almasb.fxgl.ui.UI;
import io.github.camposmdev.foursouls.app.editor.ui.factory.DialogFactory;
import io.github.camposmdev.foursouls.app.editor.ui.workspace.BaseEditor;
import io.github.camposmdev.foursouls.model.fx.FXUtil;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import io.github.camposmdev.foursouls.app.editor.model.Model;
import io.github.camposmdev.foursouls.app.editor.ui.NotificationBar;

public class CharacterEditor extends BaseEditor {
    private final VBox root;
    private final UI form;

    public CharacterEditor() {
        form = FXUtil.loadUI("workspace/character/CharacterForm.fxml");
        assert form != null;
        Button btCommit = new Button("Commit");
        btCommit.setOnAction(e -> commit());
        root = new VBox(2, form.getRoot(), btCommit);
    }

    private void build() {
        try {
            /* build CharacterCard object */
            var card = ((CharacterFormController) form.getController()).submit();
            card.setId(super.id()).setImage(super.image());
            /* add the character card to our card atlas */
            Model.instance().addCard(card);
            NotificationBar.instance().push(card);
        } catch (Exception ex) {
            DialogFactory.instance().showErrorBox(ex);
        }
    }

    @Override
    public Node getContent() {
        return root;
    }

    @Override
    public void commit() {
        build();
    }
}
