package io.github.camposmdev.foursouls.core.api.chest

import io.github.camposmdev.foursouls.core.api.VertxAPI
import io.github.camposmdev.foursouls.core.api.message.ChestMType
import io.github.camposmdev.foursouls.core.api.message.payload.ChestPayload
import io.vertx.core.Future
import io.vertx.core.Vertx
import io.vertx.core.json.JsonObject
import java.util.*

class ChestAPI(vertx: Vertx)
    : VertxAPI<ChestMType, ChestPayload>(vertx, "chest.api.${UUID.randomUUID()}", ChestAPI::class.java),
    IChestAPI {
        private val eb = vertx.eventBus()

    init {
        /* register chest message codecs */
    }

    override fun decodeMessage(mtype: ChestMType, payload: JsonObject) {
        TODO("Not yet implemented")
    }

    override fun decodeMType(mtype: String): ChestMType {
        TODO("Not yet implemented")
    }

    override fun chat(username: String?, message: String): Future<Void> {
        TODO("Not yet implemented")
    }

    override fun done(chestId: String): Future<Void> {
        TODO("Not yet implemented")
    }

    override fun leave(): Future<Void> {
        TODO("Not yet implemented")
    }

    override fun drawLoot(n: Int): Future<Void> {
        TODO("Not yet implemented")
    }

    override fun endTurn(): Future<Void> {
        TODO("Not yet implemented")
    }

    override fun drawCharacters(n: Int): Future<Void> {
        TODO("Not yet implemented")
    }

    override fun selectCharacterCard(characterId: String): Future<Void> {
        TODO("Not yet implemented")
    }

    override fun playLootCard(lootId: String): Future<Void> {
        TODO("Not yet implemented")
    }

    override fun playEternalCard(eternalId: String): Future<Void> {
        TODO("Not yet implemented")
    }

    override fun playCharacterCard(characterId: String): Future<Void> {
        TODO("Not yet implemented")
    }
}