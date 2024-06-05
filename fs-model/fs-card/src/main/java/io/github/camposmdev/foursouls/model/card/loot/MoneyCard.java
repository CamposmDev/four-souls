package io.github.camposmdev.foursouls.model.card.loot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.camposmdev.foursouls.model.card.attribute.CardType;
import io.github.camposmdev.foursouls.model.card.attribute.loot.MoneyEffect.MoneyEffect;

public class MoneyCard extends LootCard {
    private Byte value;
    private MoneyEffect effect;
    public MoneyCard() {
        super.setCardType(CardType.MONEY);
    }

    public Byte getValue() {
        return value;
    }

    public MoneyCard setValue(Byte value) {
        this.value = value;
        return this;
    }

    public MoneyEffect getEffect() {
        return effect;
    }

    public MoneyCard setEffect(MoneyEffect effect) {
        this.effect = effect;
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
