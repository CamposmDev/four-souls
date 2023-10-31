package org.camposmdev.model.card.eternal;


public class ActiveEternalCard extends EternalCard {
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
