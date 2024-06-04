package io.github.camposmdev.foursouls.app.game.entity.component.player;

import com.almasb.fxgl.entity.component.Component;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import io.github.camposmdev.foursouls.model.game.LocalGameManager;
import io.github.camposmdev.foursouls.model.card.character.CharacterCard;
import io.github.camposmdev.foursouls.model.card.eternal.EternalCard;
import io.github.camposmdev.foursouls.model.card.loot.LootCard;
import io.github.camposmdev.foursouls.model.card.treasure.TreasureCard;
import io.github.camposmdev.foursouls.model.game.player.Attribute;
import io.github.camposmdev.foursouls.model.game.player.Player;
import io.github.camposmdev.foursouls.model.game.player.Souls;
import io.github.camposmdev.foursouls.model.fx.Log;

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
