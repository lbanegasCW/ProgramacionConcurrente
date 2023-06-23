package Monitores.ProductorConsumidor;

class Consumidor implements Runnable {
    private Buffer buffer;
    private int suma;

    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
        this.suma = 0;
    }

    public void run() {
        try {
            while (true) {
                int numero = buffer.consumir();
                suma += numero;
                System.out.println("Suma: " + suma);
                Thread.sleep(1500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}