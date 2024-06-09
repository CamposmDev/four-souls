package io.github.camposmdev.foursouls.core.util.assets

import io.github.camposmdev.foursouls.core.card.attribute.CardType

interface ICardAssetsManager : Iterable<Map.Entry<String, CardAsset>> {
    fun getAssetById(cardType: CardType, id: String): CardAsset?
    fun getAssetsByType(cardType: CardType): Map<String, CardAsset>
}