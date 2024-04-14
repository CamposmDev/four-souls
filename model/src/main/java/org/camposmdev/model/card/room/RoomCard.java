package org.camposmdev.model.card.room;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camposmdev.model.card.BaseCard;
import org.camposmdev.model.card.attribute.CardType;

public class RoomCard extends BaseCard {
    private RoomType roomType;

    public RoomCard() {
        super.setCardType(CardType.ROOM);
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public RoomCard setRoomType(RoomType roomType) {
        this.roomType = roomType;
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
