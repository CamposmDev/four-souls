package io.github.camposmdev.foursouls.core.util.assets

import io.github.camposmdev.foursouls.core.card.attribute.CardType
import io.github.camposmdev.foursouls.core.card.attribute.CardType.*
import io.github.camposmdev.foursouls.core.util.CardAssetNotFoundException

class TreasureCardAssetsManager : ICardAssetsManager {
    val atreasure = LinkedHashMap<String, CardAsset>()
    val otreasure = LinkedHashMap<String, CardAsset>()
    val paidtreasure = LinkedHashMap<String, CardAsset>()
    val ptreasure = LinkedHashMap<String, CardAsset>()
    val streasure = LinkedHashMap<String, CardAsset>()

    override fun getAssetById(cardType: CardType, id: String): CardAsset? {
        return getAssetsByType(cardType)[id]
    }

    override fun getAssetsByType(cardType: CardType): Map<String, CardAsset> {
        return when (cardType) {
            ATREASURE -> atreasure
            OTREASURE -> otreasure
            PTREASURE -> ptreasure
            PAIDTREASURE -> paidtreasure
            STREASURE -> atreasure
            else -> throw CardAssetNotFoundException(cardType)
        }
    }

    override fun iterator(): Iterator<Map.Entry<String, CardAsset>> {
        return listOf(
            atreasure.entries,
            paidtreasure.entries,
            ptreasure.entries,
            otreasure.entries,
            streasure.entries
        ).flatten().iterator()
    }
}