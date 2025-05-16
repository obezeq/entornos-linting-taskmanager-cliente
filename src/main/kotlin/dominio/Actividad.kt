package es.prog2425.taskmanager.dominio

abstract class Actividad(
    var id: Long,
    private val fechaCreacion: String,
    val descripcion: String,
    val etiquetas: List<String> = emptyList()
) {


    init {
        // Validación básica: descripción no vacía
        require(descripcion.isNotBlank()) { "La descripción no puede estar vacía" }
    }

    // Puedo extender tarea y evento
    open fun obtenerDetalle(): String {
        return "$id - $descripcion"
    }

    companion object {
        private val contadorPorFecha = mutableMapOf<String, Int>()


        fun generarId(fecha: String): Long {
            val contador = contadorPorFecha.getOrDefault(fecha, 0) + 1
            contadorPorFecha[fecha] = contador
            return "${fecha.replace("-", "")}${"%03d".format(contador)}".toLong()
        }
    }


}
