module model {
    requires io.vertx.core;
    requires com.almasb.fxgl.all;
    exports org.camposmdev.model;
    exports org.camposmdev.model.card;
    exports org.camposmdev.model.card.statistic;
    exports org.camposmdev.model.card.builder;
    exports org.camposmdev.model.card.character;
    exports org.camposmdev.model.card.eternal;
    exports org.camposmdev.model.json;
    opens org.camposmdev.model.json to com.fasterxml.jackson.databind;
}