package io.github.camposmdev.foursouls.app.game.service;

import com.almasb.fxgl.texture.Texture;
import com.almasb.fxgl.time.TimerAction;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import io.github.camposmdev.foursouls.app.game.entity.component.card.CardRenderer;
import io.github.camposmdev.foursouls.app.game.ui.view.CardPreviewer;
import io.github.camposmdev.foursouls.core.card.BaseCard;

import java.util.concurrent.atomic.AtomicReference;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameTimer;

/**
 * Adds event handlers to entities
 */
public class EntityEvents {
    public <T extends BaseCard> void add_onMouseHover_PreviewAndFeedBack(CardRenderer<T> arg0) {
        add_onMouseHover_Preview(arg0);
        add_onMouseHover_Feedback(arg0);
    }

    /**
     * Adds an event mouse hover handler to preview a card's higher
     * quality image version using {@link CardPreviewer} to display it.
     * @param arg0 The card to preview its higher quality image.
     */
    public <T extends BaseCard> void add_onMouseHover_Preview(CardRenderer<T> arg0) {
        Texture texture = arg0.hiResTexture();
		AtomicReference<TimerAction> timer = new AtomicReference<>();
        final var HOVER_DURATION = 550;
        arg0.texture().addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            timer.set(getGameTimer().runOnceAfter(() -> {
                CardPreviewer.instance().setTexture(texture);
                CardPreviewer.instance().render();
            }, Duration.millis(HOVER_DURATION)));
        });
        arg0.texture().addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            timer.get().expire();
            CardPreviewer.instance().dispose();
        });
    }

    /**
     * Adds an event mouse hover handler to a card by scale up
     * the card a little to give the user feedback that they are indeed hovering
     * over the card.
     * @param arg0 CardComponent to add mouse hover event.
     */
    public <T extends BaseCard> void add_onMouseHover_Feedback(CardRenderer<T> arg0) {
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
