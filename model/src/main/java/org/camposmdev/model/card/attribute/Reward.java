package org.camposmdev.model.card.attribute;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Reward that is given to player when an event
 * conditions are met, loot cards played, etc.
 * @param loot
 * @param treasure
 * @param cents
 * @param hitPoints
 * @param rechargeItem
 * @param stealShopItem
 * @param death
 * @param isEqualToCounter
 */
public record Reward(Integer loot, Integer treasure, Integer cents, Integer hitPoints, Boolean rechargeItem, boolean stealShopItem, boolean death, boolean isEqualToCounter) {
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
