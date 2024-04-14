package org.camposmdev.editor.ui.workspace.character;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.ui.UI;
import javafx.scene.layout.VBox;
import org.camposmdev.editor.ui.CardViewer;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import org.camposmdev.editor.model.Model;
import org.camposmdev.editor.ui.NotificationBar;
import org.camposmdev.editor.ui.factory.DialogFactory;
import org.camposmdev.editor.ui.workspace.BaseEditor;
import org.camposmdev.model.card.attribute.CardSet;
import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.character.CharacterCard;
import org.camposmdev.util.FXUtil;

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
