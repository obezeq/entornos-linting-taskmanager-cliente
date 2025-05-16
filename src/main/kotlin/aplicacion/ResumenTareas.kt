package es.prog2425.taskmanager.aplicacion

import es.prog2425.taskmanager.dominio.Estado

data class ResumenTareas(
    val totalTareas: Int,
    val tareasMadre: Map<Long, Long>,
    val porEstado: Map<Estado, Int>
)