package org.camposmdev.client.service;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.texture.Texture;
import javafx.scene.input.MouseEvent;

import java.util.concurrent.atomic.AtomicBoolean;

class EntityAnimator {
    public void setOnMouseHover_Scale(Entity e, BoardPosition position) {
        var view = e.getViewComponent();
        if (view == null) return;
        if (view.getChildren().isEmpty()) return;
        if (!(view.getChildren().getFirst() instanceof Texture t)) return;
        var width = t.getFitWidth();
        var height = t.getFitHeight();
        switch (position) {
            case TOP_LEFT -> {
                view.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> e.setScaleUniform(2));
                view.addEventHandler(MouseEvent.MOUSE_EXITED, event -> e.setScaleUniform(1));
            }
            case TOP_CENTER -> {
                view.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                    e.setX(e.getX() - width / 2d);
                    e.setScaleUniform(2);
                });
                view.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                    e.setX(e.getX() + width / 2d);
                    e.setScaleUniform(1);
                });
            }
            case TOP_RIGHT -> {
                view.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                    e.setX(e.getX() - width);
                    e.setScaleUniform(2);
                });
                view.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                    e.setX(e.getX() + width);
                    e.setScaleUniform(1);
                });
            }
            case CENTER_LEFT -> {
                view.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                    e.setY(e.getY() - height / 2);
                    e.setScaleUniform(2);
                });
                view.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                    e.setY(e.getY() + height / 2);
                    e.setScaleUniform(1);
                });
            }
            case CENTER -> {
                view.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                    e.setX(e.getX() - width / 2d);
                    e.setY(e.getY() - height / 2d);
                    e.setScaleUniform(2);
                });
                view.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                    e.setX(e.getX() + width / 2d);
                    e.setY(e.getY() + height / 2d);
                    e.setScaleUniform(1);
                });
            }
            case CENTER_RIGHT -> {
                view.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                    e.setX(e.getX() - width);
                    e.setY(e.getY() - height / 2d);
                    e.setScaleUniform(2);
                });
                view.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                    e.setX(e.getX() + width);
                    e.setY(e.getY() + height / 2d);
                    e.setScaleUniform(1);
                });
            }
            case BOTTOM_LEFT -> {
                view.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                    e.setY(e.getY() - height);
                    e.setScaleUniform(2);
                });
                view.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                    e.setY(e.getY() + height);
                    e.setScaleUniform(1);
                });
            }
            case BOTTOM_CENTER -> {
                view.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                    e.setX(e.getX() - width / 2d);
                    e.setY(e.getY() - height);
                    e.setScaleUniform(2);
                });
                view.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                    e.setX(e.getX() + width / 2d);
                    e.setY(e.getY() + height);
                    e.setScaleUniform(1);
                });
            }
            case BOTTOM_RIGHT -> {
                view.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                    e.setX(e.getX() - width);
                    e.setY(e.getY() - height);
                    e.setScaleUniform(2);
                });
                view.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                    e.setX(e.getX() + width);
                    e.setY(e.getY() + height);
                    e.setScaleUniform(1);
                });
            }
        }
    }

    @Deprecated
    public void setOnClick_Scale(Entity e, BoardPosition position) {
        var view = e.getViewComponent();
        if (view == null) return;
        if (view.getChildren().isEmpty()) return;
        if (!(view.getChildren().getFirst() instanceof Texture t)) return;
        var width = t.getFitWidth();
        var height = t.getFitHeight();
        var flag = new AtomicBoolean(true);
        switch (position) {
            case BOTTOM_LEFT -> view.addOnClickHandler(event -> {
                if (flag.get()) {
                    e.setY(e.getY() - height);
                    e.setScaleUniform(2);
                } else {
                    e.setY(e.getY() + height);
                    e.setScaleUniform(1);
                }
                flag.set(!flag.get());
            });
        }
    }
}
