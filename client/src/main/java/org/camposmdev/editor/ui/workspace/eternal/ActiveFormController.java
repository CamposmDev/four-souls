package org.camposmdev.editor.ui.workspace.eternal;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.camposmdev.model.card.attribute.DeckType;
import org.camposmdev.model.card.attribute.EntityTarget;
import org.camposmdev.model.card.attribute.RerollType;
import org.camposmdev.model.card.attribute.RollType;
import org.camposmdev.model.card.eternal.ActiveEternalCard;
import org.camposmdev.util.FormController;

public class ActiveFormController extends FormController<ActiveEternalCard> {
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
        cbReroll.setValue(RerollType.NONE);
        cbReroll.getItems().addAll(RerollType.values());
        cbPreventDamageFor.setValue(EntityTarget.ANY);
        cbPreventDamageFor.getItems().addAll(EntityTarget.values());
        cbPeekDeck.setValue(DeckType.NONE);
        cbPeekDeck.getItems().addAll(DeckType.values());
        cbModifyDiceRoll.setValue(RollType.NONE);
        cbModifyDiceRoll.getItems().addAll(RollType.values());
        cbDeckType.setValue(DeckType.NONE);
        cbDeckType.getItems().addAll(DeckType.values());
        cbModifyAttackTo.setValue(EntityTarget.ANY);
        cbModifyAttackTo.getItems().addAll(EntityTarget.values());
        cbDamageTo.setValue(EntityTarget.NONE);
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
        return new ActiveEternalCard()
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
                .setVoid(cbIsVoid.isSelected())
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
                .setGirlfriend(cbIsGirlfriend.isSelected())
                .setGravity(cbIsGravity.isSelected())
                .setEmergencyMeeting(cbIsEmergencyMeeting.isSelected())
                .setFootball(cbIsFootball.isSelected())
                .setPolarStar(cbIsPolarStar.isSelected())
                .setRustySpoons(cbIsRustySpoons.isSelected())
                .setPopPop(cbIsPopPop.isSelected())
                .setPinkProglottid(cbIsPinkProglottid.isSelected());
    }
}
