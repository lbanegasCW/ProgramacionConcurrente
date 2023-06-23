package Monitores.CarreraRelevos;

public class Main {
    public static void main(String[] args) {
        int numCorredores = 4;

        Carrera carrera = new Carrera(numCorredores);

        for (int i = 1; i <= numCorredores; i++) {
            Thread corredorThread = new Thread(new Corredor(i, carrera));
            corredorThread.start();
        }
    }
}