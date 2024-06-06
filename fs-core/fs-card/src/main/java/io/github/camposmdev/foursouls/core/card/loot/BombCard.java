package io.github.camposmdev.foursouls.core.card.loot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.camposmdev.foursouls.core.card.attribute.CardType;
import io.github.camposmdev.foursouls.core.card.attribute.EntityTarget;

public class BombCard extends LootCard {
    private byte damage;
    private EntityTarget damageTo;

    public BombCard() {
        super.setCardType(CardType.BOMBS);
    }

    public byte getDamage() {
        return damage;
    }

    public BombCard setDamage(byte damage) {
        this.damage = damage;
        return this;
    }

    public EntityTarget getDamageTo() {
        return damageTo;
    }

    public BombCard setDamageTo(EntityTarget damageTo) {
        this.damageTo = damageTo;
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
