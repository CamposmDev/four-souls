package org.camposmdev.editor.ui.workspace.monster;

import org.camposmdev.editor.ui.factory.DialogFactory;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.camposmdev.model.card.attribute.*;
import org.camposmdev.model.card.attribute.monster.*;
import org.camposmdev.model.card.monster.MonsterCard;
import org.camposmdev.util.FXUtil;
import org.camposmdev.util.FormController;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MonsterFormController extends FormController<MonsterCard> {
    /* Controls for Statistics */
    @FXML private ComboBox<CardSet> stat_cbCardSet;
    @FXML private TextField stat_tfHP, stat_tfDC, stat_tfATK, stat_tfSoul;
    @FXML private ComboBox<GameType> stat_game;
    @FXML private ComboBox<ChallengeType> stat_challenge;
    /* Controls for Passive Event */
    @FXML private TextField pe_modMonstersAttackRoll, pe_modMonstersDamage, pe_modMonstersHitPoints;
    @FXML private CheckBox pe_attackable, pe_deathLink, pe_isShopkeeper, pe_isIndomitable, pe_isImposter, pe_isDickKnot;
    @FXML private TextField pe_activeItemDamage, pe_deathLinkDamage;
    @FXML private ComboBox<EntityTarget> pe_deathLinkDamageTo;
    @FXML private CheckBox pe_isCursedLilHaunt;
    @FXML private TextField pe_deathLinkExpandMonster, pe_deathLinkOptionalCents;
    @FXML private CheckBox pe_deathLinkAttackAgain, pe_endGame, pe_isDogma, pe_isUltraGreed;
    @FXML private TextField pe_attackablePlayers;
    @FXML private CheckBox pe_deathLinkCounter;
    @FXML private TextField pe_deathLinkCounterLimit;
    @FXML private CheckBox pe_forceAttackDeck;
    @FXML private CheckBox pe_playerDeathCounter;
    @FXML private CheckBox pe_isRadiance;
    @FXML private CheckBox pe_isTheCollector;
    @FXML private CheckBox pe_isTimerEater;
    @FXML private CheckBox pe_isDeliriumAlt;
    @FXML private CheckBox pe_isBallos;
    @FXML private CheckBox pe_isItLivesAlt;
    @FXML private CheckBox pe_playerDeathLinkDamage;
    @FXML private TextField pe_expandMonsterOnAttack;
    @FXML private CheckBox pe_isPride;
    @FXML private CheckBox pe_nonActivePlayerDiscardLoot;
    @FXML private CheckBox pe_disableNonActivePlayerLootAndActiveItems;
    @FXML private CheckBox pe_forceAttack;
    @FXML private CheckBox pe_isSistersVis;
    @FXML private CheckBox pe_cancelAttackOn;
    @FXML private TextField pe_damageLinkModMonstersAttackRoll;
    @FXML private CheckBox pe_isGrandParent;
    @FXML private CheckBox pe_expandMonsterOnPlay;
    @FXML private CheckBox pe_forceAttackAgain;
    @FXML private CheckBox pe_isTheButcher;
    @FXML private CheckBox pe_isMelquiades;
    @FXML private CheckBox pe_counterModsOtherMonstersAttackRoll;
    @FXML private CheckBox pe_activeOnCovered;
    @FXML private CheckBox pe_preattackGoldCounter;
    @FXML private CheckBox pe_deliriumPreventDamage;
    @FXML private CheckBox pe_deliriumRandomDeathPenaltyItem;
    @FXML private TextField pe_attackRandomOnPlay;
    @FXML private CheckBox pe_deathPlayerLink2Counters;
    @FXML private CheckBox pe_counterLinkToDamage;
    @FXML private TextField pe_forceAttackOnCounter;
    /* Controls for Attack Event */
    @FXML private TextField ae_loseCents;
    @FXML private ComboBox<EntityTarget> ae_loseCentsTarget;
    @FXML private TextField ae_healMonster;
    @FXML private TextField ae_modNextAttackRoll;
    @FXML private TextField ae_damage;
    @FXML private ComboBox<EntityTarget> ae_damageTo;
    @FXML private CheckBox ae_endTurn;
    @FXML private CheckBox ae_isBigBony;
    @FXML private CheckBox ae_isHenry;
    @FXML private TextField ae_discardLoot;
    @FXML private CheckBox ae_cancelDamage;
    @FXML private CheckBox ae_cancelAttack;
    @FXML private CheckBox ae_killCounter;
    @FXML private CheckBox ae_voteRightOrLeft;
    @FXML private CheckBox ae_roll2Dice;
    /* Controls for Damage Event */
    @FXML private TextField dmge_modPlayersNextAttackRoll, dmge_damage, dmge_modAttackRoll, dmge_modDamage;
    @FXML private ComboBox<EntityTarget> dmge_damageTo;
    @FXML private CheckBox dmge_preventDamage, dmge_isTheScourge, dmge_damageCounter, dmge_pooCounter, dmge_noteAttackRolls, dmge_flipNextAttackRoll, dmge_spiderCounter;
    /* Controls for Death Event */
    @FXML private CheckBox de_attackAgain;
    @FXML private TextField de_damage;
    @FXML private ComboBox<EntityTarget> de_damageTo;
    @FXML private TextField de_discardLoot;
    @FXML private ComboBox<EntityTarget> de_discardLootTo;
    @FXML private TextField de_loseCents;
    @FXML private ComboBox<EntityTarget> de_loseCentsTo;
    @FXML private TextField de_expandShop;
    @FXML private CheckBox de_stealPlayerItem;
    @FXML private CheckBox de_peekPlayerHand;
    @FXML private TextField de_expandMonster;
    @FXML private CheckBox de_forceAttackAgain;
    @FXML private CheckBox de_rechargeAllItems;
    @FXML private TextField de_discardSoul;
    @FXML private ComboBox<DeckType> de_peekDeck;
    @FXML private TextField de_peekDeckAmount;
    @FXML private CheckBox de_peekDeckSort;
    @FXML private ComboBox<EntityTarget> de_kill;
    @FXML private CheckBox de_cancelIfDamageDealt;
    @FXML private CheckBox de_forceAttackAgainOnSameSlot;
    @FXML private TextField de_expandAny;
    @FXML private CheckBox de_isTapeWorm;
    @FXML private CheckBox de_greedlingRush;
    @FXML private ComboBox<EntityTarget> de_rewardTo;
    @FXML private CheckBox de_giveHeartCounter, de_isHeartItem, de_summonHarbingers, de_isBeastAlt, de_deathLink,
            de_skipPlayersNextTurn, de_isPeep, de_isSloth, de_attackMonsterDeck, de_secondChance, de_giftSoul, de_isClog,
            de_summonCurse, de_isBalrog, de_discardHand, de_isPestilenceAlt, de_stealItemWithGoldCounter,
            de_skipChosenPlayersTurn, de_spiderCounterDistributeDamage, de_butItsReallyTheBloat;
    @FXML private TextField de_putInDeck, de_stealSoul, de_secondChanceAttributes;
    @FXML private GridPane se_form;
    @FXML protected FormController<StartEvent> se_formController;
    @FXML private GridPane ee_form;
    @FXML protected FormController<EndEvent> ee_formController;
    private Reward stat_reward;
    private AttributeModifier pe_modifier;
    private List<RollListener> pe_rollListeners;
    private List<RollEvent> pe_preattackRollEvents;
    private List<RollEvent> ae_rollEvents;
    private List<RollEvent> dmge_rollEvents;
    private List<RollEvent> dmge_damageRollEvents;
    private List<RollEvent> de_deathRollEvents;
    private List<RollEvent> de_killRollEvents;
    private Reward de_reward;

    public MonsterFormController() {
        stat_reward = null;
        pe_modifier = null;
        pe_rollListeners = new LinkedList<>();
        pe_preattackRollEvents = new LinkedList<>();
        ae_rollEvents = new LinkedList<>();
        dmge_rollEvents = new LinkedList<>();
        dmge_damageRollEvents = new LinkedList<>();
        de_deathRollEvents = new LinkedList<>();
        de_killRollEvents = new LinkedList<>();
    }

    @Override
    public void init() {
        FXUtil.initNumberFields(stat_tfHP, stat_tfDC, stat_tfATK, stat_tfSoul, pe_modMonstersAttackRoll,
                pe_modMonstersDamage, pe_modMonstersHitPoints, pe_activeItemDamage,
                pe_deathLinkDamage, pe_deathLinkExpandMonster, pe_deathLinkOptionalCents,
                pe_attackablePlayers, pe_deathLinkCounterLimit, pe_damageLinkModMonstersAttackRoll,
                pe_expandMonsterOnAttack, pe_attackRandomOnPlay, pe_forceAttackOnCounter, ae_loseCents, ae_healMonster,
                ae_modNextAttackRoll, ae_damage, ae_discardLoot, dmge_modPlayersNextAttackRoll,
                dmge_damage, dmge_modAttackRoll, dmge_modDamage, de_damage, de_discardLoot,
                de_loseCents, de_expandMonster, de_expandShop, de_discardSoul, de_peekDeckAmount, de_expandAny,
                de_putInDeck, de_stealSoul);
        stat_cbCardSet.setValue(CardSet.UNDEFINED);
        stat_cbCardSet.getItems().addAll(CardSet.values());
        stat_game.setValue(GameType.UNDEFINED);
        stat_game.getItems().addAll(GameType.values());
        stat_challenge.setValue(ChallengeType.UNDEFINED);
        stat_challenge.getItems().addAll(ChallengeType.values());
        pe_deathLinkDamageTo.setValue(EntityTarget.UNDEFINED);
        pe_deathLinkDamageTo.getItems().addAll(EntityTarget.values());
        ae_loseCentsTarget.setValue(EntityTarget.UNDEFINED);
        ae_loseCentsTarget.getItems().addAll(EntityTarget.values());
        ae_damageTo.setValue(EntityTarget.UNDEFINED);
        ae_damageTo.getItems().addAll(EntityTarget.values());
        dmge_damageTo.setValue(EntityTarget.UNDEFINED);
        dmge_damageTo.getItems().addAll(EntityTarget.values());
        de_damageTo.setValue(EntityTarget.UNDEFINED);
        de_damageTo.getItems().addAll(EntityTarget.values());
        de_discardLootTo.setValue(EntityTarget.UNDEFINED);
        de_discardLootTo.getItems().addAll(EntityTarget.values());
        de_loseCentsTo.setValue(EntityTarget.UNDEFINED);
        de_loseCentsTo.getItems().addAll(EntityTarget.values());
        de_peekDeck.setValue(DeckType.UNDEFINED);
        de_peekDeck.getItems().addAll(DeckType.values());
        de_kill.setValue(EntityTarget.UNDEFINED);
        de_kill.getItems().addAll(EntityTarget.values());
        de_rewardTo.setValue(EntityTarget.UNDEFINED);
        de_rewardTo.getItems().addAll(EntityTarget.values());
    }

    @Override
    public MonsterCard submit() throws Exception {
        var card = new MonsterCard();
        card.setCardSet(stat_cbCardSet.getValue());
        card.setHitPoints(Byte.parseByte(stat_tfHP.getText()));
        card.setRoll(Byte.parseByte(stat_tfDC.getText()));
        card.setDamage(Byte.parseByte(stat_tfATK.getText()));
        card.setReward(stat_reward);
        card.setSoul(Byte.parseByte(stat_tfSoul.getText()));
        card.setStartEvent(se_formController.submit());
        card.setPassiveEvent(buildPassiveEvent());
        card.setAttackEvent(buildAttackEvent());
        card.setDamageEvent(buildDamageEvent());
        card.setEndEvent(ee_formController.submit());
        card.setDeathEvent(buildDeathEvent());
        card.setGame(stat_game.getValue());
        card.setChallenge(stat_challenge.getValue());
        /* create new instance fields for efficient data entry */
        stat_reward = null;
        pe_modifier = new AttributeModifier();
        pe_rollListeners = new LinkedList<>();
        pe_preattackRollEvents = new LinkedList<>();
        ae_rollEvents = new LinkedList<>();
        dmge_rollEvents = new LinkedList<>();
        dmge_damageRollEvents = new LinkedList<>();
        de_deathRollEvents = new LinkedList<>();
        de_killRollEvents = new LinkedList<>();
        de_reward = null;
        return card;
    }

    private PassiveEvent buildPassiveEvent() {
        var event = new PassiveEvent();
        // Set values from text fields
        event.setModMonstersAttackRoll(Byte.parseByte(pe_modMonstersAttackRoll.getText()));
        event.setModMonstersDamage(Byte.parseByte(pe_modMonstersDamage.getText()));
        event.setModMonstersHitPoints(Byte.parseByte(pe_modMonstersHitPoints.getText()));
        event.setActiveItemDamage(Byte.parseByte(pe_activeItemDamage.getText()));
        event.setDeathLinkDamage(Byte.parseByte(pe_deathLinkDamage.getText()));
        event.setDeathLinkExpandMonster(Byte.parseByte(pe_deathLinkExpandMonster.getText()));
        event.setDeathLinkOptionalCents(Byte.parseByte(pe_deathLinkOptionalCents.getText()));
        event.setAttackablePlayers(Byte.parseByte(pe_attackablePlayers.getText()));
        event.setDeathLinkCounterLimit(Byte.parseByte(pe_deathLinkCounterLimit.getText()));
        event.setExpandMonsterOnAttack(Byte.parseByte(pe_expandMonsterOnAttack.getText()));
        event.setDamageLinkModMonstersAttackRoll(Byte.parseByte(pe_damageLinkModMonstersAttackRoll.getText()));
        event.setAttackRandomOnPlay(Byte.parseByte(pe_attackRandomOnPlay.getText()));
        event.setForceAttackOnCounter(Byte.parseByte(pe_forceAttackOnCounter.getText()));
        // Set values from checkboxes
        event.setAttackable(pe_attackable.isSelected());
        event.setDeathLink(pe_deathLink.isSelected());
        event.setShopkeeper(pe_isShopkeeper.isSelected());
        event.setIndomitable(pe_isIndomitable.isSelected());
        event.setImposter(pe_isImposter.isSelected());
        event.setDickKnot(pe_isDickKnot.isSelected());
        event.setCursedLilHaunt(pe_isCursedLilHaunt.isSelected());
        event.setDeathLinkAttackAgain(pe_deathLinkAttackAgain.isSelected());
        event.setEndGame(pe_endGame.isSelected());
        event.setDogma(pe_isDogma.isSelected());
        event.setUltraGreed(pe_isUltraGreed.isSelected());
        event.setDeathLinkCounter(pe_deathLinkCounter.isSelected());
        event.setForceAttackDeck(pe_forceAttackDeck.isSelected());
        event.setPlayerDeathCounter(pe_playerDeathCounter.isSelected());
        event.setRadiance(pe_isRadiance.isSelected());
        event.setTheCollector(pe_isTheCollector.isSelected());
        event.setTimeEater(pe_isTimerEater.isSelected());
        event.setDeliriumAlt(pe_isDeliriumAlt.isSelected());
        event.setBallos(pe_isBallos.isSelected());
        event.setItLivesAlt(pe_isItLivesAlt.isSelected());
        event.setPlayerDeathLinkDamage(pe_playerDeathLinkDamage.isSelected());
        event.setPride(pe_isPride.isSelected());
        event.setNonActivePlayerDiscardLoot(pe_nonActivePlayerDiscardLoot.isSelected());
        event.setDisableNonActivePlayerLootAndActiveItems(pe_disableNonActivePlayerLootAndActiveItems.isSelected());
        event.setForceAttack(pe_forceAttack.isSelected());
        event.setSistersVis(pe_isSistersVis.isSelected());
        event.setCancelAttackOn(pe_cancelAttackOn.isSelected());
        event.setGrandParent(pe_isGrandParent.isSelected());
        event.setExpandMonsterOnPlay(pe_expandMonsterOnPlay.isSelected());
        event.setForceAttackAgain(pe_forceAttackAgain.isSelected());
        event.setTheButcher(pe_isTheButcher.isSelected());
        event.setMelquiades(pe_isMelquiades.isSelected());
        event.setCounterModsOtherMonstersAttackRoll(pe_counterModsOtherMonstersAttackRoll.isSelected());
        event.setActiveOnCovered(pe_activeOnCovered.isSelected());
        event.setPreattackGoldCounter(pe_preattackGoldCounter.isSelected());
        event.setDeliriumPreventDamage(pe_deliriumPreventDamage.isSelected());
        event.setDeliriumRandomDeathPenaltyItem(pe_deliriumRandomDeathPenaltyItem.isSelected());
        event.setDeathPlayerLink2Counters(pe_deathPlayerLink2Counters.isSelected());
        event.setCounterLinkToDamage(pe_counterLinkToDamage.isSelected());
        // Set value from combo box
        event.setDeathLinkDamageTo(pe_deathLinkDamageTo.getValue());
        // Set arrays/lists (if needed)
        event.setModifier(pe_modifier);
        event.setRollListeners(pe_rollListeners);
        event.setPreattackRollEvents(pe_preattackRollEvents);
        return event;
    }

    private AttackEvent buildAttackEvent() {
        var event = new AttackEvent();
        // Set values from controls
        event.setLoseCents(Byte.parseByte(ae_loseCents.getText()));
        event.setLoseCentsTarget(ae_loseCentsTarget.getValue());
        event.setHealMonster(Byte.parseByte(ae_healMonster.getText()));
        event.setModNextAttackRoll(Byte.parseByte(ae_modNextAttackRoll.getText()));
        event.setDamage(Byte.parseByte(ae_damage.getText()));
        event.setDamageTo(ae_damageTo.getValue());
        event.setEndTurn(ae_endTurn.isSelected());
        event.setBigBony(ae_isBigBony.isSelected());
        event.setHenry(ae_isHenry.isSelected());
        event.setDiscardLoot(Byte.parseByte(ae_discardLoot.getText()));
        event.setCancelDamage(ae_cancelDamage.isSelected());
        event.setCancelAttack(ae_cancelAttack.isSelected());
        event.setKillCounter(ae_killCounter.isSelected());
        event.setVoteRightOrLeft(ae_voteRightOrLeft.isSelected());
        event.setRoll2Dice(ae_roll2Dice.isSelected());
        // Set roll events
        event.setRollEvent(ae_rollEvents.toArray(new RollEvent[]{}));
        return event;
    }

    private DamageEvent buildDamageEvent() {
        var event = new DamageEvent();
        event.setModPlayersNextAttackRoll(Byte.parseByte(dmge_modPlayersNextAttackRoll.getText()));
        event.setDamage(Byte.parseByte(dmge_damage.getText()));
        event.setDamageTo(dmge_damageTo.getValue());
        event.setRollEvents(dmge_rollEvents.toArray(new RollEvent[]{}));
        event.setPreventDamage(dmge_preventDamage.isSelected());
        event.setDamageRollEvents(dmge_damageRollEvents.toArray(new RollEvent[]{}));
        event.setModAttackRoll(Byte.parseByte(dmge_modAttackRoll.getText()));
        event.setModDamage(Byte.parseByte(dmge_modDamage.getText()));
        event.setTheScourge(dmge_isTheScourge.isSelected());
        event.setDamageCounter(dmge_damageCounter.isSelected());
        event.setPooCounter(dmge_pooCounter.isSelected());
        event.setNoteAttackRolls(dmge_noteAttackRolls.isSelected());
        event.setFlipNextAttackRoll(dmge_flipNextAttackRoll.isSelected());
        event.setSpiderCounter(dmge_spiderCounter.isSelected());
        return event;
    }

    private DeathEvent buildDeathEvent() {
        DeathEvent deathEvent = new DeathEvent();
        deathEvent.setAttackAgain(de_attackAgain.isSelected());
        deathEvent.setDamage(Byte.parseByte(de_damage.getText()));
        deathEvent.setDamageTo(de_damageTo.getValue());
        deathEvent.setDiscardLoot(Byte.parseByte(de_discardLoot.getText()));
        deathEvent.setDiscardLootTo(de_discardLootTo.getValue());
        deathEvent.setLoseCents(Byte.parseByte(de_loseCents.getText()));
        deathEvent.setLoseCentsTo(de_loseCentsTo.getValue());
        deathEvent.setExpandShop(Byte.parseByte(de_expandShop.getText()));
        deathEvent.setStealPlayerItem(de_stealPlayerItem.isSelected());
        deathEvent.setPeekPlayerHand(de_peekPlayerHand.isSelected());
        deathEvent.setExpandMonster(Byte.parseByte(de_expandMonster.getText()));
        deathEvent.setForceAttackAgain(de_forceAttackAgain.isSelected());
        deathEvent.setRechargeAllItems(de_rechargeAllItems.isSelected());
        deathEvent.setDiscardSoul(Byte.parseByte(de_discardSoul.getText()));
        deathEvent.setPeekDeck(de_peekDeck.getValue());
        deathEvent.setPeekDeckAmount(Byte.parseByte(de_peekDeckAmount.getText()));
        deathEvent.setPeekDeckSort(de_peekDeckSort.isSelected());
        deathEvent.setKill(de_kill.getValue());
        deathEvent.setCancelIfDamageDealt(de_cancelIfDamageDealt.isSelected());
        deathEvent.setForceAttackAgainOnSameSlot(de_forceAttackAgainOnSameSlot.isSelected());
        deathEvent.setExpandAny(Byte.parseByte(de_expandAny.getText()));
        deathEvent.setTapeWorm(de_isTapeWorm.isSelected());
        deathEvent.setGreedlingRush(de_greedlingRush.isSelected());

        deathEvent.setReward(de_reward);
        deathEvent.setRewardTo(de_rewardTo.getValue());
        deathEvent.setGiveHeartCounter(de_giveHeartCounter.isSelected());
        deathEvent.setHeartItem(de_isHeartItem.isSelected());
        deathEvent.setSummonHarbingers(de_summonHarbingers.isSelected());
        deathEvent.setBeastAlt(de_isBeastAlt.isSelected());
        deathEvent.setDeathLink(de_deathLink.isSelected());
        deathEvent.setSkipPlayersNextTurn(de_skipPlayersNextTurn.isSelected());
        deathEvent.setPeep(de_isPeep.isSelected());
        deathEvent.setSloth(de_isSloth.isSelected());
        deathEvent.setAttackMonsterDeck(de_attackMonsterDeck.isSelected());
        deathEvent.setSecondChance(de_secondChance.isSelected());
        deathEvent.setGiftSoul(de_giftSoul.isSelected());
        deathEvent.setClog(de_isClog.isSelected());
        deathEvent.setSummonCurse(de_summonCurse.isSelected());
        deathEvent.setBalrog(de_isBalrog.isSelected());
        deathEvent.setDiscardHand(de_discardHand.isSelected());
        deathEvent.setPestilenceAlt(de_isPestilenceAlt.isSelected());
        deathEvent.setStealItemWithGoldCounter(de_stealItemWithGoldCounter.isSelected());
        deathEvent.setSkipChosenPlayersTurn(de_skipChosenPlayersTurn.isSelected());
        deathEvent.setSpiderCounterDistributeDamage(de_spiderCounterDistributeDamage.isSelected());
        deathEvent.setButItsReallyTheBloat(de_butItsReallyTheBloat.isSelected());

        // Parsing byte fields
        deathEvent.setPutInDeck(Byte.parseByte(de_putInDeck.getText()));
        deathEvent.setStealSoul(Byte.parseByte(de_stealSoul.getText()));
        String secondChanceAttributesText = de_secondChanceAttributes.getText();
        List<Byte> secondChanceAttributes = new ArrayList<>();
        if (!secondChanceAttributesText.isBlank()) {
            String[] tokens = de_secondChanceAttributes.getText().split(",");
            for (String token : tokens)
                secondChanceAttributes.add(Byte.parseByte(token));
        }
        deathEvent.setSecondChanceAttributes(secondChanceAttributes);
        // Setting roll events
        deathEvent.setDeathRollEvents(de_deathRollEvents);
        deathEvent.setKillRollEvents(de_killRollEvents);

        return deathEvent;
    }

    public void stat_modReward() {
        DialogFactory.instance().showRewardModifierBox(stat_reward).ifPresent(x -> stat_reward = x);
    }

    public void pe_modAttributeModifier() {
        DialogFactory.instance().showAttributeModifierBox(pe_modifier).ifPresent(x -> pe_modifier = x);
    }

    public void pe_modRollListeners() {
        DialogFactory.instance().showRollListenerModifierBox(pe_rollListeners);
    }

    public void pe_modPreAttackRollEvent() {
        DialogFactory.instance().showRollEventModifierBox(pe_preattackRollEvents);
    }

    public void ae_modRollEvent() {
        DialogFactory.instance().showRollEventModifierBox(ae_rollEvents);
    }

    public void dmge_modRollEvents() {
        DialogFactory.instance().showRollEventModifierBox(dmge_rollEvents);
    }

    public void dmge_modDamageRollEvents() {
        DialogFactory.instance().showRollEventModifierBox(dmge_damageRollEvents);
    }

    public void de_modDeathRollEvents() {
        DialogFactory.instance().showRollEventModifierBox(de_deathRollEvents);
    }

    public void de_modKillRollEvents() {
        DialogFactory.instance().showRollEventModifierBox(de_killRollEvents);
    }

    public void de_modReward() {
        DialogFactory.instance().showRewardModifierBox(de_reward).ifPresent(x -> de_reward = x);
    }
}
