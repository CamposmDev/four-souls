package io.github.camposmdev.foursouls.app.editor.ui.workspace.eternal;

import com.almasb.fxgl.ui.UI;
import io.github.camposmdev.foursouls.app.editor.ui.factory.DialogFactory;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import io.github.camposmdev.foursouls.app.editor.model.Model;
import io.github.camposmdev.foursouls.app.editor.ui.NotificationBar;
import io.github.camposmdev.foursouls.app.editor.ui.workspace.BaseEditor;
import io.github.camposmdev.foursouls.core.card.attribute.CardType;
import io.github.camposmdev.foursouls.core.card.eternal.EternalCard;
import io.github.camposmdev.foursouls.core.ui.FXUtil;
import io.github.camposmdev.foursouls.core.ui.FormController;

public class EternalEditor extends BaseEditor {
    private final VBox root;

    private UI form;

    public EternalEditor(CardType type) {
        var btCommit = new Button("Commit");
        btCommit.setOnAction(e -> commit());
        switch (type) {
            case PETERNAL -> form = FXUtil.loadUI("workspace/eternal/PassiveEternalForm.fxml");
            case AETERNAL -> form = FXUtil.loadUI("workspace/eternal/ActiveEternalForm.fxml");
            case PAIDETERNAL -> form = FXUtil.loadUI("workspace/eternal/PaidEternalForm.fxml");
            case SETERNAL -> form = FXUtil.loadUI("workspace/eternal/SoulEternalForm.fxml");
        }
        if (form != null) {
            root = new VBox(4, form.getRoot(), btCommit);
        } else {
            root = new VBox(4, new Label("Move on to the next card type"));
        }
    }

    @Override
    public Node getContent() {
        return root;
    }

    @Override
    public void commit() {
        try {
            /* build eternal base */
            var card = (EternalCard) ((FormController<?>) form.getController()).submit();
            card.setId(super.id());
            Model.instance().appendCard(card);
            NotificationBar.instance().push(card);
        } catch(Exception ex) {
            DialogFactory.instance().showErrorBox(ex);
        }
    }
}
