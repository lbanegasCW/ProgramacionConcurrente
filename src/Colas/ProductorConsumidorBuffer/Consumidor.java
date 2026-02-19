package Colas.ProductorConsumidorBuffer;

/**
 * Consumidor que retira valores del Buffer sincronizado y muestra el estado acumulado de la suma.
 */
class Consumidor implements Runnable {
    private Buffer buffer;

    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        try {
            while (true) {
                int numero = buffer.consumir();
                int suma = buffer.getSuma();
                System.out.println("Suma de elementos en el buffer: " + suma + " | Elemento borrado: " + numero);
                Thread.sleep(1500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
