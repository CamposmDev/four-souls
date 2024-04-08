package org.camposmdev.client.game.component;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

public class D6AnimationComponent extends Component {
    private static final int N_SIDES = 6;
    private static final int FRAMES_PER_ROW = 12;
    private static final int FRAME_WIDTH = 90, FRAME_HEIGHT = 95;
    private int idx;
    private final AnimatedTexture animTexture;
    private final AnimationChannel[] sides;
    private final AnimationChannel animRoll;
    public D6AnimationComponent() {
        /* load the spritesheet */
        var spritesheet = image("d6_red.png");
        /* initialize animations for each side of the die */
        sides = new AnimationChannel[N_SIDES];
        for (int i = 0; i < sides.length; i++) {
            this.sides[i] = new AnimationChannel(spritesheet, FRAMES_PER_ROW, FRAME_WIDTH, FRAME_HEIGHT,
                    Duration.ONE, i, i);
        }
        /* initialize value animation */
        animRoll = new AnimationChannel(spritesheet, FRAMES_PER_ROW, FRAME_WIDTH, FRAME_HEIGHT,
                Duration.seconds(1), 6, 11);
        animTexture = new AnimatedTexture(sides[0]);
        transition = translateUpAndBack(animTexture);
    }

    private final TranslateTransition transition;

    @Override
    public void onAdded() {
        final var SCALE = 1;
        entity.getTransformComponent().setX(getAppWidth() - FRAME_WIDTH * SCALE);
        entity.getTransformComponent().setY(getAppHeight() - FRAME_HEIGHT * SCALE);
        entity.getViewComponent().addChild(animTexture);
    }

    public TranslateTransition translateUpAndBack(Node node) {
        TranslateTransition x = new TranslateTransition(Duration.seconds(.5), node);
        // Move the node up by 250 pixels
        x.setByY(-250);
        // Set the cycle count to 2 to move up and back
        x.setCycleCount(2);
        // Auto reverse to move back to the original position
        x.setAutoReverse(true);
//        x.setOnFinished(e -> {
//            idx = (int) (Math.random() * N_SIDES);
//            animTexture.playAnimationChannel(sides[idx]);
//        });
        return x;
    }

    public Future<Integer> roll() {
        Promise<Integer> promise = Promise.promise();
        if (transition.getStatus() != Animation.Status.RUNNING) {
            transition.setOnFinished(e -> {
                idx = (int) (Math.random() * N_SIDES);
                animTexture.playAnimationChannel(sides[idx]);
                promise.complete(idx + 1);
            });
            transition.play();
            animTexture.loopAnimationChannel(animRoll);
        }
        return promise.future();
    }

    public int value() {
        return idx + 1;
    }
}
