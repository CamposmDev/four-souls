package org.camposmdev.model.card.loot;

import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.attribute.DeckType;
import org.camposmdev.model.card.attribute.loot.LootOptionEvent;
import org.camposmdev.model.card.attribute.loot.RuneEvent;

public class RuneCard extends LootCard {
    private LootOptionEvent[] options;
    private boolean discardActiveMonsters;
    private RuneEvent[] events;
    private boolean jera;
    private DeckType peekDeck;
    private byte peekDeckAmount;
    private boolean peekDeckSort;
    private boolean destroyItemInPlayAndReplace, rerollAnyItem, algiz, berkano, halgalaz;

    public RuneCard() {
        super.setCardType(CardType.RUNES);
    }

    public LootOptionEvent[] options() {
        return options;
    }

    public RuneCard setOptions(LootOptionEvent[] options) {
        this.options = options;
        return this;
    }

    public boolean discardActiveMonsters() {
        return discardActiveMonsters;
    }

    public RuneCard setDiscardActiveMonsters(boolean discardActiveMonsters) {
        this.discardActiveMonsters = discardActiveMonsters;
        return this;
    }

    public RuneEvent[] events() {
        return events;
    }

    public RuneCard setEvents(RuneEvent[] events) {
        this.events = events;
        return this;
    }

    public boolean jera() {
        return jera;
    }

    public RuneCard setJera(boolean jera) {
        this.jera = jera;
        return this;
    }

    public DeckType peekDeck() {
        return peekDeck;
    }

    public RuneCard setPeekDeck(DeckType peekDeck) {
        this.peekDeck = peekDeck;
        return this;
    }

    public byte peekDeckAmount() {
        return peekDeckAmount;
    }

    public RuneCard setPeekDeckAmount(byte peekDeckAmount) {
        this.peekDeckAmount = peekDeckAmount;
        return this;
    }

    public boolean peekDeckSort() {
        return peekDeckSort;
    }

    public RuneCard setPeekDeckSort(boolean peekDeckSort) {
        this.peekDeckSort = peekDeckSort;
        return this;
    }

    public boolean destroyItemInPlayAndReplace() {
        return destroyItemInPlayAndReplace;
    }

    public RuneCard setDestroyItemInPlayAndReplace(boolean destroyItemInPlayAndReplace) {
        this.destroyItemInPlayAndReplace = destroyItemInPlayAndReplace;
        return this;
    }

    public boolean rerollAnyItem() {
        return rerollAnyItem;
    }

    public RuneCard setRerollAnyItem(boolean rerollAnyItem) {
        this.rerollAnyItem = rerollAnyItem;
        return this;
    }

    public boolean algiz() {
        return algiz;
    }

    public RuneCard setAlgiz(boolean algiz) {
        this.algiz = algiz;
        return this;
    }

    public boolean berkano() {
        return berkano;
    }

    public RuneCard setBerkano(boolean berkano) {
        this.berkano = berkano;
        return this;
    }

    public boolean halgalaz() {
        return halgalaz;
    }

    public RuneCard setHalgalaz(boolean galgalaz) {
        this.halgalaz = galgalaz;
        return this;
    }
}
