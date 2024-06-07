package io.github.camposmdev.foursouls.core.api.message

/**
 * BasementAPI <-> Basement Server
 */
enum class BasementMType {
    GREETING,          /* notify the user they successfully joined */
    CHAT,              /* notify user/server incoming lobby chat message */
    DONE,              /* notify user/server, finished with the lobby */
    USERS,             /* notify the user the lobby users updated */
    LEAVE,             /* notify the server, the user wants to leave */
    CLOSED,            /* notify the user the lobby closed, sends when host leaves */
    ERROR;             /* notify user an error occured */
}