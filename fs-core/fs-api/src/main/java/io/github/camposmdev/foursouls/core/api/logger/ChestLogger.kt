package io.github.camposmdev.foursouls.core.api.logger

import io.vertx.core.buffer.Buffer

class ChestLogger(
    private val recipient: String?
) : WSLogger("ChestServer") {
    private val sender = "Chest"

    fun readText(text: String) {
        super.readText(sender, recipient, text)
    }

    fun writeText(text: String) {
        super.writeText(sender, recipient, text)
    }

    fun readBinary(buffer: Buffer) {
        super.readBinary(sender, recipient, buffer)
    }
}