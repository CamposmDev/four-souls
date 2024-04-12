package org.camposmdev.model.card.loot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camposmdev.model.card.attribute.*;
import org.camposmdev.model.card.attribute.loot.LootOptionEvent;

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
    protected LootOptionEvent[] options;
    protected boolean devilDealStealItem;
    protected boolean theSun;
    protected boolean judgement;
    protected boolean theWorld;
    protected boolean creditCard;
    protected CardVersion holyVersion;
    protected boolean twoOfDiamonds;
    protected boolean joker;
    protected CardVersion copyActiveItemVersion;
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

    public boolean isTheFool() {
        return theFool;
    }

    public WildCard setTheFool(boolean theFool) {
        this.theFool = theFool;
        return this;
    }

    public boolean isChangeDiceRollResult() {
        return changeDiceRollResult;
    }

    public WildCard setChangeDiceRollResult(boolean changeDiceRollResult) {
        this.changeDiceRollResult = changeDiceRollResult;
        return this;
    }

    public boolean isHighPriestess() {
        return highPriestess;
    }

    public WildCard setHighPriestess(boolean highPriestess) {
        this.highPriestess = highPriestess;
        return this;
    }

    public byte getModPlayerHitPoint() {
        return modPlayerHitPoint;
    }

    public WildCard setModPlayerHitPoint(byte modPlayerHitPoint) {
        this.modPlayerHitPoint = modPlayerHitPoint;
        return this;
    }

    public byte getModPlayerDamage() {
        return modPlayerDamage;
    }

    public WildCard setModPlayerDamage(byte modPlayerDamage) {
        this.modPlayerDamage = modPlayerDamage;
        return this;
    }

    public byte getModPlayerDiceRoll() {
        return modPlayerDiceRoll;
    }

    public WildCard setModPlayerDiceRoll(byte modPlayerDiceRoll) {
        this.modPlayerDiceRoll = modPlayerDiceRoll;
        return this;
    }

    public DeckType getPeekDeck() {
        return peekDeck;
    }

    public WildCard setPeekDeck(DeckType peekDeck) {
        this.peekDeck = peekDeck;
        return this;
    }

    public byte getPeekDeckAmount() {
        return peekDeckAmount;
    }

    public WildCard setPeekDeckAmount(byte peekDeckAmount) {
        this.peekDeckAmount = peekDeckAmount;
        return this;
    }

    public boolean isPeekDeck1Top() {
        return peekDeck1Top;
    }

    public WildCard setPeekDeck1Top(boolean peekDeck1Top) {
        this.peekDeck1Top = peekDeck1Top;
        return this;
    }

    public boolean isPeekDeckSort() {
        return peekDeckSort;
    }

    public WildCard setPeekDeckSort(boolean peekDeckSort) {
        this.peekDeckSort = peekDeckSort;
        return this;
    }

    public boolean isPeekDeckBottomOption() {
        return peekDeckBottomOption;
    }

    public WildCard setPeekDeckBottomOption(boolean peekDeckBottomOption) {
        this.peekDeckBottomOption = peekDeckBottomOption;
        return this;
    }

    public byte getPreventDamageToAny() {
        return preventDamageToAny;
    }

    public WildCard setPreventDamageToAny(byte preventDamageToAny) {
        this.preventDamageToAny = preventDamageToAny;
        return this;
    }

    public byte getPreventNextDamageToAny() {
        return preventNextDamageToAny;
    }

    public WildCard setPreventNextDamageToAny(byte preventNextDamageToAny) {
        this.preventNextDamageToAny = preventNextDamageToAny;
        return this;
    }

    public boolean isJustice() {
        return justice;
    }

    public WildCard setJustice(boolean justice) {
        this.justice = justice;
        return this;
    }

    public RollEvent[] getRollEvent() {
        return rollEvent;
    }

    public WildCard setRollEvent(RollEvent[] rollEvent) {
        this.rollEvent = rollEvent;
        return this;
    }

    public boolean isAttackAgain() {
        return attackAgain;
    }

    public WildCard setAttackAgain(boolean attackAgain) {
        this.attackAgain = attackAgain;
        return this;
    }

    public EntityTarget getKill() {
        return kill;
    }

    public WildCard setKill(EntityTarget kill) {
        this.kill = kill;
        return this;
    }

    public Reward getReward() {
        return reward;
    }

    public WildCard setReward(Reward reward) {
        this.reward = reward;
        return this;
    }

    public EntityTarget getRewardTo() {
        return rewardTo;
    }

    public WildCard setRewardTo(EntityTarget rewardTo) {
        this.rewardTo = rewardTo;
        return this;
    }

    public LootOptionEvent[] getOptions() {
        return options;
    }

    public WildCard setOptions(LootOptionEvent[] options) {
        this.options = options;
        return this;
    }

    public boolean isDevilDealStealItem() {
        return devilDealStealItem;
    }

    public WildCard setDevilDealStealItem(boolean devilDealStealItem) {
        this.devilDealStealItem = devilDealStealItem;
        return this;
    }

    public boolean isTheSun() {
        return theSun;
    }

    public WildCard setTheSun(boolean theSun) {
        this.theSun = theSun;
        return this;
    }

    public boolean isJudgement() {
        return judgement;
    }

    public WildCard setJudgement(boolean judgement) {
        this.judgement = judgement;
        return this;
    }

    public boolean isTheWorld() {
        return theWorld;
    }

    public WildCard setTheWorld(boolean theWorld) {
        this.theWorld = theWorld;
        return this;
    }

    public boolean isCreditCard() {
        return creditCard;
    }

    public WildCard setCreditCard(boolean creditCard) {
        this.creditCard = creditCard;
        return this;
    }

    public CardVersion getHolyVersion() {
        return holyVersion;
    }

    public WildCard setHolyVersion(CardVersion holyVersion) {
        this.holyVersion = holyVersion;
        return this;
    }

    public boolean isTwoOfDiamonds() {
        return twoOfDiamonds;
    }

    public WildCard setTwoOfDiamonds(boolean twoOfDiamonds) {
        this.twoOfDiamonds = twoOfDiamonds;
        return this;
    }

    public boolean isJoker() {
        return joker;
    }

    public WildCard setJoker(boolean joker) {
        this.joker = joker;
        return this;
    }

    public CardVersion getCopyActiveItemVersion() {
        return copyActiveItemVersion;
    }

    public WildCard setCopyActiveItemVersion(CardVersion copyActiveItemVersion) {
        this.copyActiveItemVersion = copyActiveItemVersion;
        return this;
    }

    public boolean isDisableOtherPlayers() {
        return disableOtherPlayers;
    }

    public WildCard setDisableOtherPlayers(boolean disableOtherPlayers) {
        this.disableOtherPlayers = disableOtherPlayers;
        return this;
    }

    public boolean isAceOfDiamonds() {
        return aceOfDiamonds;
    }

    public WildCard setAceOfDiamonds(boolean aceOfDiamonds) {
        this.aceOfDiamonds = aceOfDiamonds;
        return this;
    }

    public boolean isEmergencyContact() {
        return emergencyContact;
    }

    public WildCard setEmergencyContact(boolean emergencyContact) {
        this.emergencyContact = emergencyContact;
        return this;
    }

    public boolean isTwoOfSpades() {
        return twoOfSpades;
    }

    public WildCard setTwoOfSpades(boolean twoOfSpades) {
        this.twoOfSpades = twoOfSpades;
        return this;
    }

    public boolean isFiendFire() {
        return fiendFire;
    }

    public WildCard setFiendFire(boolean fiendFire) {
        this.fiendFire = fiendFire;
        return this;
    }

    public boolean isGoldKey() {
        return goldKey;
    }

    public WildCard setGoldKey(boolean goldKey) {
        this.goldKey = goldKey;
        return this;
    }

    public boolean isDadsNote() {
        return dadsNote;
    }

    public WildCard setDadsNote(boolean dadsNote) {
        this.dadsNote = dadsNote;
        return this;
    }

    public boolean isMagicMarker() {
        return magicMarker;
    }

    public WildCard setMagicMarker(boolean magicMarker) {
        this.magicMarker = magicMarker;
        return this;
    }

    public boolean isBibleThump() {
        return bibleThump;
    }

    public WildCard setBibleThump(boolean bibleThump) {
        this.bibleThump = bibleThump;
        return this;
    }

    public boolean isBlanks() {
        return blanks;
    }

    public WildCard setBlanks(boolean blanks) {
        this.blanks = blanks;
        return this;
    }

    public boolean isCheepcheepcheep() {
        return cheepcheepcheep;
    }

    public WildCard setCheepcheepcheep(boolean cheepcheepcheep) {
        this.cheepcheepcheep = cheepcheepcheep;
        return this;
    }

    public boolean isChunkOfAmber() {
        return chunkOfAmber;
    }

    public WildCard setChunkOfAmber(boolean chunkOfAmber) {
        this.chunkOfAmber = chunkOfAmber;
        return this;
    }

    public boolean isCowOnATrashFarm() {
        return cowOnATrashFarm;
    }

    public WildCard setCowOnATrashFarm(boolean cowOnATrashFarm) {
        this.cowOnATrashFarm = cowOnATrashFarm;
        return this;
    }

    public boolean isGreedButt() {
        return greedButt;
    }

    public WildCard setGreedButt(boolean greedButt) {
        this.greedButt = greedButt;
        return this;
    }

    public boolean isJester() {
        return jester;
    }

    public WildCard setJester(boolean jester) {
        this.jester = jester;
        return this;
    }

    public boolean isMurder() {
        return murder;
    }

    public WildCard setMurder(boolean murder) {
        this.murder = murder;
        return this;
    }

    public boolean isWitch() {
        return witch;
    }

    public WildCard setWitch(boolean witch) {
        this.witch = witch;
        return this;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

