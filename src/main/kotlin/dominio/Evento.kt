package es.prog2425.taskmanager.dominio

import es.prog2425.taskmanager.utilidades.Logger
import es.prog2425.taskmanager.utilidades.Utils


class Evento private constructor(
    id: Long,
    fechaCreacion: String,
    descripcion: String,
    val fecha: String,
    val ubicacion: String,
    etiquetas: List<String> = emptyList()
) : Actividad(id, fechaCreacion, descripcion, etiquetas) {

    init {
        try {
            require(Utils.esFechaValida(fecha)) {
                Logger.error("Fecha inválida en evento: $fecha")
                "Fecha no válida"
            }
            require(ubicacion.isNotBlank()) {
                Logger.warn("Ubicación vacía en evento ID:$id")
                "Ubicación vacía"
            }
        } catch (e: Exception) {
            Logger.error("Error validación evento: ${e.message}")
            throw e
        }
    }

   override fun obtenerDetalle(): String {
        val etiquetasStr = if (etiquetas.isNotEmpty()) " 🏷️[${etiquetas.joinToString()}]" else ""
        return "Evento ${super.obtenerDetalle()} [Fecha: $fecha, Ubicación: $ubicacion]$etiquetasStr"
    }

    companion object {
        fun creaInstancia(descripcion: String, fecha: String, ubicacion: String, etiquetas: List<String> = emptyList()): Evento {
            val fechaActual = Utils.obtenerFechaActual()
            return Evento(
                Actividad.generarId(fechaActual),
                fechaActual,
                descripcion,
                fecha,
                ubicacion,
                etiquetas
            )
        }
    }
}
