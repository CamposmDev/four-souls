package io.github.camposmdev.foursouls.core.util.assets

import io.github.camposmdev.foursouls.core.card.attribute.CardType
import io.github.camposmdev.foursouls.core.card.attribute.CardType.*
import io.github.camposmdev.foursouls.core.util.CardAssetNotFoundException

class LootCardAssetsManager : ICardAssetsManager {
    val batteries = LinkedHashMap<String, CardAsset>()
    val bheart = LinkedHashMap<String, CardAsset>()
    val bombs = LinkedHashMap<String, CardAsset>()
    val butter = LinkedHashMap<String, CardAsset>()
    val cards = LinkedHashMap<String, CardAsset>()
    val dice = LinkedHashMap<String, CardAsset>()
    val keys = LinkedHashMap<String, CardAsset>()
    val lsoul = LinkedHashMap<String, CardAsset>()
    val pills = LinkedHashMap<String, CardAsset>()
    val runes = LinkedHashMap<String, CardAsset>()
    val sack = LinkedHashMap<String, CardAsset>()
    val sheart = LinkedHashMap<String, CardAsset>()
    val trinkets = LinkedHashMap<String, CardAsset>()
    val wildcard = LinkedHashMap<String, CardAsset>()
    val money = MoneyCardAssetsManager()

    override fun getAssetById(cardType: CardType, id: String): CardAsset? {
        return getAssetsByType(cardType)[id]
    }

    override fun getAssetsByType(cardType: CardType): Map<String, CardAsset> {
        return when (cardType) {
            BATTERIES -> batteries
            BHEART -> bheart
            BOMBS -> bombs
            BUTTER -> butter
            CARDS -> cards
            DICE -> dice
            KEYS -> keys
            LSOUL -> lsoul
            PILLS -> pills
            RUNES -> runes
            SACK -> sack
            SHEART -> sheart
            TRINKETS -> trinkets
            WILDCARD -> wildcard
            MONEY1C, MONEY2C, MONEY3C, MONEY4C, MONEY5C, MONEY10C ->
                money.getAssetsByType(cardType)
            else -> throw CardAssetNotFoundException(cardType)
        }
    }

    override fun iterator(): Iterator<Map.Entry<String, CardAsset>> {
        return listOf(
            batteries.entries,
            bheart.entries,
            bombs.entries,
            butter.entries,
            cards.entries,
            dice.entries,
            keys.entries,
            lsoul.entries,
            pills.entries,
            runes.entries,
            sack.entries,
            sheart.entries,
            trinkets.entries,
            wildcard.entries,
            money
        ).flatten().iterator()
    }
}