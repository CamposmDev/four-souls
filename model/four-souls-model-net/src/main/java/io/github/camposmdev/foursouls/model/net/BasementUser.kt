package io.github.camposmdev.foursouls.model.net

import java.util.*

class BasementUser {
    val id: String = UUID.randomUUID().toString()
    var userId: String? = null
    var username: String? = null
    var host: Boolean = false
}
