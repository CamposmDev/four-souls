package org.camposmdev.client.ui.controllers.sprite;

import com.almasb.fxgl.texture.Texture;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.camposmdev.client.model.json.NightmareSpriteAtlas;
import org.camposmdev.client.ui.controllers.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;

public class BubbleController extends FXController implements Initializable {
    @FXML StackPane root;
    @FXML Double CANVAS_WIDTH, CANVAS_HEIGHT;
    @FXML Canvas canvas1, canvas2, canvas3;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        var spritesheet = getAssetLoader().loadTexture("spritesheets/nightmare.png");
        var result = getAssetLoader().loadJSON("json/spritesheets/nightmare.json", NightmareSpriteAtlas.class);
        assert result.isPresent() : "Failed to parse JSON";
        var bubbles = result.get().getBubbles();
        var big = spritesheet.subTexture(bubbles.get("big").toR2D());
        var medium = spritesheet.subTexture(bubbles.get("medium").toR2D());
        var small = spritesheet.subTexture(bubbles.get("small").toR2D());
        render(big, medium, small);
        canvas1.setOpacity(0);
        canvas2.setOpacity(0);
        canvas3.setOpacity(0);
        animate(canvas3, e1 -> animate(canvas2, e2 -> animate(canvas1, null)));
    }

    private void render(Texture big, Texture medium, Texture small) {
        /* draw big bubble */
        var ctx1 = canvas1.getGraphicsContext2D();
        var x1 = (CANVAS_WIDTH-big.getWidth())/2d;
        var y1 = 0;
        ctx1.drawImage(big.getImage(), x1, y1);
        /* draw medium bubble */
        var ctx2 = canvas2.getGraphicsContext2D();
        var x2 = (CANVAS_WIDTH-big.getWidth()*0.45)/2d;
        var y2 = big.getHeight() - medium.getHeight()*0.5;
        ctx2.drawImage(medium.getImage(), x2, y2);
        /* draw small bubble */
        var ctx3 = canvas3.getGraphicsContext2D();
//        var x3 = CANVAS_WIDTH/2d - small.getWidth()*0.5;
        var x3 = x2 + small.getWidth()*1.5;
        var y3 = big.getHeight() + 5;
        ctx3.drawImage(small.getImage(), x3, y3);
    }

    private void animate(Node node, EventHandler<ActionEvent> handler) {
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
}
