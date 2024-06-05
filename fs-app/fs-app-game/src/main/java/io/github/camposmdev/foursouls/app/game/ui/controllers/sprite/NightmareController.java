package io.github.camposmdev.foursouls.app.game.ui.controllers.sprite;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.texture.Texture;
import io.github.camposmdev.foursouls.app.game.entity.sprite.NightmareSpriteAtlas;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import io.github.camposmdev.foursouls.model.ui.FXUtil;
import io.github.camposmdev.foursouls.model.ui.FXController;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getSettings;

public class NightmareController extends FXController implements Initializable {
    @FXML
    StackPane root;
    @FXML
    Double CANVAS_WIDTH, CANVAS_HEIGHT;
    @FXML
    Double LIGHT_OFFSET_Y, BOY_OFFSET_Y;
    @FXML
    ObservableList<String> outliars;
    @FXML
    Canvas canvas1, canvas2, canvas3;

    private SequentialTransition boySeqAnim;

    private void updateWhoAmI(String whoami) {
        if (boySeqAnim == null) {
            /* define animation */
            var anim1 = FXUtil.animation().translate(canvas2)
                    .duration(Duration.millis(50))
                    .build();
            anim1.setByX(1);
            anim1.setInterpolator(Interpolator.LINEAR);
            var anim2 = FXUtil.animation().translate(canvas2)
                    .duration(Duration.millis(50))
                    .build();
            anim2.setByX(-2);
            anim2.setInterpolator(Interpolator.LINEAR);
            var anim3 = FXUtil.animation().translate(canvas2)
                    .duration(Duration.millis(50))
                    .build();
            anim3.setByX(1);
            anim3.setInterpolator(Interpolator.LINEAR);
            boySeqAnim = new SequentialTransition(anim1, anim2, anim3);
            boySeqAnim.setCycleCount(TranslateTransition.INDEFINITE);
        }
        if (outliars.contains(whoami)) {
            boySeqAnim.playFromStart();
            boySeqAnim.stop();
        } else boySeqAnim.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        var spritesheet = getAssetLoader().loadTexture("spritesheets/nightmare.png");
        var data = FXUtil.loadJSON("json/spritesheets/nightmare.json", NightmareSpriteAtlas.class);
        var floorIndex = (int)(Math.random()*data.floors().length);
        renderFloor(spritesheet, data, floorIndex);
        var boyList = data.isaacs().keySet().toArray();
        var boyKey = (String) boyList[(int)(Math.random()*boyList.length)];
        renderBoy(spritesheet, data, boyKey);
        renderLight(spritesheet, data);
        if (getSettings().getApplicationMode() == ApplicationMode.DEVELOPER)
            debug(spritesheet, data);
        canvas3.setScaleX(0.1);
        var anim = new ScaleTransition(Duration.millis(500), canvas3);
        anim.setToX(1d);
        anim.playFromStart();
    }

    private void renderFloor(Texture spritesheet, NightmareSpriteAtlas data, int index) {
        var ctx = canvas1.getGraphicsContext2D();
        var floors = data.floors();
        var box = floors[index];
        var floor = spritesheet.subTexture(box.toR2D());
        ctx.drawImage(floor.getImage(), (CANVAS_WIDTH - floor.getWidth()) / 2d, CANVAS_HEIGHT - floor.getHeight());
    }

    private void renderBoy(Texture spritesheet, NightmareSpriteAtlas data, String whoami) {
        updateWhoAmI(whoami);
        var ctx = canvas2.getGraphicsContext2D();
        var box = data.isaacs().get(whoami);
        var boy = spritesheet.subTexture(box.toR2D());
        var x = (CANVAS_WIDTH-boy.getWidth())/2d;
        var y = CANVAS_HEIGHT-boy.getHeight();
        ctx.drawImage(boy.getImage(), x, y);
    }

    private void renderLight(Texture spritesheet, NightmareSpriteAtlas data) {
        var ctx = canvas3.getGraphicsContext2D();
        var light = spritesheet.subTexture(data.light().toR2D());
        ctx.drawImage(light.getImage(), 0, -LIGHT_OFFSET_Y);
    }

    private void debug(Texture spritesheet, NightmareSpriteAtlas data) {
        /* switch between floors */
        {
            var ctx = canvas1.getGraphicsContext2D();
            AtomicInteger i = new AtomicInteger(0);
            root.setOnScroll(event -> {
                ctx.clearRect(0,0,CANVAS_WIDTH,CANVAS_HEIGHT);
                renderFloor(spritesheet, data, i.getAndIncrement());
                if (i.get() > data.floors().length-1)
                    i.set(0);
            });
        }
        /* switch between isaacs */
        {
            var ctx = canvas2.getGraphicsContext2D();
            var lst = data.isaacs().keySet().toArray();
            AtomicInteger i = new AtomicInteger(0);
            root.setOnMouseClicked(event -> {
                ctx.clearRect(0,0,CANVAS_WIDTH,CANVAS_HEIGHT);
                var key = (String) lst[i.getAndIncrement()];
                if (key.equals("the_capricious")) {
                    /* TODO - Render head of tainted eden */
                    System.err.println("NOT YET IMPLEMENTED");
                }
                renderBoy(spritesheet, data, key);
                if (i.get() > lst.length-1)
                    i.set(0);
            });
        }
    }
}
