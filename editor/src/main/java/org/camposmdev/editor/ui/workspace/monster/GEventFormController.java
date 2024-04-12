package org.camposmdev.editor.ui.workspace.monster;

import org.camposmdev.editor.ui.factory.DialogFactory;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.camposmdev.model.card.attribute.CardSet;
import org.camposmdev.model.card.attribute.DeckType;
import org.camposmdev.model.card.attribute.EntityTarget;
import org.camposmdev.model.card.attribute.RollEvent;
import org.camposmdev.model.card.attribute.monster.MonsterOptionEvent;
import org.camposmdev.model.card.monster.GoodEventCard;
import org.camposmdev.util.FXUtil;
import org.camposmdev.util.FormController;

import java.util.LinkedList;
import java.util.List;

public class GEventFormController extends FormController<GoodEventCard> {
    @FXML private ComboBox<CardSet> cardSet;
    @FXML private TextField ambush;
    @FXML private ComboBox<DeckType> peekDeck;
    @FXML private TextField peekDeckAmount;
    @FXML private CheckBox peekDeckSort;
    @FXML private TextField loot;
    @FXML private TextField expandShop;
    @FXML private CheckBox attackAgain;
    @FXML private CheckBox weNeedToGoDeeper;
    @FXML private TextField expandMonster;
    @FXML private CheckBox weNeedToGoDeeperNonEvent;
    @FXML private TextField bossRush;
    @FXML private CheckBox doubleTreasure;
    @FXML private CheckBox rerollItems;
    @FXML private TextField damage;
    @FXML private ComboBox<EntityTarget> damageTo;
    @FXML private TextField putInDeck;
    @FXML private CheckBox lustForBlood;
    @FXML private CheckBox isGoldenIdol;
    @FXML private CheckBox isNightmareTick;
    @FXML private CheckBox isQwop;
    @FXML private TextField expandRoom;
    @FXML private CheckBox isTVStatic;

    private final List<RollEvent> rollEvents;
    private final List<MonsterOptionEvent> options;

    public GEventFormController() {
        rollEvents = new LinkedList<>();
        options = new LinkedList<>();
    }

    @Override
    public void init() {
        FXUtil.initNumberFields(ambush, peekDeckAmount, loot, expandShop, expandMonster, bossRush, damage, expandRoom, putInDeck);
        cardSet.setValue(CardSet.UNDEFINED);
        cardSet.getItems().addAll(CardSet.values());
        peekDeck.setValue(DeckType.UNDEFINED);
        peekDeck.getItems().addAll(DeckType.values());
        damageTo.setValue(EntityTarget.UNDEFINED);
        damageTo.getItems().addAll(EntityTarget.values());
    }

    @Override
    public GoodEventCard submit() throws Exception {
        var card = new GoodEventCard();
        card.setCardSet(cardSet.getValue());
        card.setAmbush(Byte.parseByte(ambush.getText()));
        card.setRollEvents(rollEvents.toArray(new RollEvent[]{}));
        card.setOptionEvents(options.toArray(new MonsterOptionEvent[]{}));
        card.setPeekDeck(peekDeck.getValue());
        card.setPeekDeckAmount(Byte.parseByte(peekDeckAmount.getText()));
        card.setPeekDeckSort(peekDeckSort.isSelected());
        card.setLoot(Byte.parseByte(loot.getText()));
        card.setExpandShop(Byte.parseByte(expandShop.getText()));
        card.setAttackAgain(attackAgain.isSelected());
        card.setWeNeedToGoDeeper(weNeedToGoDeeper.isSelected());
        card.setExpandMonster(Byte.parseByte(expandMonster.getText()));
        card.setWeNeedToGoDeeperNonEvent(weNeedToGoDeeperNonEvent.isSelected());
        card.setBossRush(Byte.parseByte(bossRush.getText()));
        card.setDoubleTreasure(doubleTreasure.isSelected());
        card.setRerollItems(rerollItems.isSelected());
        card.setDamage(Byte.parseByte(damage.getText()));
        card.setDamageTo(damageTo.getValue());
        card.setPutInDeck(Byte.parseByte(putInDeck.getText()));
        card.setLustForBlood(lustForBlood.isSelected());
        card.setGoldenIdol(isGoldenIdol.isSelected());
        card.setNightmareTick(isNightmareTick.isSelected());
        card.setQwop(isQwop.isSelected());
        card.setExpandRoom(Byte.parseByte(expandRoom.getText()));
        card.setTvStatic(isTVStatic.isSelected());
        return card;
    }

    public void modRollEvents() {
        DialogFactory.instance().showRollEventModifierBox(rollEvents);
    }

    public void modOptions() {
        DialogFactory.instance().showMonsterOptionEventModifierBox(options);
    }
}
