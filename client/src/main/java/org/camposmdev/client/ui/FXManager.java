package org.camposmdev.client.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.web.WebView;
import java.io.IOException;
import java.net.URL;

public class FXManager {
    private static final String FX_DIR = "./assets/ui/fxml/";

    public static <T> T loadUI(String src) {
        try {
            URL url = FXManager.class.getClassLoader().getResource(FX_DIR + src);
            assert url != null : "Failed to load " + src;
            return FXMLLoader.load(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static FXMLLoader load(String src) {
        try {
            URL url = FXManager.class.getClassLoader().getResource(FX_DIR + src);
            return new FXMLLoader(url);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static WebView loadBackground() {
        var res = FXManager.class.getClassLoader().getResource("./assets/ui/background/index.html");
        assert res != null : "Failed to load background";
        var wv = new WebView();
        wv.getEngine().load(res.toExternalForm());
        wv.setDisable(true);
        return wv;
    }

    public static void playSound(String src, Runnable onEnd) {
        var url = FXManager.class.getClassLoader().getResource("./assets/sounds/" + src);
        assert url != null : "Failed to load " + src;
        var mp = new MediaPlayer(new Media(url.toString()));
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
        playSound("death/death" + i + ".wav", onEnd);
    }
}
