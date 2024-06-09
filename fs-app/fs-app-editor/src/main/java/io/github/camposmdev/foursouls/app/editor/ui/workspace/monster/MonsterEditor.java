package io.github.camposmdev.foursouls.app.editor.ui.workspace.monster;

import com.almasb.fxgl.ui.UI;
import io.github.camposmdev.foursouls.app.editor.model.Model;
import io.github.camposmdev.foursouls.app.editor.ui.NotificationBar;
import io.github.camposmdev.foursouls.app.editor.ui.factory.DialogFactory;
import io.github.camposmdev.foursouls.app.editor.ui.workspace.BaseEditor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import io.github.camposmdev.foursouls.core.card.attribute.CardType;
import io.github.camposmdev.foursouls.core.card.monster.BaseMonsterCard;
import io.github.camposmdev.foursouls.core.ui.FXUtil;
import io.github.camposmdev.foursouls.core.ui.FormController;

public class MonsterEditor extends BaseEditor {
    private final UI form;
    private VBox root;
    private final CardType cardType;

    public MonsterEditor(CardType type) {
        cardType = type;
        var btCommit = new Button("Commit");
        btCommit.setOnAction(e -> commit());
        switch (type) {
            case GEVENT -> form = FXUtil.loadUI("workspace/monster/GEventForm.fxml");
            case BEVENT -> form = FXUtil.loadUI("workspace/monster/BEventForm.fxml");
            case CURSE -> form = FXUtil.loadUI("workspace/monster/CurseForm.fxml");
            default -> form = FXUtil.loadUI("workspace/monster/MonsterForm.fxml");
        }
        if (form != null) {
            root = new VBox(4, form.getRoot(), btCommit);
        }
    }

    @Override
    public Node getContent() {
        return root;
    }

    @Override
    public void commit() {
        try {
            var card = (BaseMonsterCard) ((FormController<?>) form.getController()).submit();
            card.setId(super.id());
            card.setCardType(cardType);
            Model.instance().appendCard(card);
            NotificationBar.instance().push(card);
        } catch (Exception ex) {
            DialogFactory.instance().showErrorBox(ex);
        }
    }
}
