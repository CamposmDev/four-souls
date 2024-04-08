package org.camposmdev.editor.ui.workspace.eternal;

import com.almasb.fxgl.ui.UI;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
    private final CardType type;
    private final VBox root;

    private UI form;

    public EternalEditor(String type) {
        this.type = CardType.parse(type);
        var btSubmit = new Button("Submit");
        btSubmit.setOnAction(e -> commit());
        /* TODO - Implement eternal sub-editors */
        switch (this.type) {
            case PETERNAL -> form = FXUtil.loadUI("PassiveForm.fxml");
            case AETERNAL -> form = FXUtil.loadUI("ActiveForm.fxml");
            case PAIDETERNAL -> form = FXUtil.loadUI("PaidForm.fxml");
            case OETERNAL -> form = FXUtil.loadUI("OneTimeUseForm.fxml");
            case SETERNAL -> form = FXUtil.loadUI("SoulForm.fxml");
        }
        assert form != null;
        root = new VBox(8, form.getRoot(), btSubmit);
//        root.setAlignment(Pos.CENTER_LEFT);
    }

    private void build() {
        try {
            /* build eternal base */
            var card = (EternalCard) ((FormController<?>) form.getController()).submit();
            card.setId(super.id());
            card.setImage(super.image());
            Model.instance().cards().addEternal(card);
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
