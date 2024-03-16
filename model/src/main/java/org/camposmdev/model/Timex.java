package org.camposmdev.model;

public class Timex {
    private long start;
    private long elapsed;

    public Timex start() {
        this.start = System.currentTimeMillis();
        return this;
    }

    public Timex stop() {
        this.elapsed = System.currentTimeMillis() - start;
        return this;
    }

    public long seconds() {
        return (elapsed / 1000) % 60;
    }

    public long minutes() {
        return elapsed / (1000 * 60);
    }

    @Override
    public String toString() {
        return String.format("%d min %d sec", minutes(), seconds());
    }
}
