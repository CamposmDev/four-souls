package org.camposmdev.client.ui.menu;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.Texture;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import org.camposmdev.client.entity.sprite.SecretsSpriteAtlas;
import org.camposmdev.util.FXUtil;
import org.camposmdev.util.Log;
import org.jetbrains.annotations.NotNull;

import static com.almasb.fxgl.dsl.FXGL.getSettings;
import static com.almasb.fxgl.dsl.FXGL.getUIFactoryService;

public class FSGameMenu extends FXGLMenu {
    private static final int BLOCK_MARGIN = 32;
    private final StackPane root;
    private VBox menuBox;
    private VBox optionBox;
    private CheckBox cbFullScreen;
    private Slider sfxSlider, musicSlider;

    public FSGameMenu(@NotNull MenuType type) {
        super(type);
        Log.info("initialized game menu");
        /* init background */
        Texture background = initBackground();
        /* init option box */
        initOptionBox();
        /* init menu box */
        initMenuBox();
        /* init root container */
        root = new StackPane(background, menuBox);
        root.setPrefWidth(getAppWidth());
        root.setPrefHeight(getAppHeight());
        root.setTranslateY(getAppHeight());
        addChild(root);
    }

    private void initOptionBox() {
        /* init full screen control */
        cbFullScreen = FXGL.getUIFactoryService().newCheckBox();
        cbFullScreen.setOnAction(e -> getSettings().getFullScreen().set(cbFullScreen.isSelected()));
        /* init sfx slider */
        sfxSlider = FXGL.getUIFactoryService().newSlider();
        sfxSlider.setMax(1d);
        sfxSlider.valueProperty().addListener((ov, arg0, arg1) -> getSettings().setGlobalSoundVolume(arg1.doubleValue()));
        sfxSlider.setOnMouseReleased(e -> FXUtil.playDeathSFX((null)));
        /* init music slider */
        musicSlider = FXGL.getUIFactoryService().newSlider();
        musicSlider.setMax(1d);
        musicSlider.valueProperty().addListener((ov, arg0, arg1) -> getSettings().setGlobalMusicVolume(arg1.doubleValue()));
        /* init controls container */
        var controlsBox = new GridPane((BLOCK_MARGIN*3), BLOCK_MARGIN);
        controlsBox.addRow(0, createText("Full Screen"), cbFullScreen);
        controlsBox.addRow(1, createText("SFX Volume"), sfxSlider);
        controlsBox.addRow(2, createText("Music Volume"), musicSlider);
        controlsBox.setAlignment(Pos.CENTER);
        /* init back button */
        var btBack = createButton("Back", e -> {
            root.getChildren().remove(optionBox);
            root.getChildren().add(menuBox);
        });
        /* init option container */
        optionBox = new VBox(BLOCK_MARGIN, controlsBox, btBack);
        optionBox.setAlignment(Pos.CENTER);
    }

    private void initMenuBox() {
        /* init options menu */
        var bt0 = createButton("Options", e -> {
            root.getChildren().remove(menuBox);
            root.getChildren().add(optionBox);
        });
        /* init resume menu */
        var bt1 = createButton("Resume", e -> this.fireResume());
        /* init exit game menu */
        var bt2 = createButton("Exit Game", e -> this.fireExitToMainMenu());
        menuBox = new VBox((BLOCK_MARGIN), bt0, bt1, bt2);
        menuBox.setAlignment(Pos.CENTER);
    }

    private Texture initBackground() {
        var texture = FXGL.texture("spritesheets/secrets.png");
        SecretsSpriteAtlas atlas = FXUtil.loadJSON("spritesheets/secrets.json", SecretsSpriteAtlas.class);
        assert atlas != null;
        var background = texture.subTexture(atlas.background().toR2D());
        background.setSmooth(false);
        background.setScaleX(2.5);
        background.setScaleY(2.5);
        return background;
    }

    private Text createButton(String content, EventHandler<MouseEvent> event) {
        Text node = getUIFactoryService().newText(content, Color.BLACK, 32);
        node.setTextAlignment(TextAlignment.CENTER);
        if (event == null) return node;
        node.setOnMouseClicked(event);
        node.setOnMouseEntered(e -> {
            node.setScaleX(1.25);
            node.setScaleY(1.25);
        });
        node.setOnMouseExited(e -> {
            node.setScaleX(1);
            node.setScaleY(1);
        });
        return node;
    }

    private Text createText(String content) {
        return createButton(content, null);
    }

    @Override
    public void onCreate() {
        cbFullScreen.setSelected(getSettings().getFullScreen().getValue());
        sfxSlider.setValue(getSettings().getGlobalSoundVolume());
        musicSlider.setValue(getSettings().getGlobalMusicVolume());
        FXUtil.animation().translate(root).to(new Point2D(0,0)).duration(Duration.millis(200)).build().play();
    }

    @Override
    public void onDestroy() {
        /* return to the menu box */
        if (!root.getChildren().contains(menuBox)) {
            root.getChildren().setAll(initBackground(), menuBox);
        }
        root.setTranslateY(getAppHeight());
//        FXUtil.animation().translate(root).to(new Point2D(0,getAppHeight())).duration(Duration.millis(200)).build().play();
    }
}
