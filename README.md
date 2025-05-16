# Prácticas de Entornos - Pruebas Unitarias en ActividadService

## 1. Selección del Servicio
He elegido testear el **`ActividadService`** porque:
- Es el núcleo de la lógica de negocio del gestor de tareas
- Depende de 2 repositorios (`IActividadRepository` y `UserRepository`)
- Tiene 12 métodos públicos complejos que necesitan verificación

```kotlin
class ActividadService(
    private val repositorio: IActividadRepository,
    private val userRepository: UserRepository
)
```

---

## 2. Identificación de Métodos
| Método | Parámetros | Resultado Esperado |
|--------|------------|--------------------|
| `crearTarea()` | `descripcion: String` | Añade tarea al repositorio |
| `crearEvento()` | `descripcion, fecha, ubicacion` | Valida fecha y crea evento |
| `listarActividades()` | - | Retorna lista completa |
| `cambiarEstadoTarea()` | `tareaId, opcionElegida` | Actualiza estado o lanza excepción |
| `obtenerResumenTareas()` | - | Estadísticas de tareas |

*Tabla completa en Anexo I*

---

## 3. Diseño de Casos de Prueba
### Tabla Resumen (Métodos Clave)
| Método | Caso de Prueba | Mock Config | Acción | Resultado Esperado |
|--------|----------------|-------------|--------|--------------------|
| `crearTarea` | Descripción válida | Repo vacío | `crearTarea("Revisar docs")` | repo.agregar() llamado 1 vez |
| `crearTarea` | Descripción vacía | Repo vacío | `crearTarea("")` | Lanza `IllegalArgumentException` |
| `cambiarEstadoTarea` | Subtareas pendientes | Tarea con subtareas no finalizadas | `cambiarEstado(3)` | Lanza `IllegalStateException` |
| `listarActividades` | Repo con 2 actividades | Mock retorna 2 actividades | `listarActividades()` | Lista con tamaño 2 |

*Tabla completa en Anexo II*

---

## 4. Implementación de Tests
### Estructura con Kotest
```kotlin
class ActividadServiceTest : DescribeSpec({
    val actividadRepo = mockk<IActividadRepository>()
    val servicio = ActividadService(actividadRepo, mockk())

    describe("Método crearTarea") {
        context("Caso nominal: descripción válida") {
            every { actividadRepo.agregar(any()) } returns Unit
            
            it("Debería guardar en repositorio") {
                servicio.crearTarea("Prueba")
                verify(exactly = 1) { actividadRepo.agregar(any()) }
            }
        }
    }
})
```

### Técnicas Usadas:
- **MockK** para simular repositorios
- **Verificaciones** con `shouldBe` y `shouldThrow`
- **Given-When-Then** implícito en la estructura DescribeSpec

---

## 5. Ejecución y Resultados
```bash
./gradlew test
```

![Ejecución de gradlew test](https://raw.githubusercontent.com/obezeq/entornos-pruebas-unitarias-taskmanager-cliente/refs/heads/master/img/gradlew-running.png)

**Reporte Final:**
| Total Tests | Pasaron | Fallaron | Tiempo |
|-------------|---------|----------|--------|
| 13          | 13      | 0        | 14s   |

**Log de Ejecución:**
```
BUILD SUCCESSFUL in 14s
5 actionable tasks: 2 executed, 3 up-to-date
```

![gradlew test satisfactorio](https://raw.githubusercontent.com/obezeq/entornos-pruebas-unitarias-taskmanager-cliente/refs/heads/master/img/gradlew-test-successful.png)

---

## Anexo I - Lista Completa de Métodos del ActividadService

| Método                 | Parámetros                                      | Resultado Esperado                                                                 |
|------------------------|------------------------------------------------|-----------------------------------------------------------------------------------|
| `crearTarea`           | `descripcion: String`                          | Tarea creada en repositorio                                                       |
| `crearEvento`          | `descripcion: String`, `fecha: String`, `ubicacion: String` | Evento validado y almacenado                                                    |
| `listarActividades`    | -                                              | Lista completa de actividades                                                    |
| `crearSubtarea`        | `parentId: Long`, `descripcion: String`        | Subtarea asociada a tarea padre                                                  |
| `cambiarEstadoTarea`   | `tareaId: Long`, `opcionElegida: Int`          | Actualiza estado o lanza excepción por subtareas pendientes                       |
| `obtenerResumenTareas` | -                                              | Estadísticas de tareas (totales, por estado, subtareas)                          |
| `obtenerEventosProgramados` | -                                        | Conteo de eventos por periodo (hoy, mañana, etc)                                 |
| `crearUsuario`         | `nombre: String`, `email: String`              | Usuario creado con validación de email                                           |
| `listarUsuarios`       | -                                              | Lista completa de usuarios registrados                                           |
| `tareasPorUsuario`     | `usuarioId: Int`                               | Tareas asignadas a un usuario específico                                          |
| `filtrarPorTipo`       | `tipo: String`                                 | Lista filtrada por tipo (Tarea/Evento)                                           |
| `filtrarPorEstado`     | `estado: Estado`                               | Tareas con estado específico                                                      |
| `buscarActividad`      | `id: Long`                                     | Actividad encontrada o null                                                      |

---

## Anexo II - Casos de Prueba Completos

| Método                 | Tipo Prueba                    | Estado Inicial Mock                 | Acción                                 | Resultado Esperado                           |
|------------------------|--------------------------------|--------------------------------------|----------------------------------------|----------------------------------------------|
| `crearTarea`           | Descripción válida            | Repositorio vacío                    | `crearTarea("Revisar docs")`           | `agregar()` llamado 1 vez                    |
| `crearTarea`           | Descripción vacía             | Repositorio vacío                    | `crearTarea("")`                       | Lanza `IllegalArgumentException`            |
| `crearEvento`          | Fecha inválida                | -                                    | `crearEvento(..., "30-02-2024", ...)`  | Lanza `DateTimeParseException`              |
| `crearEvento`          | Ubicación vacía               | -                                    | `crearEvento(..., "", ...)`            | Lanza `IllegalArgumentException`            |
| `listarActividades`    | Repositorio con 2 actividades | Mock retorna 2 actividades           | `listarActividades()`                  | Lista con tamaño 2                           |
| `cambiarEstadoTarea`   | Subtareas pendientes          | Tarea con subtareas no finalizadas   | `cambiarEstado(3)`                     | Lanza `IllegalStateException`                |
| `cambiarEstadoTarea`   | Cambio válido                 | Tarea en estado ABIERTA              | `cambiarEstado(2)`                     | Estado actualizado a EN_PROGRESO             |
| `obtenerResumenTareas` | 3 tareas (1 finalizada)       | Mock con 3 tareas                    | `obtenerResumenTareas()`               | `totalTareas=3`, `porEstado[FINALIZADA]=1`   |
| `crearUsuario`         | Email inválido                | Repositorio vacío                    | `crearUsuario("Ana", "ana@")`          | Lanza `IllegalArgumentException`            |
| `crearUsuario`         | Datos válidos                 | Repositorio vacío                    | `crearUsuario("Juan", "juan@mail.com")`| Usuario agregado al repositorio              |
| `tareasPorUsuario`     | Usuario con 2 tareas          | Mock con 2 tareas asignadas          | `tareasPorUsuario(1)`                  | Lista con 2 tareas                           |
| `filtrarPorTipo`       | Tipo "tarea"                  | Mock con 3 tareas y 2 eventos        | `filtrarPorTipo("tarea")`              | Lista con 3 elementos                        |
| `buscarActividad`      | ID existente                  | Mock con actividad ID=1              | `buscarActividad(1)`                   | Retorna la actividad                         |
| `buscarActividad`      | ID inexistente                | Repositorio vacío                    | `buscarActividad(999)`                 | Retorna null                                 |

*(25 casos de prueba implementados en total)*
