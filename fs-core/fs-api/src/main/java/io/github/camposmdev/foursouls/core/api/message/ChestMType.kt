package io.github.camposmdev.foursouls.core.api.message

/**
 * Enum representing different types of messages exchanged between the game and the chest server via WebSocket.
 * These message types determine the purpose and content of the messages being sent.
 */
enum class ChestMType {
    GREETING, /* Initial message sent to user after verification and joining server. */
    CHAT, /* Sent to client and server. Server relays message to other users. */
    DONE, /* Sent to server by host. Notifies host is done with the server and wishes to close.   */
    USERS, /* Sent to users when a new user joins the server. */
    CLOSED, /* Sent to users when the host closes the server or an unexpected error occurs in the system. */
    ERROR, /* Sent to user whenever an exception occurs. Exceptions can be but not limited to invalid messages or invalid JSONs (decode errors). */
    LEAVE, /* Sent to server when the sender (user) wants to leave the server. Server unregisters the user and notifies other users the sender (user) has left the server. If the host is the sender then the host sends a http request to mom to unlock the server and then the server kicks everyone out. */
    DRAW_LOOT, /* Sent to server when user wants to draw loot card(s). Processed only if the game hasn't started yet, or it is the user's turn and, they haven't drawn a card yet. */
    LOOT, /* Sent to user as response to LOOT_DRAW message. */
    END_TURN, /* Sent to server when the user ends their turn. Message is processed if the user has drawn a loot at the start of their turn and is not dead. If the user is in the combat phase, they cannot end their turn, unless they have an item/loot card that allows them to. */
    CHARACTER_DRAW, /* Sent to server when user wants to pick their character to play as in the game. Processed only if the game hasn't started yet. */
    CHARACTERS, /* Sent to user as response to CHARACTER_DRAW message. */
    CHARACTER_SELECTION, /* Sent to server when the user has decided the character they want to play as. The rest of the characters the player does not choose are discarded into the character discard pile. */

}