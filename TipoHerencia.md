# TIPO DE HERENCIA

## **1. Especialización ("Es un")**
### 📌 Concepto:
La especialización es la forma más intuitiva de herencia, donde una subclase representa una **versión más específica** de una clase base y puede modificar su comportamiento para adaptarse mejor a su propósito. Se aplica cuando una clase hija **"es un"** tipo más concreto de la clase padre.

📍 **Características:**
- La subclase representa una versión más concreta de la superclase.
- La subclase hereda atributos y métodos de la superclase, pero puede agregar o sobrescribir funcionalidades para especializar su comportamiento.
- Introduce atributos y comportamientos únicos que la distinguen de la superclase.
- Se usa cuando una subclase es conceptualmente un subtipo de la clase base.

🔹 **Ejemplo:**
```kotlin
open class Empleado(val nombre: String, val apellido: String) {
    open fun calcularSalario(): Double {
        return 30000.0 // Salario base
    }
}

class Ingeniero(nombre: String, apellido: String, val especialidad: String) : Empleado(nombre, apellido) {
    override fun calcularSalario(): Double {
        return super.calcularSalario() * 1.5 // Aumentamos el salario de los ingenieros
    }

    fun trabajarEnProyecto() {
        println("El ingeniero $nombre $apellido, especializado en $especialidad, está trabajando en un proyecto.")
    }
}
```
🔹 **Explicación:**
- `Ingeniero` es un `Empleado`, pero modifica `calcularSalario()` para reflejar una diferencia en el salario.
- Además, `Ingeniero` agrega una propiedad (`especialidad`) y un nuevo método (`trabajarEnProyecto()`).
- **Relación conceptual:** Un **ingeniero** sigue siendo un empleado, pero con características adicionales, es decir, un `Ingeniero` es un tipo especializado de `Empleado` porque modifica su comportamiento base y agrega más detalles.

---

## **2. Extensión (Extiende Funcionalidad Heredada)**
### 📌 Concepto:
La extensión de funcionalidad implica **agregar nuevas capacidades** a la clase base **sin modificar su comportamiento original**. Se aplica cuando la subclase amplía la funcionalidad de la superclase en lugar de especializarla.

📍 **Características:**
- No implica necesariamente una relación "es un".
- No cambia el propósito fundamental de la superclase. La subclase conserva la funcionalidad original de la superclase y **la expande con nuevas capacidades**, es decir, mantiene el comportamiento original y agrega nuevas capacidades.
- Puede sobrescribir métodos, pero el propósito del método sigue siendo el mismo.
- Se usa cuando una subclase mantiene la esencia de la clase base, pero la complementa.

🔹 **Ejemplo:**
```kotlin
open class Controlador {
    open fun manejarSolicitud() {
        println("Manejando solicitud básica.")
    }
}

class ControladorAutenticado : Controlador() {
    fun verificarAutenticacion() {
        println("Verificando autenticación del usuario.")
    }

    override fun manejarSolicitud() {
        verificarAutenticacion() // Nueva funcionalidad antes de manejar la solicitud
        super.manejarSolicitud() // Se mantiene el comportamiento original
    }
}
```
🔹 **Explicación:**
- `ControladorAutenticado` no cambia la naturaleza de `Controlador`, pero **extiende** su funcionalidad con `verificarAutenticacion()`, es decir, la **extiende**, pero no cambia su propósito.
- **No es una especialización** porque `ControladorAutenticado` sigue siendo un controlador, pero **con más funciones**.
- El método manejarSolicitud() sigue cumpliendo la misma función: manejar una solicitud, pero ahora requiere autenticación previa.
- La autenticación es una mejora adicional a la funcionalidad original, pero no altera su propósito.
- Conclusión: ControladorAutenticado no es un tipo especializado de Controlador, sino una versión con más funcionalidad.

---

## **3. Especificación (Define Interface)**
### 📌 Concepto:
La especificación se usa para **definir un contrato o interfaz común** para varias clases sin proporcionar una implementación concreta. 

Se implementa con **interfaces** o **clases abstractas** (siempre que las clases abstractas no contengan implementaciones de métodos o atributos). Los contratos puros (especificaciones) no deben tener implementación concreta.

📍 **Características:**
- La superclase no proporciona implementaciones, solo **define qué métodos deben implementarse**.
- Permite que diferentes clases compartan la misma estructura sin requerir herencia clásica.

🔹 **Ejemplo con interfaz:**
```kotlin
interface ModuloCMS {
    fun renderizar()
    fun configurar()
}

class ModuloTexto : ModuloCMS {
    override fun renderizar() {
        println("Renderizando módulo de texto.")
    }

    override fun configurar() {
        println("Configurando módulo de texto.")
    }
}

class ModuloImagen : ModuloCMS {
    override fun renderizar() {
        println("Renderizando módulo de imagen.")
    }

    override fun configurar() {
        println("Configurando módulo de imagen.")
    }
}
```
🔹 **Explicación:**
- `ModuloCMS` **no proporciona implementación**, solo especifica que `renderizar()` y `configurar()` deben existir.
- `ModuloTexto` y `ModuloImagen` **implementan** `ModuloCMS`, pero cada uno con su propia lógica.

✅ **Diferencia clave:** Se usa para **garantizar que las clases cumplen con un conjunto de métodos comunes**, sin importar cómo los implementen.

---

## **4. Construcción (Usado para Construir la Subbase)**
### 📌 Concepto:
Este tipo de herencia **se usa para construir una nueva clase sobre la base de otra**, aunque **no exista una relación conceptual fuerte entre ambas**. La subclase reutiliza la implementación de la superclase sin que haya una relación "es un".

📍 **Características:**
- La subclase usa la implementación de la superclase como **base** para construir su propia lógica.
- **No necesariamente hay una relación "es un"** entre la clase base y la subclase.

🔹 **Ejemplo:**
```kotlin
open class Lista<T> {
    private val elementos = mutableListOf<T>()

    fun agregar(elemento: T) {
        elementos.add(elemento)
    }

    fun remover(): T? = if (elementos.isNotEmpty()) elementos.removeAt(elementos.size - 1) else null
}

class Pila<T> : Lista<T>() {
    fun push(elemento: T) {
        agregar(elemento)
    }

    fun pop(): T? {
        return remover()
    }
}
```
🔹 **Explicación:**
- `Pila` **no es conceptualmente una `Lista`**, pero reutiliza su implementación para gestionar su estructura interna.
- Se usa herencia **para evitar reescribir el código**, aunque no haya una relación de especialización.

✅ **Diferencia clave:** Se hereda **para reutilizar código**, no porque haya una relación lógica fuerte entre las clases.

---

## **📌 Diferencias Claves entre los Tipos de Herencia**
| Tipo de Herencia | Relación | ¿Cambia comportamiento base? | ¿Añade nueva funcionalidad? |
|-----------------|------------|------------------------|-------------------|
| **Especialización ("Es un")** | La subclase **es un** tipo específico de la superclase. | ✅ Puede modificar comportamiento. | ✅ Puede agregar nuevos métodos. |
| **Extensión (Añade funcionalidad)** | La subclase **extiende** la superclase sin cambiar su comportamiento. | ❌ No cambia el comportamiento base. | ✅ Agrega métodos nuevos. |
| **Especificación (Define interface)** | La superclase **define un contrato** para las subclases. | ✅ Obliga a implementar ciertos métodos. | ❌ No tiene implementación concreta. |
| **Construcción (Reutiliza código)** | La subclase **se construye sobre** la superclase. | ❌ No cambia su naturaleza. | ✅ Aprovecha la implementación de la superclase. |

---

## **📌 Conclusión**
Cada tipo de herencia tiene su propósito y **debe usarse según la necesidad del diseño**:

1. **Usa Especialización** cuando una subclase **es realmente un tipo más específico** de la superclase.
2. **Usa Extensión** cuando necesitas **agregar funcionalidad** sin modificar el comportamiento original.
3. **Usa Especificación** cuando necesitas que varias clases **compartan una estructura común** sin forzar herencia innecesaria.
4. **Usa Construcción** cuando **quieres reutilizar código** sin que haya una relación conceptual entre las clases.
