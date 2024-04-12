package org.camposmdev.model.card.character;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camposmdev.model.card.BaseCard;
import org.camposmdev.model.card.attribute.CardType;

public class CharacterCard extends BaseCard {
    private byte hitPoints;
    private byte damage;
    private String eternalId;

    public CharacterCard() {
        super.setCardType(CardType.CHARACTER);
    }

    public byte getHitPoints() {
        return hitPoints;
    }

    public CharacterCard setHitPoints(byte hitPoints) {
        this.hitPoints = hitPoints;
        return this;
    }

    public byte getDamage() {
        return damage;
    }

    public CharacterCard setDamage(byte damage) {
        this.damage = damage;
        return this;
    }

    public String getEternalId() {
        return eternalId;
    }

    public CharacterCard setEternalId(String eternalId) {
        this.eternalId = eternalId;
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
