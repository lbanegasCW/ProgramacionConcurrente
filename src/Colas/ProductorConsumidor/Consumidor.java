package Colas.ProductorConsumidor;

import java.util.concurrent.ArrayBlockingQueue;

class Consumidor implements Runnable {
    private ArrayBlockingQueue<String> cola;

    public Consumidor(ArrayBlockingQueue<String> cola) {
        this.cola = cola;
    }

    public void run() {
        try {
            while (true) {
                String mensaje = cola.take();
                System.out.println("Consumidor: Tomado " + mensaje);
                // Procesar el mensaje aquí
                Thread.sleep(1500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
