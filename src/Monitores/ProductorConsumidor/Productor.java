package Monitores.ProductorConsumidor;

class Productor implements Runnable {
    private Buffer buffer;

    public Productor(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        try {
            while (true) {
                int numero = generarNumeroAleatorio();
                buffer.agregar(numero);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int generarNumeroAleatorio() {
        return (int) (Math.random() * 100);
    }
}