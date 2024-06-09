package io.github.camposmdev.foursouls.core.util.logger

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

open class Logger {
    var debug = false
    var silent = false

    private val name: String?

    constructor(clazz: Class<*>) {
        this.name = clazz.simpleName
    }

    constructor(name: String? = null) {
        this.name = name
    }

    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z")

    private fun log(level: Level, message: Any?) {
        if (silent) return;
        val pad = 5
        val timestamp = ZonedDateTime.now().format(dateFormatter)
        val arg0 = "$GRAY${timestamp}$RESET "
        val arg1 = "${level.color}${level.name.padStart(pad)}$RESET "
        val arg2 = if (name != null) "$WHITE[$name]$RESET " else ""
        println("$arg0$arg1$arg2$message$RESET")
    }

    fun debug(message: Any?) {
        if (debug)
            log(Level.DEBUG, message)
    }

    fun info(message: Any?) {
        log(Level.INFO, message)
    }

    fun warn(message: Any?) {
        log(Level.WARN, message)
    }

    fun error(message: Any?) {
        log(Level.ERROR, message)
    }

    companion object {
        const val BLUE = "\u001B[34m"
        const val GREEN = "\u001B[32m"
        const val YELLOW = "\u001B[33m"
        const val RED = "\u001B[31m"
        const val RESET = "\u001B[0m"
        const val GRAY = "\u001B[90m"
        const val PURPLE = "\u001B[35m"
        const val WHITE = "\u001B[97m"
    }

    enum class Level(val color: String) {
        DEBUG(BLUE),
        INFO(GREEN),
        WARN(YELLOW),
        ERROR(RED);
    }
}
