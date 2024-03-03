package org.camposmdev.client.ui;

import com.almasb.fxgl.app.scene.IntroScene;
import javafx.animation.FadeTransition;
import javafx.scene.Cursor;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.getAssetLoader;

public class FSIntroScene extends IntroScene {
    @Override
    public void startIntro() {
        var video = getAssetLoader().loadVideo("kickstarter_trailer.mp4");
        video.setCursor(Cursor.NONE);
        video.setOnMouseClicked(e ->  video.getMediaPlayer().seek(video.getMediaPlayer().getTotalDuration()));
        video.setFitWidth(getAppWidth());
        video.setFitHeight(getAppHeight());
        var anim = new FadeTransition(Duration.seconds(.5), video);
        anim.setFromValue(0);
        anim.setToValue(1);
        video.setOpacity(0d);
        video.getMediaPlayer().setOnPlaying(anim::playFromStart);
        video.getMediaPlayer().setOnEndOfMedia(() -> {
            anim.setRate(-1);
            anim.playFrom(Duration.seconds(.5));
            anim.setOnFinished(e -> finishIntro());
        });
        video.getMediaPlayer().setOnError(this::startIntro);
        var pane = new Pane(video);
        pane.setStyle("-fx-background-color: #1a1a1a;");
        addChild(pane);
        video.getMediaPlayer().play();
    }
}
