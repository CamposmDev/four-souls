package io.github.camposmdev.foursouls.model.ui;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.util.Duration;

public class AnimationBuilder {
    public FadeOutAnimationBuilder fadeOut(Node node) {
        return new FadeOutAnimationBuilder(node);
    }

    public TranslateAnimationBuilder translate(Node node) {
        return new TranslateAnimationBuilder(node);
    }

    public FadeInAnimationBuilder fadeIn(Node node) {
        return new FadeInAnimationBuilder(node);
    }

    public static class FadeOutAnimationBuilder {
        private final FadeTransition fadeTransition;

        public FadeOutAnimationBuilder(Node node) {
            fadeTransition = new FadeTransition();
            node.setOpacity(1d);
            fadeTransition.setNode(node);
            fadeTransition.setToValue(0);
        }

        public FadeOutAnimationBuilder onFinished(EventHandler<ActionEvent> e) {
            fadeTransition.setOnFinished(e);
            return this;
        }

        public FadeOutAnimationBuilder duration(Duration duration) {
            fadeTransition.setDuration(duration);
            return this;
        }

        public FadeTransition build() {
            return fadeTransition;
        }
    }

    public static class FadeInAnimationBuilder {
        private final FadeTransition fadeTransition;

        public FadeInAnimationBuilder(Node node) {
            fadeTransition = new FadeTransition();
            node.setOpacity(0d);
            fadeTransition.setNode(node);
            fadeTransition.setToValue(1d);
        }

        public FadeInAnimationBuilder onFinished(EventHandler<ActionEvent> e) {
            fadeTransition.setOnFinished(e);
            return this;
        }

        public FadeInAnimationBuilder duration(Duration duration) {
            fadeTransition.setDuration(duration);
            return this;
        }

        public FadeTransition build() {
            return fadeTransition;
        }
    }

    public static class TranslateAnimationBuilder {
        private final TranslateTransition translateTransition;

        public TranslateAnimationBuilder(Node node) {
            translateTransition = new TranslateTransition();
            translateTransition.setNode(node);
        }

        public TranslateAnimationBuilder onFinished(EventHandler<ActionEvent> e) {
            translateTransition.setOnFinished(e);
            return this;
        }

        public TranslateAnimationBuilder duration(Duration duration) {
            translateTransition.setDuration(duration);
            return this;
        }

        public TranslateAnimationBuilder to(Point2D to) {
            translateTransition.setToX(to.getX());
            translateTransition.setToY(to.getY());
            return this;
        }

        public TranslateTransition build() {
            return translateTransition;
        }
    }
}
