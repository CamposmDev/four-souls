package org.camposmdev.editor.ui.workspace.treasure;

import com.almasb.fxgl.ui.UI;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import org.camposmdev.editor.model.Model;
import org.camposmdev.editor.ui.NotificationBar;
import org.camposmdev.editor.ui.factory.DialogFactory;
import org.camposmdev.editor.ui.workspace.BaseEditor;
import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.treasure.TreasureCard;
import org.camposmdev.util.FXUtil;
import org.camposmdev.util.FormController;

public class TreasureEditor extends BaseEditor {
    private final VBox root;
    private UI form;

    public TreasureEditor(CardType type) {
        var btCommit = new Button("Commit");
        btCommit.setOnAction(e -> commit());
        switch (type) {
            case PTREASURE -> form = FXUtil.loadUI("workspace/treasure/PassiveTreasureForm.fxml");
            case ATREASURE -> form = FXUtil.loadUI("workspace/treasure/ActiveTreasureForm.fxml");
            case PAIDTREASURE -> form = FXUtil.loadUI("workspace/treasure/PaidTreasureForm.fxml");
            case OTREASURE -> form = FXUtil.loadUI("workspace/treasure/OneUseTreasureForm.fxml");
            case STREASURE -> form = FXUtil.loadUI("workspace/treasure/SoulTreasureForm.fxml");
        }
        assert form != null;
        root = new VBox(4, form.getRoot(), btCommit);
    }

    @Override
    public Node getContent() {
        return root;
    }

    @Override
    public void commit() {
        try {
            var card = (TreasureCard) ((FormController<?>) form.getController()).submit();
            card.setId(super.id()).setImage(super.image());
            Model.instance().addCard(card);
            NotificationBar.instance().push(card);
        } catch (Exception ex) {
            DialogFactory.instance().showErrorBox(ex);
        }
    }
}
