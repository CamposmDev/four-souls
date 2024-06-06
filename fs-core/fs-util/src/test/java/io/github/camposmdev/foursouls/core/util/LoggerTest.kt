package io.github.camposmdev.foursouls.core.util

import io.github.camposmdev.foursouls.core.util.logger.Logger
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class LoggerTest {
    @Test
    @DisplayName("Test Log Levels")
    fun testLog() {
        val logger = Logger(LoggerTest::class.java)
        logger.debug("This is a debug message.")
        logger.info("This is an info message.")
        logger.warn("This is a warning message.")
        logger.error("This is a fatal error message.")
    }
}