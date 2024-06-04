package io.github.camposmdev.foursouls.model.card.attribute.monster;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.camposmdev.foursouls.model.card.attribute.EntityTarget;

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

    public boolean isDiscard() {
        return discard;
    }

    public MonsterOptionEvent setDiscard(boolean discard) {
        this.discard = discard;
        return this;
    }

    public byte getLoot() {
        return loot;
    }

    public MonsterOptionEvent setLoot(byte loot) {
        this.loot = loot;
        return this;
    }

    public byte getDamage() {
        return damage;
    }

    public MonsterOptionEvent setDamage(byte damage) {
        this.damage = damage;
        return this;
    }

    public EntityTarget getDamageTo() {
        return damageTo;
    }

    public MonsterOptionEvent setDamageTo(EntityTarget damageTo) {
        this.damageTo = damageTo;
        return this;
    }

    public boolean isGuppyItem() {
        return guppyItem;
    }

    public MonsterOptionEvent setGuppyItem(boolean guppyItem) {
        this.guppyItem = guppyItem;
        return this;
    }

    public byte getStealCents() {
        return stealCents;
    }

    public MonsterOptionEvent setStealCents(byte stealCents) {
        this.stealCents = stealCents;
        return this;
    }

    public byte getStealLoot() {
        return stealLoot;
    }

    public MonsterOptionEvent setStealLoot(byte stealLoot) {
        this.stealLoot = stealLoot;
        return this;
    }

    public byte getStealItems() {
        return stealItems;
    }

    public MonsterOptionEvent setStealItems(byte stealItems) {
        this.stealItems = stealItems;
        return this;
    }

    public byte getStealSouls() {
        return stealSouls;
    }

    public MonsterOptionEvent setStealSouls(byte stealSouls) {
        this.stealSouls = stealSouls;
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
