package org.camposmdev.editor.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardViewer {
    static final double WIDTH = 154 * 2.6;
    static final double HEIGHT = 210 * 2.6;

    private final ImageView iv;

    public CardViewer() {
        iv = new ImageView();
        iv.setFitWidth(WIDTH);
        iv.setFitHeight(HEIGHT);
        iv.setPreserveRatio(true);
    }

    public Image getImage() {
        return iv.getImage();
    }

    public void setImage(Image image) {
        if (image == null) {
            return;
        }
        iv.setImage(image);
    }

    public ImageView getContent() {
        return iv;
    }

    public void clear() {
        iv.setImage(null);
    }
}
