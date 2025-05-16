package es.prog2425.taskmanager.utilidades

import org.slf4j.LoggerFactory

object Logger {
    private val logger = LoggerFactory.getLogger("TaskManager")

    fun info(mensaje: String) {
        logger.info(mensaje)
    }

    fun warn(mensaje: String) {
        logger.warn(mensaje)
    }

    fun error(mensaje: String, exception: Throwable? = null) {
        logger.error(mensaje, exception)
    }

    fun debug(mensaje: String) {
        logger.debug(mensaje)
    }

}