package org.camposmdev.client.service;

import com.almasb.fxgl.entity.Entity;
import javafx.scene.image.ImageView;
import org.camposmdev.client.entity.component.card.CardComponent;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

/**
 * Maps entities position in the game world
 */
public class EntityMapper {
    static final int INLINE_MARGIN = 10;
    static final int BLOCK_MARGIN = 10;
    static final int CENTER_OFFSET = 12;

    public void position(Entity entity, BoardPosition position) {
        var view = entity.getViewComponent();
        if (view == null || view.getChildren().isEmpty())
            return;
        if (!(view.getChildren().getFirst() instanceof ImageView t))
            return;
        switch (position) {
            case CENTER_LEFT -> {
                entity.setX(INLINE_MARGIN);
                entity.setY(getAppHeight()/2d - t.getFitHeight()/2d + CENTER_OFFSET);
            }
            case CENTER -> {
                entity.setX(getAppWidth()/2d - t.getFitWidth()/2d);
                entity.setY(getAppHeight()/2d - t.getFitHeight()/2d + CENTER_OFFSET);
            }
            case CENTER_RIGHT -> {
                entity.setX(getAppWidth() - t.getFitWidth() - INLINE_MARGIN);
                entity.setY(getAppHeight()/2d - t.getFitHeight()/2d + CENTER_OFFSET);
            }
            case BOTTOM_LEFT -> {
                entity.setX(INLINE_MARGIN);
                entity.setY(getAppHeight() - t.getFitHeight() - BLOCK_MARGIN);
            }
            case BOTTOM_RIGHT -> {
                entity.setX(getAppWidth() - t.getFitWidth() - INLINE_MARGIN);
                entity.setY(getAppHeight() - t.getFitHeight() - BLOCK_MARGIN);
            }
        }
    }

    /**
     * Set {@code arg0} position next to {@code arg1} with optional {@code spacing}
     * @param arg0 The entity to be positioned next to {@code arg1}
     * @param arg1 The entity that will get a new neighbor
     * @param overlap Percentage of width to overlap {@code arg0} over {@code arg1}
     * @param spacing Distance between {@code arg0} and {@code arg1}
     */
    public void neighbor(Entity arg0, Entity arg1, final double overlap, final int spacing) {
        /* get the width of arg1 */
        var width1 = arg1.getComponent(CardComponent.class).texture().getFitWidth();
        if (overlap == 0) /* put arg0 next to arg1 with spacing if any */
            arg0.setX(arg1.getX() + width1 + spacing);
        else /* put arg0 overlapping arg1 with spacing if any */
            arg0.setX(arg1.getX() + (width1-width1*overlap) + spacing);
        /* set arg0 to be at the same y coordinate as arg1 */
        arg0.setY(arg1.getY());
    }


    public void neighbor(Entity arg0, Entity arg1, final int spacing) {
        neighbor(arg0, arg1, 0, spacing);
    }

    public void neighbor(Entity arg0, Entity arg1) {
        neighbor(arg0, arg1, 0, 0);
    }
}
