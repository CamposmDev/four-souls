package io.github.camposmdev.foursouls.app.game.ui.view;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.texture.Texture;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import io.github.camposmdev.foursouls.app.game.entity.component.player.CharacterCardComponent;
import io.github.camposmdev.foursouls.app.game.entity.component.player.EternalCardComponent;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getUIFactoryService;
import static com.almasb.fxgl.dsl.FXGLForKtKt.texture;

public class PlayerStatusView implements View {
	private final BorderPane parent;
	private final Text heartText, swordText, pennyText, soulText;
	private final GridPane root;

	public PlayerStatusView(Entity entity, BorderPane parent) {
		this.parent = parent;
		/* init text controls */
		heartText = createText();
		swordText = createText();
		pennyText = createText();
		soulText = createText();
		/* init graphic icons */
		var heartIcon = createIcon("heart.png");
		var swordIcon = createIcon("sword.png");
		var pennyIcon = createIcon("penny.png");
		var soulIcon = createIcon("soul.png");
		var statusBox = new GridPane(2,0);
		statusBox.setAlignment(Pos.CENTER);
		statusBox.addColumn(0, heartIcon, swordIcon, pennyIcon, soulIcon);
		statusBox.addColumn(1, heartText, swordText, pennyText, soulText);

		root = new GridPane(28, 8);
		root.setAlignment(Pos.CENTER);
//		root.setPrefWidth(400);
//		root.setPrefHeight(140);
		/* display character */
		entity.getComponentOptional(CharacterCardComponent.class).ifPresent(comp -> {
			root.add(comp.texture(), 0, 0, 1, 3);
		});
		/* display eternal item */
		entity.getComponentOptional(EternalCardComponent.class).ifPresent(comp -> {
			root.add(comp.texture(), 2, 0, 1, 3);
		});
		/* display player status */
		root.add(statusBox, 1, 0, 1, 3);
	}

	@Override
	public void render() {
		parent.setCenter(root);
	}

	@Override
	public void dispose() {
		parent.setCenter(null);
	}

	@Override
	public Node content() {
		return root;
	}

	public void setHeartText(Integer value) {
		heartText.setText(value.toString());
	}

	public void setAttackText(Integer value) {
		swordText.setText(value.toString());
	}

	public void setPennyText(Integer value) {
		pennyText.setText(value.toString());
	}

	public void setSoulText(Integer value) {
		soulText.setText(value.toString());
	}

	private Text createText() {
		final var WRAP_WIDTH = 40;
		var x = getUIFactoryService().newText("000", Color.WHITE, 20);
		x.setTextAlignment(TextAlignment.CENTER);
		x.setWrappingWidth(WRAP_WIDTH);
		return x;
	}

	private Texture createIcon(String src) {
		final var ICO_SZ = 28;
		var t = texture(src);
		t.setFitWidth(ICO_SZ);
		t.setFitHeight(ICO_SZ);
		t.setSmooth(false);
		return t;
	}
}
