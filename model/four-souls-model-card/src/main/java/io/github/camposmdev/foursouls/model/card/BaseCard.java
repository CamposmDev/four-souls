package io.github.camposmdev.foursouls.model.card;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.camposmdev.foursouls.model.card.attribute.ImageInfo;
import io.github.camposmdev.foursouls.model.card.attribute.CardSet;
import io.github.camposmdev.foursouls.model.card.attribute.CardType;

public abstract class BaseCard implements Comparable<BaseCard> {
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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BaseCard card)) return false;
        return id.equals(card.id);
    }

    @Override
    public int compareTo(BaseCard o) {
        return id.compareTo(o.id);
    }
}
