package org.camposmdev.model.card.eternal;


import org.camposmdev.model.card.PlayableCard;

public class ActiveEternalCard extends EternalCard implements PlayableCard {
    /* Tells the program whether the card has been played or not */
    private boolean isActive;

    public ActiveEternalCard(String name) {
        super(name);
        this.isActive = false;
    }

    public boolean isActive() {
        return isActive;
    }

    public void activate() {
        this.isActive = true;
    }
}
