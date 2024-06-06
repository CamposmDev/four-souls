package io.github.camposmdev.foursouls.app.game.ui.menu;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.Texture;
import io.github.camposmdev.foursouls.app.game.entity.sprite.SecretsSpriteAtlas;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import io.github.camposmdev.foursouls.core.ui.FXUtil;
import io.github.camposmdev.foursouls.core.ui.Log;
import org.jetbrains.annotations.NotNull;

import static com.almasb.fxgl.dsl.FXGL.*;

public class FSGameMenu extends FXGLMenu {
    private static final int BLOCK_MARGIN = 32;
    private final StackPane root;
	private Texture background;
    private VBox menuBox;
    private VBox optionBox;
    private CheckBox cbFullScreen;
    private Slider sfxSlider, musicSlider;
	private AnchorPane bookletPane;

    public FSGameMenu(@NotNull MenuType type) {
        super(type);
        /* init background */
        initBackground();
        /* init option box */
        initOptionBox();
        /* init menu box */
        initMenuBox();
        /* init booklet */
        initGameplayBox();
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
		root.getChildren().setAll(background, menuBox);
		/* remove gameplay view if present */
		removeChild(bookletPane);
		root.setTranslateY(getAppHeight());
	}

	/**
	 * Initializes the background for the main menu.
	 */
	private void initBackground() {
		var texture = FXGL.texture("spritesheets/secrets.png");
		SecretsSpriteAtlas atlas = FXUtil.loadJSON("spritesheets/secrets.json", SecretsSpriteAtlas.class);
		assert atlas != null;
		background = texture.subTexture(atlas.background().toR2D());
		background.setSmooth(false);
		background.setScaleX(2.5);
		background.setScaleY(2.5);
	}

	/**
	 * Initializes the options view to adjust game settings
	 */
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
	 * Initializes the gameplay view that teaches the player how the game works
	 */
	private void initGameplayBox() {
		Platform.runLater(() -> {
			var booklet = FXUtil.loadBooklet();
			booklet.setPrefSize(getAppWidth(), getAppHeight());
			bookletPane = new AnchorPane(booklet);
			bookletPane.setPrefSize(getAppWidth(), getAppHeight());
			var bt = createButton("Back", event -> removeChild(bookletPane));
			var container = new StackPane(bt);
			container.setPrefSize(100, 50);
			bookletPane.getChildren().add(container);
			AnchorPane.setLeftAnchor(container, 0d);
			AnchorPane.setRightAnchor(container, 0d);
			AnchorPane.setBottomAnchor(container, 8d);
		});
	}

	/**
	 * Initializes the main menu that displays when user pauses the game.
	 */
	private void initMenuBox() {
		/* init resume menu */
		var btResume = createButton("Resume", e -> this.fireResume());
		/* init gameplay menu */
		var btBook = createButton("Gameplay", e -> {
			addChild(bookletPane);
		});
		/* init controls menu */
        var btControls = createButton("Controls", e -> {
            Log.info("Implement me!");
            /* TODO - Implement controls menu if any controls available for the player to play the game
            *   besides point and click. Maybe key binds for shortcuts with the user interface
            *   like in RTS games. Make their gameplay more efficient. */
        });
		/* init options menu */
		var btOptions = createButton("Options", e -> {
			root.getChildren().remove(menuBox);
			root.getChildren().add(optionBox);
		});
        /* init exit menu */
        var btExit = createButton("Exit Game", e -> this.fireExitToMainMenu());
        menuBox = new VBox((BLOCK_MARGIN), btResume, btBook, btControls, btOptions, btExit);
        menuBox.setAlignment(Pos.CENTER);
    }

	/**
	 * Utility function to build a button with custom styling.
	 * @param content
	 * @param event
	 * @return
	 */
	private Text createButton(String content, EventHandler<MouseEvent> event) {
		final var FONT_SZ = 28;
		Text node = getUIFactoryService().newText(content, Color.BLACK, FONT_SZ);
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
