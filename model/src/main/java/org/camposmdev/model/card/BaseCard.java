package org.camposmdev.model.card;

import io.vertx.core.json.JsonObject;
import org.camposmdev.model.card.attribute.CardSet;
import org.camposmdev.model.card.attribute.CardType;

import java.io.Serializable;

public abstract class BaseCard implements Serializable {
    private String id, image;
    private CardType cardType;
    private CardSet cardSet;

    public String id() {
        return id;
    }

    public BaseCard setId(String id) {
        this.id = id;
        return this;
    }

    public String image() {
        return image;
    }

    public BaseCard setImage(String image) {
        this.image = image;
        return this;
    }

    public CardType cardType() {
        return cardType;
    }

    public BaseCard setCardType(CardType cardType) {
        this.cardType = cardType;
        return this;
    }

    public CardSet cardSet() {
        return cardSet;
    }

    public BaseCard setCardSet(CardSet cardSet) {
        this.cardSet = cardSet;
        return this;
    }

    public JsonObject toJSON() {
        return JsonObject.of(
                "id", id,
                "image", image,
                "cardType", cardType,
                "cardSet", cardSet
        );
    }

    @Override
    public String toString() {
        return toJSON().encode();
    }
}
