package Colas.ProductorConsumidorBuffer;

import java.util.Random;

class Productor implements Runnable {
    private Buffer buffer;

    public Productor(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        try {
            Random random = new Random();
            while (true) {
                int numero = random.nextInt(100);
                buffer.agregar(numero);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}