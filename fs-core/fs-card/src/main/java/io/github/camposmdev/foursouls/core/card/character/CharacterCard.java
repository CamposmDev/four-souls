package io.github.camposmdev.foursouls.core.card.character;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.camposmdev.foursouls.core.card.BaseCard;
import io.github.camposmdev.foursouls.core.card.attribute.CardType;
import io.github.camposmdev.foursouls.core.card.attribute.character.SpecialType;

public class CharacterCard extends BaseCard {
    private int hp;
    private int atk;
    private String eternalId;
    private SpecialType special;

    public CharacterCard() {
        super.setCardType(CardType.CHARACTER);
        this.special = SpecialType.DEFAULT;
    }

    public Integer getHp() {
        return hp;
    }

    public CharacterCard setHp(int hp) {
        this.hp = hp;
        return this;
    }

    public Integer getAtk() {
        return atk;
    }

    public CharacterCard setAtk(int atk) {
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
