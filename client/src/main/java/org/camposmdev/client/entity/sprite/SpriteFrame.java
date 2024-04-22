package org.camposmdev.client.entity.sprite;

import javafx.geometry.Rectangle2D;

public record SpriteFrame(int x, int y, int width, int height) {
    public Rectangle2D toR2D() {
        return new Rectangle2D(x, y, width, height);
    }

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
