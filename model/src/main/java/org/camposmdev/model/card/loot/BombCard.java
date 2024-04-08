package org.camposmdev.model.card.loot;

import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.attribute.EntityTarget;

public class BombCard extends LootCard {
    private byte damage;
    private EntityTarget damageTo;

    public BombCard() {
        super.setCardType(CardType.BOMBS);
    }

    public byte damage() {
        return damage;
    }

    public BombCard setDamage(byte damage) {
        this.damage = damage;
        return this;
    }

    public EntityTarget damageTo() {
        return damageTo;
    }

    public BombCard setDamageTo(EntityTarget damageTo) {
        this.damageTo = damageTo;
        return this;
    }
}
