package org.camposmdev.model.card.factory;

import org.camposmdev.model.card.character.CharacterCard;
import org.camposmdev.model.card.builder.CharacterCardBuilder;

public class CardFactory {
    public CharacterCard createIsaac() {
        return new CharacterCardBuilder()
                .name("Isaac")
                .imgSRC("/assets/textures/cards/character/b-issac.png")
                .hp(2)
                .atk(1)
                .build();
    }
}
