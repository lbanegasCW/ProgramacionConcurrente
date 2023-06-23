package ExecutorService;

import java.util.ArrayList;
import java.util.List;
class SumaRunnable implements Runnable {
    private List<Integer> numeros;

    public SumaRunnable(List<Integer> numeros) {
        this.numeros = numeros;
    }

    @Override
    public void run() {
        synchronized (numeros) { // Sincronizamos el acceso al array para evitar conflictos
            int numero1 = numeros.remove(0);
            int numero2 = numeros.remove(0);
            int suma = numero1 + numero2;
            System.out.println("Suma realizada por el hilo " + Thread.currentThread().getId() + ": " +
                    numero1 + " + " + numero2 + " = " + suma);
        }
    }
}