package org.camposmdev.editor.ui.workspace.eternal;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.camposmdev.model.card.attribute.*;
import org.camposmdev.model.card.eternal.ActiveEternalCard;
import org.camposmdev.util.FXUtil;
import org.camposmdev.util.FormController;

public class ActiveEternalFormController extends FormController<ActiveEternalCard> {
    @FXML ComboBox<CardSet> cbCardSet;
    @FXML ComboBox<RerollType> cbReroll;
    @FXML CheckBox cbPreventDamage;
    @FXML TextField tfPreventDamageAmount;
    @FXML ComboBox<EntityTarget> cbPreventDamageFor;
    @FXML ComboBox<DeckType> cbPeekDeck;
    @FXML TextField tfPeekDeckAmount;
    @FXML ComboBox<RollType> cbModifyDiceRoll;
    @FXML TextField tfModifyDiceRollOffset;
    @FXML CheckBox cbIsForeverAlone, cbRecoverCardAtStartOfTurn, cbRecoverCard;
    @FXML TextField tfRecoverCardAmount;
    @FXML ComboBox<DeckType> cbDeckType;
    @FXML CheckBox cbModifyAttack;
    @FXML TextField tfModifyAttackAmount;
    @FXML ComboBox<EntityTarget> cbModifyAttackTo;
    @FXML CheckBox cbIsIncubus, cbRechargeAtEndOfTurn, cbCancelAttack, cbAttackAgain;
    @FXML CheckBox cbPreventDeath, cbEndTurn, cbIsWoodenNickel, cbIsVoid;
    @FXML TextField tfLootAmount, tfDiscardAmount;
    @FXML CheckBox cbIsSiblingRivalry, cbIsSpindownDice, cbIsCeremonialBlade, cbIsHemoptsis, cbIsGello, cbIsKeepersBargin;
    @FXML CheckBox cbIsAbyss, cbIsDeadWeight, cbIsLemegeton, cbIsAnimaSola, cbIsClassicRoller, cbIsTheRealLeftHand;
    @FXML TextField tfDamage;
    @FXML ComboBox<EntityTarget> cbDamageTo;
    @FXML CheckBox cbIsBowAndArrow, cbIsGirlfriend, cbIsGravity, cbIsEmergencyMeeting, cbIsFootball;
    @FXML CheckBox cbIsPolarStar, cbIsRustySpoons, cbIsPopPop, cbIsPinkProglottid;

    @Override
    public void init() {
        FXUtil.initNumberFields(tfPreventDamageAmount, tfPeekDeckAmount, tfModifyDiceRollOffset, tfRecoverCardAmount,
                tfModifyAttackAmount, tfLootAmount, tfDiscardAmount, tfDamage);
        cbCardSet.setValue(CardSet.UNDEFINED);
        cbCardSet.getItems().addAll(CardSet.values());
        cbReroll.setValue(RerollType.NONE);
        cbReroll.getItems().addAll(RerollType.values());
        cbPreventDamageFor.setValue(EntityTarget.UNDEFINED);
        cbPreventDamageFor.getItems().addAll(EntityTarget.values());
        cbPeekDeck.setValue(DeckType.UNDEFINED);
        cbPeekDeck.getItems().addAll(DeckType.values());
        cbModifyDiceRoll.setValue(RollType.NONE);
        cbModifyDiceRoll.getItems().addAll(RollType.values());
        cbDeckType.setValue(DeckType.UNDEFINED);
        cbDeckType.getItems().addAll(DeckType.values());
        cbModifyAttackTo.setValue(EntityTarget.UNDEFINED);
        cbModifyAttackTo.getItems().addAll(EntityTarget.values());
        cbDamageTo.setValue(EntityTarget.UNDEFINED);
        cbDamageTo.getItems().addAll(EntityTarget.values());
    }

    @Override
    public ActiveEternalCard submit() throws Exception {
        var preventDamageAmount = Byte.parseByte(tfPreventDamageAmount.getText());
        var peekDeckAmount = Byte.parseByte(tfPeekDeckAmount.getText());
        var modifyDiceRollOffset =  Byte.parseByte(tfModifyDiceRollOffset.getText());
        var recoverCardAmount = Byte.parseByte(tfRecoverCardAmount.getText());
        var modifyAttackAmount = Byte.parseByte(tfModifyAttackAmount.getText());
        var lootAmount  = Byte.parseByte(tfLootAmount.getText());
        var discardAmount = Byte.parseByte(tfDiscardAmount.getText());
        var damage = Byte.parseByte(tfDamage.getText());
        return (ActiveEternalCard) new ActiveEternalCard()
                .setReroll(cbReroll.getValue())
                .setPreventDamage(cbPreventDamage.isSelected())
                .setPreventDamageAmount(preventDamageAmount)
                .setPreventDamageFor(cbPreventDamageFor.getValue())
                .setPeekDeck(cbPeekDeck.getValue())
                .setPeekDeckAmount(peekDeckAmount)
                .setModifyDiceRoll(cbModifyDiceRoll.getValue())
                .setModifyDiceRollOffset(modifyDiceRollOffset)
                .setForeverAlone(cbIsForeverAlone.isSelected())
                .setRecoverCardAtStartOfTurn(cbRecoverCardAtStartOfTurn.isSelected())
                .setRecoverCard(cbRecoverCard.isSelected())
                .setRecoverCardAmount(recoverCardAmount)
                .setDeckType(cbDeckType.getValue())
                .setModifyAttack(cbModifyAttack.isSelected())
                .setModifyAttackAmount(modifyAttackAmount)
                .setModifyAttackTo(cbModifyAttackTo.getValue())
                .setIncubus(cbIsIncubus.isSelected())
                .setRechargeAtEndOfTurn(cbRechargeAtEndOfTurn.isSelected())
                .setCancelAttack(cbCancelAttack.isSelected())
                .setAttackAgain(cbAttackAgain.isSelected())
                .setPreventDeath(cbPreventDeath.isSelected())
                .setEndTurn(cbEndTurn.isSelected())
                .setWoodenNickel(cbIsWoodenNickel.isSelected())
                .setVoidFlag(cbIsVoid.isSelected())
                .setLootAmount(lootAmount)
                .setDiscardAmount(discardAmount)
                .setSiblingRivalry(cbIsSiblingRivalry.isSelected())
                .setSpindownDice(cbIsSpindownDice.isSelected())
                .setCeremonialBlade(cbIsCeremonialBlade.isSelected())
                .setHemoptysis(cbIsHemoptsis.isSelected())
                .setGello(cbIsGello.isSelected())
                .setKeepersBargin(cbIsKeepersBargin.isSelected())
                .setAbyss(cbIsAbyss.isSelected())
                .setDeadWeight(cbIsDeadWeight.isSelected())
                .setLemegeton(cbIsLemegeton.isSelected())
                .setAnimaSola(cbIsAnimaSola.isSelected())
                .setClassicRoller(cbIsClassicRoller.isSelected())
                .setTheRealLeftHand(cbIsTheRealLeftHand.isSelected())
                .setDamage(damage)
                .setDamageTo(cbDamageTo.getValue())
                .setBowAndArrow(cbIsBowAndArrow.isSelected())
                .setGirlFriend(cbIsGirlfriend.isSelected())
                .setGravity(cbIsGravity.isSelected())
                .setEmergencyMeeting(cbIsEmergencyMeeting.isSelected())
                .setFootball(cbIsFootball.isSelected())
                .setPolarStar(cbIsPolarStar.isSelected())
                .setRustySpoons(cbIsRustySpoons.isSelected())
                .setPopPop(cbIsPopPop.isSelected())
                .setPinkProglottid(cbIsPinkProglottid.isSelected())
                .setCardSet(cbCardSet.getValue());
    }
}
