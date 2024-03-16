package org.camposmdev.model.json;

import com.almasb.fxgl.dsl.FXGL;
import io.vertx.core.json.JsonObject;
import javafx.scene.image.Image;

public class Character {
    private final String id;
    private final String image;
    private short maxHealth;
    private String eternalId;
    public Character(String id, String image, short maxHealth, String eternalId) {
        this.id = id;
        this.image = image;
        this.maxHealth = maxHealth;
        this.eternalId = eternalId;
    }

    public String getId() {
        return id;
    }

    public Image getImage() {
        return FXGL.image(image);
    }

    public short getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(short maxHealth) {
        this.maxHealth = maxHealth;
    }

    public String getEternalId() {
        return eternalId;
    }

    public void setEternalId(String eternalId) {
        this.eternalId = eternalId;
    }

    public JsonObject toJSON() {
        return JsonObject.of(id, JsonObject.of(
                "image", image,
                "maxHealth", maxHealth,
                "eternalId", eternalId
        ));
    }
}
