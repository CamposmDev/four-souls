package org.camposmdev.client.entity.component.card;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.Texture;
import org.camposmdev.client.service.EntityService;
import org.camposmdev.model.card.BaseCard;
import org.camposmdev.util.FXUtil;

/**
 * Attach to entities that have a card.
 */
public class CardComponent extends Component {
	protected BaseCard card;
	protected Texture texture;

	public CardComponent(BaseCard card) {
		this.card = card;
	}

	@Override
	public void onAdded() {
		var es = EntityService.get();
		texture = FXUtil.loadCard(card.getImage().source2());
		entity.getViewComponent().addChild(texture);
		es.events().add_onMouseHover_Preview(this);
	}

	public BaseCard card() {
		return card;
	}

	public void setCard(BaseCard card) {
		this.card = card;
		var temp = FXUtil.loadCard(card.getImage().source2());
		texture.setImage(temp.getImage());
	}

	public Texture texture() {
		return texture;
	}

	/**
	 * @return High quality texture copy of the card
	 */
	public Texture source1() {
		return FXUtil.loadCard(card.getImage().source1());
	}

	/**
	 * @return Low quality texture copy of the card
	 */
	public Texture source2() {
		return FXUtil.loadCard(card.getImage().source2());
	}
}
