package org.camposmdev.model;

public class Dice {
    public static Dice create(int nSides) {
        return new Dice(nSides);
    }
    private int nSides;
    private int value;

    public Dice(int nSides) {
        if (nSides <= 0) throw new IllegalArgumentException("Dice must have more than 0 sides");
        this.nSides = nSides;
        this.value = 1;
    }

    public Integer get() {
        return value;
    }

    public Dice roll() {
        value = (int) (Math.random() * nSides) + 1;
        return this;
    }
}
