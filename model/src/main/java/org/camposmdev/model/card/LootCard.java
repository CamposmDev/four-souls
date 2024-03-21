package org.camposmdev.model.card;

import io.vertx.core.json.JsonObject;

public abstract class LootCard extends BaseCard {
    private final CardType type;
    public LootCard(String id, String image, CardType type) {
        super(id, image);
        this.type = type;
    }

    public CardType type() {
        return type;
    }

    @Override
    public JsonObject toJSON() {
        return super.toJSON()
                .put("type", type);
    }
}
