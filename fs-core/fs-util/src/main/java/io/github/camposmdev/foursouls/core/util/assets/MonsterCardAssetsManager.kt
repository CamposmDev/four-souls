package io.github.camposmdev.foursouls.core.util.assets

import io.github.camposmdev.foursouls.core.card.attribute.CardType
import io.github.camposmdev.foursouls.core.card.attribute.CardType.*
import io.github.camposmdev.foursouls.core.util.CardAssetNotFoundException

class MonsterCardAssetsManager : ICardAssetsManager {
    val bmonster = LinkedHashMap<String, CardAsset>()
    val cmonster = LinkedHashMap<String, CardAsset>()
    val hmonster = LinkedHashMap<String, CardAsset>()
    val chamonster = LinkedHashMap<String, CardAsset>()
    val gevent = LinkedHashMap<String, CardAsset>()
    val bevent = LinkedHashMap<String, CardAsset>()
    val curse = LinkedHashMap<String, CardAsset>()
    val boss = LinkedHashMap<String, CardAsset>()
    val epic = LinkedHashMap<String, CardAsset>()

    override fun getAssetById(cardType: CardType, id: String): CardAsset? {
        return getAssetsByType(cardType)[id]
    }

    override fun getAssetsByType(cardType: CardType): Map<String, CardAsset> {
        return when (cardType) {
            BMONSTER -> bmonster
            CMONSTER -> cmonster
            HMONSTER -> hmonster
            CHAMONSTER -> chamonster
            GEVENT -> gevent
            BEVENT -> bevent
            CURSE -> curse
            BOSS -> boss
            EPIC -> epic
            else -> throw CardAssetNotFoundException(cardType)
        }
    }

    override fun iterator(): Iterator<Map.Entry<String, CardAsset>> {
        return listOf(
            bmonster.entries,
            cmonster.entries,
            hmonster.entries,
            chamonster.entries,
            gevent.entries,
            bevent.entries,
            curse.entries,
            boss.entries,
            epic.entries
        ).flatten().iterator()
    }
}