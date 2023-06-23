package Hilos;

public class ValoresHilo implements Runnable {
    private final String nombre;
    private final int valor;

    public ValoresHilo(String nombre, int valor) {
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
        Thread hiloPepe = new Thread(new ValoresHilo("Pepe", 10));
        Thread hiloJuan = new Thread(new ValoresHilo("Juan", 20));

        hiloPepe.start();
        hiloJuan.start();
    }
}
