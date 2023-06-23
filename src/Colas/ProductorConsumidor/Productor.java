package Colas.ProductorConsumidor;

import java.util.concurrent.ArrayBlockingQueue;

class Productor implements Runnable {
    private ArrayBlockingQueue<String> cola;

    public Productor(ArrayBlockingQueue<String> cola) {
        this.cola = cola;
    }

    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                String mensaje = "Mensaje " + i;
                cola.put(mensaje);
                System.out.println("Productor: Agregado " + mensaje);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}