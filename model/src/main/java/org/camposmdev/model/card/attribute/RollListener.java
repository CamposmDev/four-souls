package org.camposmdev.model.card.attribute;

import com.almasb.fxgl.logging.Logger;
import io.vertx.core.json.JsonObject;
import org.camposmdev.model.game.Reward;

/**
 * When a die is rolled, this listener is applied to see if
 * the die rolled meets the listener's conditions
 * @param type
 * @param roll
 * @param reward
 */
public record RollListener(RollType type, byte roll, Reward reward) {
    public void apply(RollType type, byte result) {
        if (!this.type.equals(type)) return;
        if (roll != result) return;
        /* TODO - apply reward to player who owns this listener  */
        Logger.get(RollListener.class).fatal("NOT YET IMPLEMENTED");
    }

    public JsonObject toJSON() {
        return JsonObject.of(
                "type", type,
                "roll", roll,
                "reward", reward
        );
    }

    @Override
    public String toString() {
        return toJSON().encode();
    }
}
