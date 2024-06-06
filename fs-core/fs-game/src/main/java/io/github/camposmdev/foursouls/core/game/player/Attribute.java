package io.github.camposmdev.foursouls.core.game.player;

import javafx.beans.property.SimpleObjectProperty;

/**
 * A generic class representing an attribute with a maximum and current value.
 *
 * @param <T> A type that extends {@link Number}, representing the type of the attribute values.
 */
public class Attribute<T extends Number> {
    private final SimpleObjectProperty<T> max;
    private final SimpleObjectProperty<T> current;

    /**
     * Constructs an Attribute with the specified maximum value. The current value is initialized to the maximum value.
     *
     * @param max The maximum value for this attribute.
     */
    public Attribute(T max) {
        this.max = new SimpleObjectProperty<>(max);
        this.current = new SimpleObjectProperty<>(max);
    }

    /**
     * Returns the maximum value of this attribute.
     *
     * @return The maximum value.
     */
    public SimpleObjectProperty<T> max() {
        return max;
    }

    /**
     * Returns the current value of this attribute.
     *
     * @return The current value.
     */
    public SimpleObjectProperty<T> current() {
        return current;
    }

    /**
     * Resets the current value of this attribute to the maximum value.
     */
    public void reset() {
        current.set(max.get());
    }

    /**
     * Returns a string representation of this attribute.
     *
     * @return A string in the format "Attribute{max=value, current=value}".
     */
    @Override
    public String toString() {
        return "{" +
                "max=" + max +
                ", current=" + current +
                '}';
    }
}
