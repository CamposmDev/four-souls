package io.github.camposmdev.foursouls.model.net

import java.util.*

class BasementUser {
    val id: String = UUID.randomUUID().toString()   /* client id of the user */
    var userId: String? = null                      /* id of the user */
    var username: String? = null                    /* name of the user */
    var host: Boolean = false                       /* flag for host permissions */
}
