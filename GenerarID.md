```kotlin
/**
* Genera un identificador único basado en:
* - La fecha convertida a `YYYYMMDD` (año, mes y día).
* - Un contador incremental que asegura un valor único en caso de múltiples eventos en la misma fecha.
*
* El contador se almacena en `mapaIdEventos`, que mantiene el número de eventos generados para cada día.
*
* @param fecha La fecha en formato `dd-MM-yyyy` utilizada para generar el identificador único.
* @return Un identificador único en formato `YYYYMMDDN`, donde `N` es el contador del evento en ese día.
*/
fun generarId(fecha: String): Int {
  // TODO: Cálculo automático del id según la documentación aportada...
}
```
