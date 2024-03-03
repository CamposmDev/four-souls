package org.camposmdev.client.model

import com.almasb.fxgl.logging.Logger;
import org.camposmdev.client.ui.FXUtil

class Log private constructor() {
    private companion object {
        @JvmStatic
        private val callerClass: Class<*>
            get() {
                val stackTrace = Thread.currentThread().stackTrace
                return try {
                    Class.forName(stackTrace[4].className)
                } catch (e: ClassNotFoundException) {
                    FXUtil::class.java
                }
            }

        @JvmStatic
        fun debug(message: String) {
            Logger.get(callerClass).debug(message)
        }

        @JvmStatic
        fun debugf(format: String, vararg args: Any) {
            Logger.get(callerClass).debugf(format, *args)
        }

        @JvmStatic
        fun info(message: String) {
            Logger.get(callerClass).info(message)
        }

        @JvmStatic
        fun infof(format: String, vararg args: Any) {
            Logger.get(callerClass).infof(format, *args)
        }

        @JvmStatic
        fun warn(message: String) {
            Logger.get(callerClass).warning(message)
        }

        @JvmStatic
        fun warn(message: String, error: Throwable) {
            Logger.get(callerClass).fatal(message, error)
        }

        @JvmStatic
        fun warnf(format: String, vararg args: Any) {
            Logger.get(callerClass).warningf(format, *args)
        }

        @JvmStatic
        fun fatal(message: String) {
            Logger.get(callerClass).fatal(message)
        }

        @JvmStatic
        fun fatal(message: String, error: Throwable) {
            Logger.get(callerClass).fatal(message, error)
        }

        @JvmStatic
        fun fatalf(format: String, vararg args: Any) {
            Logger.get(callerClass).fatalf(format, *args)
        }
    }
}
