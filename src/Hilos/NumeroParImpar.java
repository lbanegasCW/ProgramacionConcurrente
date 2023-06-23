package Hilos;

class NumeroParImpar {
    public static void main(String[] args) {
        Thread hiloPar = new Thread(() -> {
            for (int i = 2; i <= 10; i += 2) {
                System.out.println("hiloPar: " + i);
            }
        });

        Thread hiloImpar = new Thread(() -> {
            for (int i = 1; i <= 10; i += 2) {
                System.out.println("hiloImpar: " + i);
            }
        });

        hiloPar.start();
        hiloImpar.start();
    }
}
