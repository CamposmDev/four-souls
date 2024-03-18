package org.camposmdev.model.game;

import io.vertx.core.json.JsonObject;

/**
 * Reward that is given to player when an event
 * conditions are met, loot cards played, etc.
 * @param loot
 * @param treasure
 * @param cents
 * @param souls
 * @param hitPoints
 * @param rechargeItem
 */
public record Reward(Byte loot, Byte treasure, Byte cents, Byte souls, Byte hitPoints, Boolean rechargeItem) {
    public static RewardBuilder create() {
        return new RewardBuilder();
    }

    public JsonObject toJSON() {
        return JsonObject.of(
                "loot", loot,
                "treasure", treasure,
                "cents", cents,
                "souls", souls,
                "hitPoints", hitPoints,
                "rechargeItem", rechargeItem
        );
    }

    public String toPrettyString() {
        return toJSON().encodePrettily();
    }

    @Override
    public String toString() {
        return toJSON().encode();
    }

    public static class RewardBuilder {
        private byte loot, treasure, cents, souls, hitPoints;
        private boolean rechargeItem;
        private RewardBuilder() { }

        public RewardBuilder loot(byte n) {
            loot = n;
            return this;
        }

        public RewardBuilder treasure(byte n) {
            treasure = n;
            return this;
        }

        public RewardBuilder cents(byte n) {
            cents = n;
            return this;
        }

        public RewardBuilder souls(byte n) {
            souls = n;
            return this;
        }

        public RewardBuilder hitPoints(byte n) {
            hitPoints = n;
            return this;
        }

        public RewardBuilder rechargeItem(boolean flag) {
            this.rechargeItem = flag;
            return this;
        }

        public Reward build() {
            return new Reward(loot, treasure,
                    cents, souls, hitPoints, rechargeItem);
        }
    }
}
