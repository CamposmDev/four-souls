package org.camposmdev.model.card.character;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camposmdev.model.card.BaseCard;
import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.attribute.character.SpecialType;

public class CharacterCard extends BaseCard {
    private byte hp;
    private byte atk;
    private String eternalId;
    private SpecialType special;

    public CharacterCard() {
        super.setCardType(CardType.CHARACTER);
        this.special = SpecialType.DEFAULT;
    }

    public byte getHp() {
        return hp;
    }

    public CharacterCard setHp(byte hp) {
        this.hp = hp;
        return this;
    }

    public byte getAtk() {
        return atk;
    }

    public CharacterCard setAtk(byte atk) {
        this.atk = atk;
        return this;
    }

    public String getEternalId() {
        return eternalId;
    }

    public CharacterCard setEternalId(String eternalId) {
        this.eternalId = eternalId;
        return this;
    }

    public SpecialType getSpecial() {
        return special;
    }

    public CharacterCard setSpecial(SpecialType special) {
        this.special = special;
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
