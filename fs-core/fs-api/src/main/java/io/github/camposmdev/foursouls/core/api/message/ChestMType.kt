package io.github.camposmdev.foursouls.core.api.message

enum class ChestMType {
    GREETING, /* initial message sent to user after verification/joining server. */
    CHAT, /* sent to client and server. Server relays message to other users. */
    DONE, /* sent to server by host. Notifies host is done with the server and wishes to close.   */
    USERS, /* sent to users when a new user joins the server. */
    CLOSED, /* sent to users when the host closes the server or an unexpected error occurs in the system. */
    ERROR, /* sent to user whenever an exception occurs. Exceptions can be but not limited to invalid messages or invalid JSONs (decode errors). */
    LEAVE, /* sent to server when the sender (user) wants to leave the server. Server unregisters the user and notifies other users the sender (user) has left the server. If the host is the sender then the host sends a http request to mom to unlock the server and then the server kicks everyone out. */
    LOOT_DRAW,
    LOOT,
    END_TURN,
    CHARACTER_DRAW,
    CHARACTERS,
    CHARACTER_CHOICE,

}