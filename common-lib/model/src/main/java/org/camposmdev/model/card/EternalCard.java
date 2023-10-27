package org.camposmdev.model.card;

public class EternalCard extends BaseCard {
    public EternalCard(String name) {
        super(name);
    }

    public EternalCard(EternalCard card) {
        this(card.name);
    }
}
