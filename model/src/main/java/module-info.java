module model {
    requires io.vertx.core;
    requires com.almasb.fxgl.all;
    exports org.camposmdev.model;
    exports org.camposmdev.model.card;
    opens org.camposmdev.model.card to com.fasterxml.jackson.databind;
    exports org.camposmdev.model.atlas;
    opens org.camposmdev.model.atlas to com.fasterxml.jackson.databind;
    exports org.camposmdev.model.game;
    exports org.camposmdev.model.net;
    exports org.camposmdev.model.card.attribute;
    opens org.camposmdev.model.card.attribute to com.fasterxml.jackson.databind;
    exports org.camposmdev.model.card.eternal;
    opens org.camposmdev.model.card.eternal to com.fasterxml.jackson.databind;
    exports org.camposmdev.model.card.monster;
    opens org.camposmdev.model.card.monster to com.fasterxml.jackson.databind;
    exports org.camposmdev.model.card.loot;
    opens org.camposmdev.model.card.loot to com.fasterxml.jackson.databind;
    exports org.camposmdev.model.card.bonus_soul;
    opens org.camposmdev.model.card.bonus_soul to com.fasterxml.jackson.databind;
    exports org.camposmdev.model.card.character;
    opens org.camposmdev.model.card.character to com.fasterxml.jackson.databind;
}