package org.camposmdev.editor.ui.workspace.monster;

import com.almasb.fxgl.ui.UI;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import org.camposmdev.editor.ui.workspace.BaseEditor;
import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.util.FXUtil;

public class MonsterEditor extends BaseEditor {
    private final CardType cardType;
    private UI form;
    private VBox root;

    public MonsterEditor(CardType type) {
        cardType = type;
        var btSubmit = new Button("Submit");
        btSubmit.setOnAction(e -> commit());
        switch (cardType) {
            case GEVENT -> form = FXUtil.loadUI("workspace/monster/GEventForm.fxml");
            case BEVENT -> form = FXUtil.loadUI("workspace/monster/BEventForm.fxml");
            case CURSE -> form = FXUtil.loadUI("workspace/monster/CurseForm.fxml");
            default -> form = FXUtil.loadUI("workspace/monster/MonsterForm.fxml");
        }
        if (form != null) {
            root = new VBox(4, form.getRoot(), btSubmit);
        }
    }

    @Override
    public Node getContent() {
        return root;
    }

    @Override
    public void commit() {

    }
}
