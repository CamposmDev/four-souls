package io.github.camposmdev.foursouls.app.game.ui.view;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;

public class PlayerItemsView extends InventoryView {
	public PlayerItemsView(BorderPane parent) {
		super(parent);
		tilePane.setAlignment(Pos.CENTER_LEFT);
	}

	@Override
	public void render() {
		parent.setRight(root);
	}

	@Override
	public void dispose() {
		parent.setRight(null);
	}
}
