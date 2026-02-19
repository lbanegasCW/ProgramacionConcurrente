package Hilos;

/**
 * Ejemplo de concurrencia extendiendo Thread para comparar este enfoque con Runnable.
 */
public class ValoresHiloThread extends Thread {
    private final String nombre;
    private final int valor;

    public ValoresHiloThread(String nombre, int valor) {
        this.nombre = nombre;
        this.valor = valor;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i + " - " + nombre + " - " + valor);
        }
    }

    public static void main(String[] args) {
        ValoresHiloThread hiloPepe = new ValoresHiloThread("Pepe", 10);
        ValoresHiloThread hiloJuan = new ValoresHiloThread("Juan", 20);

        hiloPepe.start();
        hiloJuan.start();
    }
}
