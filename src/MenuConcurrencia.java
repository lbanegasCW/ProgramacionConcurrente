import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

/**
 * Menú de consola centralizado para ejecutar todas las demos del repositorio y mostrar su explicación teórica.
 */
public class MenuConcurrencia {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            imprimirMenu();
            OpcionMenu opcion = leerOpcion(scanner);

            if (opcion == OpcionMenu.SALIR) {
                System.out.println("¡Hasta luego!");
                return;
            }

            ejecutar(opcion);
        }
    }

    private static void imprimirMenu() {
        System.out.println("\n================ MENÚ DE CONCURRENCIA ================");
        System.out.println("Ejecuta con: java -cp src MenuConcurrencia");

        for (OpcionMenu opcion : OpcionMenu.values()) {
            if (opcion == OpcionMenu.SALIR) {
                continue;
            }
            System.out.printf("%2d. %s%n", opcion.numero(), opcion.titulo());
        }

        System.out.println(" 0. Salir");
        System.out.println("=======================================================");
    }

    private static OpcionMenu leerOpcion(Scanner scanner) {
        while (true) {
            System.out.print("Selecciona una opción: ");
            String entrada = scanner.nextLine();

            try {
                int numero = Integer.parseInt(entrada);
                Optional<OpcionMenu> opcion = OpcionMenu.desdeNumero(numero);
                if (opcion.isPresent()) {
                    return opcion.get();
                }
                System.out.println("⚠️ Opción no válida. Intenta nuevamente.\n");
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Entrada inválida. Debes ingresar un número.\n");
            }
        }
    }

    private static void ejecutar(OpcionMenu opcion) {
        System.out.println("\n▶ " + opcion.titulo());
        System.out.println(opcion.teoria());
        System.out.println("(Si la demo queda en ejecución continua, deténla con Ctrl+C)\n");

        try {
            Class<?> clase = Class.forName(opcion.clasePrincipal());
            Method main = clase.getDeclaredMethod("main", String[].class);
            main.setAccessible(true);
            main.invoke(null, (Object) new String[]{});
        } catch (Exception e) {
            System.out.println("❌ Error al ejecutar la demo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Opciones disponibles del menú con número, título, clase principal y teoría asociada.
     */
    private enum OpcionMenu {
        SALIR(0, "Salir", "", ""),
        HILOS_PAR_IMPAR(1, "Hilos: Números pares e impares", "Hilos.NumeroParImpar",
                "Teoría: dos hilos ejecutan trabajo en paralelo lógico y comparten CPU por planificación."),
        HILOS_RUNNABLE(2, "Hilos: Valores compartidos con Runnable", "Hilos.ValoresHilo",
                "Teoría: Runnable separa la tarea del hilo, mejorando reutilización y testeo."),
        HILOS_THREAD(3, "Hilos: Valores compartidos con Thread", "Hilos.ValoresHiloThread",
                "Teoría: extender Thread simplifica ejemplos, pero acopla la lógica al mecanismo de ejecución."),
        MUTEX(4, "Mutex: Exclusión mutua en memoria compartida", "Mutex.Mutex",
                "Teoría: la exclusión mutua evita condiciones de carrera en secciones críticas."),
        MONITOR_PRODUCTOR_CONSUMIDOR(5, "Monitores: Productor/Consumidor", "Monitores.ProductorConsumidor.Main",
                "Teoría: un monitor combina synchronized con wait/notify para coordinar estados."),
        MONITOR_CARRERA_RELEVOS(6, "Monitores: Carrera de relevos", "Monitores.CarreraRelevos.Main",
                "Teoría: el monitor define turnos y orden de ejecución entre hilos."),
        COLA_ARRAY_BLOCKING_QUEUE(7, "Colas: Productor/Consumidor con ArrayBlockingQueue", "Colas.ProductorConsumidor.Main",
                "Teoría: BlockingQueue bloquea automáticamente productores/consumidores según capacidad."),
        COLA_BUFFER_PROPIO(8, "Colas: Productor/Consumidor con Buffer propio", "Colas.ProductorConsumidorBuffer.Main",
                "Teoría: implementar buffer manual ayuda a comprender la coordinación con monitores."),
        COLA_EXCHANGER(9, "Colas: Productor/Consumidor con Exchanger", "Colas.ProductorConsumidorExchanger.Main",
                "Teoría: Exchanger sincroniza dos hilos para intercambio directo de datos."),
        SEMAFOROS_FILOSOFOS(10, "Semáforos: Cena de los filósofos", "Semaforos.CenaFilosofos",
                "Teoría: Semaphore modela permisos para limitar acceso concurrente a recursos."),
        EXECUTOR_SERVICE(11, "ExecutorService: Suma de números aleatorios", "ExecutorService.RandomNumberSum",
                "Teoría: ExecutorService administra un pool y reduce el costo de crear hilos."),
        JAVA_CALCULADORA(12, "Java básico: Calculadora multihilo", "Java.Calculadora",
                "Apoyo: ejemplo base para organizar tareas que luego pueden paralelizarse."),
        JAVA_SUMADOR_ARRAY(13, "Java básico: Sumador con array", "Java.SumadorConArray",
                "Apoyo: ejercicio para practicar descomposición de datos y resultados."),
        JAVA_PARES_IMPARES(14, "Java básico: Suma de pares e impares", "Java.SumaParesImpares",
                "Apoyo: base para separar trabajo antes de aplicar sincronización.");

        private final int numero;
        private final String titulo;
        private final String clasePrincipal;
        private final String teoria;

        OpcionMenu(int numero, String titulo, String clasePrincipal, String teoria) {
            this.numero = numero;
            this.titulo = titulo;
            this.clasePrincipal = clasePrincipal;
            this.teoria = teoria;
        }

        public int numero() {
            return numero;
        }

        public String titulo() {
            return titulo;
        }

        public String clasePrincipal() {
            return clasePrincipal;
        }

        public String teoria() {
            return teoria;
        }

        public static Optional<OpcionMenu> desdeNumero(int numero) {
            return Arrays.stream(values())
                    .filter(opcion -> opcion.numero == numero)
                    .findFirst();
        }
    }
}
