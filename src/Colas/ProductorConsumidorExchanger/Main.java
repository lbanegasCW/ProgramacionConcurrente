package Colas.ProductorConsumidorExchanger;

import java.util.concurrent.Exchanger;

/**
 * Punto de entrada del ejemplo de intercambio punto a punto usando Exchanger.
 */
public class Main {
    public static void main(String[] args) {
        Exchanger<int[]> exchanger = new Exchanger<>();

        Thread producerThread = new Thread(new IntArrayProducer(exchanger));
        Thread consumerThread = new Thread(new IntArrayConsumer(exchanger));

        producerThread.start();
        consumerThread.start();
    }
}
