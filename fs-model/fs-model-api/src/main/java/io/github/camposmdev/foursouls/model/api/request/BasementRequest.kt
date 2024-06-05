package io.github.camposmdev.foursouls.model.api.request

import com.fasterxml.jackson.annotation.JsonProperty

data class FreeBasementReq(
    @JsonProperty("basementId")
    val basementId: String,
    @JsonProperty("basementKey")
    val basement: String
)