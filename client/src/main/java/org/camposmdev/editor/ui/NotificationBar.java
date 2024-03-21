package org.camposmdev.editor.ui;

import com.almasb.fxgl.dsl.FXGL;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class NotificationBar {
    private static NotificationBar singleton;

    public static NotificationBar instance() {
        if (singleton == null)
            singleton = new NotificationBar();
        return singleton;
    }

    private final Label text;
    private final StackPane root;

    private NotificationBar() {
        text = new Label("Notifications Goes Here");
        root = new StackPane(text);
        root.setAlignment(Pos.CENTER_LEFT);
        root.setPadding(new Insets(4, 0, 4, 8));
    }

    public void push(String message) {
        text.setText(message);
        text.setOpacity(1f);
        FXGL.animationBuilder()
                .duration(Duration.millis(500))
                .translate(text).from(new Point2D(-500, text.getTranslateY()))
                .to(new Point2D(text.getTranslateX(), text.getTranslateY())).buildAndPlay();
    }

    public StackPane getContent() {
        return root;
    }
}
