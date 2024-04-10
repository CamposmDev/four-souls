package org.camposmdev.editor.ui.workspace.monster;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.camposmdev.model.card.attribute.EntityTarget;
import org.camposmdev.model.card.attribute.monster.StartEvent;
import org.camposmdev.util.FormController;

public class StartEventFormController extends FormController<StartEvent> {
    /* Controls for Start Event */
    @FXML private CheckBox se_rerollShopItem, se_kekeIsYou, se_isZombieJesus, se_isMomsHandAlt, se_isEvis;
    @FXML private TextField se_damage;
    @FXML private ComboBox<EntityTarget> se_damageTo;
    @FXML private CheckBox se_rechargeOneTime, se_isGurdyAlt, se_counterDistributeDamage, se_isTheAdversary;

    @Override
    public void init() {
        se_damageTo.setValue(EntityTarget.UNDEFINED);
        se_damageTo.getItems().addAll(EntityTarget.values());
    }

    @Override
    public StartEvent submit() throws Exception {
        return new StartEvent()
                .setRerollShopItem(se_rerollShopItem.isSelected())
                .setKekeIsYou(se_kekeIsYou.isSelected())
                .setZombieJesus(se_isZombieJesus.isSelected())
                .setMomsHandAlt(se_isMomsHandAlt.isSelected())
                .setEvis(se_isEvis.isSelected())
                .setDamage(Byte.parseByte(se_damage.getText()))
                .setDamageTo(se_damageTo.getValue())
                .setRechargeOneItem(se_rechargeOneTime.isSelected())
                .setGurdyAlt(se_isGurdyAlt.isSelected())
                .setCounterDistributeDamage(se_counterDistributeDamage.isSelected())
                .setTheAdversary(se_isTheAdversary.isSelected());
    }
}
