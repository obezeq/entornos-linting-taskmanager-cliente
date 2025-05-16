package es.prog2425.taskmanager.aplicacion

import es.prog2425.taskmanager.datos.IActividadRepository
import es.prog2425.taskmanager.datos.UserRepository
import es.prog2425.taskmanager.dominio.*
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.maps.shouldContainKey
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import io.kotest.assertions.throwables.shouldThrow

class ActividadServiceTest : DescribeSpec({
    val actividadRepo = mockk<IActividadRepository>(relaxed = true)
    val userRepo = mockk<UserRepository>(relaxed = true)
    val servicio = ActividadService(actividadRepo, userRepo)

    // ============================================
    // Tests para crearEvento()
    // ============================================
    describe("Método crearEvento") {
        context("Caso nominal: evento con fecha futura") {
            val fechaValida = LocalDate.now().plusDays(5)
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))

            it("Debería guardar el evento correctamente") {
                servicio.crearEvento("Conferencia", fechaValida, "Auditorio")

                // Verificar que se llamó a agregar en el repositorio
                verify(exactly = 1) { actividadRepo.agregar(any()) }
            }
        }
    }

    // ============================================
    // Tests para obtenerResumenTareas()
    // ============================================
    describe("Método obtenerResumenTareas") {
        context("Repositorio con 2 tareas y 1 subtarea") {
            val tareaPadre = Tarea.creaInstancia("Padre")
            val subtarea = Tarea.creaInstancia("Subtarea", tareaPadre)
            val tareaFinalizada = Tarea.creaInstancia("Finalizada").apply {
                estado = Estado.FINALIZADA
            }

            beforeEach {
                every { actividadRepo.listar() } returns listOf(tareaPadre, subtarea, tareaFinalizada)
            }

            it("Debería calcular correctamente el resumen") {
                val resumen = servicio.obtenerResumenTareas()

                resumen.totalTareas shouldBe 3
                resumen.tareasMadre shouldContainKey tareaPadre.id
                resumen.porEstado[Estado.FINALIZADA] shouldBe 1
            }
        }

        context("Repositorio vacío") {
            every { actividadRepo.listar() } returns emptyList()

            it("Debería retornar contadores en cero") {
                val resumen = servicio.obtenerResumenTareas()
                resumen.totalTareas shouldBe 0
            }
        }
    }

    // ============================================
    // Tests para obtenerEventosProgramados()
    // ============================================
    describe("Método obtenerEventosProgramados") {
        val hoy = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
        val manana = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))

        context("Eventos en diferentes fechas") {
            val eventoHoy = Evento.creaInstancia("Hoy", hoy, "Sala 1")
            val eventoManana = Evento.creaInstancia("Mañana", manana, "Sala 2")

            beforeEach {
                every { actividadRepo.listar() } returns listOf(eventoHoy, eventoManana)
            }

            it("Debería contar eventos correctamente") {
                val resumen = servicio.obtenerEventosProgramados()
                resumen.hoy shouldBe 1
                resumen.manana shouldBe 1
            }
        }
    }

    // ============================================
    // Tests para crearUsuario()
    // ============================================
    describe("Método crearUsuario") {
        context("Caso nominal: datos válidos") {
            val usuarioMock = Usuario(1, "Ana", "ana@taskmanager.com")

            beforeEach {
                every { userRepo.agregar(any()) } returns Unit
                every { userRepo.listar() } returns listOf(usuarioMock)
            }

            it("Debería agregar el usuario al repositorio") {
                servicio.crearUsuario("Ana", "ana@taskmanager.com")
                verify(exactly = 1) { userRepo.agregar(any()) }
            }
        }

        context("Caso error: email inválido") {
            it("Debería lanzar IllegalArgumentException") {
                shouldThrow<IllegalArgumentException> {
                    servicio.crearUsuario("Ana", "ana@")
                }
            }
        }
    }

    // ============================================
    // Tests para tareasPorUsuario()
    // ============================================
    describe("Método tareasPorUsuario") {
        context("Usuario con 2 tareas asignadas") {
            val usuario = Usuario(1, "Carlos", "carlos@mail.com")
            val tarea1 = Tarea.creaInstancia("Tarea 1").apply { asignadoA = usuario }
            val tarea2 = Tarea.creaInstancia("Tarea 2").apply { asignadoA = usuario }

            beforeEach {
                every { actividadRepo.listar() } returns listOf(tarea1, tarea2)
            }

            it("Debería retornar 2 tareas") {
                val tareas = servicio.tareasPorUsuario(1)
                tareas.size shouldBe 2
            }
        }
    }

    // ============================================
    // Tests para buscarActividad()
    // ============================================
    describe("Método buscarActividad") {
        context("ID existente") {
            val tarea = Tarea.creaInstancia("Existente")

            beforeEach {
                every { actividadRepo.buscarPorId(1) } returns tarea
            }

            it("Debería retornar la actividad correcta") {
                servicio.buscarActividad(1) shouldBe tarea
            }
        }

        context("ID inexistente") {
            every { actividadRepo.buscarPorId(999) } returns null

            it("Debería retornar null") {
                servicio.buscarActividad(999) shouldBe null
            }
        }
    }

    // ============================================
    // Tests para crearSubtarea()
    // ============================================
    describe("Método listarActividades") {
        context("Repositorio con 2 actividades") {
            val tarea1 = Tarea.creaInstancia("Tarea 1")
            val evento1 = Evento.creaInstancia("Evento 1", "25-12-2024", "Sala")

            beforeEach {
                every { actividadRepo.listar() } returns listOf(tarea1, evento1)
            }

            it("Debería retornar 2 actividades") {
                servicio.listarActividades().size shouldBe 2
            }
        }
    }
})