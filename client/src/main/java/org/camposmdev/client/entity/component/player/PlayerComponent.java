package org.camposmdev.client.entity.component.player;

import com.almasb.fxgl.entity.component.Component;
import org.camposmdev.client.model.LocalGameManager;
import org.camposmdev.model.game.player.Player;
import org.camposmdev.util.Log;

import static com.almasb.fxgl.dsl.FXGLForKtKt.geto;

public class PlayerComponent extends Component {
	private final Player player;

	public PlayerComponent(String characterId) {
		LocalGameManager game = geto("game");
		var character = game.deck().characters().find(x -> x.getId().equals(characterId));
		var eternal = game.deck().eternals().find(x -> x.getId().equals(character.getEternalId()));
		this.player = new Player(character, eternal);
		/* add player to game manager */
		game.players().add(player);
	}

	@Override
	public void onAdded() {
		Log.info(player.toString());

	}

	@Override
	public void onUpdate(double tpf) {

	}

	public Player get() {
		return player;
	}
}
