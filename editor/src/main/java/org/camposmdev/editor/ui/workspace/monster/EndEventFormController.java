package org.camposmdev.editor.ui.workspace.monster;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import org.camposmdev.model.card.attribute.monster.EndEvent;
import org.camposmdev.util.FXUtil;
import org.camposmdev.util.FormController;

public class EndEventFormController extends FormController<EndEvent> {
    /* Controls for End Event */
    @FXML private CheckBox ee_isEyeStabber;
    @FXML private CheckBox ee_moveMonsterToAnotherSlot;
    @FXML private TextField ee_loot;
    @FXML private TextField ee_discardLoot;
    @FXML private TextField ee_lootCents;
    @FXML private CheckBox ee_hasTinyHands;
    @FXML private CheckBox ee_deactivateItemsAndCharacter;
    @FXML private CheckBox ee_discardLootAndCentsEqualToSouls;
    @FXML private CheckBox ee_lastManStanding;
    @FXML private CheckBox ee_addCounter;
    @FXML private CheckBox ee_lastManStandingAlt;
    @FXML private TextField ee_putInMonsterDeck;
    @FXML private CheckBox ee_notAttackedCounter;

    @FXML
    public void initialize() {
        FXUtil.initNumberFields(ee_loot, ee_discardLoot, ee_lootCents, ee_putInMonsterDeck);
    }

    @Override
    public EndEvent submit() throws Exception {
        var event = new EndEvent();
        event.setEyeStabber(ee_isEyeStabber.isSelected());
        event.setMoveMonsterToAnotherSlot(ee_moveMonsterToAnotherSlot.isSelected());

        // Parse byte values directly
        event.setLoot(Byte.parseByte(ee_loot.getText()));
        event.setDiscardLoot(Byte.parseByte(ee_discardLoot.getText()));
        event.setLootCents(Byte.parseByte(ee_lootCents.getText()));
        event.setPutInMonsterDeck(Byte.parseByte(ee_putInMonsterDeck.getText()));

        event.setTinyHands(ee_hasTinyHands.isSelected());
        event.setDeactivateItemsAndCharacter(ee_deactivateItemsAndCharacter.isSelected());
        event.setDiscardLootAndCentsEqualToSouls(ee_discardLootAndCentsEqualToSouls.isSelected());
        event.setLastManStanding(ee_lastManStanding.isSelected());
        event.setAddCounter(ee_addCounter.isSelected());
        event.setLastManStandingAlt(ee_lastManStandingAlt.isSelected());
        event.setNotAttackedCounter(ee_notAttackedCounter.isSelected());
        return event;
    }
}
