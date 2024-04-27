package org.camposmdev.client.ui.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;

public class PlayerLootView extends InventoryView {
	public PlayerLootView(BorderPane parent) {
		super(parent);
		tilePane.setAlignment(Pos.CENTER_RIGHT);
		tilePane.setPadding(new Insets(0,MARGIN,0,0));
	}

	@Override
	public void render() {
		parent.setLeft(root);
	}

	@Override
	public void dispose() {
		parent.setLeft(null);
	}
}
