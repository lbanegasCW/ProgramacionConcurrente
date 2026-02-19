package Monitores.CarreraRelevos;

/**
 * Hilo corredor que participa en la carrera respetando el orden definido por el monitor Carrera.
 */
class Corredor implements Runnable {
    private int numeroCorredor;
    private Carrera carrera;

    public Corredor(int numeroCorredor, Carrera carrera) {
        this.numeroCorredor = numeroCorredor;
        this.carrera = carrera;
    }

    public void run() {
        try {
            carrera.correr(numeroCorredor);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
