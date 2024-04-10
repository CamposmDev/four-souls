package org.camposmdev.model.card.attribute;

public class RollEvent {
    private byte[] values;
    private byte multReward;
    private boolean noMonsterDamage;
    private byte modMonsterDamage;
    private byte multMonsterDamage;
    private boolean cancelTurn;
    private boolean stealPlayerLoot;
    private byte damage;
    private EntityTarget damageTo;
    private boolean attackAgain;
    private boolean forceAttackAgain;
    private AttributeModifier modPlayer;
    private boolean attackable;
    private boolean returnToDeck;
    private byte modRoll;
    private byte modAttack;
    private byte healMonster;
    private boolean cancelAttack;
    private byte expandMonster;
    private Reward reward;
    private byte discardLoot;
    private byte discardCents;
    private DeckType peekDeck;
    private byte peekDeckAmount;
    private boolean peekDeckSort;
    private byte soulHitPoints;
    private boolean isSoul;
    private boolean skipNextTurn;
    private boolean isHeartItem;
    private byte rerollItem;
    private boolean guppyItem;
    private EntityTarget kill;
    private byte modMonstersAttackRoll;
    private boolean putOnTopMonsterDeck;
    private RollEvent[] rollEvents;
    private boolean damageLink;
    private boolean discardRandomItem;
    private boolean putOnMonsterSlot;

    public byte[] values() {
        return values;
    }

    public RollEvent setValues(byte[] values) {
        this.values = values;
        return this;
    }

    public byte multReward() {
        return multReward;
    }

    public RollEvent setMultReward(byte multReward) {
        this.multReward = multReward;
        return this;
    }

    public boolean noMonsterDamage() {
        return noMonsterDamage;
    }

    public RollEvent setNoMonsterDamage(boolean noMonsterDamage) {
        this.noMonsterDamage = noMonsterDamage;
        return this;
    }

    public byte modMonsterDamage() {
        return modMonsterDamage;
    }

    public RollEvent setModMonsterDamage(byte modMonsterDamage) {
        this.modMonsterDamage = modMonsterDamage;
        return this;
    }

    public byte multMonsterDamage() {
        return multMonsterDamage;
    }

    public RollEvent setMultMonsterDamage(byte multMonsterDamage) {
        this.multMonsterDamage = multMonsterDamage;
        return this;
    }

    public boolean cancelTurn() {
        return cancelTurn;
    }

    public RollEvent setCancelTurn(boolean cancelTurn) {
        this.cancelTurn = cancelTurn;
        return this;
    }

    public boolean stealPlayerLoot() {
        return stealPlayerLoot;
    }

    public RollEvent setStealPlayerLoot(boolean stealPlayerLoot) {
        this.stealPlayerLoot = stealPlayerLoot;
        return this;
    }

    public byte damage() {
        return damage;
    }

    public RollEvent setDamage(byte damage) {
        this.damage = damage;
        return this;
    }

    public EntityTarget damageTo() {
        return damageTo;
    }

    public RollEvent setDamageTo(EntityTarget damageTo) {
        this.damageTo = damageTo;
        return this;
    }

    public boolean attackAgain() {
        return attackAgain;
    }

    public RollEvent setAttackAgain(boolean attackAgain) {
        this.attackAgain = attackAgain;
        return this;
    }

    public boolean forceAttackAgain() {
        return forceAttackAgain;
    }

    public RollEvent setForceAttackAgain(boolean forceAttackAgain) {
        this.forceAttackAgain = forceAttackAgain;
        return this;
    }

    public AttributeModifier modPlayer() {
        return modPlayer;
    }

    public RollEvent setModPlayer(AttributeModifier modPlayer) {
        this.modPlayer = modPlayer;
        return this;
    }

    public boolean attackable() {
        return attackable;
    }

    public RollEvent setAttackable(boolean attackable) {
        this.attackable = attackable;
        return this;
    }

    public boolean returnToDeck() {
        return returnToDeck;
    }

    public RollEvent setReturnToDeck(boolean returnToDeck) {
        this.returnToDeck = returnToDeck;
        return this;
    }

    public byte modRoll() {
        return modRoll;
    }

    public RollEvent setModRoll(byte modRoll) {
        this.modRoll = modRoll;
        return this;
    }

    public byte modAttack() {
        return modAttack;
    }

    public RollEvent setModAttack(byte modAttack) {
        this.modAttack = modAttack;
        return this;
    }

    public byte healMonster() {
        return healMonster;
    }

    public RollEvent setHealMonster(byte healMonster) {
        this.healMonster = healMonster;
        return this;
    }

    public boolean cancelAttack() {
        return cancelAttack;
    }

    public RollEvent setCancelAttack(boolean cancelAttack) {
        this.cancelAttack = cancelAttack;
        return this;
    }

    public byte expandMonster() {
        return expandMonster;
    }

    public RollEvent setExpandMonster(byte expandMonster) {
        this.expandMonster = expandMonster;
        return this;
    }

    public Reward reward() {
        return reward;
    }

    public RollEvent setReward(Reward reward) {
        this.reward = reward;
        return this;
    }

    public byte discardLoot() {
        return discardLoot;
    }

    public RollEvent setDiscardLoot(byte discardLoot) {
        this.discardLoot = discardLoot;
        return this;
    }

    public byte discardCents() {
        return discardCents;
    }

    public RollEvent setDiscardCents(byte discardCents) {
        this.discardCents = discardCents;
        return this;
    }

    public DeckType peekDeck() {
        return peekDeck;
    }

    public RollEvent setPeekDeck(DeckType peekDeck) {
        this.peekDeck = peekDeck;
        return this;
    }

    public byte peekDeckAmount() {
        return peekDeckAmount;
    }

    public RollEvent setPeekDeckAmount(byte peekDeckAmount) {
        this.peekDeckAmount = peekDeckAmount;
        return this;
    }

    public boolean peekDeckSort() {
        return peekDeckSort;
    }

    public RollEvent setPeekDeckSort(boolean peekDeckSort) {
        this.peekDeckSort = peekDeckSort;
        return this;
    }

    public byte soulHitPoints() {
        return soulHitPoints;
    }

    public RollEvent setSoulHitPoints(byte soulHitPoints) {
        this.soulHitPoints = soulHitPoints;
        return this;
    }

    public boolean isSoul() {
        return isSoul;
    }

    public RollEvent setSoul(boolean soul) {
        isSoul = soul;
        return this;
    }

    public boolean skipNextTurn() {
        return skipNextTurn;
    }

    public RollEvent setSkipNextTurn(boolean skipNextTurn) {
        this.skipNextTurn = skipNextTurn;
        return this;
    }

    public boolean isHeartItem() {
        return isHeartItem;
    }

    public RollEvent setHeartItem(boolean heartItem) {
        isHeartItem = heartItem;
        return this;
    }

    public byte rerollItem() {
        return rerollItem;
    }

    public RollEvent setRerollItem(byte rerollItem) {
        this.rerollItem = rerollItem;
        return this;
    }

    public boolean guppyItem() {
        return guppyItem;
    }

    public RollEvent setGuppyItem(boolean guppyItem) {
        this.guppyItem = guppyItem;
        return this;
    }

    public EntityTarget kill() {
        return kill;
    }

    public RollEvent setKill(EntityTarget kill) {
        this.kill = kill;
        return this;
    }

    public byte modMonstersAttackRoll() {
        return modMonstersAttackRoll;
    }

    public RollEvent setModMonstersAttackRoll(byte modMonstersAttackRoll) {
        this.modMonstersAttackRoll = modMonstersAttackRoll;
        return this;
    }

    public boolean putOnTopMonsterDeck() {
        return putOnTopMonsterDeck;
    }

    public RollEvent setPutOnTopMonsterDeck(boolean putOnTopMonsterDeck) {
        this.putOnTopMonsterDeck = putOnTopMonsterDeck;
        return this;
    }

    public RollEvent[] rollEvents() {
        return rollEvents;
    }

    public RollEvent setRollEvents(RollEvent[] rollEvents) {
        this.rollEvents = rollEvents;
        return this;
    }

    public boolean damageLink() {
        return damageLink;
    }

    public RollEvent setDamageLink(boolean damageLink) {
        this.damageLink = damageLink;
        return this;
    }

    public boolean discardRandomItem() {
        return discardRandomItem;
    }

    public RollEvent setDiscardRandomItem(boolean discardRandomItem) {
        this.discardRandomItem = discardRandomItem;
        return this;
    }

    public boolean putOnMonsterSlot() {
        return putOnMonsterSlot;
    }

    public RollEvent setPutOnMonsterSlot(boolean putOnMonsterSlot) {
        this.putOnMonsterSlot = putOnMonsterSlot;
        return this;
    }
}
