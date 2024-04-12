package org.camposmdev.model.card;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camposmdev.model.atlas.ImageInfo;
import org.camposmdev.model.card.attribute.CardSet;
import org.camposmdev.model.card.attribute.CardType;

public abstract class BaseCard {
    private String id;
    private ImageInfo image;
    private CardType cardType;
    private CardSet cardSet;

    public String getId() {
        return id;
    }

    public BaseCard setId(String id) {
        this.id = id;
        return this;
    }

    public ImageInfo getImage() {
        return image;
    }

    public BaseCard setImage(ImageInfo image) {
        this.image = image;
        return this;
    }

    public CardType getCardType() {
        return cardType;
    }

    public BaseCard setCardType(CardType cardType) {
        this.cardType = cardType;
        return this;
    }

    public CardSet getCardSet() {
        return cardSet;
    }

    public BaseCard setCardSet(CardSet cardSet) {
        this.cardSet = cardSet;
        return this;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
