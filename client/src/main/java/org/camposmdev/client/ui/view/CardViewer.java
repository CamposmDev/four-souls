package org.camposmdev.client.ui.view;

import com.almasb.fxgl.texture.Texture;
import javafx.scene.Node;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class CardViewer implements View {
	private static final float SCALE = 2.75f;
	private Texture texture;

	private CardViewer() {
		texture = null;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
		this.texture.setScaleX(SCALE);
		this.texture.setScaleY(SCALE);
	}

	@Override
	public void render() {
//		var x = getAppWidth() - (getAppWidth()/4d) - (texture.getFitWidth()/2d);
		var x = getAppWidth()/2d - texture.getFitWidth()/2d;
		var y = getAppHeight()/2d - texture.getFitHeight()/2d;
		addUINode(texture, x, y);
		animationBuilder()
				.duration(Duration.millis(200))
				.fadeIn(texture)
				.buildAndPlay();
	}

	@Override
	public void dispose() {
		if (texture != null) {
			animationBuilder()
					.onFinished(() -> removeUINode(texture))
					.duration(Duration.millis(200))
					.fadeOut(texture)
					.buildAndPlay();
		}
	}

	@Override
	public Node content() {
		return texture;
	}

	private static CardViewer singleton;

	public static CardViewer instance() {
		if (singleton == null) singleton = new CardViewer();
		return singleton;
	}
}
