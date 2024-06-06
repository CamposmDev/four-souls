package io.github.camposmdev.foursouls.app.game.ui.controllers.sprite;

import com.almasb.fxgl.texture.Texture;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import io.github.camposmdev.foursouls.app.game.entity.sprite.NightmareSpriteAtlas;
import io.github.camposmdev.foursouls.core.ui.FXUtil;
import io.github.camposmdev.foursouls.core.ui.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;

public class BubbleController extends FXController implements Initializable {
    @FXML StackPane root;
    @FXML Double CANVAS_WIDTH, CANVAS_HEIGHT;
    @FXML Canvas canvas1, canvas2, canvas3;

    private EventHandler<ActionEvent> onFinished;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        var spritesheet = getAssetLoader().loadTexture("spritesheets/nightmare.png");
        var result = FXUtil.loadJSON("spritesheets/nightmare.json", NightmareSpriteAtlas.class);
        var bubbles = result.bubbles();
        var big = spritesheet.subTexture(bubbles.get("big").toR2D());
        var medium = spritesheet.subTexture(bubbles.get("medium").toR2D());
        var small = spritesheet.subTexture(bubbles.get("small").toR2D());
        render(big, medium, small);
        FXUtil.SquashStretch(canvas3, e1 -> FXUtil.SquashStretch(canvas2, e2 -> FXUtil.SquashStretch(canvas1, onFinished)));
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

    public void setOnFinished(EventHandler<ActionEvent> event) {
        this.onFinished = event;
    }
}
