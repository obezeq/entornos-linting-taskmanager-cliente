package es.prog2425.taskmanager

import es.prog2425.taskmanager.aplicacion.ActividadService
import es.prog2425.taskmanager.datos.ActividadRepositoryMemoria
import es.prog2425.taskmanager.datos.UserRepositoryMemoria
import es.prog2425.taskmanager.presentacion.ConsolaUI
import es.prog2425.taskmanager.utilidades.Logger
import kotlin.system.exitProcess

fun main() {
    println("\n┌──────────────────────────────────┐")
    println("│   SISTEMA GESTOR DE ACTIVIDADES  │")
    println("│         Versión 3.0.0            │")
    println("└──────────────────────────────────┘")

    Logger.info("Iniciando aplicación TaskManager v3.0.0")

    try {
        val actividadRepo = ActividadRepositoryMemoria()
        val userRepo = UserRepositoryMemoria()
        Logger.debug("Repositorios inicializados: Actividades[${actividadRepo.listar().size}] | Usuarios[${userRepo.listar().size}]")

        val servicio = ActividadService(actividadRepo, userRepo)
        val consola = ConsolaUI(servicio)

        Logger.info("Servicios inicializados - Iniciando UI")
        consola.iniciar()
    } catch (e: Exception) {
        Logger.error("Error crítico durante la inicialización: ${e.message}", e)
        exitProcess(1)
    }
}

