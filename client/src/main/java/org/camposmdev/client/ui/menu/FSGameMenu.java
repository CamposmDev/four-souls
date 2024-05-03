package org.camposmdev.client.ui.menu;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.Texture;
import javafx.application.Platform;
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
import javafx.scene.web.WebView;
import javafx.util.Duration;
import org.camposmdev.client.entity.sprite.SecretsSpriteAtlas;
import org.camposmdev.util.FXUtil;
import org.camposmdev.util.Log;
import org.jetbrains.annotations.NotNull;

import static com.almasb.fxgl.dsl.FXGL.*;

public class FSGameMenu extends FXGLMenu {
    private static final int BLOCK_MARGIN = 32;
    private final StackPane root;
    private VBox menuBox;
    private VBox optionBox;
    private CheckBox cbFullScreen;
    private Slider sfxSlider, musicSlider;
    private WebView booklet;

    public FSGameMenu(@NotNull MenuType type) {
        super(type);
        /* init background */
        var background = initBackground();
        /* init option box */
        initOptionBox();
        /* init menu box */
        initMenuBox();
        /* init booklet */
        Platform.runLater(() -> {
            booklet = FXUtil.loadBooklet();
            booklet.setPrefSize(getAppWidth(), getAppHeight());
        });
        /* init root container */
        root = new StackPane(background, menuBox);
        root.setPrefWidth(getAppWidth());
        root.setPrefHeight(getAppHeight());
        root.setTranslateY(getAppHeight());
        addChild(root);
    }

	@Override
	public void onCreate() {
		/* set the current state if the game is full screen */
		cbFullScreen.setSelected(getSettings().getFullScreen().getValue());
		/* set the current volume for sfxSlider */
		sfxSlider.setValue(getSettings().getGlobalSoundVolume());
		/* set the current volume for musicSlider */
		musicSlider.setValue(getSettings().getGlobalMusicVolume());
		/* translate the menu */
		FXUtil.animation().translate(root).to(new Point2D(0,0)).duration(Duration.millis(200)).build().play();
		play("feedback/book page turn.wav");
	}

	@Override
	public void onDestroy() {
		/* return to the menu box */
		if (!root.getChildren().contains(menuBox)) {
			root.getChildren().setAll(initBackground(), menuBox);
		}
		removeChild(booklet);
		root.setTranslateY(getAppHeight());
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

	/**
	 * Initializes the main menu that displays when user pauses the game.
	 */
	private void initMenuBox() {
        /* init options menu */
        var btOptions = createButton("Options", e -> {
            root.getChildren().remove(menuBox);
            root.getChildren().add(optionBox);
        });
        var btControls = createButton("Controls", e -> {
            Log.info("Implement me!");
            /* TODO - Implement controls menu if any controls available for the player to play the game
            *   besides point and click. Maybe key binds for shortcuts with the user interface
            *   like in RTS games. Make their gameplay more efficient. */
        });
        var btBook = createButton("How to Play", e -> {
            addChild(booklet);
        });
        /* init resume menu */
        var btResume = createButton("Resume", e -> this.fireResume());
        /* init exit game menu */
        var btExit = createButton("Exit Game", e -> this.fireExitToMainMenu());
        menuBox = new VBox((BLOCK_MARGIN), btResume, btBook, btControls, btOptions, btExit);
        menuBox.setAlignment(Pos.CENTER);
    }

	/**
	 * Initializes the background for the main menu.
	 * @return texture of game board
	 */
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

	/**
	 * Utility function to build a button with custom styling.
	 * @param content
	 * @param event
	 * @return
	 */
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

	/**
	 * Utility function to build text with custom styling.
	 * @param content
	 * @return
	 */
	private Text createText(String content) {
		return createButton(content, null);
	}

}
