package org.camposmdev.editor.ui.workspace.monster;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.camposmdev.model.card.attribute.CardSet;
import org.camposmdev.model.card.attribute.monster.EndEvent;
import org.camposmdev.model.card.attribute.monster.StartEvent;
import org.camposmdev.model.card.monster.CurseCard;
import org.camposmdev.util.FormController;

public class CurseFormController extends FormController<CurseCard> {
    @FXML private ComboBox<CardSet> cardSet;
    @FXML private CheckBox discardSoulOnDeath;
    @FXML private TextField modMonsterAttackRoll;
    @FXML private CheckBox forceAttack;
    @FXML private CheckBox monsterEmpathy;
    @FXML private TextField monsterEmpathyLoot;
    @FXML private TextField monsterEmpathyCents;
    @FXML private TextField modMonsterDamage;
    @FXML private CheckBox soulless;
    @FXML private CheckBox gift;
    @FXML private CheckBox suspicious;
    @FXML protected FormController<StartEvent> se_formController;
    @FXML protected FormController<EndEvent> ee_formController;

    @Override
    public void init() {
        cardSet.setValue(CardSet.UNDEFINED);
        cardSet.getItems().addAll(CardSet.values());
    }

    @Override
    public CurseCard submit() throws Exception {
        var card = new CurseCard();
        card.setCardSet(cardSet.getValue());
        card.setStartEffect(se_formController.submit());
        card.setEndEffect(ee_formController.submit());
        card.setDiscardSoulOnDeath(discardSoulOnDeath.isSelected());
        card.setModMonsterAttackRoll(Byte.parseByte(modMonsterAttackRoll.getText()));
        card.setForceAttack(forceAttack.isSelected());
        card.setMonsterEmpathy(monsterEmpathy.isSelected());
        card.setMonsterEmpathyLoot(Byte.parseByte(monsterEmpathyLoot.getText()));
        card.setMonsterEmpathyCents(Byte.parseByte(monsterEmpathyCents.getText()));
        card.setModMonsterDamage(Byte.parseByte(modMonsterDamage.getText()));
        card.setSoulless(soulless.isSelected());
        card.setGift(gift.isSelected());
        card.setSuspicious(suspicious.isSelected());
        return card;
    }
}
