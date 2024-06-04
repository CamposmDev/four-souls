package io.github.camposmdev.foursouls.model.card.attribute.monster;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.camposmdev.foursouls.model.card.attribute.EntityTarget;

public class StartEvent {
    private boolean rerollShopItem;
    private boolean kekeIsYou;
    private boolean zombieJesus;
    private boolean momsHandAlt;
    private boolean evis;
    private byte damage;
    private EntityTarget damageTo;
    private boolean rechargeOneItem;
    private boolean gurdyAlt;
    private boolean counterDistributeDamage;
    private boolean theAdversary;

    public boolean isRerollShopItem() {
        return rerollShopItem;
    }

    public StartEvent setRerollShopItem(boolean rerollShopItem) {
        this.rerollShopItem = rerollShopItem;
        return this;
    }

    public boolean isKekeIsYou() {
        return kekeIsYou;
    }

    public StartEvent setKekeIsYou(boolean kekeIsYou) {
        this.kekeIsYou = kekeIsYou;
        return this;
    }

    public boolean isZombieJesus() {
        return zombieJesus;
    }

    public StartEvent setZombieJesus(boolean zombieJesus) {
        this.zombieJesus = zombieJesus;
        return this;
    }

    public boolean isMomsHandAlt() {
        return momsHandAlt;
    }

    public StartEvent setMomsHandAlt(boolean momsHandAlt) {
        this.momsHandAlt = momsHandAlt;
        return this;
    }

    public boolean isEvis() {
        return evis;
    }

    public StartEvent setEvis(boolean evis) {
        this.evis = evis;
        return this;
    }

    public byte getDamage() {
        return damage;
    }

    public StartEvent setDamage(byte damage) {
        this.damage = damage;
        return this;
    }

    public EntityTarget getDamageTo() {
        return damageTo;
    }

    public StartEvent setDamageTo(EntityTarget damageTo) {
        this.damageTo = damageTo;
        return this;
    }

    public boolean isRechargeOneItem() {
        return rechargeOneItem;
    }

    public StartEvent setRechargeOneItem(boolean rechargeOneItem) {
        this.rechargeOneItem = rechargeOneItem;
        return this;
    }

    public boolean isGurdyAlt() {
        return gurdyAlt;
    }

    public StartEvent setGurdyAlt(boolean gurdyAlt) {
        this.gurdyAlt = gurdyAlt;
        return this;
    }

    public boolean isCounterDistributeDamage() {
        return counterDistributeDamage;
    }

    public StartEvent setCounterDistributeDamage(boolean counterDistributeDamage) {
        this.counterDistributeDamage = counterDistributeDamage;
        return this;
    }

    public boolean isTheAdversary() {
        return theAdversary;
    }

    public StartEvent setTheAdversary(boolean theAdversary) {
        this.theAdversary = theAdversary;
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
