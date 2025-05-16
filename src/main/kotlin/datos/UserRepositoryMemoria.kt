package es.prog2425.taskmanager.datos

import es.prog2425.taskmanager.dominio.Usuario
import es.prog2425.taskmanager.utilidades.Logger

class UserRepositoryMemoria : UserRepository {
    private val usuarios = mutableListOf<Usuario>()
    private var lastId = 0

    override fun agregar(usuario: Usuario) {
        Logger.debug("Agregando usuario: ${usuario.email}")
        try {
            require(usuario.email.isNotBlank()) {
                Logger.warn("Intento de crear usuario con email vacío")
                "Email vacío"
            }

            val nuevoUsuario = usuario.copy(id = ++lastId)
            usuarios.add(nuevoUsuario)
            Logger.info("Usuario creado: ID:${nuevoUsuario.id} | ${nuevoUsuario.email}")
            Logger.debug("Repositorio usuarios: ${usuarios.size + 1} registros")
        } catch (e: Exception) {
            Logger.error("Error creando usuario: ${e.message}", e)
            throw e
        }
    }

    override fun listar(): List<Usuario> = usuarios.toList()

    override fun buscarPorId(id: Int): Usuario? {
        return usuarios.find { it.id == id }
    }
}