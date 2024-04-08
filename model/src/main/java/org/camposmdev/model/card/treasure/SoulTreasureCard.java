package org.camposmdev.model.card.treasure;

import org.camposmdev.model.card.attribute.CardType;

public class SoulTreasureCard extends TreasureCard {
    private boolean theChest;
    private boolean pandorasBox;
    private boolean ultraFleshKid;
    private boolean experimentalTreatment;
    private boolean cursedSoul;

    public SoulTreasureCard() {
        super.setCardType(CardType.STREASURE);
    }

    public boolean theChest() {
        return theChest;
    }

    public SoulTreasureCard setTheChest(boolean theChest) {
        this.theChest = theChest;
        return this;
    }

    public boolean pandorasBox() {
        return pandorasBox;
    }

    public SoulTreasureCard setPandorasBox(boolean pandorasBox) {
        this.pandorasBox = pandorasBox;
        return this;
    }

    public boolean ultraFleshKid() {
        return ultraFleshKid;
    }

    public SoulTreasureCard setUltraFleshKid(boolean ultraFleshKid) {
        this.ultraFleshKid = ultraFleshKid;
        return this;
    }

    public boolean experimentalTreatment() {
        return experimentalTreatment;
    }

    public SoulTreasureCard setExperimentalTreatment(boolean experimentalTreatment) {
        this.experimentalTreatment = experimentalTreatment;
        return this;
    }

    public boolean cursedSoul() {
        return cursedSoul;
    }

    public SoulTreasureCard setCursedSoul(boolean cursedSoul) {
        this.cursedSoul = cursedSoul;
        return this;
    }
}
