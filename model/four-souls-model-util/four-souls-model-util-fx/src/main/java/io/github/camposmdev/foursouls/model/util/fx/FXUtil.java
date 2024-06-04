package io.github.camposmdev.foursouls.model.util.fx;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.Texture;
import com.almasb.fxgl.ui.UI;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.ScrollEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import javafx.util.Duration;
import io.github.camposmdev.foursouls.model.atlas.MasterCardAtlas;

import java.io.IOException;
import java.net.URL;

public class FXUtil {
    private static final String TEXTURE_DIR = "assets/textures/";
    private static final String UI_DIR = "assets/ui/";
    private static final String WEB_DIR = "assets/ui/web/";
    private static final String SFX_DIR = "assets/sounds/";
    private static final String JSON_DIR = "json/";

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

    @SuppressWarnings("unchecked")
    public static <T> T loadJSON(String src, Class<T> type) {
        /* if the class type is not MasterCardAtlas, then deserialize like a normal JSON */
        if (type != MasterCardAtlas.class) {
            var result = FXGL.getAssetLoader().loadJSON((JSON_DIR + src), type);
            assert result.isPresent();
            return result.get();
        }
        /* otherwise use the custom deserializer from MasterCardAtlas */
        final var name = "assets/" + JSON_DIR + src;
        try (var input = FXUtil.class.getClassLoader().getResourceAsStream(name)) {
            assert input != null : ("Failed to load " + src);
            var bytes = input.readAllBytes();
            var atlas = MasterCardAtlas.deserialize(bytes);
            return (T) atlas;
        } catch (IOException ex) {
            return null;
        }
    }

    public static Texture loadCard(String src) {
        final var WIDTH_SZ = 96;
        final var HEIGHT_SZ = 139;
        Texture texture = FXGL.texture(src);
        Image img = texture.getImage();
        if (img.getWidth() < img.getHeight()) {
            texture.setFitWidth(WIDTH_SZ);
            texture.setFitHeight(HEIGHT_SZ);
        } else {
            texture.setFitWidth(HEIGHT_SZ);
            texture.setFitHeight(WIDTH_SZ);
        }
//        Rectangle clip = new Rectangle(texture.getFitWidth(), texture.getFitHeight());
//        clip.setArcWidth(10);
//        clip.setArcHeight(10);
//        texture.setClip(clip);
        var dropShadow = new DropShadow(BlurType.GAUSSIAN, Color.rgb(0,0,0,0.75), 12, 0.25, 4,4);
        texture.setEffect(dropShadow);
//        clip.setEffect(dropShadow);
        return texture;
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
        var src = "player/death" + i + ".wav";
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
