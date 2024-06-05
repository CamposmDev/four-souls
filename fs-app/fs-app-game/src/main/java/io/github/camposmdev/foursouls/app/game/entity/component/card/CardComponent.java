package io.github.camposmdev.foursouls.app.game.entity.component.card;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.Texture;
import io.github.camposmdev.foursouls.app.game.service.EntityService;
import io.github.camposmdev.foursouls.model.card.BaseCard;
import io.github.camposmdev.foursouls.model.ui.FXUtil;

/**
 * Attach to entities that have a card.
 */
public class CardComponent extends Component implements CardRenderer<BaseCard> {
	protected BaseCard card;
	protected Texture texture;

	public CardComponent(BaseCard card) {
		this.card = card;
	}

	@Override
	public void onAdded() {
		var es = EntityService.get();
		texture = FXUtil.loadCard(card.getImage().loResSrc());
		entity.getViewComponent().addChild(texture);
		es.events().add_onMouseHover_Preview(this);
	}

	@Override
	public BaseCard card() {
		return card;
	}

	@Override
	public void setCard(BaseCard card) {
		this.card = card;
		var temp = FXUtil.loadCard(card.getImage().loResSrc());
		texture.setImage(temp.getImage());
	}

	@Override
	public Texture texture() {
		return texture;
	}


	@Override
	public double width() {
		return texture.getFitWidth();
	}

	@Override
	public double height() {
		return texture.getFitWidth();
	}

	@Override
	public Texture hiResTexture() {
		return FXUtil.loadCard(card.getImage().hiResSrc());
	}

	@Override
	public Texture loResTexture() {
		return FXUtil.loadCard(card.getImage().loResSrc());
	}
}
