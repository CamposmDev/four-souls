package org.camposmdev.model.card;

import io.vertx.core.json.JsonObject;

public abstract class BaseCard {
    private String id, image;

    public BaseCard(String id, String image) {
        this.id = id;
        this.image = image;
    }

    public String id() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String image() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public JsonObject toJSON() {
        return JsonObject.of(
                "id", id,
                "image", image
        );
    }

    @Override
    public String toString() {
        return toJSON().encode();
    }
}
