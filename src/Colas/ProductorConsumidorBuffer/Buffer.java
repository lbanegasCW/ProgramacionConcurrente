package Colas.ProductorConsumidorBuffer;

class Buffer {
    private int[] buffer;
    private int size;
    private int count;
    private int suma;

    public Buffer(int size) {
        this.buffer = new int[size];
        this.size = size;
        this.count = 0;
        this.suma = 0;
    }

    public synchronized void agregar(int numero) throws InterruptedException {
        while (count == size) {
            wait();
        }

        buffer[count] = numero;
        System.out.println("Productor agregó: " + numero);
        count++;
        suma += numero;

        notifyAll();
    }

    public synchronized int consumir() throws InterruptedException {
        while (count == 0) {
            wait();
        }

        int numero = buffer[count - 1];
        System.out.println("Consumidor eliminó: " + numero);
        count--;
        suma -= numero;

        notifyAll();
        return numero;
    }

    public synchronized int getSuma() {
        return suma;
    }
}