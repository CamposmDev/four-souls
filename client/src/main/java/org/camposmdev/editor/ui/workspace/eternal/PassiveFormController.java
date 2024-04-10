package org.camposmdev.editor.ui.workspace.eternal;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.camposmdev.editor.ui.factory.DialogFactory;
import org.camposmdev.model.card.eternal.PassiveEternalCard;
import org.camposmdev.model.card.attribute.*;
import org.camposmdev.model.card.attribute.Reward;
import org.camposmdev.util.FormController;

import java.util.LinkedList;
import java.util.List;

public class PassiveFormController extends FormController<PassiveEternalCard> {
    @FXML
    CheckBox cbHasCounter;
    @FXML
    TextField tfTreasureAfterDeath;
    @FXML
    CheckBox cbCounterIsHitPoints;
    @FXML
    TextField tfCounterLimit, tfCounterResetsTo;
    @FXML
    CheckBox cbIsAllowedToAttackAgain,
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
        var hasCounter = cbHasCounter.isSelected();
        byte treasureAfterDeath = Byte.parseByte(tfTreasureAfterDeath.getText());
        byte counterLimit = Byte.parseByte(tfCounterLimit.getText());
        byte counterResetsTo = Byte.parseByte(tfCounterResetsTo.getText());
        var counterIsHitPoints = cbCounterIsHitPoints.isSelected();
        var isAllowedToAttackAgain = cbIsAllowedToAttackAgain.isSelected();
        var isAllowedToFlipCharacter = cbIsAllowedToFlipCharacter.isSelected();
        var isAllowedToCopyShopItem = cbIsAllowedToCopyShopItem.isSelected();
        var isAllowedToShopItemForever = cbIsCopiedShopItemForever.isSelected();
        var isDeathPenaltyMoneyOn = cbIsDeathPenaltyMoneyOn.isSelected();
        var isDeathPenaltyLootOn = cbIsDeathPenaltyLootOn.isSelected();
        var isBallOfTumors = cbIsBallOfTumors.isSelected();
        var isDeathPenaltyTreasureOn = cbIsDeathPenaltyTreasureOn.isSelected();
        var peekLootDeckAtStartToPublic = cbPeekLootDeckAtStartToPublic.isSelected();
        var isAllowedToPlayMatchingLootCard = cbIsAllowedToPlayMatchingLootCard.isSelected();
        var isPossession = cbIsPossession.isSelected();
        var isAllowedToCopyEternalItemAtStart = cbIsAllowedToCopyEternalItemAtStart.isSelected();
        var isLollypop = cbIsLollypop.isSelected();
        var isHunkyBoys = cbIsHunkyBoys.isSelected();
        var isFocus = cbIsFocus.isSelected();
        var isRingOfSnake = cbIsRingOfSnake.isSelected();
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
