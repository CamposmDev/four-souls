package org.camposmdev.editor.ui.workspace.eternal;

import com.almasb.fxgl.ui.UI;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.camposmdev.editor.model.Model;
import org.camposmdev.editor.ui.NotificationBar;
import org.camposmdev.editor.ui.factory.DialogFactory;
import org.camposmdev.editor.ui.workspace.BaseEditor;
import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.eternal.EternalCard;
import org.camposmdev.util.FXUtil;
import org.camposmdev.util.FormController;

public class EternalEditor extends BaseEditor {
    private final CardType cardType;
    private final VBox root;

    private UI form;

    public EternalEditor(CardType type) {
        cardType = type;
        var btSubmit = new Button("Submit");
        btSubmit.setOnAction(e -> commit());
        switch (cardType) {
            case PETERNAL -> form = FXUtil.loadUI("workspace/eternal/PassiveEternalForm.fxml");
            case AETERNAL -> form = FXUtil.loadUI("workspace/eternal/ActiveEternalForm.fxml");
            case PAIDETERNAL -> form = FXUtil.loadUI("workspace/eternal/PaidEternalForm.fxml");
            case SETERNAL -> form = FXUtil.loadUI("workspace/eternal/SoulEternalForm.fxml");
        }
        if (form != null) {
            root = new VBox(4, form.getRoot(), btSubmit);
        } else {
            root = new VBox(4, new Label("Move on to the next card type"));
        }
    }

    private void build() {
        try {
            /* build eternal base */
            var card = (EternalCard) ((FormController<?>) form.getController()).submit();
            card.setId(super.id());
            card.setImage(super.image());
            Model.instance().cards().add(card);
            var message = card.toString();
            NotificationBar.instance().push(message);
        } catch(Exception ex) {
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
