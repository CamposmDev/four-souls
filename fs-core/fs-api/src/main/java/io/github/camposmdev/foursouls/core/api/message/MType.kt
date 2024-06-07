package io.github.camposmdev.foursouls.core.api.message

enum class MType {
    /* Messages: Client Api (Deprecated) <-> Old Server */
    G_CHAT,  /* deprecated */
    LOCAL_CHAT,  /* deprecated */
    HOST_GAME,  /* deprecated */
    /* Messages: Common */
    ERROR;  /* notify the user an error occurred */
}
