package org.camposmdev.model.card.attribute;

import io.vertx.core.json.JsonObject;

/**
 * Executes when a kill is completed by player
 * @param reward Reward given when conditions are met
 */
public record KillListener(Reward reward) {
    public JsonObject toJSON() {
        return JsonObject.of(
          "reward", reward
        );
    }
}
