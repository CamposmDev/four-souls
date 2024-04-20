package org.camposmdev.util;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.Texture;
import com.almasb.fxgl.ui.UI;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.web.WebView;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class FXUtil {
    private static final String UI_DIR = "assets/ui/";
    private static final String WEB_DIR = "assets/ui/web/";
    private static final String SFX_DIR = "assets/sounds/";

    /**
     * Loads the FXML file and returns the view and controller
     * @param src File name of FXML file
     * @return UI object if loaded successfully, otherwise null
     */
    public static UI loadUI(String src) {
        try {
            URL url = FXUtil.class.getClassLoader().getResource(UI_DIR + src);
            var fxml = new FXMLLoader(url);
            var node = (Parent) fxml.load();
            /* Call controller's init method */
            ((FXController) fxml.getController()).init();
            return new UI(node, fxml.getController());
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    /**
     * Loads the FXML and returns the view only.
     * @param src File name of FXML file
     * @return A type of node, otherwise null
     * @param <T> Expected to be a type of Node
     */
    public static <T extends Parent> T loadFXML(String src) {
        try {
            URL url = FXUtil.class.getClassLoader().getResource(UI_DIR + src);
            assert url != null : "Failed to load " + src;
            var fxml = new FXMLLoader();
            fxml.setLocation(url);
            return fxml.load();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public static <T> T loadJSON(String src, Class<T> type) {
        var result = FXGL.getAssetLoader().loadJSON(src, type);
        assert result.isPresent() : "Failed to find " + src;
        return result.get();
    }

    public static Texture loadCard(String src) {
        final var FIT_WIDTH = 120;
        final var FIT_HEIGHT = 176;
        final var ARC_SIZE = 24;
        var iv = FXGL.texture(src);
        var img = iv.getImage();
//        var clip = new Rectangle();
//        clip.setArcWidth(ARC_SIZE);
//        clip.setArcHeight(ARC_SIZE);
        if (img.getWidth() < img.getHeight()) {
            iv.setFitWidth(FIT_WIDTH);
            iv.setFitHeight(FIT_HEIGHT);
//            clip.setWidth(img.getWidth());
//            clip.setHeight(img.getHeight());
        } else {
            iv.setFitWidth(FIT_HEIGHT);
            iv.setFitHeight(FIT_WIDTH);
//            clip.setWidth(img.getHeight());
//            clip.setHeight(img.getWidth());
        }
//        iv.setClip(clip);
        Platform.runLater(() -> {
//            SnapshotParameters parameters = new SnapshotParameters();
//            parameters.setFill(Color.TRANSPARENT);
//            var w_img = iv.snapshot(parameters, null);
//            iv.setClip(null);
//            iv.setImage(w_img);
//            if (w_img.getWidth() < w_img.getHeight()) {
//                iv.setFitWidth(FIT_WIDTH);
//                iv.setFitHeight(FIT_HEIGHT);
//            } else {
//                iv.setFitWidth(FIT_HEIGHT);
//                iv.setFitHeight(FIT_WIDTH);
//            }
            iv.setEffect(new DropShadow(12, 8, 12, Color.BLACK));
        });
        return iv;
    }

    public static WebView loadSpace() {
        var res = FXUtil.class.getClassLoader().getResource(WEB_DIR  + "space/index.html");
        assert res != null : "Failed to load space";
        var wv = new WebView();
        wv.getEngine().load(res.toExternalForm());
        wv.getEngine().setJavaScriptEnabled(true);
        wv.setDisable(true);
        return wv;
    }

    public static WebView loadBooklet() {
        var res = FXUtil.class.getClassLoader().getResource(WEB_DIR  + "booklet/index.html");
        assert res != null : "Failed to load booklet";
        var wv = new WebView();
        wv.getEngine().load(res.toExternalForm());
        wv.getEngine().setJavaScriptEnabled(true);
        wv.setDisable(false);
        return wv;
    }

    public static void playSFX(String src, Runnable onEnd) {
        var url = FXUtil.class.getClassLoader().getResource(SFX_DIR + src);
        assert url != null : "Failed to load " + src;
        var mp = new MediaPlayer(new Media(url.toString()));
        mp.setVolume(FXGL.getSettings().getGlobalSoundVolume());
        mp.setOnEndOfMedia(onEnd);
        mp.play();
    }

    public static void playSFX(String src) {
        playSFX(src, null);
    }

    public static void playDeathSFX(Runnable onEnd) {
        final var NUM_OF_FILES = 3;
        /* fetch a random file in the folder */
        var i = (int)(Math.random() * NUM_OF_FILES) + 1;
        var src = "death/death" + i + ".wav";
        playSFX(src, onEnd);
    }

    public static AnimationBuilder animation() {
        return new AnimationBuilder();
    }

    public static void SquashStretch(Node node, EventHandler<ActionEvent> handler) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new KeyValue(node.opacityProperty(), 0, Interpolator.LINEAR),
                        new KeyValue(node.scaleXProperty(), 0.1, Interpolator.LINEAR),
                        new KeyValue(node.scaleYProperty(), 0.1, Interpolator.LINEAR)), // Fade-in from 0 opacity and small size
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(node.opacityProperty(), 1, Interpolator.LINEAR),
                        new KeyValue(node.scaleXProperty(), 1.0, Interpolator.LINEAR),
                        new KeyValue(node.scaleYProperty(), 1.0, Interpolator.LINEAR)), // Fade to full opacity and original size
                new KeyFrame(Duration.seconds(1.01), new KeyValue(node.scaleXProperty(), 1.2, Interpolator.EASE_BOTH)),
                new KeyFrame(Duration.seconds(1.03), new KeyValue(node.scaleXProperty(), 0.8, Interpolator.EASE_BOTH)),
                new KeyFrame(Duration.seconds(1.05), new KeyValue(node.scaleXProperty(), 1.1, Interpolator.EASE_BOTH)),
                new KeyFrame(Duration.seconds(1.07), new KeyValue(node.scaleXProperty(), 0.9, Interpolator.EASE_BOTH)),
                new KeyFrame(Duration.seconds(1.1), new KeyValue(node.scaleXProperty(), 1.0, Interpolator.EASE_BOTH))
        );
        timeline.setRate(3);
        timeline.setAutoReverse(false);
        timeline.setCycleCount(1);
        timeline.setOnFinished(handler);
        timeline.play();
    }

    public static String getByteRange() {
        return String.format("[%d, %d]", Byte.MIN_VALUE, Byte.MAX_VALUE);
    }

    public static void initNumberFields(TextField... nodes) {
        for (var textfield : nodes) {
            initNumberField(textfield);
        }
    }

    private static void initNumberField(TextField tf) {
        tf.setText("0");
        // Add scroll event listener to the text field
        tf.addEventFilter(ScrollEvent.SCROLL, event -> {
            // Get current number from the text field, or set to 0 if blank
            short number = 0;
            try {
                number = Short.parseShort(tf.getText().trim());
            } catch (NumberFormatException ignored) {
            }
            // Increment or decrement the number based on scroll direction
            if (event.getDeltaY() < 0) {
                number--;
            } else {
                number++;
            }

            // Loop around if number exceeds range [-128,127]
            if (number > Byte.MAX_VALUE) {
                number = Byte.MIN_VALUE;
            } else if (number < Byte.MIN_VALUE) {
                number = Byte.MAX_VALUE;
            }

            // Update the text field with the new number
            tf.setText(String.valueOf(number));
        });
    }
}
