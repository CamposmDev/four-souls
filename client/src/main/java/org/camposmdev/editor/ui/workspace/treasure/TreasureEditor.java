package org.camposmdev.editor.ui.workspace.treasure;

import com.almasb.fxgl.ui.UI;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import org.camposmdev.editor.ui.factory.DialogFactory;
import org.camposmdev.editor.ui.workspace.BaseEditor;
import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.treasure.TreasureCard;
import org.camposmdev.util.FXUtil;
import org.camposmdev.util.FormController;

public class TreasureEditor extends BaseEditor {
    private VBox root;
    private final CardType cardType;
    private UI form;

    public TreasureEditor(CardType type) {
        cardType = type;
        var btSubmit = new Button("Submit");
        btSubmit.setOnAction(e -> commit());
        switch (this.cardType) {
            case PTREASURE -> form = FXUtil.loadUI("workspace/treasure/PassiveTreasureForm.fxml");
            case ATREASURE -> form = FXUtil.loadUI("workspace/treasure/ActiveTreasureForm.fxml");
            case PAIDTREASURE -> form = FXUtil.loadUI("workspace/treasure/PaidTreasureForm.fxml");
            case OTREASURE -> form = FXUtil.loadUI("workspace/treasure/OneTimeUseTreasureForm.fxml");
            case STREASURE -> form = FXUtil.loadUI("workspace/treasure/SoulTreasureForm.fxml");
        }
        assert form != null;
        root = new VBox(8, form.getRoot(), btSubmit);
    }

    @Override
    public Node getContent() {
        return root;
    }

    @Override
    public void commit() {
        try {
            var card = (TreasureCard) ((FormController<?>) form.getController()).submit();
            card.setId(super.id());
            card.setImage(super.image());
            /* TODO - add card to treasure deck */
        } catch (Exception ex) {
            DialogFactory.instance().showErrorBox(ex);
        }
    }
}
