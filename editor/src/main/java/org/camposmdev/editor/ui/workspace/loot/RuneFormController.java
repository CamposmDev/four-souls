package org.camposmdev.editor.ui.workspace.loot;

import org.camposmdev.editor.ui.factory.DialogFactory;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.camposmdev.model.card.attribute.CardSet;
import org.camposmdev.model.card.attribute.DeckType;
import org.camposmdev.model.card.attribute.loot.LootOptionEvent;
import org.camposmdev.model.card.attribute.loot.RuneEvent;
import org.camposmdev.model.card.loot.RuneCard;
import org.camposmdev.util.FXUtil;
import org.camposmdev.util.FormController;

import java.util.LinkedList;
import java.util.List;

public class RuneFormController extends FormController<RuneCard> {
    @FXML
    private ComboBox<CardSet> cbCardSet;
    @FXML
    private CheckBox cbDiscardActiveMonsters;
    @FXML
    private ComboBox<DeckType> cbPeekDeck;
    @FXML
    private TextField tfPeekDeckAmount;
    @FXML
    private CheckBox cbPeekDeckSort;
    @FXML
    private CheckBox cbDestroyItemInPlayAndReplace;
    @FXML
    private CheckBox cbRerollAnyItem;
    @FXML
    private CheckBox cbJera;
    @FXML
    private CheckBox cbAlgiz;
    @FXML
    private CheckBox cbBerkano;
    @FXML
    private CheckBox cbHalgalaz;

    private List<LootOptionEvent> lootOptionEvents;
    private List<RuneEvent> runeEvents;

    @Override
    public void init() {
        FXUtil.initNumberFields(tfPeekDeckAmount);
        lootOptionEvents = new LinkedList<>();
        runeEvents = new LinkedList<>();
        cbCardSet.setValue(CardSet.UNDEFINED);
        cbCardSet.getItems().addAll(CardSet.values());
        cbPeekDeck.setValue(DeckType.UNDEFINED);
        cbPeekDeck.getItems().addAll(DeckType.values());
    }

    public void modOptions() {
        DialogFactory.instance().showLootOptionEventModifierBox(lootOptionEvents);
    }

    public void modRuneEvents() {
        DialogFactory.instance().showRuneEventModifierBox(runeEvents);
    }

    @Override
    public RuneCard submit() throws Exception {
        // Retrieve values from UI components
        CardSet selectedCardSet = cbCardSet.getValue();
        boolean discardActiveMonsters = cbDiscardActiveMonsters.isSelected();
        DeckType selectedPeekDeck = cbPeekDeck.getValue();
        byte peekDeckAmount = Byte.parseByte(tfPeekDeckAmount.getText());
        boolean peekDeckSort = cbPeekDeckSort.isSelected();
        boolean destroyItemInPlayAndReplace = cbDestroyItemInPlayAndReplace.isSelected();
        boolean rerollAnyItem = cbRerollAnyItem.isSelected();
        boolean jera = cbJera.isSelected();
        boolean algiz = cbAlgiz.isSelected();
        boolean berkano = cbBerkano.isSelected();
        boolean halgalaz = cbHalgalaz.isSelected();

        // Create a new RuneCard object
        RuneCard runeCard = new RuneCard();
        runeCard.setCardSet(selectedCardSet);
        runeCard.setDiscardActiveMonsters(discardActiveMonsters);
        runeCard.setPeekDeck(selectedPeekDeck);
        runeCard.setPeekDeckAmount(peekDeckAmount);
        runeCard.setPeekDeckSort(peekDeckSort);
        runeCard.setDestroyItemInPlayAndReplace(destroyItemInPlayAndReplace);
        runeCard.setRerollAnyItem(rerollAnyItem);
        runeCard.setJera(jera);
        runeCard.setAlgiz(algiz);
        runeCard.setBerkano(berkano);
        runeCard.setHalgalaz(halgalaz);

        // Return the created RuneCard object
        return runeCard;
    }
}
