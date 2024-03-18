package org.camposmdev.editor.ui.workspace.eternal;

import com.almasb.fxgl.ui.UI;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import org.camposmdev.editor.model.Model;
import org.camposmdev.editor.ui.NotificationBar;
import org.camposmdev.editor.ui.factory.DialogFactory;
import org.camposmdev.editor.ui.workspace.IEditor;
import org.camposmdev.model.card.CardType;
import org.camposmdev.model.card.EternalCard;
import org.camposmdev.util.FXUtil;
import org.camposmdev.util.FormController;

public class EternalEditor implements IEditor {
    private final CardType type;
    private String id, image;
    private final VBox root;

    private UI form;

    public EternalEditor(String type) {
        this.type = CardType.parse(type);
        var btSubmit = new Button("Submit");
        btSubmit.setOnAction(e -> build());
        /* TODO - Implement the other eternal editors */
        switch (this.type) {
            case PETERNAL -> form = FXUtil.loadUI("PassiveForm.fxml");
            case AETERNAL -> form = FXUtil.loadUI("ActiveForm.fxml");
            case PAIDETERNAL -> form = FXUtil.loadUI("PaidForm.fxml");
            case OETERNAL -> form = FXUtil.loadUI("OneTimeUseForm.fxml");
            case SETERNAL -> form = FXUtil.loadUI("SoulForm.fxml");
        }
        assert form != null;
        root = new VBox(8, form.getRoot(), btSubmit);
        root.setAlignment(Pos.CENTER);
    }

    private void build() {
        try {
            /* build eternal base */
            var obj = ((FormController) form.getController()).submit();
            var card = (EternalCard) obj;
            card.setId(id);
            card.setImage(image);
            Model.instance().cards().addEternal(card);
            var message = card.toJSON().toString();
//            switch (type) {
//                case PETERNAL -> message = ((PassiveEternalCard) card).toJSON().encode();
//                case AETERNAL -> message = "";
//                case PAIDETERNAL -> message = "";
//                case OETERNAL -> message = "";
//                case SETERNAL -> message = "";
//            }
            NotificationBar.instance().push(message);
        } catch(Exception ex) {
            DialogFactory.instance().showErrorBox(ex);
        }
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public Node getContent() {
        return root;
    }
}
