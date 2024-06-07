package io.github.camposmdev.foursouls.core.api.basement

import com.fasterxml.jackson.annotation.JsonProperty

class BasementUser(
    @JsonProperty("id") val id: String,
    @JsonProperty("userId") var userId: String
) {
    var username: String? = null                    /* name of the user */
    var host: Boolean = false                       /* flag for host permissions */
}
