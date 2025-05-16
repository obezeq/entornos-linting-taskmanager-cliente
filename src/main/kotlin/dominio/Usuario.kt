package es.prog2425.taskmanager.dominio

data class Usuario(
    val id: Int,
    val nombre: String,
    val email: String
) {
    override fun toString(): String = "$id - $nombre ($email)"
}