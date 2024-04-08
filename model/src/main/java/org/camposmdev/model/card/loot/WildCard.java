package org.camposmdev.model.card.loot;

import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.attribute.DeckType;
import org.camposmdev.model.card.attribute.EntityTarget;
import org.camposmdev.model.card.attribute.RollEvent;
import org.camposmdev.model.game.Reward;

public class WildCard extends LootCard {
    protected boolean theFool;
    protected boolean changeDiceRollResult;
    protected boolean highPriestess;
    protected byte modPlayerHitPoint;
    protected byte modPlayerDamage;
    protected byte modPlayerDiceRoll;
    protected DeckType peekDeck;
    protected byte peekDeckAmount;
    protected boolean peekDeck1Top;
    protected boolean peekDeckSort;
    protected boolean peekDeckBottomOption;
    protected byte preventDamageToAny;
    protected byte preventNextDamageToAny;
    protected boolean justice;
    protected RollEvent[] rollEvent;
    protected boolean attackAgain;
    protected EntityTarget kill;
    protected Reward reward;
    protected EntityTarget rewardTo;
    protected OptionEvent[] options;
    protected boolean devilDealStealItem;
    protected boolean theSun;
    protected boolean judgement;
    protected boolean theWorld;
    protected boolean creditCard;
    protected byte holyVersion;
    protected boolean twoOfDiamonds;
    protected boolean joker;
    protected byte copyActiveItemVersion;
    protected boolean disableOtherPlayers;
    protected boolean aceOfDiamonds;
    protected boolean emergencyContact;
    protected boolean twoOfSpades;
    protected boolean fiendFire;
    protected boolean goldKey;
    protected boolean dadsNote;
    protected boolean magicMarker;
    protected boolean bibleThump;
    protected boolean blanks;
    protected boolean cheepcheepcheep;
    protected boolean chunkOfAmber;
    protected boolean cowOnATrashFarm;
    protected boolean greedButt;
    protected boolean jester;
    protected boolean murder;
    protected boolean witch;

    public WildCard() {
        super.setCardType(CardType.WILDCARD);
    }

    public boolean theFool() {
        return theFool;
    }

    public WildCard setTheFool(boolean theFool) {
        this.theFool = theFool;
        return this;
    }

    public boolean changeDiceRollResult() {
        return changeDiceRollResult;
    }

    public WildCard setChangeDiceRollResult(boolean changeDiceRollResult) {
        this.changeDiceRollResult = changeDiceRollResult;
        return this;
    }

    public boolean highPriestess() {
        return highPriestess;
    }

    public WildCard setHighPriestess(boolean highPriestess) {
        this.highPriestess = highPriestess;
        return this;
    }

    public byte modPlayerHitPoint() {
        return modPlayerHitPoint;
    }

    public WildCard setModPlayerHitPoint(byte modPlayerHitPoint) {
        this.modPlayerHitPoint = modPlayerHitPoint;
        return this;
    }

    public byte modPlayerDamage() {
        return modPlayerDamage;
    }

    public WildCard setModPlayerDamage(byte modPlayerDamage) {
        this.modPlayerDamage = modPlayerDamage;
        return this;
    }

    public byte modPlayerDiceRoll() {
        return modPlayerDiceRoll;
    }

    public WildCard setModPlayerDiceRoll(byte modPlayerDiceRoll) {
        this.modPlayerDiceRoll = modPlayerDiceRoll;
        return this;
    }

    public DeckType peekDeck() {
        return peekDeck;
    }

    public WildCard setPeekDeck(DeckType peekDeck) {
        this.peekDeck = peekDeck;
        return this;
    }

    public byte peekDeckAmount() {
        return peekDeckAmount;
    }

    public WildCard setPeekDeckAmount(byte peekDeckAmount) {
        this.peekDeckAmount = peekDeckAmount;
        return this;
    }

    public boolean peekDeck1Top() {
        return peekDeck1Top;
    }

    public WildCard setPeekDeck1Top(boolean peekDeck1Top) {
        this.peekDeck1Top = peekDeck1Top;
        return this;
    }

    public boolean peekDeckSort() {
        return peekDeckSort;
    }

    public WildCard setPeekDeckSort(boolean peekDeckSort) {
        this.peekDeckSort = peekDeckSort;
        return this;
    }

    public boolean peekDeckBottomOption() {
        return peekDeckBottomOption;
    }

    public WildCard setPeekDeckBottomOption(boolean peekDeckBottomOption) {
        this.peekDeckBottomOption = peekDeckBottomOption;
        return this;
    }

    public byte preventDamageToAny() {
        return preventDamageToAny;
    }

    public WildCard setPreventDamageToAny(byte preventDamageToAny) {
        this.preventDamageToAny = preventDamageToAny;
        return this;
    }

    public byte preventNextDamageToAny() {
        return preventNextDamageToAny;
    }

    public WildCard setPreventNextDamageToAny(byte preventNextDamageToAny) {
        this.preventNextDamageToAny = preventNextDamageToAny;
        return this;
    }

    public boolean justice() {
        return justice;
    }

    public WildCard setJustice(boolean justice) {
        this.justice = justice;
        return this;
    }

    public RollEvent[] rollEvent() {
        return rollEvent;
    }

    public WildCard setRollEvent(RollEvent[] rollEvent) {
        this.rollEvent = rollEvent;
        return this;
    }

    public boolean attackAgain() {
        return attackAgain;
    }

    public WildCard setAttackAgain(boolean attackAgain) {
        this.attackAgain = attackAgain;
        return this;
    }

    public EntityTarget kill() {
        return kill;
    }

    public WildCard setKill(EntityTarget kill) {
        this.kill = kill;
        return this;
    }

    public Reward reward() {
        return reward;
    }

    public WildCard setReward(Reward reward) {
        this.reward = reward;
        return this;
    }

    public EntityTarget rewardTo() {
        return rewardTo;
    }

    public WildCard setRewardTo(EntityTarget rewardTo) {
        this.rewardTo = rewardTo;
        return this;
    }

    public OptionEvent[] options() {
        return options;
    }

    public WildCard setOptions(OptionEvent[] options) {
        this.options = options;
        return this;
    }

    public boolean devilDealStealItem() {
        return devilDealStealItem;
    }

    public WildCard setDevilDealStealItem(boolean devilDealStealItem) {
        this.devilDealStealItem = devilDealStealItem;
        return this;
    }

    public boolean theSun() {
        return theSun;
    }

    public WildCard setTheSun(boolean theSun) {
        this.theSun = theSun;
        return this;
    }

    public boolean judgement() {
        return judgement;
    }

    public WildCard setJudgement(boolean judgement) {
        this.judgement = judgement;
        return this;
    }

    public boolean theWorld() {
        return theWorld;
    }

    public WildCard setTheWorld(boolean theWorld) {
        this.theWorld = theWorld;
        return this;
    }

    public boolean creditCard() {
        return creditCard;
    }

    public WildCard setCreditCard(boolean creditCard) {
        this.creditCard = creditCard;
        return this;
    }

    public byte holyVersion() {
        return holyVersion;
    }

    public WildCard setHolyVersion(byte holyVersion) {
        this.holyVersion = holyVersion;
        return this;
    }

    public boolean twoOfDiamonds() {
        return twoOfDiamonds;
    }

    public WildCard setTwoOfDiamonds(boolean twoOfDiamonds) {
        this.twoOfDiamonds = twoOfDiamonds;
        return this;
    }

    public boolean joker() {
        return joker;
    }

    public WildCard setJoker(boolean joker) {
        this.joker = joker;
        return this;
    }

    public byte copyActiveItemVersion() {
        return copyActiveItemVersion;
    }

    public WildCard setCopyActiveItemVersion(byte copyActiveItemVersion) {
        this.copyActiveItemVersion = copyActiveItemVersion;
        return this;
    }

    public boolean disableOtherPlayers() {
        return disableOtherPlayers;
    }

    public WildCard setDisableOtherPlayers(boolean disableOtherPlayers) {
        this.disableOtherPlayers = disableOtherPlayers;
        return this;
    }

    public boolean aceOfDiamonds() {
        return aceOfDiamonds;
    }

    public WildCard setAceOfDiamonds(boolean aceOfDiamonds) {
        this.aceOfDiamonds = aceOfDiamonds;
        return this;
    }

    public boolean emergencyContact() {
        return emergencyContact;
    }

    public WildCard setEmergencyContact(boolean emergencyContact) {
        this.emergencyContact = emergencyContact;
        return this;
    }

    public boolean twoOfSpades() {
        return twoOfSpades;
    }

    public WildCard setTwoOfSpades(boolean twoOfSpades) {
        this.twoOfSpades = twoOfSpades;
        return this;
    }

    public boolean fiendFire() {
        return fiendFire;
    }

    public WildCard setFiendFire(boolean fiendFire) {
        this.fiendFire = fiendFire;
        return this;
    }

    public boolean goldKey() {
        return goldKey;
    }

    public WildCard setGoldKey(boolean goldKey) {
        this.goldKey = goldKey;
        return this;
    }

    public boolean dadsNote() {
        return dadsNote;
    }

    public WildCard setDadsNote(boolean dadsNote) {
        this.dadsNote = dadsNote;
        return this;
    }

    public boolean magicMarker() {
        return magicMarker;
    }

    public WildCard setMagicMarker(boolean magicMarker) {
        this.magicMarker = magicMarker;
        return this;
    }

    public boolean bibleThump() {
        return bibleThump;
    }

    public WildCard setBibleThump(boolean bibleThump) {
        this.bibleThump = bibleThump;
        return this;
    }

    public boolean blanks() {
        return blanks;
    }

    public WildCard setBlanks(boolean blanks) {
        this.blanks = blanks;
        return this;
    }

    public boolean cheepcheepcheep() {
        return cheepcheepcheep;
    }

    public WildCard setCheepcheepcheep(boolean cheepcheepcheep) {
        this.cheepcheepcheep = cheepcheepcheep;
        return this;
    }

    public boolean chunkOfAmber() {
        return chunkOfAmber;
    }

    public WildCard setChunkOfAmber(boolean chunkOfAmber) {
        this.chunkOfAmber = chunkOfAmber;
        return this;
    }

    public boolean cowOnATrashFarm() {
        return cowOnATrashFarm;
    }

    public WildCard setCowOnATrashFarm(boolean cowOnATrashFarm) {
        this.cowOnATrashFarm = cowOnATrashFarm;
        return this;
    }

    public boolean greedButt() {
        return greedButt;
    }

    public WildCard setGreedButt(boolean greedButt) {
        this.greedButt = greedButt;
        return this;
    }

    public boolean jester() {
        return jester;
    }

    public WildCard setJester(boolean jester) {
        this.jester = jester;
        return this;
    }

    public boolean murder() {
        return murder;
    }

    public WildCard setMurder(boolean murder) {
        this.murder = murder;
        return this;
    }

    public boolean witch() {
        return witch;
    }

    public WildCard setWitch(boolean witch) {
        this.witch = witch;
        return this;
    }
}
