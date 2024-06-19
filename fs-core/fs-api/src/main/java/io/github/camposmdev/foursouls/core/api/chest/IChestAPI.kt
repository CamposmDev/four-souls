package io.github.camposmdev.foursouls.core.api.chest

import io.vertx.core.Future

interface IChestAPI {
    fun chat(username: String?, message: String): Future<Void>
    fun done(chestId: String): Future<Void>
    fun leave(): Future<Void>
    fun drawLoot(n: Int): Future<Void>
    fun endTurn(message: String): Future<Void>
    fun drawCharacters(n: Int): Future<Void>
    fun selectCharacterCard(characterId: String): Future<Void>
    fun playLootCard(lootId: String): Future<Void>
    fun playEternalCard(eternalId: String): Future<Void>
    fun playCharacterCard(characterId: String): Future<Void>
}