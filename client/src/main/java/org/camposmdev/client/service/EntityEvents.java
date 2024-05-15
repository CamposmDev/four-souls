package org.camposmdev.client.service;

import com.almasb.fxgl.texture.Texture;
import com.almasb.fxgl.time.TimerAction;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.camposmdev.client.entity.component.card.CardComponent;
import org.camposmdev.client.ui.view.CardViewer;

import java.util.concurrent.atomic.AtomicReference;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameTimer;

/**
 * Adds event handlers to entities
 */
public class EntityEvents {
    /**
     * Adds an event mouse hover handler to preview a card's higher
     * quality image version using {@link CardViewer} to display it.
     * @param arg0 The card to preview its higher quality image.
     */
    public void add_onMouseHover_Preview(CardComponent arg0) {
        Texture texture = arg0.source1();
		AtomicReference<TimerAction> timer = new AtomicReference<>();
        arg0.texture().addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            timer.set(getGameTimer().runOnceAfter(() -> {
                CardViewer.instance().setTexture(texture);
                CardViewer.instance().render();
            }, Duration.millis(750)));
        });
        arg0.texture().addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            timer.get().expire();
            CardViewer.instance().dispose();
        });
    }

    /**
     * Adds an event mouse hover handler to a card by scale up
     * the card a little to give the user feedback that they are indeed hovering
     * over the card.
     * @param arg0 CardComponent to add mouse hover event.
     */
    public void add_onMouseHover_Feedback(CardComponent arg0) {
        var width = arg0.texture().getFitWidth();
        var height = arg0.texture().getFitHeight();
        /* scale the card by 5% */
        final var SCALE = 1.05;
        var modX = (width / 2d) * SCALE - width / 2d;
        var modY = (height / 2d) * SCALE - height / 2d;
        /* when mouse enters texture, update position and scale up */
        arg0.texture().addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            arg0.texture().setX(arg0.texture().getX() - modX);
            arg0.texture().setY(arg0.texture().getY() - modY);
            arg0.texture().setScaleX(SCALE);
            arg0.texture().setScaleY(SCALE);
        });
        /* when mouse exits texture, update position and scale down */
        arg0.texture().addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            arg0.texture().setX(arg0.texture().getX() + modX);
            arg0.texture().setY(arg0.texture().getY() + modY);
            arg0.texture().setScaleX(1);
            arg0.texture().setScaleY(1);
        });
    }
}
