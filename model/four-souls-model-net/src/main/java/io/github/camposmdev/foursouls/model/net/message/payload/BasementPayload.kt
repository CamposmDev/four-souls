package io.github.camposmdev.foursouls.model.net.message.payload

interface BasementPayload

class BasementGreeting : BasementPayload {
    var message: String? = null
}

class BasementChat : BasementPayload {
    var username: String? = null
    var message: String? = null
}

class BasementFinished : BasementPayload {
    /* TODO - Implement BasementFinished payload */
}

class BasementUpdatePlayers : BasementPayload {
    /* TODO - Implement BasementUpdatePlayers payload */
}

class BasementClosed : BasementPayload {
    /* TODO - Implement BasementClosed payload */
}

class BasementError : BasementPayload {
    var message: String? = null
}

