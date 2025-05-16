```kotlin
private val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

/**
 * Obtiene la fecha actual.
 *
 * @return Un `String` con la fecha actual en formato `"dd-MM-yyyy"`.
 */
fun obtenerFechaActual(): String {
    return LocalDate.now().format(formatter)
}

/**
 * Verifica si una fecha proporcionada es válida según el formato "dd-MM-yyyy".
 *
 * @param fecha La fecha a validar en formato `"dd-MM-yyyy"`.
 * @return `true` si la fecha tiene el formato correcto y es válida en el calendario; `false` en caso contrario.
 */
fun esFechaValida(fecha: String): Boolean {
    return try {
        LocalDate.parse(fecha, formatter)
        true
    } catch (e: DateTimeParseException) {
        false
    }
}
```
