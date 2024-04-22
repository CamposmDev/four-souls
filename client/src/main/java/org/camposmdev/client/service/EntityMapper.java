package org.camposmdev.client.service;

import com.almasb.fxgl.entity.Entity;
import javafx.scene.image.ImageView;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

class EntityMapper {
    static final int INLINE_MARGIN = 16;
    static final int BLOCK_MARGIN = 16;
    static final int CENTER_OFFSET = 12;

    public void map(Entity entity, BoardPosition position) {
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
}
