package io.github.camposmdev.app.game.ui.view;

import com.almasb.fxgl.texture.Texture;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;

public abstract class InventoryView implements View {
	protected static final int WIDTH = 600;
	protected static final int HEIGHT = 300;

	protected final BorderPane parent;
	protected final ScrollPane root;
	protected final TilePane tilePane;

	public InventoryView(BorderPane parent) {
		this.parent = parent;
		final var GAP = 4;
		tilePane = new TilePane(GAP, GAP);
		tilePane.setPrefSize(WIDTH, HEIGHT);
		root = new ScrollPane(tilePane);
		root.setId("card-scroll-pane");
		root.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		root.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
	}

	@Override
	public Node content() {
		return root;
	}

	public void add(Texture arg0) {
		tilePane.getChildren().add(arg0);
	}

	public void remove(Texture arg0) {
		tilePane.getChildren().remove(arg0);
	}
}
