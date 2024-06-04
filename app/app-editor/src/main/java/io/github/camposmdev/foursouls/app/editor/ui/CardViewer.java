package io.github.camposmdev.foursouls.app.editor.ui;

import javafx.scene.SnapshotParameters;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CardViewer {
    static final double WIDTH = 154 * 2.6;
    static final double HEIGHT = 210 * 2.6;
    static final double ARC_SIZE = 36;

    private final ImageView iv;

    public CardViewer() {
        iv = new ImageView();
    }

    public Image getImage() {
        return iv.getImage();
    }
    public void setImage(Image image) {
        if (image == null) return;
        iv.setImage(image);
        var clip = new Rectangle();
        if (image.getWidth() < image.getHeight()) {
            iv.setFitWidth(WIDTH);
            iv.setFitHeight(HEIGHT);
            clip.setWidth(WIDTH);
            clip.setHeight(HEIGHT);
        } else {
            iv.setFitWidth(HEIGHT);
            iv.setFitHeight(WIDTH);
            clip.setWidth(HEIGHT);
            clip.setHeight(WIDTH);
        }
        clip.setArcHeight(ARC_SIZE);
        clip.setArcWidth(ARC_SIZE);
        iv.setClip(clip);
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        var writeImage = iv.snapshot(parameters, null);
        iv.setClip(null);
        iv.setImage(writeImage);
    }

    public ImageView getContent() {
        return iv;
    }

    public void clear() {
        iv.setImage(null);
    }
}
