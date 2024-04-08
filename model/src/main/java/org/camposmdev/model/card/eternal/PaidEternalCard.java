package org.camposmdev.model.card.eternal;

import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.attribute.CardVersionType;

public class PaidEternalCard extends EternalCard {
    private boolean isBagOTrash;
    private CardVersionType bagOTrash;
    private boolean isBagOfCrafting, isIBS, isSumpotrium, isLilSteven;
    private boolean isSpelunkingPack, isLemegeton, isSpindownDice;

    public PaidEternalCard() {
        super.setCardType(CardType.PAIDETERNAL);
    }

    public boolean isBagOTrash() {
        return isBagOTrash;
    }

    public PaidEternalCard setBagOTrash(boolean bagOTrash) {
        isBagOTrash = bagOTrash;
        return this;
    }

    public CardVersionType bagOTrash() {
        return bagOTrash;
    }

    public PaidEternalCard setBagOTrash(CardVersionType bagOTrash) {
        this.bagOTrash = bagOTrash;
        return this;
    }

    public boolean isBagOfCrafting() {
        return isBagOfCrafting;
    }

    public PaidEternalCard setBagOfCrafting(boolean bagOfCrafting) {
        isBagOfCrafting = bagOfCrafting;
        return this;
    }

    public boolean isIBS() {
        return isIBS;
    }

    public PaidEternalCard setIBS(boolean IBS) {
        isIBS = IBS;
        return this;
    }

    public boolean isSumpotrium() {
        return isSumpotrium;
    }

    public PaidEternalCard setSumpotrium(boolean sumpotrium) {
        isSumpotrium = sumpotrium;
        return this;
    }

    public boolean isLilSteven() {
        return isLilSteven;
    }

    public PaidEternalCard setLilSteven(boolean lilSteven) {
        isLilSteven = lilSteven;
        return this;
    }

    public boolean isSpelunkingPack() {
        return isSpelunkingPack;
    }

    public PaidEternalCard setSpelunkingPack(boolean spelunkingPack) {
        isSpelunkingPack = spelunkingPack;
        return this;
    }

    public boolean isLemegeton() {
        return isLemegeton;
    }

    public PaidEternalCard setLemegeton(boolean lemegeton) {
        isLemegeton = lemegeton;
        return this;
    }

    public boolean isSpindownDice() {
        return isSpindownDice;
    }

    public PaidEternalCard setSpindownDice(boolean spindownDice) {
        isSpindownDice = spindownDice;
        return this;
    }
}
