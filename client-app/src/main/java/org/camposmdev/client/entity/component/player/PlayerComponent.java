package org.camposmdev.client.entity.component.player;

import com.almasb.fxgl.entity.component.Component;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import org.camposmdev.model.game.LocalGameManager;
import org.camposmdev.model.card.character.CharacterCard;
import org.camposmdev.model.card.eternal.EternalCard;
import org.camposmdev.model.card.loot.LootCard;
import org.camposmdev.model.card.treasure.TreasureCard;
import org.camposmdev.model.game.player.Attribute;
import org.camposmdev.model.game.player.Player;
import org.camposmdev.model.game.player.Souls;
import org.camposmdev.util.Log;

import static com.almasb.fxgl.dsl.FXGLForKtKt.geto;

public class PlayerComponent extends Component {
	private Player player;

	@Override
	public void onAdded() {
		var characterId = getEntity().getProperties().getString("characterId");
		LocalGameManager game = geto("game");
		/* initialize player object */
		var character = game.deck().characters().find(x -> x.getId().equals(characterId));
		var eternal = game.deck().eternals().find(x -> x.getId().equals(character.getEternalId()));
		player = new Player(character, eternal);
		/* add player to game manager */
		game.players().add(player);
		Log.info(player.toString());
	}

	public Attribute<Integer> hp() {
		return player.hp();
	}

	public Attribute<Integer> atk() {
		return player.atk();
	}

	public CharacterCard character() {
		return player.character();
	}

	public void setCharacter(CharacterCard character) {
		player.setCharacter(character);
	}

	public EternalCard eternal() {
		return player.eternal();
	}

	public void setEternal(EternalCard eternal) {
		player.setEternal(eternal);
	}

	public ObservableList<LootCard> loot() {
		return player.loot();
	}

	public ObservableList<TreasureCard> treasure() {
		return player.treasure();
	}

	public Souls souls() {
		return player.souls();
	}

	public SimpleIntegerProperty cents() {
		return player.cents();
	}
}
