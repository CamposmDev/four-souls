package org.camposmdev.model.card.attribute;

import io.vertx.core.json.JsonObject;
import org.camposmdev.model.game.Reward;

/**
 * When an entity dies, this listener reacts to it
 * @param type Type of death the listener is looking for
 * @param reward Reward if conditions are met
 */
public record DeathListener(DeathType type, Reward reward) {
    /* TODO - Implement method to listen for deaths in the game */

    public JsonObject toJSON() {
        return JsonObject.of(
                "type", type,
                "reward", reward
        );
    }

    @Override
    public String toString() {
        return toJSON().encode();
    }
}
