package Semaforos;

import java.util.concurrent.Semaphore;

/**
 * Comportamiento de un filósofo: alterna pensar/comer y usa semáforos para adquirir/liberar tenedores.
 */
class Filosofo implements Runnable {
    private final int id;
    private final Semaphore tenedorIzquierdo;
    private final Semaphore tenedorDerecho;

    public Filosofo(int id, Semaphore tenedorIzquierdo, Semaphore tenedorDerecho) {
        this.id = id;
        this.tenedorIzquierdo = tenedorIzquierdo;
        this.tenedorDerecho = tenedorDerecho;
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filósofo " + id + " está pensando.");
        Thread.sleep(2000);
    }

    private void comer() throws InterruptedException {
        tenedorIzquierdo.acquire();
        tenedorDerecho.acquire();

        System.out.println("Filósofo " + id + " está comiendo.");
        Thread.sleep(2000);

        tenedorDerecho.release();
        tenedorIzquierdo.release();
    }

    @Override
    public void run() {
        try {
            while (true) {
                pensar();
                comer();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }
}
