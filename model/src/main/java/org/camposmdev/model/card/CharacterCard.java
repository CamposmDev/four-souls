package org.camposmdev.model.card;

import io.vertx.core.json.JsonObject;

public class CharacterCard extends BaseCard {
    private byte hitPoints, damage;
    private String eternalId;

    public CharacterCard(String id, String image, byte hitPoints, byte damage, String eternalId) {
        super(id, image);
        this.hitPoints = hitPoints;
        this.damage = damage;
        this.eternalId = eternalId;
    }



    @Override
    public JsonObject toJSON() {
        return super.toJSON()
                .put("hitPoints", hitPoints)
                .put("damage", damage)
                .put("eternalId", eternalId);
    }
}
