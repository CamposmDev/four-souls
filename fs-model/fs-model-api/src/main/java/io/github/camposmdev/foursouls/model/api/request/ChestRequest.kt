package io.github.camposmdev.foursouls.model.api.request

import com.fasterxml.jackson.annotation.JsonProperty

data class FreeChestReq(
    @JsonProperty("chestId")
    val chestId: String,
    @JsonProperty("chestKey")
    val chestKey: String
)