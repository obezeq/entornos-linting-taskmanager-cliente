# Práctica: Analizadores de Código Estático (Detekt)
**Rama:** `master`

---

## 1. Instalación y Configuración de Detekt

### Proceso de Instalación
1. **Instalación plugin (IDE)**: me instale el plugin de `Detekt` desde el panel de plugins de IntelliJ IDEA Ultimate.
2. **Añadí el plugin** en el archivo de configuracion de gradle `build.gradle.kts`:
   ```kotlin
   plugins {
       id("io.gitlab.arturbosch.detekt") version "1.23.6"
   }
   ```
3. **Añadi configuraciones Detekt** en el archivo de configuración de gradle `build.gradle.kts` segun mis preferencias:
    ```kotlin
   detekt {
        toolVersion = "1.23.6"
        config.setFrom(files("config/detekt.yml"))
        buildUponDefaultConfig = true
    }
    
    tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
        reports {
            html.required.set(true)
            xml.required.set(false)
        }
    }
    ```
4. **Configuré las reglas** en `config/detekt.yml`, ajustando la configuración para adaptarlas al proyecto segun las necesidades.
5. **Ejecuté el análisis** con `./gradlew detekt`. A continuación incluiré capturas de pantalla y explicaré el proceso para realizar un análisis de ejecución.

**Aquí ejecuto el detekt y se prepara para su inicialización.**

**Se empieza a ejecutar**

**Detekt ha terminado con el análisis** y me empieza a dar los errores del código en la terminal.

**A parte de los errores que me salen en la terminal, Detekt me genera un reporte** en HTML como he configurado previamente el cual se encuentra en la carpeta `build/reports/detekt`. A continuación listo cada uno de los reportes en los diferentes formatos.

---

## 2. Errores Detectados y Soluciones

| # | Error                | Archivo               | Descripción                                                                 | Solución                                                                 | Commit                   |
|---|----------------------|-----------------------|-----------------------------------------------------------------------------|--------------------------------------------------------------------------|--------------------------|
| 1 | MagicNumber          | ConsolaUI.kt (línea 22) | Uso del número `4` sin constante.                                          | Creé `const val OPCION_SALIR = 4`                                        | [b7e9437](https://github.com/obezeq/entornos-linting-taskmanager-cliente/commit/b7e943780c7014240183f1ad578f89f3b38f3d14) |
| 2 | NewLineAtEndOfFile   | ResumenEventos.kt     | Falta línea vacía al final del archivo.                                     | Añadí un salto de línea al final.                                        | [a8a4a74](https://github.com/obezeq/entornos-linting-taskmanager-cliente/commit/a8a4a74986c85e965fbcef91ffada7c1b894abe6)  |
| 3 | WildcardImport       | ActividadServiceTest.kt | Importación con `.*` en lugar de clases específicas.                       | Reemplacé por imports detallados.                                        | [2911c28](https://github.com/obezeq/entornos-linting-taskmanager-cliente/commit/2911c28bec509f3c3d560e013d40e972f709a38b)  |
| 4 | UnusedPrivateMember  | ActividadService.kt   | Método `verificarSubtareas` no utilizado.                                   | Eliminé el método.                                                       | [7a3f0eb](https://github.com/obezeq/entornos-linting-taskmanager-cliente/commit/7a3f0eb329b1c532e18e417369d348e1182e4754)  |
| 5 | TooGenericExceptionCaught | ActividadService.kt | Captura genérica de `Exception` en `crearTarea()`.                         | Cambié a `IllegalArgumentException` + logging específico.                | [d41437a](https://github.com/obezeq/entornos-linting-taskmanager-cliente/commit/d41437a7aab2f52548af45c975ca5d995723efc7)  |

---

## 3. Personalización de Configuración

**Regla Modificada:** `LongParameterList`
- **Antes:** Límite de 5 parámetros por defecto.
- **Después:**
  ```yaml
  complexity:
    LongParameterList:
      functionThreshold: 6
      constructorThreshold: 6
  ```
- **Impacto:** Permitió mantener constructores como el de `Evento` sin violar reglas (**ENLACE ARCHIVO: PONER DESPUES EN GITHUB**).

---

## 4. Preguntas de Reflexión

### 1.a ¿Qué herramienta has usado?
- He usado el plugin de Detekt para el análisis estático del código de mi proyecto en Kotlin. 
- Me ha ayudado a detectar code smells y mantener estándares muy facilmente.

### 1.b Características principales
- Más de 200 reglas configurables, lo cual hace que el plugin sea muy "responsive", y que se adapte a las necesidades del desarrollador sea las que sea.
- Integración con Gradle, me ha permitido integrarlo en Gradle muy facilmente, lo que hace que este plugin sea perfecto si se busca una agilidad y integridad en cualquier dispositivo que se genere el reporte.
- Genera informes HTML/XML, gracias a los informes muy vistosos de HTML/XML, pueden ser leidos de forma muy facil visualmente, a su vez que permite que estos se envien a otros departamentos para su posterior análisis sin necesidad de la perdida te tiempo por parte del desarrollador para generar estos.

### 1.c Beneficios
- Gracias a esta herramienta permite al desarrollador mejorar el código para que sea mas limpio y mantenible a largo plazo.
- Genera una detección temprana de malas prácticas, muy útil para no arrastrar estas a lo largo del código, siendo dificiles y tediosas de eliminar en el largo plazo cuando el código aumente su volumen.

### 2. Análisis de Errores Clave

#### 2.a Error que más mejoró mi código  
**`TooGenericExceptionCaught` en `crearTarea()`:**  
Al principio tenía un `catch (Exception)` que capturaba *cualquier* error, incluso los inesperados. Esto hacía difícil rastrear fallos específicos. Al cambiarlo para capturar solo `IllegalArgumentException` (que es el error que realmente esperaba), el código quedo más claro. Ahora, si hay un problema de validación (como una descripción vacia), el error se maneja de forma específica, y los demas errores se registran como "inesperados". Esto hace el debugging mucho más eficiente.

#### 2.b ¿La solución fue adecuada?  
Totalmente. Entendí que al usar excepciones genéricas podía estar ocultando errores graves sin querer. La solución de capturar primero la excepción específica y luego una general (pero con un log detallado) me parece un equilibrio perfecto entre robustez y claridad.

#### 2.c ¿Por qué ocurrió este error?  
Por prisas. Cuando implementé `crearTarea()`, usé `Exception` para terminar rápido el código sin pensar en mantenimiento a largo plazo. Detekt me hizo ver que esto era una mala práctica que podía llevar a bugs difíciles de detectar.

---

### 3. Personalización de Detekt

#### 3.a Configuraciones disponibles  
Detekt permite:  
- **Activar/desactivar reglas** (ej: deshabilité `MaxLineLength` temporalmente)  
- **Ajustar umbrales** (complejidad ciclomática, parámetros por método)  
- **Listas blancas** (ignorar números mágicos como 0 o 1)  

#### 3.b Cambio principal en configuración  
Modifiqué `LongParameterList` en `detekt.yml`:  
```yaml
complexity:
  LongParameterList:
    functionThreshold: 6  # Por defecto era 5
    constructorThreshold: 6
```

#### 3.c Impacto real en el código
**Antes:** El constructor de `Evento` tenía 6 parámetros y Detekt lo marcaba como error:
```kotlin
// Detekt: "Too many parameters (6 > 5)"
class Evento(
    id: Long, 
    fechaCreacion: String,
    descripcion: String,
    fecha: String,
    ubicacion: String,
    etiquetas: List<String>
)
```

**Después:** Tras ajustar el umbral, el mismo constructor pasó las validaciones sin cambiar su funcionalidad:
```kotlin
// Ahora es válido
class Evento(...)  # Mismos 6 parámetros
```

---

**Reflexión final:**  
Al principio me frustró ver tantos errores en Detekt pensé que estaba haciendo algo mal mientras ejecutaba el Detekt, pero ahora entiendo que era simplemente el reporte que te mostraba y que cada warning es una oportunidad para mejorar mi código.

### 4 Conclusiones
- Creo que herramientas como Detekt son **esenciales** para proyectos profesionales y de producción.
- Automatizan revisiones que humanos podrían pasar por alto, incluso te genera un `html` para que los desarrolles puedan visualizarlo de forma muy visual y poder mandar directamente a otros departamentos sin que el desarrollador tenga que perder el tiempo en escribir tal informe.
- Muy util para detectar errores y mejoras de código que garantizan una mejor seguridad de código.