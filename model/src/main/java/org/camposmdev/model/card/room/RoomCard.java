package org.camposmdev.model.card.room;

import org.camposmdev.model.card.BaseCard;
import org.camposmdev.model.card.attribute.CardType;

public class RoomCard extends BaseCard {
    private RoomType roomType;

    public RoomCard() {
        super.setCardType(CardType.ROOM);
    }

    public RoomType roomType() {
        return roomType;
    }

    public RoomCard setRoomType(RoomType roomType) {
        this.roomType = roomType;
        return this;
    }
}
