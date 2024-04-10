package org.camposmdev.model.card.attribute.monster;

import org.camposmdev.model.card.attribute.EntityTarget;

public class StartEvent {
    private boolean rerollShopItem;
    private boolean kekeIsYou;
    private boolean isZombieJesus;
    private boolean IsMomsHandAlt;
    private boolean isEvis;
    private byte damage;
    private EntityTarget damageTo;
    private boolean rechargeOneItem;
    private boolean isGurdyAlt;
    private boolean counterDistributeDamage;
    private boolean isTheAdversary;

    public boolean rerollShopItem() {
        return rerollShopItem;
    }

    public StartEvent setRerollShopItem(boolean rerollShopItem) {
        this.rerollShopItem = rerollShopItem;
        return this;
    }

    public boolean kekeIsYou() {
        return kekeIsYou;
    }

    public StartEvent setKekeIsYou(boolean kekeIsYou) {
        this.kekeIsYou = kekeIsYou;
        return this;
    }

    public boolean isZombieJesus() {
        return isZombieJesus;
    }

    public StartEvent setZombieJesus(boolean zombieJesus) {
        isZombieJesus = zombieJesus;
        return this;
    }

    public boolean IsMomsHandAlt() {
        return IsMomsHandAlt;
    }

    public StartEvent setMomsHandAlt(boolean momsHandAlt) {
        IsMomsHandAlt = momsHandAlt;
        return this;
    }

    public boolean isEvis() {
        return isEvis;
    }

    public StartEvent setEvis(boolean evis) {
        isEvis = evis;
        return this;
    }

    public byte damage() {
        return damage;
    }

    public StartEvent setDamage(byte damage) {
        this.damage = damage;
        return this;
    }

    public EntityTarget damageTarget() {
        return damageTo;
    }

    public StartEvent setDamageTo(EntityTarget damageTo) {
        this.damageTo = damageTo;
        return this;
    }

    public boolean rechargeOneItem() {
        return rechargeOneItem;
    }

    public StartEvent setRechargeOneItem(boolean rechargeOneItem) {
        this.rechargeOneItem = rechargeOneItem;
        return this;
    }

    public boolean isGurdyAlt() {
        return isGurdyAlt;
    }

    public StartEvent setGurdyAlt(boolean gurdyAlt) {
        isGurdyAlt = gurdyAlt;
        return this;
    }

    public boolean counterDistributeDamage() {
        return counterDistributeDamage;
    }

    public StartEvent setCounterDistributeDamage(boolean counterDistributeDamage) {
        this.counterDistributeDamage = counterDistributeDamage;
        return this;
    }

    public boolean isTheAdversary() {
        return isTheAdversary;
    }

    public StartEvent setTheAdversary(boolean theAdversary) {
        isTheAdversary = theAdversary;
        return this;
    }
}
