package Colas.ProductorConsumidor;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Punto de entrada del ejemplo Productor-Consumidor con ArrayBlockingQueue, mostrando comunicación segura entre hilos.
 */
public class Main {
    public static void main(String[] args) {
        ArrayBlockingQueue<String> cola = new ArrayBlockingQueue<>(5);

        Thread productorThread = new Thread(new Productor(cola));
        Thread consumidorThread = new Thread(new Consumidor(cola));

        productorThread.start();
        consumidorThread.start();
    }
}
