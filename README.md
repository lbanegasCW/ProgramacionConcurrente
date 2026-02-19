# Programación concurrente en Java

Este repositorio recopila ejemplos prácticos de concurrencia en Java. Ahora incluye:

1. **Teoría resumida de cada método/modelo de concurrencia** usado en el proyecto.
2. **Un menú único** para ejecutar cualquier ejemplo sin navegar carpeta por carpeta.

---

## Métodos de concurrencia explicados

## 1) Hilos (Threads)

### Idea
Un hilo es una unidad de ejecución dentro de un proceso. Con varios hilos, una aplicación puede realizar tareas en paralelo lógico (o paralelo real si hay varios núcleos).

### ¿Cómo se usa en Java?
- Implementando `Runnable`.
- Extendiendo `Thread`.
- Arrancando con `start()`.

### Ventajas
- Modelo directo para empezar en concurrencia.
- Fácil de entender para tareas simples.

### Riesgos
- Condiciones de carrera si se comparten variables sin sincronización.
- Dificultad para escalar cuando crece el número de hilos.

### Ejemplos en este repo
- `Hilos.NumeroParImpar`
- `Hilos.ValoresHilo`
- `Hilos.ValoresHiloThread`

---

## 2) Mutex (exclusión mutua)

### Idea
Un mutex protege una sección crítica: solo un hilo puede entrar a la vez. Sirve para evitar corrupción de datos compartidos.

### ¿Cómo se usa en Java?
- Con `synchronized` (bloqueo implícito).
- Con clases como `ReentrantLock` (bloqueo explícito).

### Ventajas
- Evita condiciones de carrera en recursos compartidos.

### Riesgos
- Posibles bloqueos (deadlocks) si se adquieren locks en orden inconsistente.
- Contención: demasiados hilos esperando por el mismo lock reduce rendimiento.

### Ejemplo en este repo
- `Mutex.Mutex`

---

## 3) Monitores

### Idea
Un monitor combina:
- exclusión mutua, y
- coordinación entre hilos con espera/notificación.

### ¿Cómo se usa en Java?
- Métodos/bloques `synchronized`.
- `wait()`, `notify()`, `notifyAll()` para sincronizar estados.

### Ventajas
- Muy útil en patrones productor/consumidor.
- Permite modelar estados de espera de forma controlada.

### Riesgos
- Errores sutiles si se usa `wait/notify` sin bucles de condición.
- Notificaciones perdidas si la lógica no está bien diseñada.

### Ejemplos en este repo
- `Monitores.ProductorConsumidor.Main`
- `Monitores.CarreraRelevos.Main`

---

## 4) Colas concurrentes

### Idea
Una cola desacopla productores y consumidores. Unos hilos agregan trabajo y otros lo procesan.

### ¿Cómo se usa en Java?
- `ArrayBlockingQueue` y otras `BlockingQueue`.
- `Exchanger` para intercambio directo entre dos hilos.
- Buffers propios sincronizados.

### Ventajas
- Diseño robusto para pipelines y procesamiento por lotes.
- Reduce el acoplamiento entre hilos.

### Riesgos
- Elegir mal tamaño de buffer puede causar esperas innecesarias.
- Si no se controla el ritmo, puede haber saturación de memoria/cola.

### Ejemplos en este repo
- `Colas.ProductorConsumidor.Main`
- `Colas.ProductorConsumidorBuffer.Main`
- `Colas.ProductorConsumidorExchanger.Main`

---

## 5) Semáforos

### Idea
Un semáforo mantiene un contador de permisos. Un hilo adquiere (`acquire`) y libera (`release`) permisos para entrar a una región o usar recursos limitados.

### ¿Cómo se usa en Java?
- `java.util.concurrent.Semaphore`.

### Ventajas
- Excelente para limitar concurrencia (por ejemplo, máximo N accesos simultáneos).
- Útil en coordinación clásica como “cena de filósofos”.

### Riesgos
- Si no se liberan permisos correctamente, el sistema puede bloquearse.
- Diseño incorrecto puede causar inanición de algunos hilos.

### Ejemplo en este repo
- `Semaforos.CenaFilosofos`

---

## 6) ExecutorService (pool de hilos)

### Idea
En lugar de crear hilos manualmente, se envían tareas a un pool administrado. Mejora control, reutilización y rendimiento.

### ¿Cómo se usa en Java?
- `ExecutorService` + `Executors.newFixedThreadPool(...)`.
- Envío de tareas con `execute()` o `submit()`.
- Cierre ordenado con `shutdown()` y `awaitTermination()`.

### Ventajas
- Escala mejor que crear hilos “a mano”.
- Facilita control de recursos y ciclo de vida.

### Riesgos
- Si el pool es pequeño, puede convertirse en cuello de botella.
- Si es demasiado grande, aumenta overhead y competencia por CPU.

### Ejemplo en este repo
- `ExecutorService.RandomNumberSum`

---

## 7) Ejercicios base de Java

Estos ejemplos (`Java.Calculadora`, `Java.SumadorConArray`, `Java.SumaParesImpares`) ayudan a reforzar lógica y estructura general antes o durante el trabajo concurrente.

---

## Menú único para ejecutar demos

Se agregó `MenuConcurrencia` para ejecutar cualquier ejemplo desde un solo punto.

### Compilar

```bash
javac $(find src -name "*.java")
```

### Ejecutar menú

```bash
java -cp src MenuConcurrencia
```

El menú muestra opciones numeradas para cada demo y la opción `0` para salir.

> Nota: Algunas demos pueden correr de forma continua; si quieres detenerlas, usa `Ctrl + C`.

---

## Estructura general

- `src/Hilos`
- `src/Mutex`
- `src/Monitores`
- `src/Colas`
- `src/Semaforos`
- `src/ExecutorService`
- `src/Java`
- `src/MenuConcurrencia.java` (nuevo launcher)

---

## Contribuir

Si quieres agregar más modelos (por ejemplo `CompletableFuture`, `ForkJoinPool`, o programación reactiva), puedes extender tanto los ejemplos como el menú central.
