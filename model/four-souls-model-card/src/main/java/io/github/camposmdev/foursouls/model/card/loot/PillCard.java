package io.github.camposmdev.foursouls.model.card.loot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.camposmdev.foursouls.model.card.attribute.CardType;
import io.github.camposmdev.foursouls.model.card.attribute.loot.PillEvent;

import java.util.List;

public class PillCard extends LootCard {
    private List<PillEvent> events;
    private byte repeat;
    private boolean share;

    public PillCard() {
        super.setCardType(CardType.PILLS);
    }

    public List<PillEvent> getEvents() {
        return events;
    }

    public PillCard setEvents(List<PillEvent> events) {
        this.events = events;
        return this;
    }

    public byte getRepeat() {
        return repeat;
    }

    public PillCard setRepeat(byte repeat) {
        this.repeat = repeat;
        return this;
    }

    public boolean isShare() {
        return share;
    }

    public PillCard setShare(boolean share) {
        this.share = share;
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
