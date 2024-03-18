package org.camposmdev.model.card;

import io.vertx.core.json.JsonObject;

public abstract class EternalCard extends BaseCard {
    private final CardType type;

    public EternalCard(String id, String image, CardType type) {
        super(id, image);
        this.type = type;
    }

    public CardType type() {
        return type;
    }

    @Override
    public JsonObject toJSON() {
        var obj = super.toJSON()
            .put("type", type);
        return obj;
    }
}
