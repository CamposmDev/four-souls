package io.github.camposmdev.foursouls.model.api.request

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateBasementReq(
    @JsonProperty("floor")
    val floor: String,
    @JsonProperty("level")
    val level: Int
)

data class FreeBasementReq(
    @JsonProperty("basementId")
    val basementId: String,
    @JsonProperty("basementKey")
    val basement: String
)