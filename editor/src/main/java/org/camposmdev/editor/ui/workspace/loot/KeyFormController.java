package org.camposmdev.editor.ui.workspace.loot;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import org.camposmdev.editor.ui.factory.DialogFactory;
import org.camposmdev.model.card.attribute.CardSet;
import org.camposmdev.model.card.attribute.loot.LootOptionEvent;
import org.camposmdev.model.card.loot.KeyCard;
import org.camposmdev.util.FormController;

import java.util.LinkedList;
import java.util.List;

public class KeyFormController extends FormController<KeyCard> {
    @FXML
    ComboBox<CardSet> cbCardSet;
    @FXML
    CheckBox cbGoldKey;

    private List<LootOptionEvent> lootOptionEvents;

    @Override
    public void init() {
        lootOptionEvents = new LinkedList<>();
        cbCardSet.setValue(CardSet.UNDEFINED);
        cbCardSet.getItems().addAll(CardSet.values());
    }

    public void modOptions() {
        DialogFactory.instance().showLootOptionEventModifierBox(lootOptionEvents);
    }

    @Override
    public KeyCard submit() throws Exception {
        return (KeyCard) new KeyCard()
                .setGoldKey(cbGoldKey.isSelected())
                .setOptions(lootOptionEvents.toArray(new LootOptionEvent[]{}))
                .setCardSet(cbCardSet.getValue());
    }
}
