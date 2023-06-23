package Colas.ProductorConsumidorExchanger;

import java.util.Arrays;
import java.util.concurrent.Exchanger;

class IntArrayConsumer implements Runnable {
    private Exchanger<int[]> exchanger;

    public IntArrayConsumer(Exchanger<int[]> exchanger) {
        this.exchanger = exchanger;
    }

    public void run() {
        try {
            int[] array = new int[]{6, 7, 8, 9, 10};
            System.out.println("Hilo Consumidor: Array creado");
            int[] receivedArray = exchanger.exchange(array);
            System.out.println("Hilo Consumidor: Array recibido");
            System.out.println("Hilo Consumidor: Array original: " + Arrays.toString(array));
            System.out.println("Hilo Consumidor: Array recibido: " + Arrays.toString(receivedArray));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}