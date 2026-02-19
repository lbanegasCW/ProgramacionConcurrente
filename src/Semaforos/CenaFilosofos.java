package Semaforos;

import java.util.concurrent.Semaphore;

/**
 * Implementación del problema Cena de los Filósofos usando semáforos para modelar tenedores y acceso concurrente.
 */
public class CenaFilosofos {
    public static void main(String[] args) {
        int numFilosofos = 5;

        Semaphore[] tenedores = new Semaphore[numFilosofos];
        for (int i = 0; i < numFilosofos; i++) {
            tenedores[i] = new Semaphore(1);
        }

        Thread[] filosofos = new Thread[numFilosofos];
        for (int i = 0; i < numFilosofos; i++) {
            Semaphore tenedorIzquierdo = tenedores[i];
            Semaphore tenedorDerecho = tenedores[(i + 1) % numFilosofos];
            filosofos[i] = new Thread(new Filosofo(i, tenedorIzquierdo, tenedorDerecho));
        }

        for (Thread filosofo : filosofos) {
            filosofo.start();
        }

        try {
            Thread.sleep(10000);
            for (Thread filosofo : filosofos) {
                filosofo.interrupt();
            }
            for (Thread filosofo : filosofos) {
                filosofo.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
