package Colas.ProductorConsumidorExchanger;

import java.util.Arrays;
import java.util.concurrent.Exchanger;

class IntArrayProducer implements Runnable {
    private Exchanger<int[]> exchanger;

    public IntArrayProducer(Exchanger<int[]> exchanger) {
        this.exchanger = exchanger;
    }

    public void run() {
        try {
            int[] array = new int[]{1, 2, 3, 4, 5};
            System.out.println("Hilo Productor: Array creado");
            int[] receivedArray = exchanger.exchange(array);
            System.out.println("Hilo Productor: Array recibido");
            System.out.println("Hilo Productor: Array original: " + Arrays.toString(array));
            System.out.println("Hilo Productor: Array recibido: " + Arrays.toString(receivedArray));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}