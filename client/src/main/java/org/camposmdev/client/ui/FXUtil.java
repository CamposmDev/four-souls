package org.camposmdev.client.ui;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.ui.UI;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URL;

public class FXUtil {
    private static final String FX_DIR = "./assets/ui/";

    /**
     * Loads the FXML file and returns the view and controller
     * @param src File name of FXML file
     * @return UI object if loaded successfully, otherwise null
     */
    public static UI loadUI(String src) {
        try {
            URL url = FXUtil.class.getClassLoader().getResource(FX_DIR + src);
            assert url != null : "Failed to load " + src;
            var fxml = new FXMLLoader(url);
            var node = (Parent) fxml.load();
            return new UI(node, fxml.getController());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Loads the FXML and returns the view only.
     * @param src File name of FXML file
     * @return A type of node, otherwise null
     * @param <T> Expected to be a type of Node
     */
    public static <T> T loadFXML(String src) {
        try {
            URL url = FXUtil.class.getClassLoader().getResource(FX_DIR + src);
            assert url != null : "Failed to load " + src;
            return FXMLLoader.load(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static WebView loadBG() {
        var res = FXUtil.class.getClassLoader().getResource("./assets/ui/background/index.html");
        assert res != null : "Failed to load background";
        var wv = new WebView();
        wv.getEngine().load(res.toExternalForm());
        wv.setDisable(true);
        return wv;
    }

    public static void playSound(String src, Runnable onEnd) {
        var url = FXUtil.class.getClassLoader().getResource("./assets/sounds/" + src);
        assert url != null : "Failed to load " + src;
        var mp = new MediaPlayer(new Media(url.toString()));
        mp.setVolume(FXGL.getSettings().getGlobalSoundVolume());
        mp.setOnEndOfMedia(onEnd);
        mp.play();
    }

    public static void playSound(String src) {
        playSound(src, null);
    }

    public static void playDeathSound(Runnable onEnd) {
        final var NUM_OF_FILES = 3;
        /* fetch a random file in the folder */
        var i = (int)(Math.random() * NUM_OF_FILES) + 1;
        var src = "death/death" + i + ".wav";
        playSound(src, onEnd);
    }

    public static AnimationBuilder animation() {
        return new AnimationBuilder();
    }
}
