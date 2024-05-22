package org.camposmdev.client.ui.view;

import com.almasb.fxgl.app.scene.GameView;
import com.almasb.fxgl.texture.Texture;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.TilePane;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameScene;


public class ItemShopView implements View {
	protected static final int WIDTH = 550;
	protected static final int HEIGHT = 300;
	private TilePane root;
	private GameView view;

	public ItemShopView() {
		final var GAP = 4;
		root = new TilePane(GAP,GAP);
		root.setPrefSize(WIDTH, HEIGHT);
		root.setAlignment(Pos.CENTER);
		root.setStyle("-fx-background-color: purple;");
		view = new GameView(root, -1);
	}

	@Override
	public void render() {
		getGameScene().addGameView(view);
	}

	@Override
	public void dispose() {
		getGameScene().removeGameView(view);
	}

	@Override
	public Node content() {
		return root;
	}

	public void add(Texture arg0) {
		root.getChildren().add(arg0);
	}

	public void remove(Texture arg0) {
		root.getChildren().remove(arg0);
	}
}
