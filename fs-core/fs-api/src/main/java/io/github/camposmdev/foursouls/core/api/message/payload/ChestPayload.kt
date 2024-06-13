package io.github.camposmdev.foursouls.core.api.message.payload

import com.fasterxml.jackson.annotation.JsonProperty
import io.github.camposmdev.foursouls.core.api.chest.ChestUser
import io.github.camposmdev.foursouls.core.card.attribute.CardType

interface ChestPayload : Payload

data class ChestGreeting(
    @JsonProperty("host")
    val host: Boolean,
    @JsonProperty("username")
    val username: String?,
    @JsonProperty("users")
    val users: List<ChestUser>
) : ChestPayload {
    @JsonProperty("message")
    val message: String = "Joined Chest"
}

data class ChestChat(
    @JsonProperty("username")
    val username: String?,
    @JsonProperty("message")
    val message: String
) : ChestPayload

data class ChestDone(
    @JsonProperty("chestId")
    val chestId: String
) : ChestPayload

data class ChestUsers(
    @JsonProperty("users")
    val users: List<ChestUser>,
    @JsonProperty("message")
    val message: String /* who joined/left the chest */
) : ChestPayload

data class ChestClosed(
    @JsonProperty("message")
    val message: String
): ChestPayload

data class ChestError(
    @JsonProperty("message")
    val message: String
) : ChestPayload

data class ChestLeave(
    @JsonProperty("message")
    val message: String = "Goodbye Cruel World"
) : ChestPayload

data class ChestDrawLoot(
    @JsonProperty("amount")
    val amount: Int
)

data class ChestLoot(
    @JsonProperty("size")
    val size: Int,
    @JsonProperty("lootIds")
    val lootIds: Map<String, CardType>
)

data class ChestDrawCharacter(
    @JsonProperty("amount")
    val amount: Int
)

data class ChestCharacterEternal(
    @JsonProperty("eternalId")
    val eternalId: String,
    @JsonProperty("eternalType")
    val eternalType: CardType
)

data class ChestCharacters(
    @JsonProperty("size")
    val size: Int,
    @JsonProperty("characterIds")
    val characterIds: Map<String, ChestCharacterEternal>
)

data class ChestCharacterSelection(
    @JsonProperty("size")
    val size: Int,
    @JsonProperty("characterId")
    val characterId: String
)

data class ChestBuyShopItem(
    @JsonProperty("itemId")
    val itemId: String
)