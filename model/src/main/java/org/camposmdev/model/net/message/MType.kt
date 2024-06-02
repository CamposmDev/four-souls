package org.camposmdev.model.net.message

import io.vertx.core.json.JsonObject

enum class MType {
    G_CHAT,  /* send messages to everyone (global scope) */
    LOCAL_CHAT,  /* send messages locally to everyone (lobby scope) */
    HOST_GAME,  /* deprecated */
    JOIN_LOBBY,  /* notify the user, someone has joined */
    UPDATE_LOBBY,  /* notify the user the lobby updated */
    LEAVE_LOBBY,  /* notify the server, the user wants to leave */
    LOBBY_CLOSED,  /* notify the user the lobby closed */
    ERROR,  /* notify the user an error occurred */
    GREETING,  /* notify the user they successfully joined */
    HOST; /* notify the user they are the host */

    companion object {
        fun parse(arg0: JsonObject): MType {
            val fieldName = "mtype"
            val str = arg0.getString(fieldName)
            return valueOf(str)
        }
    }
}
