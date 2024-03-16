package org.camposmdev.editor.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class CardViewer {
    static final double WIDTH = 154 * 3.5;
    static final double HEIGHT = 210 * 3.5;
    private StackPane root;
    private final ImageView iv;

    public CardViewer() {
        iv = new ImageView();
        iv.setFitWidth(WIDTH);
        iv.setFitHeight(HEIGHT);
        root = new StackPane(iv);
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
