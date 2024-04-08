package org.camposmdev.model.card.character;

import io.vertx.core.json.JsonObject;
import org.camposmdev.model.card.BaseCard;

public class CharacterCard extends BaseCard {
    private byte hitPoints;
    private byte damage;
    private String eternalId;

    public CharacterCard() {
        super();
    }

    public byte hitPoints() {
        return hitPoints;
    }

    public CharacterCard setHitPoints(byte hitPoints) {
        this.hitPoints = hitPoints;
        return this;
    }

    public byte damage() {
        return damage;
    }

    public CharacterCard setDamage(byte damage) {
        this.damage = damage;
        return this;
    }

    public String eternalId() {
        return eternalId;
    }

    public CharacterCard setEternalId(String eternalId) {
        this.eternalId = eternalId;
        return this;
    }

    @Override
    public JsonObject toJSON() {
        return super.toJSON()
                .put("hitPoints", hitPoints)
                .put("damage", damage)
                .put("eternalId", eternalId);
    }

    @Override
    public String toString() {
        return toJSON().encode();
    }
}
