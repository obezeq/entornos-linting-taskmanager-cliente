# detekt

## Metrics

* 103 number of properties

* 79 number of functions

* 18 number of classes

* 6 number of packages

* 17 number of kt files

## Complexity Report

* 1,224 lines of code (loc)

* 986 source lines of code (sloc)

* 755 logical lines of code (lloc)

* 45 comment lines of code (cloc)

* 211 cyclomatic complexity (mcc)

* 97 cognitive complexity

* 93 number of total code smells

* 4% comment source ratio

* 279 mcc per 1,000 lloc

* 123 code smells per 1,000 lloc

## Findings (93)

### complexity, CyclomaticComplexMethod (1)

Prefer splitting up complex methods into smaller, easier to test methods.

[Documentation](https://detekt.dev/docs/rules/complexity#cyclomaticcomplexmethod)

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/aplicacion/ActividadService.kt:74:17
```
The function obtenerPorFecha appears to be too complex based on Cyclomatic Complexity (complexity: 16). Defined complexity threshold for methods is set to '15'
```
```kotlin
71         return obtenerPorFecha(fecha)
72     }
73 
74     private fun obtenerPorFecha(fecha: String): List<Evento> {
!!                 ^ error
75         val hoy = LocalDate.now()
76         val eventos = repositorio.listar().filterIsInstance<Evento>()
77         val lista = mutableListOf<Evento>()

```

### complexity, LongParameterList (2)

The more parameters a function has the more complex it is. Long parameter lists are often used to control complex algorithms and violate the Single Responsibility Principle. Prefer functions with short parameter lists.

[Documentation](https://detekt.dev/docs/rules/complexity#longparameterlist)

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/dominio/Evento.kt:7:33
```
The constructor(id: Long, fechaCreacion: String, descripcion: String, fecha: String, ubicacion: String, etiquetas: List<String>) has too many parameters. The current threshold is set to 5.
```
```kotlin
4  import es.prog2425.taskmanager.utilidades.Utils
5  
6  
7  class Evento private constructor(
!                                  ^ error
8      id: Long,
9      fechaCreacion: String,
10     descripcion: String,

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/dominio/Tarea.kt:6:32
```
The constructor(id: Long, fechaCreacion: String, descripcion: String, estado: Estado, parent: Tarea?, asignadoA: Usuario?, etiquetas: List<String>) has too many parameters. The current threshold is set to 5.
```
```kotlin
3  import es.prog2425.taskmanager.utilidades.Logger
4  import es.prog2425.taskmanager.utilidades.Utils
5  
6  class Tarea private constructor(
!                                 ^ error
7      id: Long,
8      fechaCreacion: String,
9      descripcion: String,

```

### complexity, TooManyFunctions (2)

Too many functions inside a/an file/class/object/interface always indicate a violation of the single responsibility principle. Maybe the file/class/object/interface wants to manage too many things at once. Extract functionality which clearly belongs together.

[Documentation](https://detekt.dev/docs/rules/complexity#toomanyfunctions)

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/aplicacion/ActividadService.kt:10:7
```
Class 'ActividadService' with '20' functions detected. Defined threshold inside classes is set to '11'
```
```kotlin
7  import java.time.LocalDate
8  import java.time.format.DateTimeFormatter
9  
10 class ActividadService(
!!       ^ error
11     private val repositorio: IActividadRepository,
12     private val userRepository: UserRepository
13 ) {

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/presentacion/ConsolaUI.kt:10:7
```
Class 'ConsolaUI' with '24' functions detected. Defined threshold inside classes is set to '11'
```
```kotlin
7  import es.prog2425.taskmanager.utilidades.Logger
8  import es.prog2425.taskmanager.utilidades.Utils
9  
10 class ConsolaUI(private val servicio: ActividadService) {
!!       ^ error
11 
12     fun iniciar() {
13         Logger.info("Sistema interactivo iniciado")

```

### exceptions, SwallowedException (2)

The caught exception is swallowed. The original exception could be lost.

[Documentation](https://detekt.dev/docs/rules/exceptions#swallowedexception)

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/aplicacion/ActividadService.kt:81:22
```
The caught exception is swallowed. The original exception could be lost.
```
```kotlin
78         fun fechaValida(evento: Evento): LocalDate? {
79             return try {
80                 LocalDate.parse(evento.fecha, DateTimeFormatter.ofPattern("dd-MM-yyyy"))
81             } catch (e: Exception) {
!!                      ^ error
82                 null
83             }
84         }

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/aplicacion/ActividadService.kt:166:22
```
The caught exception is swallowed. The original exception could be lost.
```
```kotlin
163         fun fechaValida(evento: Evento): LocalDate? {
164             return try {
165                 LocalDate.parse(evento.fecha, DateTimeFormatter.ofPattern("dd-MM-yyyy"))
166             } catch (e: Exception) {
!!!                      ^ error
167                 null
168             }
169         }

```

### exceptions, TooGenericExceptionCaught (16)

The caught exception is too generic. Prefer catching specific exceptions to the case that is currently handled.

[Documentation](https://detekt.dev/docs/rules/exceptions#toogenericexceptioncaught)

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/Main.kt:28:14
```
The caught exception is too generic. Prefer catching specific exceptions to the case that is currently handled.
```
```kotlin
25 
26         Logger.info("Servicios inicializados - Iniciando UI")
27         consola.iniciar()
28     } catch (e: Exception) {
!!              ^ error
29         Logger.error("Error crítico durante la inicialización: ${e.message}", e)
30         exitProcess(1)
31     }

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/aplicacion/ActividadService.kt:26:18
```
The caught exception is too generic. Prefer catching specific exceptions to the case that is currently handled.
```
```kotlin
23             val tarea = Tarea.creaInstancia(descripcion, null, etiquetas)
24             repositorio.agregar(tarea)
25             Logger.info("Tarea creada exitosamente | ID:${tarea.id} | Desc:${descripcion.take(15)}...")
26         } catch (e: Exception) {
!!                  ^ error
27             Logger.error("Fallo creación tarea: ${e.message} | Stacktrace:", e)
28             throw e
29         }

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/aplicacion/ActividadService.kt:81:22
```
The caught exception is too generic. Prefer catching specific exceptions to the case that is currently handled.
```
```kotlin
78         fun fechaValida(evento: Evento): LocalDate? {
79             return try {
80                 LocalDate.parse(evento.fecha, DateTimeFormatter.ofPattern("dd-MM-yyyy"))
81             } catch (e: Exception) {
!!                      ^ error
82                 null
83             }
84         }

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/aplicacion/ActividadService.kt:166:22
```
The caught exception is too generic. Prefer catching specific exceptions to the case that is currently handled.
```
```kotlin
163         fun fechaValida(evento: Evento): LocalDate? {
164             return try {
165                 LocalDate.parse(evento.fecha, DateTimeFormatter.ofPattern("dd-MM-yyyy"))
166             } catch (e: Exception) {
!!!                      ^ error
167                 null
168             }
169         }

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/datos/ActividadRepositoryMemoria.kt:19:18
```
The caught exception is too generic. Prefer catching specific exceptions to the case that is currently handled.
```
```kotlin
16             }
17             actividades.add(actividad)
18             Logger.info("Actividad ${actividad.id} almacenada (Total: ${actividades.size})")
19         } catch (e: Exception) {
!!                  ^ error
20             Logger.error("Error almacenando actividad: ${e.message}", e)
21             throw e
22         }

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/datos/UserRepositoryMemoria.kt:22:18
```
The caught exception is too generic. Prefer catching specific exceptions to the case that is currently handled.
```
```kotlin
19             usuarios.add(nuevoUsuario)
20             Logger.info("Usuario creado: ID:${nuevoUsuario.id} | ${nuevoUsuario.email}")
21             Logger.debug("Repositorio usuarios: ${usuarios.size + 1} registros")
22         } catch (e: Exception) {
!!                  ^ error
23             Logger.error("Error creando usuario: ${e.message}", e)
24             throw e
25         }

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/dominio/Evento.kt:26:18
```
The caught exception is too generic. Prefer catching specific exceptions to the case that is currently handled.
```
```kotlin
23                 Logger.warn("Ubicación vacía en evento ID:$id")
24                 "Ubicación vacía"
25             }
26         } catch (e: Exception) {
!!                  ^ error
27             Logger.error("Error validación evento: ${e.message}")
28             throw e
29         }

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/dominio/Tarea.kt:57:18
```
The caught exception is too generic. Prefer catching specific exceptions to the case that is currently handled.
```
```kotlin
54             registrarAccion("CambioEstado", "${this.estado} → $estado")
55             this.estado = estado
56             Logger.debug("Estado actualizado correctamente")
57         } catch (e: Exception) {
!!                  ^ error
58             Logger.error("Error cambiando estado tarea $id: ${e.message}")
59             throw e
60         }

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/presentacion/ConsolaUI.kt:31:22
```
The caught exception is too generic. Prefer catching specific exceptions to the case that is currently handled.
```
```kotlin
28                     print("[+] Presiona ENTER para continuar...")
29                     readln()
30                 }
31             } catch (e: Exception) {
!!                      ^ error
32                 Logger.error("Error en flujo principal: ${e.javaClass.simpleName} - ${e.message}")
33             }
34         } while (opcion != 4)

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/presentacion/ConsolaUI.kt:85:18
```
The caught exception is too generic. Prefer catching specific exceptions to the case that is currently handled.
```
```kotlin
82         try {
83             val usuario = servicio.crearUsuario(nombre, email)
84             println("[+] Usuario creado: ${usuario.nombre}")
85         } catch (e: Exception) {
!!                  ^ error
86             Logger.error("No se ha podido crear el usuario correctamente")
87             println("[-] Error al crear usuario: ${e.message}")
88         }

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/presentacion/ConsolaUI.kt:111:18
```
The caught exception is too generic. Prefer catching specific exceptions to the case that is currently handled.
```
```kotlin
108         try {
109             servicio.asignarTarea(tareaId, usuarioId)
110             println("[+] Tarea asignada correctamente")
111         } catch (e: Exception) {
!!!                  ^ error
112             Logger.error(e.message.toString())
113         }
114     }

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/presentacion/ConsolaUI.kt:138:18
```
The caught exception is too generic. Prefer catching specific exceptions to the case that is currently handled.
```
```kotlin
135                     mostrarHistorial(tareas[seleccion - 1].id)
136                 }
137             }
138         } catch (e: Exception) {
!!!                  ^ error
139             Logger.error("${e.message}")
140         }
141     }

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/presentacion/ConsolaUI.kt:208:18
```
The caught exception is too generic. Prefer catching specific exceptions to the case that is currently handled.
```
```kotlin
205         try {
206             servicio.crearEvento(descripcion, fecha, ubicacion)
207             println("[+] Evento creado correctamente")
208         } catch (e: Exception) {
!!!                  ^ error
209             Logger.warn("Error al crear evento: ${e.message}")
210             println("[-] Error al crear evento: ${e.message}")
211 

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/presentacion/ConsolaUI.kt:330:18
```
The caught exception is too generic. Prefer catching specific exceptions to the case that is currently handled.
```
```kotlin
327         try {
328             servicio.crearSubtarea(id, desc)
329             println("[+] Subtarea creada correctamente")
330         } catch (e: Exception) {
!!!                  ^ error
331             println("[-] Error: ${e.message}")
332         }
333     }

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/presentacion/ConsolaUI.kt:353:18
```
The caught exception is too generic. Prefer catching specific exceptions to the case that is currently handled.
```
```kotlin
350         try {
351             servicio.cambiarEstadoTarea(id, leerOpcion())
352             println("[+] Estado actualizado")
353         } catch (e: Exception) {
!!!                  ^ error
354             Logger.error("Error al cambiar de estado: ${e.message}")
355         }
356     }

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/presentacion/ConsolaUI.kt:404:18
```
The caught exception is too generic. Prefer catching specific exceptions to the case that is currently handled.
```
```kotlin
401             }
402 
403             println("└──────────┴──────────┴────────────────┘")
404         } catch (e: Exception) {
!!!                  ^ error
405             println("[-] Error al obtener historial: ${e.message}")
406         }
407     }

```

### naming, InvalidPackageDeclaration (17)

Kotlin source files should be stored in the directory corresponding to its package statement.

[Documentation](https://detekt.dev/docs/rules/naming#invalidpackagedeclaration)

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/Main.kt:1:1
```
The package declaration does not match the actual file location.
```
```kotlin
1 package es.prog2425.taskmanager
! ^ error
2 
3 import es.prog2425.taskmanager.aplicacion.ActividadService
4 import es.prog2425.taskmanager.datos.ActividadRepositoryMemoria

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/aplicacion/ActividadService.kt:1:1
```
The package declaration does not match the actual file location.
```
```kotlin
1 package es.prog2425.taskmanager.aplicacion
! ^ error
2 
3 import es.prog2425.taskmanager.datos.IActividadRepository
4 import es.prog2425.taskmanager.datos.UserRepository

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/aplicacion/ResumenEventos.kt:1:1
```
The package declaration does not match the actual file location.
```
```kotlin
1 package es.prog2425.taskmanager.aplicacion
! ^ error
2 
3 data class ResumenEventos(
4     val hoy: Int,

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/aplicacion/ResumenTareas.kt:1:1
```
The package declaration does not match the actual file location.
```
```kotlin
1 package es.prog2425.taskmanager.aplicacion
! ^ error
2 
3 import es.prog2425.taskmanager.dominio.Estado
4 

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/datos/ActividadRepositoryMemoria.kt:1:1
```
The package declaration does not match the actual file location.
```
```kotlin
1 package es.prog2425.taskmanager.datos
! ^ error
2 
3 import es.prog2425.taskmanager.dominio.Actividad
4 import es.prog2425.taskmanager.utilidades.Logger

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/datos/IActividadRepository.kt:1:1
```
The package declaration does not match the actual file location.
```
```kotlin
1 package es.prog2425.taskmanager.datos
! ^ error
2 
3 import es.prog2425.taskmanager.dominio.Actividad
4 

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/datos/UserRepository.kt:1:1
```
The package declaration does not match the actual file location.
```
```kotlin
1 package es.prog2425.taskmanager.datos
! ^ error
2 
3 import es.prog2425.taskmanager.dominio.Usuario
4 

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/datos/UserRepositoryMemoria.kt:1:1
```
The package declaration does not match the actual file location.
```
```kotlin
1 package es.prog2425.taskmanager.datos
! ^ error
2 
3 import es.prog2425.taskmanager.dominio.Usuario
4 import es.prog2425.taskmanager.utilidades.Logger

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/dominio/Actividad.kt:1:1
```
The package declaration does not match the actual file location.
```
```kotlin
1 package es.prog2425.taskmanager.dominio
! ^ error
2 
3 abstract class Actividad(
4     var id: Long,

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/dominio/Estado.kt:1:1
```
The package declaration does not match the actual file location.
```
```kotlin
1 package es.prog2425.taskmanager.dominio
! ^ error
2 
3 enum class Estado { ABIERTA, FINALIZADA, EN_PROGRESO }

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/dominio/Evento.kt:1:1
```
The package declaration does not match the actual file location.
```
```kotlin
1 package es.prog2425.taskmanager.dominio
! ^ error
2 
3 import es.prog2425.taskmanager.utilidades.Logger
4 import es.prog2425.taskmanager.utilidades.Utils

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/dominio/Tarea.kt:1:1
```
The package declaration does not match the actual file location.
```
```kotlin
1 package es.prog2425.taskmanager.dominio
! ^ error
2 
3 import es.prog2425.taskmanager.utilidades.Logger
4 import es.prog2425.taskmanager.utilidades.Utils

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/dominio/Usuario.kt:1:1
```
The package declaration does not match the actual file location.
```
```kotlin
1 package es.prog2425.taskmanager.dominio
! ^ error
2 
3 data class Usuario(
4     val id: Int,

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/presentacion/ConsolaUI.kt:1:1
```
The package declaration does not match the actual file location.
```
```kotlin
1 package es.prog2425.taskmanager.presentacion
! ^ error
2 
3 import es.prog2425.taskmanager.aplicacion.ActividadService
4 import es.prog2425.taskmanager.dominio.Actividad

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/utilidades/Logger.kt:1:1
```
The package declaration does not match the actual file location.
```
```kotlin
1 package es.prog2425.taskmanager.utilidades
! ^ error
2 
3 import org.slf4j.LoggerFactory
4 

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/utilidades/Utils.kt:1:1
```
The package declaration does not match the actual file location.
```
```kotlin
1 package es.prog2425.taskmanager.utilidades
! ^ error
2 
3 import java.time.LocalDate
4 import java.time.format.DateTimeFormatter

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/test/kotlin/ActividadServiceTest.kt:1:1
```
The package declaration does not match the actual file location.
```
```kotlin
1 package es.prog2425.taskmanager.aplicacion
! ^ error
2 
3 import es.prog2425.taskmanager.datos.IActividadRepository
4 import es.prog2425.taskmanager.datos.UserRepository

```

### naming, MemberNameEqualsClassName (1)

A member should not be given the same name as its parent class or object.

[Documentation](https://detekt.dev/docs/rules/naming#membernameequalsclassname)

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/utilidades/Logger.kt:6:5
```
A member is named after the object. This might result in confusion. Please rename the member.
```
```kotlin
3  import org.slf4j.LoggerFactory
4  
5  object Logger {
6      private val logger = LoggerFactory.getLogger("TaskManager")
!      ^ error
7  
8      fun info(mensaje: String) {
9          logger.info(mensaje)

```

### style, MagicNumber (26)

Report magic numbers. Magic number is a numeric literal that is not defined as a constant and hence it's unclear what the purpose of this number is. It's better to declare such numbers as constants and give them a proper name. By default, -1, 0, 1, and 2 are not considered to be magic numbers.

[Documentation](https://detekt.dev/docs/rules/style#magicnumber)

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/aplicacion/ActividadService.kt:25:95
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
22 
23             val tarea = Tarea.creaInstancia(descripcion, null, etiquetas)
24             repositorio.agregar(tarea)
25             Logger.info("Tarea creada exitosamente | ID:${tarea.id} | Desc:${descripcion.take(15)}...")
!!                                                                                               ^ error
26         } catch (e: Exception) {
27             Logger.error("Fallo creación tarea: ${e.message} | Stacktrace:", e)
28             throw e

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/aplicacion/ActividadService.kt:133:13
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
130 
131         val estado = when (opcionElegida) {
132             1 -> Estado.ABIERTA
133             2 -> Estado.EN_PROGRESO
!!!             ^ error
134             3 -> {
135                 if (!tarea.puedeCerrarse()) {
136                     Logger.error("Intento de cerrar tarea $tareaId con subtareas pendientes")

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/aplicacion/ActividadService.kt:134:13
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
131         val estado = when (opcionElegida) {
132             1 -> Estado.ABIERTA
133             2 -> Estado.EN_PROGRESO
134             3 -> {
!!!             ^ error
135                 if (!tarea.puedeCerrarse()) {
136                     Logger.error("Intento de cerrar tarea $tareaId con subtareas pendientes")
137                     throw IllegalStateException("Subtareas pendientes | Tarea:$tareaId")

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/presentacion/ConsolaUI.kt:22:21
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
19                 Logger.debug("Opción seleccionada: $opcion")
20                 when (opcion) {
21                     1 -> gestionUsuarios()
22                     2 -> gestionActividades()
!!                     ^ error
23                     3 -> mostrarDashboard()
24                     4 -> println("[+] ¡Hasta luego!")
25                     else -> println("[-] Opción no válida")

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/presentacion/ConsolaUI.kt:23:21
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
20                 when (opcion) {
21                     1 -> gestionUsuarios()
22                     2 -> gestionActividades()
23                     3 -> mostrarDashboard()
!!                     ^ error
24                     4 -> println("[+] ¡Hasta luego!")
25                     else -> println("[-] Opción no válida")
26                 }

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/presentacion/ConsolaUI.kt:24:21
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
21                     1 -> gestionUsuarios()
22                     2 -> gestionActividades()
23                     3 -> mostrarDashboard()
24                     4 -> println("[+] ¡Hasta luego!")
!!                     ^ error
25                     else -> println("[-] Opción no válida")
26                 }
27                 if (opcion != 4) {

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/presentacion/ConsolaUI.kt:27:31
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
24                     4 -> println("[+] ¡Hasta luego!")
25                     else -> println("[-] Opción no válida")
26                 }
27                 if (opcion != 4) {
!!                               ^ error
28                     print("[+] Presiona ENTER para continuar...")
29                     readln()
30                 }

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/presentacion/ConsolaUI.kt:34:28
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
31             } catch (e: Exception) {
32                 Logger.error("Error en flujo principal: ${e.javaClass.simpleName} - ${e.message}")
33             }
34         } while (opcion != 4)
!!                            ^ error
35         Logger.info("Aplicación finalizada correctamente")
36     }
37 

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/presentacion/ConsolaUI.kt:67:17
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
64             opcion = leerOpcion()
65             when (opcion) {
66                 1 -> crearUsuario()
67                 2 -> listarUsuarios()
!!                 ^ error
68                 3 -> asignarTareaAUsuario()
69                 4 -> verTareasUsuario()
70                 5 -> return

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/presentacion/ConsolaUI.kt:68:17
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
65             when (opcion) {
66                 1 -> crearUsuario()
67                 2 -> listarUsuarios()
68                 3 -> asignarTareaAUsuario()
!!                 ^ error
69                 4 -> verTareasUsuario()
70                 5 -> return
71                 else -> println("[-] Opción no válida")

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/presentacion/ConsolaUI.kt:69:17
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
66                 1 -> crearUsuario()
67                 2 -> listarUsuarios()
68                 3 -> asignarTareaAUsuario()
69                 4 -> verTareasUsuario()
!!                 ^ error
70                 5 -> return
71                 else -> println("[-] Opción no válida")
72             }

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/presentacion/ConsolaUI.kt:70:17
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
67                 2 -> listarUsuarios()
68                 3 -> asignarTareaAUsuario()
69                 4 -> verTareasUsuario()
70                 5 -> return
!!                 ^ error
71                 else -> println("[-] Opción no válida")
72             }
73         } while (opcion != 5)

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/presentacion/ConsolaUI.kt:73:28
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
70                 5 -> return
71                 else -> println("[-] Opción no válida")
72             }
73         } while (opcion != 5)
!!                            ^ error
74     }
75 
76     private fun crearUsuario() {

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/presentacion/ConsolaUI.kt:161:17
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
158             opcion = leerOpcion()
159             when (opcion) {
160                 1 -> crearActividad()
161                 2 -> listarActividades()
!!!                 ^ error
162                 3 -> filtrarActividades()
163                 4 -> gestionarSubtareas()
164                 5 -> cambiarEstadoTarea()

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/presentacion/ConsolaUI.kt:162:17
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
159             when (opcion) {
160                 1 -> crearActividad()
161                 2 -> listarActividades()
162                 3 -> filtrarActividades()
!!!                 ^ error
163                 4 -> gestionarSubtareas()
164                 5 -> cambiarEstadoTarea()
165                 6 -> return

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/presentacion/ConsolaUI.kt:163:17
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
160                 1 -> crearActividad()
161                 2 -> listarActividades()
162                 3 -> filtrarActividades()
163                 4 -> gestionarSubtareas()
!!!                 ^ error
164                 5 -> cambiarEstadoTarea()
165                 6 -> return
166                 else -> println("[-] Opción no válida")

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/presentacion/ConsolaUI.kt:164:17
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
161                 2 -> listarActividades()
162                 3 -> filtrarActividades()
163                 4 -> gestionarSubtareas()
164                 5 -> cambiarEstadoTarea()
!!!                 ^ error
165                 6 -> return
166                 else -> println("[-] Opción no válida")
167             }

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/presentacion/ConsolaUI.kt:165:17
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
162                 3 -> filtrarActividades()
163                 4 -> gestionarSubtareas()
164                 5 -> cambiarEstadoTarea()
165                 6 -> return
!!!                 ^ error
166                 else -> println("[-] Opción no válida")
167             }
168         } while (opcion != 6)

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/presentacion/ConsolaUI.kt:168:28
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
165                 6 -> return
166                 else -> println("[-] Opción no válida")
167             }
168         } while (opcion != 6)
!!!                            ^ error
169     }
170 
171     private fun crearActividad() {

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/presentacion/ConsolaUI.kt:181:13
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
178 
179         when (leerOpcion()) {
180             1 -> crearTarea()
181             2 -> crearEvento()
!!!             ^ error
182             else -> println("[-] Opción incorrecta")
183         }
184     }

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/presentacion/ConsolaUI.kt:239:13
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
236 
237         when (leerOpcion()) {
238             1 -> filtrarPorTipo()
239             2 -> filtrarPorEstado()
!!!             ^ error
240             3 -> filtrarPorEtiqueta()
241             4 -> filtrarPorUsuario()
242             5 -> filtrarPorFecha()

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/presentacion/ConsolaUI.kt:240:13
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
237         when (leerOpcion()) {
238             1 -> filtrarPorTipo()
239             2 -> filtrarPorEstado()
240             3 -> filtrarPorEtiqueta()
!!!             ^ error
241             4 -> filtrarPorUsuario()
242             5 -> filtrarPorFecha()
243 

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/presentacion/ConsolaUI.kt:241:13
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
238             1 -> filtrarPorTipo()
239             2 -> filtrarPorEstado()
240             3 -> filtrarPorEtiqueta()
241             4 -> filtrarPorUsuario()
!!!             ^ error
242             5 -> filtrarPorFecha()
243 
244             else -> println("[-] Opción incorrecta")

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/presentacion/ConsolaUI.kt:242:13
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
239             2 -> filtrarPorEstado()
240             3 -> filtrarPorEtiqueta()
241             4 -> filtrarPorUsuario()
242             5 -> filtrarPorFecha()
!!!             ^ error
243 
244             else -> println("[-] Opción incorrecta")
245         }

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/presentacion/ConsolaUI.kt:400:73
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
397 
398             historial.forEach { registro ->
399                 println("├──────────┼──────────┼────────────────┤")
400                 println("│ ${registro.fecha} │ ${registro.accion.padEnd(8)} │ ${registro.detalle.padEnd(14)} │")
!!!                                                                         ^ error
401             }
402 
403             println("└──────────┴──────────┴────────────────┘")

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/presentacion/ConsolaUI.kt:400:105
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
397 
398             historial.forEach { registro ->
399                 println("├──────────┼──────────┼────────────────┤")
400                 println("│ ${registro.fecha} │ ${registro.accion.padEnd(8)} │ ${registro.detalle.padEnd(14)} │")
!!!                                                                                                         ^ error
401             }
402 
403             println("└──────────┴──────────┴────────────────┘")

```

### style, MaxLineLength (8)

Line detected, which is longer than the defined maximum line length in the code style.

[Documentation](https://detekt.dev/docs/rules/style#maxlinelength)

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/Main.kt:21:1
```
Line detected, which is longer than the defined maximum line length in the code style.
```
```kotlin
18     try {
19         val actividadRepo = ActividadRepositoryMemoria()
20         val userRepo = UserRepositoryMemoria()
21         Logger.debug("Repositorios inicializados: Actividades[${actividadRepo.listar().size}] | Usuarios[${userRepo.listar().size}]")
!! ^ error
22 
23         val servicio = ActividadService(actividadRepo, userRepo)
24         val consola = ConsolaUI(servicio)

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/dominio/Evento.kt:38:1
```
Line detected, which is longer than the defined maximum line length in the code style.
```
```kotlin
35     }
36 
37     companion object {
38         fun creaInstancia(descripcion: String, fecha: String, ubicacion: String, etiquetas: List<String> = emptyList()): Evento {
!! ^ error
39             val fechaActual = Utils.obtenerFechaActual()
40             return Evento(
41                 Actividad.generarId(fechaActual),

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/dominio/Tarea.kt:37:1
```
Line detected, which is longer than the defined maximum line length in the code style.
```
```kotlin
34             else -> "❌"
35         }
36         val asignadoStr = asignadoA?.let { " 👤${it.nombre}" } ?: ""
37         val subtareasStr = if (subtareas.isNotEmpty()) "\n  └ Subtareas: ${subtareas.joinToString { "#${it.id}" }}" else ""
!! ^ error
38         val etiquetasStr = if (etiquetas.isNotEmpty()) " 🏷️[${etiquetas.joinToString()}]" else ""
39         return "Tarea #$id$detallePadre: $descripcion $estadoStr$asignadoStr$etiquetasStr$subtareasStr"
40     }

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/presentacion/ConsolaUI.kt:261:1
```
Line detected, which is longer than the defined maximum line length in the code style.
```
```kotlin
258         print("[+] Introduce la etiqueta a filtrar: ")
259         val etiqueta = readln().trim().lowercase()
260         val actividades = servicio.filtrarPorEtiqueta(etiqueta)
261         if (comprobarListaVacia(actividades, "No se han encontrado actividades con '$etiqueta'", "\n[!] No hay actividades con esa etiqueta")) return
!!! ^ error
262         println("\n── LISTADO DE ACTIVIDADES ──")
263         actividades.forEach { println(it.obtenerDetalle()) }
264     }

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/presentacion/ConsolaUI.kt:272:1
```
Line detected, which is longer than the defined maximum line length in the code style.
```
```kotlin
269         var estado: Estado? = null
270         Estado.entries.forEach { if (it.name.lowercase() == resp) estado = it }
271         val actividades = if (estado != null) servicio.filtrarPorEstado(estado!!) else listOf()
272         if (comprobarListaVacia(actividades, "No se han encontrado actividades con estado '$estado'", "\n[!] No hay actividades con ese estado")) return
!!! ^ error
273         println("\n── LISTADO DE ACTIVIDADES ──")
274         actividades.forEach { println(it.obtenerDetalle()) }
275     }

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/presentacion/ConsolaUI.kt:281:1
```
Line detected, which is longer than the defined maximum line length in the code style.
```
```kotlin
278         print("[+] Introduce el tipo a filtrar: ")
279         val tipo = readln().trim().lowercase()
280         val actividades = servicio.filtrarPorTipo(tipo)
281         if (comprobarListaVacia(actividades, "No se han encontrado actividades con tipo '$tipo'", "\n[!] No hay actividades con ese tipo")) return
!!! ^ error
282         println("\n── LISTADO DE ACTIVIDADES ──")
283         actividades.forEach { println(it.obtenerDetalle()) }
284     }

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/presentacion/ConsolaUI.kt:295:1
```
Line detected, which is longer than the defined maximum line length in the code style.
```
```kotlin
292             return
293         }
294         val actividades = servicio.filtrarPorUsuario(id)
295         if (comprobarListaVacia(actividades, "No se han encontrado actividades del usuario con id '$id'", "\n[!] No hay actividades de ese usuario")) return
!!! ^ error
296         println("\n── LISTADO DE ACTIVIDADES ──")
297         actividades.forEach { println(it.obtenerDetalle()) }
298     }

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/presentacion/ConsolaUI.kt:304:1
```
Line detected, which is longer than the defined maximum line length in the code style.
```
```kotlin
301         print("[+] Introduce la fecha a filtrar: ")
302         val fecha = readln().trim().lowercase()
303         val actividades = servicio.filtrarPorFecha(fecha)
304         if (comprobarListaVacia(actividades, "No se han encontrado actividades con fecha '$fecha'", "\n[!] No hay actividades con esa fecha")) return
!!! ^ error
305         println("\n── LISTADO DE ACTIVIDADES ──")
306         actividades.forEach { println(it.obtenerDetalle()) }
307     }

```

### style, NewLineAtEndOfFile (10)

Checks whether files end with a line separator.

[Documentation](https://detekt.dev/docs/rules/style#newlineatendoffile)

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/aplicacion/ResumenEventos.kt:8:2
```
The file D:\ezequiel\INSTITUTO\Entornos de desarrollo\entornos-linting-taskmanager-cliente\src\main\kotlin\aplicacion\ResumenEventos.kt is not ending with a new line.
```
```kotlin
5      val manana: Int,
6      val estaSemana: Int,
7      val esteMes: Int
8  )
!   ^ error

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/aplicacion/ResumenTareas.kt:9:2
```
The file D:\ezequiel\INSTITUTO\Entornos de desarrollo\entornos-linting-taskmanager-cliente\src\main\kotlin\aplicacion\ResumenTareas.kt is not ending with a new line.
```
```kotlin
6      val totalTareas: Int,
7      val tareasMadre: Map<Long, Long>,
8      val porEstado: Map<Estado, Int>
9  )
!   ^ error

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/datos/ActividadRepositoryMemoria.kt:36:2
```
The file D:\ezequiel\INSTITUTO\Entornos de desarrollo\entornos-linting-taskmanager-cliente\src\main\kotlin\datos\ActividadRepositoryMemoria.kt is not ending with a new line.
```
```kotlin
33             else Logger.debug("Actividad encontrada: ${it::class.simpleName}")
34         }
35     }
36 }
!!  ^ error

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/datos/IActividadRepository.kt:9:2
```
The file D:\ezequiel\INSTITUTO\Entornos de desarrollo\entornos-linting-taskmanager-cliente\src\main\kotlin\datos\IActividadRepository.kt is not ending with a new line.
```
```kotlin
6      fun agregar(actividad: Actividad)
7      fun listar(): List<Actividad>
8      fun buscarPorId(id: Long): Actividad?
9  }
!   ^ error

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/datos/UserRepository.kt:9:2
```
The file D:\ezequiel\INSTITUTO\Entornos de desarrollo\entornos-linting-taskmanager-cliente\src\main\kotlin\datos\UserRepository.kt is not ending with a new line.
```
```kotlin
6      fun agregar(usuario: Usuario)
7      fun listar(): List<Usuario>
8      fun buscarPorId(id: Int): Usuario?
9  }
!   ^ error

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/datos/UserRepositoryMemoria.kt:33:2
```
The file D:\ezequiel\INSTITUTO\Entornos de desarrollo\entornos-linting-taskmanager-cliente\src\main\kotlin\datos\UserRepositoryMemoria.kt is not ending with a new line.
```
```kotlin
30     override fun buscarPorId(id: Int): Usuario? {
31         return usuarios.find { it.id == id }
32     }
33 }
!!  ^ error

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/dominio/Estado.kt:3:55
```
The file D:\ezequiel\INSTITUTO\Entornos de desarrollo\entornos-linting-taskmanager-cliente\src\main\kotlin\dominio\Estado.kt is not ending with a new line.
```
```kotlin
1 package es.prog2425.taskmanager.dominio
2 
3 enum class Estado { ABIERTA, FINALIZADA, EN_PROGRESO }
!                                                       ^ error

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/dominio/Usuario.kt:9:2
```
The file D:\ezequiel\INSTITUTO\Entornos de desarrollo\entornos-linting-taskmanager-cliente\src\main\kotlin\dominio\Usuario.kt is not ending with a new line.
```
```kotlin
6      val email: String
7  ) {
8      override fun toString(): String = "$id - $nombre ($email)"
9  }
!   ^ error

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/utilidades/Logger.kt:24:2
```
The file D:\ezequiel\INSTITUTO\Entornos de desarrollo\entornos-linting-taskmanager-cliente\src\main\kotlin\utilidades\Logger.kt is not ending with a new line.
```
```kotlin
21         logger.debug(mensaje)
22     }
23 
24 }
!!  ^ error

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/test/kotlin/ActividadServiceTest.kt:186:3
```
The file D:\ezequiel\INSTITUTO\Entornos de desarrollo\entornos-linting-taskmanager-cliente\src\test\kotlin\ActividadServiceTest.kt is not ending with a new line.
```
```kotlin
183             }
184         }
185     }
186 })
!!!   ^ error

```

### style, ThrowsCount (1)

Restrict the number of throw statements in methods.

[Documentation](https://detekt.dev/docs/rules/style#throwscount)

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/aplicacion/ActividadService.kt:123:9
```
Too many throw statements in the function cambiarEstadoTarea. The maximum number of allowed throw statements is 2.
```
```kotlin
120         parent.agregarSubtarea(subtarea)
121     }
122 
123     fun cambiarEstadoTarea(tareaId: Long, opcionElegida: Int) {
!!!         ^ error
124         Logger.info("Cambiando estado tarea:$tareaId | Opción:$opcionElegida")
125 
126         val tarea = repositorio.buscarPorId(tareaId) as? Tarea

```

### style, UnusedPrivateMember (1)

Private function is unused and should be removed.

[Documentation](https://detekt.dev/docs/rules/style#unusedprivatemember)

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/aplicacion/ActividadService.kt:209:17
```
Private function `verificarSubtareas` is unused.
```
```kotlin
206             .filter { it.asignadoA?.id == usuarioId }
207     }
208 
209     private fun verificarSubtareas(tareaId: Long): Estado {
!!!                 ^ error
210         val tarea = repositorio.buscarPorId(tareaId) as? Tarea
211             ?: throw IllegalArgumentException("ID de tarea no válido")
212         if (!tarea.puedeCerrarse()) throw IllegalStateException("Subtareas pendientes")

```

### style, UnusedPrivateProperty (1)

Property is unused and should be removed.

[Documentation](https://detekt.dev/docs/rules/style#unusedprivateproperty)

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/dominio/Actividad.kt:5:17
```
Private property `fechaCreacion` is unused.
```
```kotlin
2 
3 abstract class Actividad(
4     var id: Long,
5     private val fechaCreacion: String,
!                 ^ error
6     val descripcion: String,
7     val etiquetas: List<String> = emptyList()
8 ) {

```

### style, UseCheckOrError (3)

Use check() or error() instead of throwing an IllegalStateException.

[Documentation](https://detekt.dev/docs/rules/style#usecheckorerror)

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/aplicacion/ActividadService.kt:137:21
```
Use check() or error() instead of throwing an IllegalStateException.
```
```kotlin
134             3 -> {
135                 if (!tarea.puedeCerrarse()) {
136                     Logger.error("Intento de cerrar tarea $tareaId con subtareas pendientes")
137                     throw IllegalStateException("Subtareas pendientes | Tarea:$tareaId")
!!!                     ^ error
138                 } else Estado.FINALIZADA
139             }
140             else -> throw IllegalArgumentException("Opción incorrecta")

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/aplicacion/ActividadService.kt:212:37
```
Use check() or error() instead of throwing an IllegalStateException.
```
```kotlin
209     private fun verificarSubtareas(tareaId: Long): Estado {
210         val tarea = repositorio.buscarPorId(tareaId) as? Tarea
211             ?: throw IllegalArgumentException("ID de tarea no válido")
212         if (!tarea.puedeCerrarse()) throw IllegalStateException("Subtareas pendientes")
!!!                                     ^ error
213         return Estado.FINALIZADA
214     }
215 

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/dominio/Tarea.kt:51:17
```
Use check() or error() instead of throwing an IllegalStateException.
```
```kotlin
48         try {
49             if (estado == Estado.FINALIZADA && !puedeCerrarse()) {
50                 Logger.error("Intento de cerrar tarea $id con subtareas pendientes")
51                 throw IllegalStateException("Subtareas no finalizadas")
!!                 ^ error
52             }
53 
54             registrarAccion("CambioEstado", "${this.estado} → $estado")

```

### style, WildcardImport (2)

Wildcard imports should be replaced with imports using fully qualified class names. Wildcard imports can lead to naming conflicts. A library update can introduce naming clashes with your classes which results in compilation errors.

[Documentation](https://detekt.dev/docs/rules/style#wildcardimport)

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/main/kotlin/aplicacion/ActividadService.kt:5:1
```
es.prog2425.taskmanager.dominio.* is a wildcard import. Replace it with fully qualified imports.
```
```kotlin
2 
3 import es.prog2425.taskmanager.datos.IActividadRepository
4 import es.prog2425.taskmanager.datos.UserRepository
5 import es.prog2425.taskmanager.dominio.*
! ^ error
6 import es.prog2425.taskmanager.utilidades.Logger
7 import java.time.LocalDate
8 import java.time.format.DateTimeFormatter

```

* D:/ezequiel/INSTITUTO/Entornos de desarrollo/entornos-linting-taskmanager-cliente/src/test/kotlin/ActividadServiceTest.kt:5:1
```
es.prog2425.taskmanager.dominio.* is a wildcard import. Replace it with fully qualified imports.
```
```kotlin
2 
3 import es.prog2425.taskmanager.datos.IActividadRepository
4 import es.prog2425.taskmanager.datos.UserRepository
5 import es.prog2425.taskmanager.dominio.*
! ^ error
6 import io.kotest.core.spec.style.DescribeSpec
7 import io.kotest.matchers.collections.shouldContain
8 import io.kotest.matchers.maps.shouldContainKey

```

generated with [detekt version 1.23.6](https://detekt.dev/) on 2025-05-16 13:14:09 UTC
