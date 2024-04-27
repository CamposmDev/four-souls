package org.camposmdev.client.ui.view;

import com.almasb.fxgl.entity.Entity;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getAppHeight;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getAppWidth;

/**
 * Displays the character the player is playing as.
 */
public class PlayerHUDView implements View {
	private static final int WIDTH = getAppWidth();
	private static final int HEIGHT = 300;
	private final Entity entity;
	private final BorderPane root;
	private final PlayerStatusView statusView;
	private final PlayerLootView lootView;
	private final PlayerItemsView itemsView;

	public PlayerHUDView(Entity entity) {
		this.entity = entity;
		root = new BorderPane();
		root.setPrefSize(WIDTH, HEIGHT);
		statusView = new PlayerStatusView(entity, root);
		statusView.render();
		lootView = new PlayerLootView(root);
		lootView.render();
		itemsView = new PlayerItemsView(root);
		itemsView.render();
	}

	public PlayerStatusView status() {
		return statusView;
	}

	public PlayerLootView loot() {
		return lootView;
	}

	public PlayerItemsView items() {
		return itemsView;
	}

	@Override
	public void render() {
		entity.getViewComponent().addChild(root);
		entity.setY(getAppHeight()-HEIGHT);
	}

	@Override
	public void dispose() {
		entity.getViewComponent().removeChild(root);
	}

	@Override
	public Node content() {
		return root;
	}
}
