package io.github.camposmdev.app.game.ui.scene;

import com.almasb.fxgl.app.scene.IntroScene;
import com.almasb.fxgl.scene.Scene;
import javafx.animation.FadeTransition;
import javafx.scene.Cursor;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import static com.almasb.fxgl.dsl.FXGL.getAssetLoader;

public class FSIntroScene extends IntroScene {
    private MediaView video = null;
    @Override
    public void startIntro() {
        video = getAssetLoader().loadVideo("kickstarter_trailer.mp4");
        video.setCursor(Cursor.NONE);
        video.setFitWidth(getAppWidth());
        video.setFitHeight(getAppHeight());
        var anim = new FadeTransition(Duration.seconds(.5), video);
        anim.setFromValue(0);
        anim.setToValue(1);
        video.setOpacity(0d);
        video.getMediaPlayer().setOnPlaying(anim::playFromStart);
        video.setOnMouseClicked(e -> {
            anim.setRate(-1);
            anim.playFrom(Duration.seconds(.5));
            anim.setOnFinished(ev -> finishIntro());
        });
        video.getMediaPlayer().setOnEndOfMedia(() -> {
            anim.setRate(-1);
            anim.playFrom(Duration.seconds(.5));
            anim.setOnFinished(e -> finishIntro());
        });
        video.getMediaPlayer().setOnError(this::startIntro);
        var pane = new Pane(video);
//        pane.setStyle("-fx-background-color: #1a1a1a;");
        pane.setStyle("-fx-background-color: #000");
        addChild(pane);
        video.getMediaPlayer().play();
    }

    @Override
    public void onExitingTo(@NotNull Scene nextState) {
        super.onExitingTo(nextState);
        video.getMediaPlayer().stop();
    }
}
