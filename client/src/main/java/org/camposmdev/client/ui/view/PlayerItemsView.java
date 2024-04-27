package org.camposmdev.client.ui.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;

public class PlayerItemsView extends InventoryView {
	public PlayerItemsView(BorderPane parent) {
		super(parent);
		tilePane.setAlignment(Pos.CENTER_LEFT);
		tilePane.setPadding(new Insets(0,0,0,MARGIN));
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
