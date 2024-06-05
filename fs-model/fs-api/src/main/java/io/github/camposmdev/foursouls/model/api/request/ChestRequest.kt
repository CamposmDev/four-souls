package io.github.camposmdev.foursouls.model.api.request

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateChestReq(
    @JsonProperty("location")
    val location: String,
    @JsonProperty("gate")
    val gate: Int
)

data class UnlockChestReq(
    @JsonProperty("chestId")
    val chestId: String,
    @JsonProperty("chestKey")
    val chestKey: String
)