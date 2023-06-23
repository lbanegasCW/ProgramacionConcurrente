package ExecutorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RandomNumberSum {
    private static int tamPool = 5;

    public static void main(String[] args) {
        List<Integer> numeros = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(tamPool);
        Random random = new Random();

        // Generar 100 números aleatorios y agregarlos al array
        for (int i = 0; i < 100; i++) {
            int numero = random.nextInt(100);
            numeros.add(numero);
        }

        System.out.println("Array inicial: " + numeros);

        // Ejecutar el programa durante 10 segundos
        long endTime = System.currentTimeMillis() + 10000;

        while (System.currentTimeMillis() < endTime) {
            // Verificar si hay suficientes elementos para realizar una suma
            if (numeros.size() >= 2) {
                for (int i = 0; i < tamPool; i++) { // Recorremos todos los hilos disponibles en el pool
                    if (numeros.size() >= 2) { // Verificamos nuevamente si hay suficientes elementos
                        Runnable sumaRunnable = new SumaRunnable(numeros);
                        executor.execute(sumaRunnable);
                    } else {
                        break; // Si no hay suficientes elementos, salimos del bucle
                    }
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();

        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Array final: " + numeros);
    }
}