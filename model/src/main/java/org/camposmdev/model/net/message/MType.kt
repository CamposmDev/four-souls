package org.camposmdev.model.net.message

/**
 * MessageType
 * Used for identifying messages between the client and
 * the server in the context of websockets
 */
enum class MType {
    G_CHAT, L_CHAT, HOST_GAME, JOIN_LOBBY, UPDATE_LOBBY, LEAVE_LOBBY, LOBBY_CLOSED, ERROR,
    GREETING, DISPLAY_NAME
}

