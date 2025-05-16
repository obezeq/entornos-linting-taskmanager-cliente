package es.prog2425.taskmanager.datos

import es.prog2425.taskmanager.dominio.Actividad

interface IActividadRepository {
    fun agregar(actividad: Actividad)
    fun listar(): List<Actividad>
    fun buscarPorId(id: Long): Actividad?
}