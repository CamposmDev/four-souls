package io.github.camposmdev.foursouls.core.util.assets

import io.github.camposmdev.foursouls.core.card.attribute.CardType
import io.github.camposmdev.foursouls.core.card.attribute.CardType.*
import io.github.camposmdev.foursouls.core.util.CardAssetNotFoundException
import java.util.*

class MasterCardAssetsManager : ICardAssetsManager {
    val bsoul = LinkedHashMap<String, CardAsset>()
    val character = LinkedHashMap<String, CardAsset>()
    val eternal = EternalCardAssetsManager()
    val treasure = TreasureCardAssetsManager()
    val monster = MonsterCardAssetsManager()
    val loot = LootCardAssetsManager()
    val room = LinkedHashMap<String, CardAsset>()
    val outside = LinkedHashMap<String, CardAsset>()
    val verso = LinkedHashMap<String, CardAsset>()

    override fun getAssetById(cardType: CardType, id: String): CardAsset? {
        return getAssetsByType(cardType)[id]
    }

    override fun getAssetsByType(cardType: CardType): Map<String, CardAsset> {
        return when (cardType) {
            BSOUL -> bsoul
            CHARACTER -> character
            AETERNAL, PETERNAL, SETERNAL, OETERNAL, PAIDETERNAL ->
                eternal.getAssetsByType(cardType)
            PTREASURE, ATREASURE, PAIDTREASURE, OTREASURE, STREASURE ->
                treasure.getAssetsByType(cardType)
            BMONSTER, CMONSTER, HMONSTER, CHAMONSTER, GEVENT, BEVENT, CURSE, BOSS, EPIC ->
                monster.getAssetsByType(cardType)
            CARDS, TRINKETS, PILLS, RUNES, BOMBS, BUTTER, BATTERIES, KEYS, DICE, SHEART, BHEART, SACK, LSOUL, WILDCARD, MONEY1C, MONEY2C, MONEY3C, MONEY4C, MONEY5C, MONEY10C ->
                loot.getAssetsByType(cardType)
            ROOM -> room
            OUTSIDE -> outside
            VERSO -> verso
            else -> throw CardAssetNotFoundException(cardType)
        }
    }

    override fun iterator(): Iterator<Map.Entry<String, CardAsset>> {
        return listOf(
            bsoul.entries,
            character.entries,
            eternal,
            treasure,
            monster,
            loot,
            room.entries,
            outside.entries,
            verso.entries
        ).flatten().iterator()
    }

    fun getAssetsByCategory(cardType: CardType): Map<String, CardAsset> {
        return when (cardType) {
            BSOUL -> Collections.unmodifiableMap(bsoul)
            CHARACTER -> Collections.unmodifiableMap(character)
            ETERNAL -> {
                val map = LinkedHashMap<String, CardAsset>()
                map.putAll(eternal.aeternal)
                map.putAll(eternal.oeternal)
                map.putAll(eternal.paideternal)
                map.putAll(eternal.peternal)
                map.putAll(eternal.seternal)
                Collections.unmodifiableMap(map)
            }
            TREASURE -> {
                val map = LinkedHashMap<String, CardAsset>()
                map.putAll(treasure.atreasure)
                map.putAll(treasure.otreasure)
                map.putAll(treasure.paidtreasure)
                map.putAll(treasure.ptreasure)
                map.putAll(treasure.streasure)
                Collections.unmodifiableMap(map)
            }
            /* TODO - implement the rest */
            else -> throw CardAssetNotFoundException(cardType)
        }
    }
}