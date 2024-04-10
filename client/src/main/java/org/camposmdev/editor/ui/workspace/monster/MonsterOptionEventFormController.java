package org.camposmdev.editor.ui.workspace.monster;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.camposmdev.editor.ui.factory.DialogFactory;
import org.camposmdev.model.card.attribute.EntityTarget;
import org.camposmdev.model.card.attribute.monster.MonsterOptionEvent;
import org.camposmdev.util.FormController;

public class MonsterOptionEventFormController extends FormController<MonsterOptionEvent[]> {
    @FXML private ListView<MonsterOptionEvent> lv;
    @FXML private CheckBox discard;
    @FXML private TextField loot;
    @FXML private TextField damage;
    @FXML private ComboBox<EntityTarget> damageTo;
    @FXML private CheckBox guppyItem;
    @FXML private TextField stealCents;
    @FXML private TextField stealLoot;
    @FXML private TextField stealItems;
    @FXML private TextField stealSouls;

    @Override
    public void init() {
        damageTo.setValue(EntityTarget.UNDEFINED);
        damageTo.getItems().addAll(EntityTarget.values());
    }

    private MonsterOptionEvent build() {
        var event = new MonsterOptionEvent();
        event.setDiscard(discard.isSelected());
        event.setLoot(Byte.parseByte(loot.getText()));
        event.setDamage(Byte.parseByte(damage.getText()));
        event.setDamageTo(damageTo.getValue());
        event.setGuppyItem(guppyItem.isSelected());
        event.setStealCents(Byte.parseByte(stealCents.getText()));
        event.setStealLoot(Byte.parseByte(stealLoot.getText()));
        event.setStealItems(Byte.parseByte(stealItems.getText()));
        event.setStealSouls(Byte.parseByte(stealSouls.getText()));
        return event;
    }

    public void load(MonsterOptionEvent... options) {
        lv.getItems().setAll(options);
    }

    public void commit() {
        try {
            var event = build();
            lv.getItems().add(event);
        } catch (Exception ex) {
            DialogFactory.instance().showErrorBox(ex);
        }
    }

    @Override
    public MonsterOptionEvent[] submit() throws Exception {
        return lv.getItems().toArray(new MonsterOptionEvent[]{});
    }
}
