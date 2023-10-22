package com.camposmdev.model;

public class Card {
    private CardType type;
    private String name;
    private String desc;

    public Card(CardType type, String name, String desc) {
        this.type = type;
        this.name = name;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return type + "::" + name + "::" + desc;
    }
}
