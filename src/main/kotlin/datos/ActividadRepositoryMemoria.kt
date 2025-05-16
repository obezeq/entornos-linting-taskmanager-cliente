package es.prog2425.taskmanager.datos

import es.prog2425.taskmanager.dominio.Actividad
import es.prog2425.taskmanager.utilidades.Logger

// clase de actividad repositorio
class ActividadRepositoryMemoria : IActividadRepository {
    private val actividades = mutableListOf<Actividad>()

    override fun agregar(actividad: Actividad) {
        Logger.debug("Agregando actividad: ${actividad::class.simpleName} ID:${actividad.id}")
        try {
            require(actividad.id > 0) {
                Logger.error("ID inválido al agregar actividad: ${actividad.id}")
                "ID inválido"
            }
            actividades.add(actividad)
            Logger.info("Actividad ${actividad.id} almacenada (Total: ${actividades.size})")
        } catch (e: Exception) {
            Logger.error("Error almacenando actividad: ${e.message}", e)
            throw e
        }
    }

    override fun listar(): List<Actividad> {
        return actividades.toList()
    }

    override fun buscarPorId(id: Long): Actividad? {
        Logger.debug("Buscando actividad ID:$id")
        return actividades.find { it.id == id }.also {
            if (it == null) Logger.warn("Actividad $id no encontrada")
            else Logger.debug("Actividad encontrada: ${it::class.simpleName}")
        }
    }
}