package es.prog2425.taskmanager.presentacion

import es.prog2425.taskmanager.aplicacion.ActividadService
import es.prog2425.taskmanager.dominio.Actividad
import es.prog2425.taskmanager.dominio.Tarea
import es.prog2425.taskmanager.dominio.Estado
import es.prog2425.taskmanager.utilidades.Logger
import es.prog2425.taskmanager.utilidades.Utils

class ConsolaUI(private val servicio: ActividadService) {

    fun iniciar() {
        Logger.info("Sistema interactivo iniciado")
        var opcion = 0
        do {
            try {
                mostrarMenuPrincipal()
                opcion = leerOpcion()
                Logger.debug("Opción seleccionada: $opcion")
                when (opcion) {
                    1 -> gestionUsuarios()
                    2 -> gestionActividades()
                    3 -> mostrarDashboard()
                    4 -> println("[+] ¡Hasta luego!")
                    else -> println("[-] Opción no válida")
                }
                if (opcion != 4) {
                    print("[+] Presiona ENTER para continuar...")
                    readln()
                }
            } catch (e: Exception) {
                Logger.error("Error en flujo principal: ${e.javaClass.simpleName} - ${e.message}")
            }
        } while (opcion != 4)
        Logger.info("Aplicación finalizada correctamente")
    }

    // ==================== MENÚ PRINCIPAL ====================
    private fun mostrarMenuPrincipal() {
        println("\n┌─────────────────────────────────┐")
        println("│      GESTOR TASKMANAGER 3000     │")
        println("└─────────────────────────────────┘")
        println("[1] Gestión de usuarios")
        println("[2] Gestión de actividades")
        println("[3] Panel de control (Dashboard)")
        println("[4] Salir")
        print("\n[+] Seleccione una opción: ")
    }

    // ==================== GESTIÓN DE USUARIOS ====================
    private fun gestionUsuarios() {
        var opcion: Int
        do {
            println("\n┌─────────────────────────────────┐")
            println("│        GESTIÓN DE USUARIOS       │")
            println("└─────────────────────────────────┘")
            println("[1] Crear usuario")
            println("[2] Listar usuarios")
            println("[3] Asignar tarea a usuario")
            println("[4] Ver tareas de usuario")
            println("[5] Volver al menú principal")
            print("\n[+] Seleccione una opción: ")

            opcion = leerOpcion()
            when (opcion) {
                1 -> crearUsuario()
                2 -> listarUsuarios()
                3 -> asignarTareaAUsuario()
                4 -> verTareasUsuario()
                5 -> return
                else -> println("[-] Opción no válida")
            }
        } while (opcion != 5)
    }

    private fun crearUsuario() {
        print("[+] Nombre del usuario: ")
        val nombre = readln()
        print("[+] Email del usuario: ")
        val email = readln()

        try {
            val usuario = servicio.crearUsuario(nombre, email)
            println("[+] Usuario creado: ${usuario.nombre}")
        } catch (e: Exception) {
            Logger.error("No se ha podido crear el usuario correctamente")
            println("[-] Error al crear usuario: ${e.message}")
        }
    }

    private fun listarUsuarios() {
        val usuarios = servicio.listarUsuarios()
        if (usuarios.isEmpty()) {
            Logger.warn("No se han registrado todavía usuarios")
            println("\n[!] No hay usuarios registrados")
            return
        }
        println("\n── LISTADO DE USUARIOS ──")
        usuarios.forEach { println(it) }
    }

    private fun asignarTareaAUsuario() {
        print("[+] ID de la tarea a asignar: ")
        val tareaId = readln().toLongOrNull() ?: return println("[-] ID inválido")
        print("[+] ID del usuario: ")
        val usuarioId = readln().toIntOrNull() ?: return println("[-] ID inválido")

        try {
            servicio.asignarTarea(tareaId, usuarioId)
            println("[+] Tarea asignada correctamente")
        } catch (e: Exception) {
            Logger.error(e.message.toString())
        }
    }

    private fun verTareasUsuario() {
        print("[+] ID del usuario: ")
        val usuarioId = readln().toIntOrNull() ?: return println("[-] ID inválido")

        try {
            val tareas = servicio.tareasPorUsuario(usuarioId)
            if (tareas.isEmpty()) {
                println("\n[!] El usuario no tiene tareas asignadas")
                return
            }

            println("\n── TAREAS ASIGNADAS ──")
            tareas.forEachIndexed { index, tarea ->
                println("${index + 1}. ${tarea.obtenerDetalle()}")
            }

            print("\n[+] Ver historial de alguna tarea? (número o 0 para cancelar): ")
            readln().toIntOrNull()?.let { seleccion ->
                if (seleccion in 1..tareas.size) {
                    mostrarHistorial(tareas[seleccion - 1].id)
                }
            }
        } catch (e: Exception) {
            Logger.error("${e.message}")
        }
    }

    // ==================== GESTIÓN DE ACTIVIDADES ====================
    private fun gestionActividades() {
        var opcion: Int
        do {
            println("\n┌─────────────────────────────────┐")
            println("│      GESTIÓN DE ACTIVIDADES      │")
            println("└─────────────────────────────────┘")
            println("[1] Crear nueva actividad")
            println("[2] Listar todas las actividades")
            println("[3] Filtrar actividades")
            println("[4] Gestionar subtareas")
            println("[5] Cambiar estado de una tarea")
            println("[6] Volver al menú principal")
            print("\n[+] Seleccione una opción: ")

            opcion = leerOpcion()
            when (opcion) {
                1 -> crearActividad()
                2 -> listarActividades()
                3 -> filtrarActividades()
                4 -> gestionarSubtareas()
                5 -> cambiarEstadoTarea()
                6 -> return
                else -> println("[-] Opción no válida")
            }
        } while (opcion != 6)
    }

    private fun crearActividad() {
        println("\n┌───────────────────────────────────────────────┐")
        println("│          TIPO DE ACTIVIDAD A CREAR             │")
        println("└───────────────────────────────────────────────┘")
        println("[1] Tarea")
        println("[2] Evento")
        print("\n[+] Elija una opción: ")

        when (leerOpcion()) {
            1 -> crearTarea()
            2 -> crearEvento()
            else -> println("[-] Opción incorrecta")
        }
    }

    private fun crearTarea() {
        print("[+] Descripción de la tarea: ")
        val descripcion = readln()
        try {
            servicio.crearTarea(descripcion)
            println("[+] Tarea creada correctamente")
        } catch (e: IllegalArgumentException) {
            Logger.error(e.message.toString())
        }
    }

    private fun crearEvento() {
        print("- Descripción del evento: ")
        val descripcion = readln()
        print("- Fecha (dd-MM-yyyy): ")
        val fecha = readln()
        print("- Ubicación: ")
        val ubicacion = readln()

        try {
            servicio.crearEvento(descripcion, fecha, ubicacion)
            println("[+] Evento creado correctamente")
        } catch (e: Exception) {
            Logger.warn("Error al crear evento: ${e.message}")
            println("[-] Error al crear evento: ${e.message}")

        }
    }

    private fun listarActividades() {
        val actividades = servicio.listarActividades()
        if (actividades.isEmpty()) {
            Logger.warn("No se han registrado actividades todavía")
            println("\n[!] No hay actividades registradas")
            return
        }
        println("\n── LISTADO DE ACTIVIDADES ──")
        actividades.forEach { println(it.obtenerDetalle()) }
    }

    private fun filtrarActividades(){
        println("\n┌───────────────────────────────────────────────┐")
        println("│          FILTRAR ACTIVIDADES POR..             │")
        println("└───────────────────────────────────────────────┘")
        println("[1] Tipo")
        println("[2] Estado")
        println("[3] Etiqueta")
        println("[4] Usuario")
        println("[5] Fecha (hoy, mañana, esta semana o este mes)")
        print("\n[+] Elija una opción: ")

        when (leerOpcion()) {
            1 -> filtrarPorTipo()
            2 -> filtrarPorEstado()
            3 -> filtrarPorEtiqueta()
            4 -> filtrarPorUsuario()
            5 -> filtrarPorFecha()

            else -> println("[-] Opción incorrecta")
        }
    }

    private fun <T> comprobarListaVacia(lista: List<T>, log: String, msj: String): Boolean{
        if (lista.isEmpty()) {
            Logger.warn(log)
            println(msj)
            return true
        }
        return false
    }

    private fun filtrarPorEtiqueta() {
        print("[+] Introduce la etiqueta a filtrar: ")
        val etiqueta = readln().trim().lowercase()
        val actividades = servicio.filtrarPorEtiqueta(etiqueta)
        if (comprobarListaVacia(actividades, "No se han encontrado actividades con '$etiqueta'", "\n[!] No hay actividades con esa etiqueta")) return
        println("\n── LISTADO DE ACTIVIDADES ──")
        actividades.forEach { println(it.obtenerDetalle()) }
    }

    private fun filtrarPorEstado() {
        print("[+] Introduce el estado a filtrar: ")
        val resp = readln().trim().lowercase()
        var estado: Estado? = null
        Estado.entries.forEach { if (it.name.lowercase() == resp) estado = it }
        val actividades = if (estado != null) servicio.filtrarPorEstado(estado!!) else listOf()
        if (comprobarListaVacia(actividades, "No se han encontrado actividades con estado '$estado'", "\n[!] No hay actividades con ese estado")) return
        println("\n── LISTADO DE ACTIVIDADES ──")
        actividades.forEach { println(it.obtenerDetalle()) }
    }

    private fun filtrarPorTipo() {
        print("[+] Introduce el tipo a filtrar: ")
        val tipo = readln().trim().lowercase()
        val actividades = servicio.filtrarPorTipo(tipo)
        if (comprobarListaVacia(actividades, "No se han encontrado actividades con tipo '$tipo'", "\n[!] No hay actividades con ese tipo")) return
        println("\n── LISTADO DE ACTIVIDADES ──")
        actividades.forEach { println(it.obtenerDetalle()) }
    }

    private fun filtrarPorUsuario(){
        print("[+] Introduce la id del usuario a filtrar: ")
        val id = readln().toIntOrNull()
        if (id == null){
            Logger.warn("La id debe ser numérica")
            println("\n[!]La id debe ser numérica")
            return
        }
        val actividades = servicio.filtrarPorUsuario(id)
        if (comprobarListaVacia(actividades, "No se han encontrado actividades del usuario con id '$id'", "\n[!] No hay actividades de ese usuario")) return
        println("\n── LISTADO DE ACTIVIDADES ──")
        actividades.forEach { println(it.obtenerDetalle()) }
    }

    private fun filtrarPorFecha(){
        print("[+] Introduce la fecha a filtrar: ")
        val fecha = readln().trim().lowercase()
        val actividades = servicio.filtrarPorFecha(fecha)
        if (comprobarListaVacia(actividades, "No se han encontrado actividades con fecha '$fecha'", "\n[!] No hay actividades con esa fecha")) return
        println("\n── LISTADO DE ACTIVIDADES ──")
        actividades.forEach { println(it.obtenerDetalle()) }
    }

    private fun gestionarSubtareas() {
        print("[+] ID de la tarea padre: ")
        val id = readln().toLongOrNull()
        if (id == null) {
            Logger.warn("Debes introducir una id numérica válida")
            return
        }

        val tarea = servicio.buscarActividad(id) as? Tarea
        if (tarea == null) {
            Logger.warn("Actividad $id no encontrada")
            println("[-] ID no válido o no es una tarea")
            return
        }

        print("[+] Descripción de la subtarea: ")
        val desc = readln()

        try {
            servicio.crearSubtarea(id, desc)
            println("[+] Subtarea creada correctamente")
        } catch (e: Exception) {
            println("[-] Error: ${e.message}")
        }
    }

    private fun cambiarEstadoTarea() {
        print("[+] ID de la tarea: ")
        val id = readln().toLongOrNull() ?: return println("[-] ID inválido")

        val tarea = servicio.buscarActividad(id) as? Tarea
            ?: return println("[-] No es una tarea válida")

        println("\n┌───────────────────────────────────────────────┐")
        println("│           CAMBIAR ESTADO DE TAREA              │")
        println("└───────────────────────────────────────────────┘")
        println("[1] ABIERTA")
        println("[2] EN PROGRESO")
        println("[3] FINALIZADA")
        print("\n[+] Nuevo estado: ")

        try {
            servicio.cambiarEstadoTarea(id, leerOpcion())
            println("[+] Estado actualizado")
        } catch (e: Exception) {
            Logger.error("Error al cambiar de estado: ${e.message}")
        }
    }

    // ==================== DASHBOARD ====================
    private fun mostrarDashboard() {
        val resumen = servicio.obtenerResumenTareas()
        val eventos = servicio.obtenerEventosProgramados()

        println("\n┌─────────────────────────────────┐")
        println("│        PANEL DE CONTROL          │")
        println("├─────────────────────────────────┤")
        println("│       RESUMEN DE TAREAS          │")
        println("├─────────────────────────────────┤")
        println("│ Tareas totales: ${resumen.totalTareas}")
        println("│ Tareas madre: ${resumen.tareasMadre.size}")
        resumen.tareasMadre.forEach {
            println("│   - Tarea #${it.key} → ${it.value} subtareas")
        }
        println("├─────────────────────────────────┤")
        println("│ Tareas por estado:")
        println("│   Abiertas: ${resumen.porEstado[Estado.ABIERTA] ?: 0}")
        println("│   En progreso: ${resumen.porEstado[Estado.EN_PROGRESO] ?: 0}")
        println("│   Finalizadas: ${resumen.porEstado[Estado.FINALIZADA] ?: 0}")
        println("├─────────────────────────────────┤")
        println("│   EVENTOS PROGRAMADOS            │")
        println("├─────────────────────────────────┤")
        println("│ Hoy: ${eventos.hoy}")
        println("│ Mañana: ${eventos.manana}")
        println("│ Esta semana: ${eventos.estaSemana}")
        println("│ Este mes: ${eventos.esteMes}")
        println("└─────────────────────────────────┘")
    }

    // ==================== HISTORIAL ====================
    private fun mostrarHistorial(tareaId: Long) {
        try {
            val historial = servicio.obtenerHistorialTarea(tareaId)

            println("\n┌─────────────────────────────────┐")
            println("│       HISTORIAL DE TAREA #$tareaId    │")
            println("├──────────┬──────────┬────────────────┤")
            println("│   Fecha  │  Acción  │    Detalle     │")

            historial.forEach { registro ->
                println("├──────────┼──────────┼────────────────┤")
                println("│ ${registro.fecha} │ ${registro.accion.padEnd(8)} │ ${registro.detalle.padEnd(14)} │")
            }

            println("└──────────┴──────────┴────────────────┘")
        } catch (e: Exception) {
            println("[-] Error al obtener historial: ${e.message}")
        }
    }

    // ==================== UTILIDADES ====================
    private fun leerOpcion(): Int {
        return try {
            readln().toInt()
        } catch (e: NumberFormatException) {
            Logger.warn("Input no numérico recibido")
            -1
        }
    }
}
