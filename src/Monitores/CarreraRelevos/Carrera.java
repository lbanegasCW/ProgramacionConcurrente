package Monitores.CarreraRelevos;

/**
 * Monitor que coordina la carrera de relevos controlando turnos de corredores con exclusión mutua.
 */
class Carrera {
    private int numCorredores;
    private Object monitor;
    private int testigo;

    public Carrera(int numCorredores) {
        this.numCorredores = numCorredores;
        this.monitor = new Object();
        this.testigo = 1;
    }

    public void correr(int numeroCorredor) throws InterruptedException {
        synchronized (monitor) {
            while (numeroCorredor != testigo) {
                monitor.wait();
            }

            System.out.println("Corredor " + numeroCorredor + " corriendo...");
            Thread.sleep(1000); // Simulamos el tiempo de carrera

            if (numeroCorredor < numCorredores) {
                testigo++;
                System.out.println("Corredor " + numeroCorredor + " entrega el testigo al corredor " + (numeroCorredor + 1));
            } else {
                System.out.println("Corredor " + numeroCorredor + " ha terminado la carrera.");
            }

            monitor.notifyAll();
        }
    }
}
