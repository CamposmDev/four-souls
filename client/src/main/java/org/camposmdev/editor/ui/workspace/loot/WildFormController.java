package org.camposmdev.editor.ui.workspace.loot;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.camposmdev.editor.ui.factory.DialogFactory;
import org.camposmdev.model.card.attribute.*;
import org.camposmdev.model.card.attribute.loot.LootOptionEvent;
import org.camposmdev.model.card.loot.WildCard;
import org.camposmdev.util.FormController;

import java.util.LinkedList;
import java.util.List;

public class WildFormController extends FormController<WildCard> {
    @FXML
    private CheckBox fool;
    @FXML
    private CheckBox changeDiceRollResult;
    @FXML
    private CheckBox highPriestess;
    @FXML
    private TextField modPlayerHitPoint;
    @FXML
    private TextField modPlayerDamage;
    @FXML
    private TextField modPlayerDiceRoll;
    @FXML
    private ComboBox<DeckType> peekDeck;
    @FXML
    private TextField peekDeckAmount;
    @FXML
    private CheckBox peekDeck1Top;
    @FXML
    private CheckBox peekDeckSort;
    @FXML
    private CheckBox peekDeckBottomOption;
    @FXML
    private TextField preventDamageToAny;
    @FXML
    private TextField preventNextDamageToAny;
    @FXML
    private CheckBox justice;
    @FXML
    private CheckBox attackAgain;
    @FXML
    private ComboBox<EntityTarget> kill;
    @FXML
    private ComboBox<EntityTarget> rewardTo;
    @FXML
    private CheckBox devilDealStealItem;
    @FXML
    private CheckBox sun;
    @FXML
    private CheckBox judgement;
    @FXML
    private CheckBox world;
    @FXML
    private CheckBox creditCard;
    @FXML
    private ComboBox<CardVersion> holy;
    @FXML
    private CheckBox twoOfDiamonds;
    @FXML
    private CheckBox joker;
    @FXML
    private ComboBox<CardVersion> copyActiveItem;
    @FXML
    private CheckBox disableOtherPlayers;
    @FXML
    private CheckBox aceOfDiamonds;
    @FXML
    private CheckBox emergencyContact;
    @FXML
    private CheckBox twoOfSpades;
    @FXML
    private CheckBox fiendFire;
    @FXML
    private CheckBox goldKey;
    @FXML
    private CheckBox dadsNote;
    @FXML
    private CheckBox magicMarker;
    @FXML
    private CheckBox bibleThump;
    @FXML
    private CheckBox blanks;
    @FXML
    private CheckBox cheepcheepcheep;
    @FXML
    private CheckBox chunkOfAmber;
    @FXML
    private CheckBox cow;
    @FXML
    private CheckBox greedButt;
    @FXML
    private CheckBox jester;
    @FXML
    private CheckBox murder;
    @FXML
    private CheckBox witch;

    private List<RollEvent> rollEvents;
    private Reward reward;
    private List<LootOptionEvent> options;

    @Override
    public void init() {
        rollEvents = new LinkedList<>();
        reward = null;
        options = new LinkedList<>();

        peekDeck.setValue(DeckType.UNDEFINED);
        peekDeck.getItems().addAll(DeckType.values());
        kill.setValue(EntityTarget.UNDEFINED);
        kill.getItems().addAll(EntityTarget.values());
        rewardTo.setValue(EntityTarget.UNDEFINED);
        rewardTo.getItems().addAll(EntityTarget.values());
        holy.setValue(CardVersion.UNDEFINED);
        holy.getItems().addAll(CardVersion.values());
        copyActiveItem.setValue(CardVersion.UNDEFINED);
        copyActiveItem.getItems().addAll(CardVersion.values());
    }
    public void modRollEvents() {
        DialogFactory.instance().showRollEventModifierBox(rollEvents);
    }

    public void modReward() {
        DialogFactory.instance().showRewardModifierBox(reward);
    }

    public void modOptions() {
        DialogFactory.instance().showLootOptionEventModifierBox(options);
    }

    @Override
    public WildCard submit() throws Exception {
        var card = new WildCard();
        card.setTheFool(fool.isSelected())
                .setChangeDiceRollResult(changeDiceRollResult.isSelected())
                .setHighPriestess(highPriestess.isSelected())
                .setModPlayerHitPoint(Byte.parseByte(modPlayerHitPoint.getText()))
                .setModPlayerDamage(Byte.parseByte(modPlayerDamage.getText()))
                .setModPlayerDiceRoll(Byte.parseByte(modPlayerDiceRoll.getText()))
                .setPeekDeck(peekDeck.getValue())
                .setPeekDeckAmount(Byte.parseByte(peekDeckAmount.getText()))
                .setPeekDeck1Top(peekDeck1Top.isSelected())
                .setPeekDeckSort(peekDeckSort.isSelected())
                .setPeekDeckBottomOption(peekDeckBottomOption.isSelected())
                .setPreventDamageToAny(Byte.parseByte(preventDamageToAny.getText()))
                .setPreventNextDamageToAny(Byte.parseByte(preventNextDamageToAny.getText()))
                .setJustice(justice.isSelected())
                .setRollEvent(rollEvents.toArray(new RollEvent[0]))
                .setAttackAgain(attackAgain.isSelected())
                .setKill(kill.getValue())
                .setReward(reward)
                .setRewardTo(rewardTo.getValue())
                .setOptions(options.toArray(new LootOptionEvent[9]))
                .setDevilDealStealItem(devilDealStealItem.isSelected())
                .setTheSun(sun.isSelected())
                .setJudgement(judgement.isSelected())
                .setTheWorld(world.isSelected())
                .setCreditCard(creditCard.isSelected())
                .setHolyVersion(holy.getValue())
                .setTwoOfDiamonds(twoOfDiamonds.isSelected())
                .setJoker(joker.isSelected())
                .setCopyActiveItemVersion(copyActiveItem.getValue())
                .setDisableOtherPlayers(disableOtherPlayers.isSelected())
                .setAceOfDiamonds(aceOfDiamonds.isSelected())
                .setEmergencyContact(emergencyContact.isSelected())
                .setTwoOfSpades(twoOfSpades.isSelected())
                .setFiendFire(fiendFire.isSelected())
                .setGoldKey(goldKey.isSelected())
                .setDadsNote(dadsNote.isSelected())
                .setMagicMarker(magicMarker.isSelected())
                .setBibleThump(bibleThump.isSelected())
                .setBlanks(blanks.isSelected())
                .setCheepcheepcheep(cheepcheepcheep.isSelected())
                .setChunkOfAmber(chunkOfAmber.isSelected())
                .setCowOnATrashFarm(cow.isSelected())
                .setGreedButt(greedButt.isSelected())
                .setJester(jester.isSelected())
                .setMurder(murder.isSelected())
                .setWitch(witch.isSelected());
        return card;
    }
}
