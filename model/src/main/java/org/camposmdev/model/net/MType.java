package org.camposmdev.model.net;

/**
 * MessageType
 * Used for identifying messages between the client and
 * the server in the context of websockets
 */
public enum MType {
    G_CHAT, L_CHAT, HOST_GAME, JOIN_LOBBY, UPDATE_LOBBY, LEAVE_LOBBY, LOBBY_CLOSED, ERROR
}

