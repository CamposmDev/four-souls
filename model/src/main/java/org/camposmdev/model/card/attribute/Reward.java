package org.camposmdev.model.card.attribute;

import io.vertx.core.json.JsonObject;

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
public record Reward(Byte loot, Byte treasure, Byte cents, Byte hitPoints, Boolean rechargeItem, boolean stealShopItem, boolean death, boolean isEqualToCounter) {
    public JsonObject toJSON() {
        /* TODO - Update toString */
        return JsonObject.of(
                "loot", loot,
                "treasure", treasure,
                "cents", cents,
                "hitPoints", hitPoints,
                "rechargeItem", rechargeItem
        );
    }

    @Override
    public String toString() {
        return toJSON().encode();
    }
}
