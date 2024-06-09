package io.github.camposmdev.foursouls.core.util.assets

import io.github.camposmdev.foursouls.core.card.attribute.CardType
import io.github.camposmdev.foursouls.core.card.attribute.CardType.*
import io.github.camposmdev.foursouls.core.util.CardAssetNotFoundException

class MoneyCardAssetsManager : ICardAssetsManager {
    val money1c = LinkedHashMap<String, CardAsset>()
    val money2c = LinkedHashMap<String, CardAsset>()
    val money3c = LinkedHashMap<String, CardAsset>()
    val money4c = LinkedHashMap<String, CardAsset>()
    val money5c = LinkedHashMap<String, CardAsset>()
    val money10c = LinkedHashMap<String, CardAsset>()

    override fun getAssetById(cardType: CardType, id: String): CardAsset? {
        return getAssetsByType(cardType)[id]
    }

    override fun getAssetsByType(cardType: CardType): Map<String, CardAsset> {
        return when (cardType) {
            MONEY1C -> money1c
            MONEY2C -> money2c
            MONEY3C -> money3c
            MONEY4C -> money4c
            MONEY5C -> money5c
            MONEY10C -> money10c
            else -> throw CardAssetNotFoundException(cardType)
        }
    }

    override fun iterator(): Iterator<Map.Entry<String, CardAsset>> {
        return listOf(
            money1c.entries,
            money2c.entries,
            money3c.entries,
            money4c.entries,
            money5c.entries,
            money10c.entries
        ).flatten().iterator()
    }
}