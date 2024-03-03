package org.camposmdev.model.card.statistic;

import java.io.Serializable;

public class Attribute implements Serializable {
    private int max;
    private int current;

    public Attribute(int max) {
        this.max = max;
        this.current = max;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    @Override
    public String toString() {
        return "{" +
                "max=" + max +
                ", current=" + current +
                '}';
    }
}
