package Colas.ProductorConsumidorBuffer;

/**
 * Punto de entrada del ejemplo Productor-Consumidor con buffer propio sincronizado.
 */
public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(5);

        Thread productorThread = new Thread(new Productor(buffer));
        Thread consumidorThread = new Thread(new Consumidor(buffer));

        productorThread.start();
        consumidorThread.start();
    }
}
