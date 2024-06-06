package io.github.camposmdev.foursouls.app.game.entity.component.card;

import com.almasb.fxgl.texture.Texture;
import io.github.camposmdev.foursouls.core.card.BaseCard;

/**
 * A contract for components that allow viewing card information.
 *
 * @param <T> The type of card this viewer can handle.
 */
public interface CardRenderer<T extends BaseCard> {

	/**
	 * Retrieves the card currently being viewed.
	 *
	 * @return The card being viewed.
	 */
	T card();

	/**
	 * Sets the card to be viewed.
	 *
	 * @param card The card to be viewed.
	 */
	void setCard(T card);

	/**
	 * Retrieves the texture representing the viewed card.
	 *
	 * @return The texture representing the card.
	 */
	Texture texture();

	/**
	 * Retrieves the current width of the texture.
	 *
	 * @return The width of the texture.
	 */
	double width();

	/**
	 * Retrives the current height of the texture
	 *
	 * @return The height of the texture;
	 */
	double height();

	/**
	 * Retrieves copy of high-resolution texture representing the viewed card.
	 *
	 * @return The high-resolution texture representing the card.
	 */
	Texture hiResTexture();

	/**
	 * Retrieves copy of low-resolution texture representing the viewed card.
	 *
	 * @return The low-resolution texture representing the card.
	 */
	Texture loResTexture();
}
