package org.camposmdev.model.card;

import org.camposmdev.model.card.b_soul.BSoulCard;
import org.camposmdev.model.card.character.CharacterCard;
import org.camposmdev.model.card.eternal.EternalCard;
import org.camposmdev.model.card.loot.LootCard;
import org.camposmdev.model.card.monster.MonsterCard;
import org.camposmdev.model.card.room.RoomCard;
import org.camposmdev.model.card.treasure.TreasureCard;

public enum CardType {
    CHARACTER, ETERNAL, TREASURE, LOOT, MONSTER, B_SOUL, ROOM, EXTRA;

    public static CardType get(BaseCard card) {
        if (card instanceof CharacterCard) return CHARACTER;
        if (card instanceof EternalCard) return ETERNAL;
        if (card instanceof TreasureCard) return TREASURE;
        if (card instanceof LootCard) return LOOT;
        if (card instanceof MonsterCard) return MONSTER;
        if (card instanceof BSoulCard) return B_SOUL;
        if (card instanceof RoomCard) return ROOM;
        return EXTRA;
    }
}
