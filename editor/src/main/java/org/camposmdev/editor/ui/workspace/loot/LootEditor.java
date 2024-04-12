package org.camposmdev.editor.ui.workspace.loot;

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
import org.camposmdev.model.card.loot.LootCard;
import org.camposmdev.util.FXUtil;
import org.camposmdev.util.FormController;

public class LootEditor extends BaseEditor {
    private final VBox root;
    private UI form;

    public LootEditor(CardType type) {
        var btCommit = new Button("Commit");
        btCommit.setOnAction(e -> commit());
        switch (type) {
            case BATTERIES -> form = FXUtil.loadUI("workspace/loot/BatteryForm.fxml");
            case BHEART -> form = FXUtil.loadUI("workspace/loot/BlackHeartForm.fxml");
            case BOMBS -> form = FXUtil.loadUI("workspace/loot/BombForm.fxml");
            case BUTTER -> form = FXUtil.loadUI("workspace/loot/ButterBeanForm.fxml");
            case DICE -> form = FXUtil.loadUI("workspace/loot/DiceShardForm.fxml");
            case KEYS -> form = FXUtil.loadUI("workspace/loot/KeyForm.fxml");
            case LSOUL -> form = FXUtil.loadUI("workspace/loot/LostSoulForm.fxml");
            case PILLS -> form = FXUtil.loadUI("workspace/loot/PillForm.fxml");
            case RUNES -> form = FXUtil.loadUI("workspace/loot/RuneForm.fxml");
            case SACK -> form = FXUtil.loadUI("workspace/loot/SackForm.fxml");
            case SHEART -> form = FXUtil.loadUI("workspace/loot/SoulHeartForm.fxml");
            case TRINKETS -> form = FXUtil.loadUI("workspace/loot/TrinketForm.fxml");
            case WILDCARD -> form = FXUtil.loadUI("workspace/loot/WildForm.fxml");
        }
        if (form != null) {
            root = new VBox(4, form.getRoot(), btCommit);
        } else {
            root = new VBox(4, new Label("Move on to the next card type."));
        }
    }

    @Override
    public Node getContent() {
        return root;
    }

    @Override
    public void commit() {
        try {
            var card = (LootCard) ((FormController<?>) form.getController()).submit();
            card.setId(super.id()).setImage(super.image());
            Model.instance().cards().add(card);
            NotificationBar.instance().push(card);
        } catch (Exception ex) {
            DialogFactory.instance().showErrorBox(ex);
        }
    }
}
