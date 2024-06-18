package io.github.camposmdev.foursouls.core.api.message.payload

import io.github.camposmdev.foursouls.core.api.basement.BasementUser
import io.github.camposmdev.foursouls.core.api.chest.ChestUser
import io.github.camposmdev.foursouls.core.card.attribute.CardType
import io.vertx.core.json.JsonObject

interface Payload

/**
 * Builds payloads for WSPackets
 */
object PayloadFactory {
    private const val MSG_FIELD = "message"

    fun message(message: String): JsonObject {
        return JsonObject.of(MSG_FIELD, message)
    }

    fun basement(): BasementPayloadFactory {
        return BasementPayloadFactory
    }

    fun chest(): ChestPayloadFactory {
        return ChestPayloadFactory
    }
}

object BasementPayloadFactory {
    fun greeting(host: Boolean, username: String?, users: List<BasementUser>): BasementGreeting {
        return BasementGreeting(host, username, users)
    }

    fun chat(username: String?, message: String): BasementChat {
        return BasementChat(username, message)
    }

    fun done(chestId: String): BasementDone {
        return BasementDone(chestId)
    }

    fun leave(): BasementLeave {
        return BasementLeave()
    }

    fun leave(message: String): BasementLeave {
        return BasementLeave(message)
    }
}

object ChestPayloadFactory {
    fun greeting(host: Boolean, username: String?, users: List<ChestUser>): ChestGreeting {
        return ChestGreeting(host, username, users)
    }
    
    fun chat(username: String?, message: String): ChestChat {
        return ChestChat(username, message)
    }
    
    fun done(chestId: String): ChestDone {
        return ChestDone(chestId)
    }
    
    fun users(users: List<ChestUser>, message: String): ChestUsers {
        return ChestUsers(users, message)
    }
    
    fun close(message: String): ChestClosed {
        return ChestClosed(message)
    }
    
    fun err(message: String): ChestError {
        return ChestError(message)
    }
    
    fun leave(): ChestLeave {
        return ChestLeave()
    }
    
    fun drawLoot(n: Int): ChestDrawLoot {
        return ChestDrawLoot(n)
    }

    fun drawLootResult(size: Int, loot: Map<String, CardType>): ChestDrawLootResult {
        return ChestDrawLootResult(size, loot)
    }
    
    fun drawCharacters(n: Int): ChestDrawCharacters {
        return ChestDrawCharacters(n)
    }
    
    fun drawCharactersResult(size: Int, characterIds: Map<String, Pair<String, CardType>>): ChestDrawCharactersResult {
        return ChestDrawCharactersResult(size, characterIds)
    }
    
    fun selectedCharacter(characterId: String): ChestSelectedCharacter {
        return ChestSelectedCharacter(characterId)
    }
}