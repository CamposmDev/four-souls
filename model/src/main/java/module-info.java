module model {
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    exports org.camposmdev.model;
    exports org.camposmdev.model.card;
    opens org.camposmdev.model.card to com.fasterxml.jackson.databind;
    exports org.camposmdev.model.atlas;
    opens org.camposmdev.model.atlas to com.fasterxml.jackson.databind;
    exports org.camposmdev.model.game.player;
    exports org.camposmdev.model.net;
    exports org.camposmdev.model.card.attribute;
    opens org.camposmdev.model.card.attribute to com.fasterxml.jackson.databind;
    exports org.camposmdev.model.card.eternal;
    exports org.camposmdev.model.card.attribute.eternal;
    opens org.camposmdev.model.card.eternal to com.fasterxml.jackson.databind;
    exports org.camposmdev.model.card.monster;
    opens org.camposmdev.model.card.monster to com.fasterxml.jackson.databind;
    exports org.camposmdev.model.card.loot;
    opens org.camposmdev.model.card.loot to com.fasterxml.jackson.databind;
    exports org.camposmdev.model.card.bsoul;
    opens org.camposmdev.model.card.bsoul to com.fasterxml.jackson.databind;
    exports org.camposmdev.model.card.character;
    opens org.camposmdev.model.card.character to com.fasterxml.jackson.databind;
    exports org.camposmdev.model.card.attribute.loot;
    opens org.camposmdev.model.card.attribute.loot to com.fasterxml.jackson.databind;
    exports org.camposmdev.model.card.attribute.monster;
    opens org.camposmdev.model.card.attribute.monster to com.fasterxml.jackson.databind;
    exports org.camposmdev.model.card.treasure;
    exports org.camposmdev.model.card.attribute.treasure;
    exports org.camposmdev.model.card.room;
    exports org.camposmdev.model.card.extra;
}