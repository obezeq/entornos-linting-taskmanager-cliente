package es.prog2425.taskmanager.datos

import es.prog2425.taskmanager.dominio.Usuario

interface UserRepository {
    fun agregar(usuario: Usuario)
    fun listar(): List<Usuario>
    fun buscarPorId(id: Int): Usuario?
}