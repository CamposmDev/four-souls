package org.camposmdev.model.card.attribute.monster;

import org.camposmdev.model.card.attribute.EntityTarget;

public class MonsterOptionEvent {
    private boolean discard;
    private byte loot;
    private byte damage;
    private EntityTarget damageTo;
    private boolean guppyItem;
    private byte stealCents;
    private byte stealLoot;
    private byte stealItems;
    private byte stealSouls;

    public boolean discard() {
        return discard;
    }

    public MonsterOptionEvent setDiscard(boolean discard) {
        this.discard = discard;
        return this;
    }

    public byte loot() {
        return loot;
    }

    public MonsterOptionEvent setLoot(byte loot) {
        this.loot = loot;
        return this;
    }

    public byte damage() {
        return damage;
    }

    public MonsterOptionEvent setDamage(byte damage) {
        this.damage = damage;
        return this;
    }

    public EntityTarget damageTo() {
        return damageTo;
    }

    public MonsterOptionEvent setDamageTo(EntityTarget damageTo) {
        this.damageTo = damageTo;
        return this;
    }

    public boolean guppyItem() {
        return guppyItem;
    }

    public MonsterOptionEvent setGuppyItem(boolean guppyItem) {
        this.guppyItem = guppyItem;
        return this;
    }

    public byte stealCents() {
        return stealCents;
    }

    public MonsterOptionEvent setStealCents(byte stealCents) {
        this.stealCents = stealCents;
        return this;
    }

    public byte stealLoot() {
        return stealLoot;
    }

    public MonsterOptionEvent setStealLoot(byte stealLoot) {
        this.stealLoot = stealLoot;
        return this;
    }

    public byte stealItems() {
        return stealItems;
    }

    public MonsterOptionEvent setStealItems(byte stealItems) {
        this.stealItems = stealItems;
        return this;
    }

    public byte stealSouls() {
        return stealSouls;
    }

    public MonsterOptionEvent setStealSouls(byte stealSouls) {
        this.stealSouls = stealSouls;
        return this;
    }
}
