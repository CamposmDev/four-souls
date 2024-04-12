package org.camposmdev.model.card.attribute;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * When a die is rolled, this listener is applied to see if
 * the die rolled meets the listener's conditions
 * @param type
 * @param value
 * @param reward
 */
public record RollListener(
        RollType type, byte value, Reward reward, byte loseCents, byte discardLoot,
        byte buffAllActiveMonsterAttack, byte healAllActiveMonsters, byte damage, EntityTarget damageTo,
        boolean endTurn, byte heal, byte gainCents, boolean rechargeItem, DeckType peekDeck,
        byte peekDeckAmount, byte modMonsterRoll, boolean satanAlt, byte modRoll
) {
    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
