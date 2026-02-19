import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuConcurrencia {
    private static final Map<Integer, OpcionDemo> OPCIONES = new LinkedHashMap<>();

    static {
        OPCIONES.put(1, new OpcionDemo("Hilos: Números pares e impares", "Hilos.NumeroParImpar"));
        OPCIONES.put(2, new OpcionDemo("Hilos: Valores compartidos con Runnable", "Hilos.ValoresHilo"));
        OPCIONES.put(3, new OpcionDemo("Hilos: Valores compartidos con Thread", "Hilos.ValoresHiloThread"));

        OPCIONES.put(4, new OpcionDemo("Mutex: Exclusión mutua en memoria compartida", "Mutex.Mutex"));

        OPCIONES.put(5, new OpcionDemo("Monitores: Productor/Consumidor", "Monitores.ProductorConsumidor.Main"));
        OPCIONES.put(6, new OpcionDemo("Monitores: Carrera de relevos", "Monitores.CarreraRelevos.Main"));

        OPCIONES.put(7, new OpcionDemo("Colas: Productor/Consumidor con ArrayBlockingQueue", "Colas.ProductorConsumidor.Main"));
        OPCIONES.put(8, new OpcionDemo("Colas: Productor/Consumidor con Buffer propio", "Colas.ProductorConsumidorBuffer.Main"));
        OPCIONES.put(9, new OpcionDemo("Colas: Productor/Consumidor con Exchanger", "Colas.ProductorConsumidorExchanger.Main"));

        OPCIONES.put(10, new OpcionDemo("Semáforos: Cena de los filósofos", "Semaforos.CenaFilosofos"));

        OPCIONES.put(11, new OpcionDemo("ExecutorService: Suma de números aleatorios", "ExecutorService.RandomNumberSum"));

        OPCIONES.put(12, new OpcionDemo("Java básico: Calculadora multihilo", "Java.Calculadora"));
        OPCIONES.put(13, new OpcionDemo("Java básico: Sumador con array", "Java.SumadorConArray"));
        OPCIONES.put(14, new OpcionDemo("Java básico: Suma de pares e impares", "Java.SumaParesImpares"));
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
        for (Map.Entry<Integer, OpcionDemo> entrada : OPCIONES.entrySet()) {
            System.out.printf("%2d. %s%n", entrada.getKey(), entrada.getValue().nombre());
        }
        System.out.println(" 0. Salir");
        System.out.println("=======================================================");
    }

    private static void ejecutarDemo(OpcionDemo demo) {
        System.out.println("\n▶ Ejecutando: " + demo.nombre());
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

    private record OpcionDemo(String nombre, String clasePrincipal) {
    }
}
