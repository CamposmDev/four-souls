package io.github.camposmdev.foursouls.app.game.entity;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.texture.Texture;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import io.github.camposmdev.foursouls.model.ui.FXUtil;
import org.jetbrains.annotations.NotNull;

public class TextureButtonEntity extends Entity {
	private boolean disable;
	private final double SCALE = 1.05;
	private final Texture textureHover;
	private final Texture texturePressed;
	private Runnable runnable;
	private final EventHandler<MouseEvent> onClickHandler;

	public TextureButtonEntity(String src) {
		Texture texture = FXUtil.loadCard(src);
		textureHover = texture.toGrayscale();
		textureHover.setFitWidth(texture.getFitWidth());
		textureHover.setFitHeight(texture.getFitHeight());
		texturePressed = texture.darker();
		texturePressed.setFitWidth(texture.getFitWidth());
		texturePressed.setFitHeight(texture.getFitHeight());
		runnable = () -> {};
		var width = texture.getFitWidth();
		var height = texture.getFitHeight();
		var modX = (width / 2d) * SCALE - width / 2d;
		var modY = (height / 2d) * SCALE - height / 2d;
		onClickHandler = e -> {
			if (!disable)
				runnable.run();
			getViewComponent().removeChild(texturePressed);
		};
		EventHandler<MouseEvent> onEnteredHandler = e -> {
			setPosition(new Point2D(getX() - modX, getY() - modY));
			setScaleUniform(SCALE);
		};
		EventHandler<MouseEvent> onExitedHandler = e -> {
			setPosition(new Point2D(getX() + modX, getY() + modY));
			setScaleUniform(1.0);
		};
		getViewComponent().addChild(texture);
		getViewComponent().addOnClickHandler(onClickHandler);
		getViewComponent().addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
			if (!disable)
				getViewComponent().addChild(texturePressed);
		});
		getViewComponent().addEventHandler(MouseEvent.MOUSE_ENTERED, onEnteredHandler);
		getViewComponent().addEventHandler(MouseEvent.MOUSE_EXITED, onExitedHandler);
	}

	public void setOnClick(@NotNull Runnable runnable) {
		this.runnable = runnable;
	}

	public boolean isDisable() {
		return disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
		final var view = getViewComponent();
		if (disable) {
			view.addChild(textureHover);
			view.removeOnClickHandler(onClickHandler);
		} else {
			view.removeChild(textureHover);
			view.addOnClickHandler(onClickHandler);
		}
	}
}
