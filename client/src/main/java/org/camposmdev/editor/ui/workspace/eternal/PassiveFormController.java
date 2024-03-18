package org.camposmdev.editor.ui.workspace.eternal;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.camposmdev.editor.ui.factory.DialogFactory;
import org.camposmdev.model.card.PassiveEternalCard;
import org.camposmdev.model.card.attribute.*;
import org.camposmdev.model.game.Reward;
import org.camposmdev.util.FormController;

import java.util.LinkedList;
import java.util.List;

public class PassiveFormController extends FormController<PassiveEternalCard> {
    @FXML
    ComboBox<Boolean> cbHasCounter;
    @FXML
    TextField tfTreasureAfterDeath;
    @FXML
    ComboBox<Boolean> cbCounterIsHitPoints;
    @FXML
    TextField tfCounterLimit, tfCounterResetsTo;
    @FXML
    ComboBox<Boolean> cbIsAllowedToAttackAgain,
        cbIsAllowedToFlipCharacter,
        cbIsAllowedToCopyShopItem,
        cbIsCopiedShopItemForever,
        cbIsDeathPenaltyMoneyOn,
        cbIsDeathPenaltyLootOn,
        cbIsBallOfTumors,
        cbIsDeathPenaltyTreasureOn,
        cbPeekLootDeckAtStartToPublic,
        cbIsAllowedToPlayMatchingLootCard,
        cbIsPossession,
        cbIsAllowedToCopyEternalItemAtStart,
        cbIsLollypop,
        cbIsHunkyBoys,
        cbIsFocus,
        cbIsRingOfSnake;

    private List<RollListener> rollListeners;
    private List<DeathListener> deathListeners;
    private DamageTakenOptions damageTaken;
    private DamageDealthRollForEffect damageDealt;
    private List<KillListener> killListeners;
    private Reward counterExceededReward;

    @Override
    public void init() {
        rollListeners = new LinkedList<>();
        deathListeners = new LinkedList<>();
        damageTaken = null;
        damageDealt = null;
        killListeners = new LinkedList<>();
        counterExceededReward = null;

        initBooleanComboBox(cbHasCounter);
        initBooleanComboBox(cbCounterIsHitPoints);
        initBooleanComboBox(cbIsAllowedToAttackAgain);
        initBooleanComboBox(cbIsAllowedToFlipCharacter);
        initBooleanComboBox(cbIsAllowedToCopyShopItem);
        initBooleanComboBox(cbIsCopiedShopItemForever);
        initBooleanComboBox(cbIsDeathPenaltyMoneyOn);
        initBooleanComboBox(cbIsDeathPenaltyLootOn);
        initBooleanComboBox(cbIsBallOfTumors);
        initBooleanComboBox(cbIsDeathPenaltyTreasureOn);
        initBooleanComboBox(cbPeekLootDeckAtStartToPublic);
        initBooleanComboBox(cbIsAllowedToPlayMatchingLootCard);
        initBooleanComboBox(cbIsPossession);
        initBooleanComboBox(cbIsAllowedToCopyEternalItemAtStart);
        initBooleanComboBox(cbIsLollypop);
        initBooleanComboBox(cbIsHunkyBoys);
        initBooleanComboBox(cbIsFocus);
        initBooleanComboBox(cbIsRingOfSnake);
    }

    private void initBooleanComboBox(ComboBox<Boolean> cb) {
        cb.getItems().addAll(true, false);
        cb.setValue(false);
    }

    public void modRollListener() {
        DialogFactory.instance().showRollListenerModifierBox(rollListeners);
    }

    public void modDeathListener() {
        DialogFactory.instance().showDeathListenerModifierBox(deathListeners);
    }

    public void modDamageTakenOption() {
        DialogFactory.instance().showDamageTakenModifierBox(damageTaken)
                .ifPresent(damageTaken -> this.damageTaken = damageTaken);
    }

    public void modDamageRollForEffect() {
        DialogFactory.instance().showDamageDealtRollForEffectModifierBox(damageDealt)
                .ifPresent(damageDealt -> this.damageDealt = damageDealt);
    }

    public void modKillListener() {
        DialogFactory.instance().showKillListenerModifierBox(killListeners);
    }

    public void modCounterExceededReward() {
        DialogFactory.instance().showRewardModifierBox(counterExceededReward)
                .ifPresent(reward -> counterExceededReward = reward);
    }

    @Override
    public PassiveEternalCard submit() throws NumberFormatException {
        var hasCounter = cbHasCounter.getValue();
        byte treasureAfterDeath = Byte.parseByte(tfTreasureAfterDeath.getText());
        byte counterLimit = Byte.parseByte(tfCounterLimit.getText());
        byte counterResetsTo = Byte.parseByte(tfCounterResetsTo.getText());
        var counterIsHitPoints = cbCounterIsHitPoints.getValue();
        var isAllowedToAttackAgain = cbIsAllowedToAttackAgain.getValue();
        var isAllowedToFlipCharacter = cbIsAllowedToFlipCharacter.getValue();
        var isAllowedToCopyShopItem = cbIsAllowedToCopyShopItem.getValue();
        var isAllowedToShopItemForever = cbIsCopiedShopItemForever.getValue();
        var isDeathPenaltyMoneyOn = cbIsDeathPenaltyMoneyOn.getValue();
        var isDeathPenaltyLootOn = cbIsDeathPenaltyLootOn.getValue();
        var isBallOfTumors = cbIsBallOfTumors.getValue();
        var isDeathPenaltyTreasureOn = cbIsDeathPenaltyTreasureOn.getValue();
        var peekLootDeckAtStartToPublic = cbPeekLootDeckAtStartToPublic.getValue();
        var isAllowedToPlayMatchingLootCard = cbIsAllowedToPlayMatchingLootCard.getValue();
        var isPossession = cbIsPossession.getValue();
        var isAllowedToCopyEternalItemAtStart = cbIsAllowedToCopyEternalItemAtStart.getValue();
        var isLollypop = cbIsLollypop.getValue();
        var isHunkyBoys = cbIsHunkyBoys.getValue();
        var isFocus = cbIsFocus.getValue();
        var isRingOfSnake = cbIsRingOfSnake.getValue();
        return new PassiveEternalCard().setHasCounter(hasCounter)
                .setTreasureAfterDeath(treasureAfterDeath)
                .setCounterIsHitPoints(counterIsHitPoints)
                .setCounterLimit(counterLimit)
                .setCounterLimitResetTo(counterResetsTo)
                .setAllowedToAttackAgain(isAllowedToAttackAgain)
                .setAllowedToFlipCharacter(isAllowedToFlipCharacter)
                .setAllowedToCopyShopItem(isAllowedToCopyShopItem)
                .setCopiedShopItemForever(isAllowedToShopItemForever)
                .setDeathPenaltyMoneyOn(isDeathPenaltyMoneyOn)
                .setDeathPenaltyLootOn(isDeathPenaltyLootOn)
                .setBallOfTumors(isBallOfTumors)
                .setDeathPenaltyTreasureOn(isDeathPenaltyTreasureOn)
                .setPeekLootDeckAtStartToPublic(peekLootDeckAtStartToPublic)
                .setAllowedToPlayMatchingLootCard(isAllowedToPlayMatchingLootCard)
                .setPossesion(isPossession)
                .setAllowedToCopyEternalItemAtStart(isAllowedToCopyEternalItemAtStart)
                .setLollypop(isLollypop)
                .setHunkyBoys(isHunkyBoys)
                .setFocus(isFocus)
                .setRingOfSnake(isRingOfSnake)
                .setRollListeners(rollListeners)
                .setDeathListeners(deathListeners)
                .setDamageTaken(damageTaken)
                .setDamageDealt(damageDealt)
                .setKillListeners(killListeners)
                .setCounterExceededReward(counterExceededReward);
    }
}
