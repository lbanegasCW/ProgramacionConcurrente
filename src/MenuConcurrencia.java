import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Lanzador en consola para ejecutar las demos de concurrencia del repositorio.
 *
 * Uso:
 * 1) javac $(find src -name "*.java")
 * 2) java -cp src MenuConcurrencia
 */
public class MenuConcurrencia {
    private static final Map<Integer, OpcionDemo> OPCIONES = new LinkedHashMap<>();

    static {
        // HILOS: cada tarea corre en su propio Thread/Runnable.
        OPCIONES.put(1, new OpcionDemo(
                "Hilos: Números pares e impares",
                "Hilos.NumeroParImpar",
                "Teoría: un hilo es una unidad de ejecución dentro de un proceso. " +
                        "Permite trabajo concurrente, pero si comparten estado hay riesgo de race conditions."));

        OPCIONES.put(2, new OpcionDemo(
                "Hilos: Valores compartidos con Runnable",
                "Hilos.ValoresHilo",
                "Teoría: Runnable separa la lógica del hilo. Es más flexible para reutilizar código y combinarlo con pools."));

        OPCIONES.put(3, new OpcionDemo(
                "Hilos: Valores compartidos con Thread",
                "Hilos.ValoresHiloThread",
                "Teoría: extender Thread es directo para ejemplos simples, pero acopla la tarea al mecanismo de ejecución."));

        // MUTEX: exclusión mutua para secciones críticas.
        OPCIONES.put(4, new OpcionDemo(
                "Mutex: Exclusión mutua en memoria compartida",
                "Mutex.Mutex",
                "Teoría: un mutex permite que solo un hilo entre a la sección crítica. " +
                        "Evita corrupción de datos compartidos."));

        // MONITORES: sincronización + wait/notify sobre un objeto monitor.
        OPCIONES.put(5, new OpcionDemo(
                "Monitores: Productor/Consumidor",
                "Monitores.ProductorConsumidor.Main",
                "Teoría: un monitor combina exclusión mutua y coordinación de estados con wait/notify."));

        OPCIONES.put(6, new OpcionDemo(
                "Monitores: Carrera de relevos",
                "Monitores.CarreraRelevos.Main",
                "Teoría: los monitores permiten turnos ordenados entre hilos sin sondeo activo constante."));

        // COLAS CONCURRENTES: desacoplan productores y consumidores.
        OPCIONES.put(7, new OpcionDemo(
                "Colas: Productor/Consumidor con ArrayBlockingQueue",
                "Colas.ProductorConsumidor.Main",
                "Teoría: BlockingQueue bloquea automáticamente cuando está vacía/llena y simplifica la coordinación."));

        OPCIONES.put(8, new OpcionDemo(
                "Colas: Productor/Consumidor con Buffer propio",
                "Colas.ProductorConsumidorBuffer.Main",
                "Teoría: un buffer manual muestra la sincronización explícita y ayuda a entender la base del patrón."));

        OPCIONES.put(9, new OpcionDemo(
                "Colas: Productor/Consumidor con Exchanger",
                "Colas.ProductorConsumidorExchanger.Main",
                "Teoría: Exchanger sincroniza a dos hilos para intercambiar datos punto a punto."));

        // SEMÁFOROS: control por permisos, útil para recursos limitados.
        OPCIONES.put(10, new OpcionDemo(
                "Semáforos: Cena de los filósofos",
                "Semaforos.CenaFilosofos",
                "Teoría: Semaphore usa permisos (acquire/release) para limitar accesos simultáneos a recursos."));

        // EXECUTOR SERVICE: administración de tareas con pool de hilos.
        OPCIONES.put(11, new OpcionDemo(
                "ExecutorService: Suma de números aleatorios",
                "ExecutorService.RandomNumberSum",
                "Teoría: ExecutorService reutiliza hilos en un pool; mejora control de recursos y escalabilidad."));

        // Ejercicios base complementarios.
        OPCIONES.put(12, new OpcionDemo("Java básico: Calculadora multihilo", "Java.Calculadora",
                "Apoyo: ejemplo base para practicar descomposición de tareas."));
        OPCIONES.put(13, new OpcionDemo("Java básico: Sumador con array", "Java.SumadorConArray",
                "Apoyo: ejemplo base para practicar división de trabajo sobre datos."));
        OPCIONES.put(14, new OpcionDemo("Java básico: Suma de pares e impares", "Java.SumaParesImpares",
                "Apoyo: ejemplo base para validar lógica antes de sincronizar recursos compartidos."));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            imprimirMenu();
            System.out.print("Selecciona una opción: ");
            String entrada = scanner.nextLine();

            int opcion;
            try {
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Entrada inválida. Debes ingresar un número.\n");
                continue;
            }

            if (opcion == 0) {
                System.out.println("¡Hasta luego!");
                break;
            }

            OpcionDemo demo = OPCIONES.get(opcion);
            if (demo == null) {
                System.out.println("⚠️ Opción no válida. Intenta nuevamente.\n");
                continue;
            }

            ejecutarDemo(demo);
        }
    }

    private static void imprimirMenu() {
        System.out.println("\n================ MENÚ DE CONCURRENCIA ================");
        System.out.println("Ejecuta con: java -cp src MenuConcurrencia");
        for (Map.Entry<Integer, OpcionDemo> entrada : OPCIONES.entrySet()) {
            System.out.printf("%2d. %s%n", entrada.getKey(), entrada.getValue().nombre());
        }
        System.out.println(" 0. Salir");
        System.out.println("=======================================================");
    }

    private static void ejecutarDemo(OpcionDemo demo) {
        System.out.println("\n▶ " + demo.nombre());
        System.out.println(demo.teoria());
        System.out.println("(Si la demo queda en ejecución continua, deténla con Ctrl+C)\n");

        try {
            Class<?> clase = Class.forName(demo.clasePrincipal());
            var main = clase.getDeclaredMethod("main", String[].class);
            main.setAccessible(true);
            main.invoke(null, (Object) new String[]{});
        } catch (Exception e) {
            System.out.println("❌ Error al ejecutar la demo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private record OpcionDemo(String nombre, String clasePrincipal, String teoria) {
    }
}
