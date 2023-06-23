package Monitores.CarreraRelevos;

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