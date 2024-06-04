package io.github.camposmdev.app.game.entity.sprite;

import javafx.geometry.Rectangle2D;

/**
 * Represents a frame of a sprite with its position and dimensions
 * @param x
 * @param y
 * @param width
 * @param height
 */
public record SpriteFrame(int x, int y, int width, int height) {
    /**
     * Converts the sprite frame to a Rectangle2D object.
     * @return a Rectangle2D representing this sprite frame.
     */
    public Rectangle2D toR2D() {
        return new Rectangle2D(x, y, width, height);
    }

    /**
     * Returns a string representation of this sprite frame.
     * @return a string containing the x, y, width, and height of this sprite frame.
     */
    @Override
    public String toString() {
        return "SpriteFrame{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
