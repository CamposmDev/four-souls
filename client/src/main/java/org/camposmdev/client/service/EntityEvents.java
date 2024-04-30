package org.camposmdev.client.service;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.texture.Texture;
import com.almasb.fxgl.time.TimerAction;
import javafx.geometry.Point2D;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.camposmdev.client.entity.component.card.CardComponent;
import org.camposmdev.client.ui.view.CardViewer;
import org.camposmdev.util.Log;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class EntityEvents {
    /**
     * Adds an event mouse hover handler to preview a card's higher
     * quality image version using {@link CardViewer} to display it
     * @param arg0
     */
    public void onMouseHover_Preview(CardComponent arg0) {
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
     * Adds an event mouse hover handler to highlight a card by scaling
     * the card a little to give the user feedback
     * @param arg0
     */
    public void onMouseHover_Highlight(CardComponent arg0) {
        var width = arg0.texture().getFitWidth();
        var height = arg0.texture().getFitHeight();
        /* scale the card by 5% */
        final var SCALE = 1.05;
        var modX = (width / 2d) * SCALE - width / 2d;
        var modY = (height / 2d) * SCALE - height / 2d;
        arg0.texture().addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            arg0.texture().setX(arg0.texture().getX() - modX);
            arg0.texture().setY(arg0.texture().getY() - modY);
            arg0.texture().setScaleX(SCALE);
            arg0.texture().setScaleY(SCALE);
        });
        arg0.texture().addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            arg0.texture().setX(arg0.texture().getX() + modX);
            arg0.texture().setY(arg0.texture().getY() + modY);
            arg0.texture().setScaleX(1);
            arg0.texture().setScaleY(1);
        });
    }

    /**
     * Display a large scaled view of the entity's view component
     * @param entity View to be scaled
     */
    @Deprecated
    public void onMouseClick_View(Entity entity) {
        if (!entity.hasComponent(CardComponent.class)) {
            Log.warnf("Entity %s is missing CardComponent", entity);
            return;
        }
        var view = entity.getViewComponent();
        AtomicBoolean isShowing = new AtomicBoolean(false);
        view.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (!event.isShortcutDown()) return;
            if (!isShowing.get()) {
                var t = entity.getComponent(CardComponent.class).source1();
                /* add tooltip to make it clear how to remove the card viewer */
                Tooltip.install(t, new Tooltip("Click to make me disappear"));
                final var SCALE = 3;
                var card = entityBuilder()
                        .view(t)
                        .at(new Point2D(getAppWidth()/2d-t.getFitWidth()*SCALE/2d, getAppHeight()/2d-t.getFitHeight()*SCALE/2d))
//                        .at(new Point2D(getAppWidth()/2d-t.getFitWidth()*SCALE/2d, getAppHeight()/2d))
                        .scale(SCALE, SCALE)
                        .buildAndAttach();
                isShowing.set(true);
//                animationBuilder().onFinished(() -> {
//                            isShowing.set(true);
//                        }).duration(Duration.millis(250)).translate(card)
//                        .to(new Point2D(card.getX(), FXGL.getAppHeight()/2d-t.getFitHeight()*SCALE/2d))
//                        .buildAndPlay();
                card.getViewComponent().addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                    card.removeFromWorld();
                    isShowing.set(false);
                });
//                card.getViewComponent().addEventHandler(MouseEvent.MOUSE_CLICKED, event2 -> {
//                    animationBuilder().onFinished(() -> {
//                        card.removeFromWorld();
//                                isShowing.set(false);
//                            }).duration(Duration.millis(200)).translate(card)
//                            .to(new Point2D(card.getX(), FXGL.getAppHeight()))
//                            .buildAndPlay();
//                });
            }
        });
    }

    @Deprecated
    public void onMouseHover_Scale(Entity entity, BoardPosition position) {
        if (!entity.hasComponent(CardComponent.class)) {
            Log.warnf("Entity %s is missing CardComponent", entity);
            return;
        }
        var component = entity.getComponent(CardComponent.class);
        var t = component.texture();
        var view = entity.getViewComponent();
        var width = t.getFitWidth();
        var height = t.getFitHeight();
        final var SCALE = 1.25;
        switch (position) {
            case TOP_LEFT -> {
                view.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> entity.setScaleUniform(SCALE));
                view.addEventHandler(MouseEvent.MOUSE_EXITED, event -> entity.setScaleUniform(1));
            }
            case TOP_CENTER -> {
                /* TODO - Update the math */
                view.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                    entity.setX(entity.getX() - width / 2d);
                    entity.setScaleUniform(2);
                });
                view.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                    entity.setX(entity.getX() + width / 2d);
                    entity.setScaleUniform(1);
                });
            }
            case TOP_RIGHT -> {
                /* TODO - Update the math */
                view.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                    entity.setX(entity.getX() - width);
                    entity.setScaleUniform(SCALE);
                });
                view.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                    entity.setX(entity.getX() + width);
                    entity.setScaleUniform(1);
                });
            }
            case CENTER_LEFT -> {
                /* TODO - Update the math */
                view.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                    entity.setY(entity.getY() - height / 2);
                    entity.setScaleUniform(SCALE);
                });
                view.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                    entity.setY(entity.getY() + height / 2);
                    entity.setScaleUniform(1);
                });
            }
            case CENTER -> {
                var modX = (width/2d)*SCALE-width/2d;
                var modY = (height/2d)*SCALE-height/2d;
                view.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                    entity.setPosition(entity.getX()-modX,entity.getY()-modY);
                    entity.setScaleUniform(SCALE);
                });
                view.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                    entity.setPosition(entity.getX()+modX,entity.getY()+modY);
                    entity.setScaleUniform(1);
                });
            }
            case CENTER_RIGHT -> {
                /* TODO - Update the math */
                view.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                    entity.setX(entity.getX() - width);
                    entity.setY(entity.getY() - height / 2d);
                    entity.setScaleUniform(SCALE);
                });
                view.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                    entity.setX(entity.getX() + width);
                    entity.setY(entity.getY() + height / 2d);
                    entity.setScaleUniform(1);
                });
            }
            case BOTTOM_LEFT -> {
                var modY = height*SCALE-height;
                view.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                    entity.setY(entity.getY() - modY);
                    entity.setScaleUniform(SCALE);
                });
                view.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                    entity.setY(entity.getY() + modY);
                    entity.setScaleUniform(1);
                });
            }
            case BOTTOM_CENTER -> {
                var modX = (width/2d)*SCALE-width/2d;
                var modY = height*SCALE-height;
                view.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                    entity.setX(entity.getX() - modX);
                    entity.setY(entity.getY() - modY);
                    entity.setScaleUniform(SCALE);
                });
                view.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                    entity.setX(entity.getX() + modX);
                    entity.setY(entity.getY() + modY);
                    entity.setScaleUniform(1);
                });
            }
            case BOTTOM_RIGHT -> {
                var modX = width*SCALE-width;
                var modY = height*SCALE-height;
                view.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                    entity.setPosition(entity.getX()-modX, entity.getY()-modY);
                    entity.setScaleUniform(SCALE);
                });
                view.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                    entity.setPosition(entity.getX()+modX, entity.getY()+modY);
                    entity.setScaleUniform(1);
                });
            }
        }
    }
}
