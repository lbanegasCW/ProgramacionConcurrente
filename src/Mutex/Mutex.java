package Mutex;

import java.time.Clock;

public class Mutex {
    private static final int KTHREADS = 3;

    public static void main(String[] args) {
        Clock clock = Clock.systemUTC();
        Long startMillis = clock.millis();

        System.out.println("--- Inicio ---");
        SharedMemory sharedMemory = new SharedMemory();
        TaskInAThread[] threads = new TaskInAThread[KTHREADS];

        for (int i = 0; i < KTHREADS; i++) {
            threads[i] = new TaskInAThread(sharedMemory, "Hilo" + i);
            threads[i].start();
        }

        String aux;
        boolean stop = false;
        while(!stop) {
            try {
                Thread.sleep(1000);
                stop = true;

                for (int i = 0; i < KTHREADS; i++) {
                    if(threads[i].isAlive()) {
                        stop = false;
                    }
                }
            }
            catch(Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        System.out.println("--- Final " + (clock.millis() - startMillis) + " milisegundos y "
                + sharedMemory.getMovements() + " movimientos realizados");
    }
}