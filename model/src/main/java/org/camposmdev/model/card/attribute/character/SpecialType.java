package org.camposmdev.model.card.attribute.character;

public enum SpecialType {
    DEFAULT(null),
    AA_EDEN("aa-eden"),
    AA_EDEN_2("aa-eden_2"),
    AA_THE_CAPRICIOUS("aa-the_capricious"),
    AA_THE_LOST("aa-the_lost"),
    B_CAIN("b-cain"),
    B_EDEN("b-eden"),
    B2_CAIN("b2-cain"),
    B2_EDEN("b2-eden"),
    BOX_CAIN("box-cain"),
    BOX_THE_LOST("box-the_lost"),
    G2_THE_LOST("g2-the_lost"),
    PSP_EDEN("psp-eden"),
    P_EDEN_3("p-eden_3"),
    RET_EDEN("ret-eden"),
    R_EDEN("r-eden"),
    R_EDEN_2("r-eden_2"),
    R_THE_BALEFUL("r-the_baleful"),
    R_THE_CAPRICIOUS("r-the_capricious"),
    R_THE_ENIGMA("r-the_enigma"),
    R_THE_ENIGMA_BACK("r-the_enigma_back"),
    RWZ_BABA("rwz-baba"),
    RWZ_BLIND_JOHNNY("rwz-blind_johnny"),
    RWZ_CAPTAIN_VIRIDIAN("rwz-captain_viridian"),
    RWZ_CREWMATE("rwz-crewmate"),
    RWZ_QUOTE("rwz-quote"),
    RWZ_STEVEN("rwz-steven"),
    SP_EDEN("sp-eden");

    private final String id;

    SpecialType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static SpecialType fromId(String id) {
        for (SpecialType type : SpecialType.values()) {
            if (type.getId() != null && type.getId().equals(id)) {
                return type;
            }
        }
        return DEFAULT;
    }
}

