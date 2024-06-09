package io.github.camposmdev.foursouls.core.util.assets

import io.github.camposmdev.foursouls.core.card.attribute.CardType
import io.github.camposmdev.foursouls.core.card.attribute.CardType.*
import io.github.camposmdev.foursouls.core.util.CardAssetNotFoundException

class EternalCardAssetsManager : ICardAssetsManager {
    val aeternal = LinkedHashMap<String, CardAsset>()
    val oeternal = LinkedHashMap<String, CardAsset>()
    val peternal = LinkedHashMap<String, CardAsset>()
    val paideternal = LinkedHashMap<String, CardAsset>()
    val seternal = LinkedHashMap<String, CardAsset>()

    override fun getAssetById(cardType: CardType, id: String): CardAsset? {
        return getAssetsByType(cardType)[id]
    }

    override fun getAssetsByType(cardType: CardType): Map<String, CardAsset> {
        return when (cardType) {
            AETERNAL -> aeternal
            OETERNAL -> oeternal
            PETERNAL -> peternal
            PAIDETERNAL -> paideternal
            SETERNAL -> seternal
            else -> throw CardAssetNotFoundException(cardType)
        }
    }

    override fun iterator(): Iterator<Map.Entry<String, CardAsset>> {
        return listOf(
            aeternal.entries,
            paideternal.entries,
            oeternal.entries,
            peternal.entries,
            seternal.entries
        ).flatten().iterator()
    }
}