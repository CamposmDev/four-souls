package org.camposmdev.model.net.message.payload

class BasementGreeting {
    var message: String? = null
}

class BasementChat {
    var username: String? = null
    var message: String? = null
}

class BasementFinished {
    /* TODO - Implement BasementFinished payload */
}

class BasementUpdatePlayers {
    /* TODO - Implement BasementUpdatePlayers payload */
}

class BasementClosed {
    /* TODO - Implement BasementClosed payload */
}

class BasementError {
    var message: String? = null
}

