package io.github.camposmdev.foursouls.app.editor.ui.workspace.loot;

import io.github.camposmdev.foursouls.app.editor.ui.RewardBox;
import io.github.camposmdev.foursouls.app.editor.ui.factory.DialogFactory;
import io.github.camposmdev.foursouls.model.card.attribute.EntityTarget;
import io.github.camposmdev.foursouls.model.card.attribute.loot.LootOption;
import io.github.camposmdev.foursouls.model.fx.FXUtil;
import io.github.camposmdev.foursouls.model.fx.FormController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.List;

public class LootOptionFormController extends FormController<List<LootOption>> {
    @FXML private ListView<LootOption> lv;
    @FXML private TextField damage;
    @FXML private ComboBox<EntityTarget> damageTo;
    @FXML private Button rewardButton;
    @FXML private CheckBox mayAttackAgain;
    @FXML private CheckBox summonMonster;
    @FXML private CheckBox destroyCurse;
    @FXML private TextField preventDamage;
    @FXML private ComboBox<EntityTarget> preventDamageTo;
    @FXML private GridPane root;
    private RewardBox rewardBox;
    @FXML
    public void init() {
        rewardBox = new RewardBox();
        root.add(rewardBox.getContent(), 1, 2);
        lv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        lv.setTooltip(new Tooltip("BACKSPACE or DEL to delete item"));
        FXUtil.initNumberFields(damage, preventDamage);

        damageTo.setValue(EntityTarget.UNDEFINED);
        damageTo.getItems().addAll(EntityTarget.values());
        preventDamageTo.setValue(EntityTarget.UNDEFINED);
        preventDamageTo.getItems().addAll(EntityTarget.values());
    }

    public void load(List<LootOption> lst) {
        lv.getItems().clear();
        lv.getItems().addAll(lst);
    }

    @FXML
    public void commit() {
        try {
            var option = new LootOption()
                .setDamage(Byte.parseByte(damage.getText()))
                .setDamageTo(EntityTarget.UNDEFINED)
                .setReward(rewardBox.submit())
                .setMayAttackAgain(mayAttackAgain.isSelected())
                .setSummonMonster(summonMonster.isSelected())
                .setDestroyCurse(destroyCurse.isSelected())
                .setPreventDamage(Byte.parseByte(preventDamage.getText()))
                .setPreventDamageTo(preventDamageTo.getValue());
            lv.getItems().add(option);
        } catch (Exception ex) {
            DialogFactory.instance().showErrorBox(ex);
        }
    }

    @Override
    public List<LootOption> submit() throws Exception {
        return lv.getItems().stream().toList();
    }
}
