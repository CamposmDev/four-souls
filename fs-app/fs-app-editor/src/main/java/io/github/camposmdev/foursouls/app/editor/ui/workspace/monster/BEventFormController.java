package io.github.camposmdev.foursouls.app.editor.ui.workspace.monster;

import io.github.camposmdev.foursouls.app.editor.ui.factory.DialogFactory;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import io.github.camposmdev.foursouls.model.card.attribute.CardSet;
import io.github.camposmdev.foursouls.model.card.attribute.EntityTarget;
import io.github.camposmdev.foursouls.model.card.attribute.RollEvent;
import io.github.camposmdev.foursouls.model.card.monster.BadEventCard;
import io.github.camposmdev.foursouls.model.ui.FXUtil;
import io.github.camposmdev.foursouls.model.ui.FormController;

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
        FXUtil.initNumberFields(ambush, damage, discardLoot);
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
        event.setAmbush(Byte.parseByte(ambush.getText()))
            .setAmbushAlt(ambushAlt.isSelected())
            .setGreed(greed.isSelected())
            .setDamage(Byte.parseByte(damage.getText()))
            .setDamageTo(damageTo.getValue())
            .setBossRush(bossRush.isSelected())
            .setDiscardLoot(Byte.parseByte(discardLoot.getText()))
            .setMothersShadow(mothersShadow.isSelected())
            .setOverflow(overflow.isSelected())
            .setEndTurn(endTurn.isSelected())
            .setDontStarve(dontStarve.isSelected())
            .setBloat(bloat.isSelected())
            .setGoldenIdol(isGoldenIdol.isSelected())
            .setGrubFather(isGrubFather.isSelected())
            .setNightmareTick(isNightmareTick.isSelected())
            .setQwop(isQwop.isSelected())
            .setTrialByTrolly(isTrialByTrolly.isSelected())
            .setCorruptedData(isCorruptedData.isSelected())
            .setRollEvents(rollEvents);
        /* create new roll events for efficient data entry */
        rollEvents = new LinkedList<>();
        return event;
    }

    public void modRollEvents() {
        DialogFactory.instance().showRollEventModifierBox(rollEvents);
    }
}
