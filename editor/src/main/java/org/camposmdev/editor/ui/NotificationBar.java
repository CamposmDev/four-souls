package org.camposmdev.editor.ui;

import com.almasb.fxgl.dsl.FXGL;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.camposmdev.model.card.BaseCard;

public class NotificationBar {
    private static NotificationBar singleton;

    public static NotificationBar instance() {
        if (singleton == null)
            singleton = new NotificationBar();
        return singleton;
    }

    private final Text text;
    private final StackPane root;

    private NotificationBar() {
        text = new Text("Notifications Goes Here");
        text.setWrappingWidth(FXGL.getAppWidth() * 0.7125);
        text.prefWidth(FXGL.getAppWidth());
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

    public void push(BaseCard card) {
        push(card.toString());
    }

    public StackPane getContent() {
        return root;
    }
}
