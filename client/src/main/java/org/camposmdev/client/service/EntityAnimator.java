package org.camposmdev.client.service;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.texture.Texture;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicBoolean;

public class EntityAnimator {
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

    @Deprecated
    public RotateTransition rotateOut(Node node) {
        RotateTransition rotator = new RotateTransition(Duration.millis(500), node);
        rotator.setAxis(Rotate.Y_AXIS);
        rotator.setFromAngle(0);
        rotator.setToAngle(90);
        rotator.setInterpolator(Interpolator.LINEAR);
        rotator.setCycleCount(1);
        return rotator;
    }

    @Deprecated
    public RotateTransition rotateIn(Node node) {
        RotateTransition rotator = new RotateTransition(Duration.millis(500), node);
        rotator.setAxis(Rotate.Y_AXIS);
        rotator.setFromAngle(90);
        rotator.setToAngle(0);
        rotator.setInterpolator(Interpolator.LINEAR);
        rotator.setCycleCount(1);
        return rotator;
    }
}
