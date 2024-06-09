package io.github.camposmdev.foursouls.core.card.attribute;

public enum CardSet {
    ALT_ART("Alt Art"),
    BASE_V1("Base Game V1"),
    BASE_V2("Base Game V2"),
    BLANK("Blank Cards"),
    CHALLENGES("Challenges"),
    CUSTOM("Custom"),
    DICK_KNOTS("Dick Knots"),
    ERRATA("Errata"),
    FOUR_SOULS_PLUS_V1("Four Souls+ V1"),
    FOUR_SOULS_PLUS_V2("Four Souls+ V2"),
    G_FUEL("G-Fuel"),
    GISH("Gish"),
    GOLD_BOX_V1("Gold Box V1"),
    GOLD_BOX_V2("Gold Box V2"),
    LEGEND_OF_BUM_BO("The Legend of Bum-bo!"),
    PROMOS("Promos"),
    REBALANCE("Rebalance"),
    REQUIEM("Requiem"),
    REQUIEM_WARP_ZONE("Requiem Warp Zone"),
    RETRO("Retro"),
    SUMMER_OF_ISAAC("Summer of Isaac"),
    TAPEWORM("Tapeworm"),
    TARGET("Target"),
    UNBOXING_OF_ISAAC("The Unboxing of Isaac"),
    UNDEFINED("undefined");

    private final String pretty;

    CardSet(String prettyName) {
        this.pretty = prettyName;
    }

    public String pretty() {
        return pretty;
    }
}
