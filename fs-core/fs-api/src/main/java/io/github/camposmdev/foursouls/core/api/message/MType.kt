package io.github.camposmdev.foursouls.core.api.message

import io.vertx.core.json.JsonObject

enum class MType {
    /* Messages: Client Api (Deprecated) <-> Old Server */
    G_CHAT,  /* deprecated */
    LOCAL_CHAT,  /* deprecated */
    HOST_GAME,  /* deprecated */

    /* Messages: BasementApi <-> Basement Server */
    BASEMENT_GREETING,          /* notify the user they successfully joined */
    BASEMENT_CHAT,              /* notify user/server incoming lobby chat message */
    BASEMENT_DONE,              /* notify user/server, finished with the lobby */
    BASEMENT_USERS,             /* notify the user the lobby users updated */
    BASEMENT_LEAVE,             /* notify the server, the user wants to leave */
    BASEMENT_CLOSED,            /* notify the user the lobby closed, sends when host leaves */
    BASEMENT_ERROR,             /* notify user an error occured */

    /* Messages: Common */
    ERROR;  /* notify the user an error occurred */

    companion object {
        fun parse(arg0: JsonObject): MType {
            val field = MessageFactory.MTYPE_FIELD
            val str = arg0.getString(field)
            return valueOf(str)
        }
    }
}
