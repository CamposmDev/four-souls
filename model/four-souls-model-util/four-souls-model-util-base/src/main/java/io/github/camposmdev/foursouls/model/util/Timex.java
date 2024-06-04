package io.github.camposmdev.foursouls.model.util;

public class Timex {
    private long start;
    private long elapsed;

    public Timex start() {
        this.start = System.currentTimeMillis();
        return this;
    }

    public Timex stop() {
        this.elapsed = System.currentTimeMillis() - this.start;
        return this;
    }

    public long seconds() {
        return (this.elapsed / 1000) % 60;
    }

    public long minutes() {
        return this.elapsed / (1000 * 60);
    }

    /**
     * @return The time in milliseconds
     */
    public long toMillis() {
        return this.elapsed;
    }

    @Override
    public String toString() {
        return String.format("%d min %d sec", minutes(), seconds());
    }
}
