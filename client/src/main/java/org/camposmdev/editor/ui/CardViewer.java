package org.camposmdev.editor.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class CardViewer {
    static final double WIDTH = 154 * 2.75;
    static final double HEIGHT = 210 * 2.75;
    private final StackPane root;
    private final ImageView iv;

    public CardViewer() {
        iv = new ImageView();
        iv.setFitWidth(WIDTH);
        iv.setFitHeight(HEIGHT);
        iv.setPreserveRatio(true);
        root = new StackPane(iv);
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

    public StackPane getContent() {
        return root;
    }
}
