package io.github.camposmdev.foursouls.app.game.ui.view;

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
		this.root = new BorderPane();
		this.root.setPrefSize(WIDTH, HEIGHT);
		this.statusView = new PlayerStatusView(entity, root);
		this.statusView.render();
		this.lootView = new PlayerLootView(root);
		this.lootView.render();
		this.itemsView = new PlayerItemsView(root);
		this.itemsView.render();
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
