package org.camposmdev.client.model.json;

import javafx.geometry.Rectangle2D;

public class SpriteFrame {
    private int x, y, width, height;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

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
