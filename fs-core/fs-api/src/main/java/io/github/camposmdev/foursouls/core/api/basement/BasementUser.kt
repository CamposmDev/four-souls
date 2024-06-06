package io.github.camposmdev.foursouls.core.api.basement

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

class BasementUser(@JsonProperty("userId") var userId: String) {
    val id: String = UUID.randomUUID().toString()   /* client id of the user */
    var username: String? = null                    /* name of the user */
    var host: Boolean = false                       /* flag for host permissions */
}
