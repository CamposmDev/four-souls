package org.camposmdev.editor.ui.workspace.monster;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.camposmdev.editor.ui.factory.DialogFactory;
import org.camposmdev.model.card.attribute.CardSet;
import org.camposmdev.model.card.attribute.EntityTarget;
import org.camposmdev.model.card.attribute.RollEvent;
import org.camposmdev.model.card.monster.BadEventCard;
import org.camposmdev.util.FormController;

import java.util.LinkedList;
import java.util.List;

public class BEventFormController extends FormController<BadEventCard> {
    @FXML private ComboBox<CardSet> cardSet;
    @FXML private TextField ambush;
    @FXML private CheckBox ambushAlt;
    @FXML private CheckBox greed;
    @FXML private TextField damage;
    @FXML private ComboBox<EntityTarget> damageTo;
    @FXML private CheckBox bossRush;
    @FXML private TextField discardLoot;
    @FXML private CheckBox mothersShadow;
    @FXML private CheckBox overflow;
    @FXML private CheckBox endTurn;
    @FXML private CheckBox dontStarve;
    @FXML private CheckBox bloat;
    @FXML private CheckBox isGoldenIdol;
    @FXML private CheckBox isGrubFather;
    @FXML private CheckBox isNightmareTick;
    @FXML private CheckBox isQwop;
    @FXML private CheckBox isTrialByTrolly;
    @FXML private CheckBox isCorruptedData;

    private List<RollEvent> rollEvents;

    public BEventFormController() {
        rollEvents = new LinkedList<>();
    }

    @Override
    public void init() {
        cardSet.setValue(CardSet.UNDEFINED);
        cardSet.getItems().addAll(CardSet.values());
        damageTo.setValue(EntityTarget.UNDEFINED);
        damageTo.getItems().addAll(EntityTarget.values());
    }

    @Override
    public BadEventCard submit() throws Exception {
        var event = new BadEventCard();
        // Set values from controls
        event.setCardSet(cardSet.getValue());
        event.setAmbush(Byte.parseByte(ambush.getText()));
        event.setAmbushAlt(ambushAlt.isSelected());
        event.setGreed(greed.isSelected());
        event.setDamage(Byte.parseByte(damage.getText()));
        event.setDamageTo(damageTo.getValue());
        event.setBossRush(bossRush.isSelected());
        event.setDiscardLoot(Byte.parseByte(discardLoot.getText()));
        event.setMothersShadow(mothersShadow.isSelected());
        event.setOverflow(overflow.isSelected());
        event.setEndTurn(endTurn.isSelected());
        event.setDontStarve(dontStarve.isSelected());
        event.setBloat(bloat.isSelected());
        event.setGoldenIdol(isGoldenIdol.isSelected());
        event.setGrubFather(isGrubFather.isSelected());
        event.setNightmareTick(isNightmareTick.isSelected());
        event.setQwop(isQwop.isSelected());
        event.setTrialByTrolly(isTrialByTrolly.isSelected());
        event.setCorruptedData(isCorruptedData.isSelected());

        // Assuming rollEvents list is not null
        event.setRollEvents(rollEvents.toArray(new RollEvent[]{}));

        return event;
    }

    public void modRollEvents() {
        DialogFactory.instance().showRollEventModifierBox(rollEvents);
    }
}
