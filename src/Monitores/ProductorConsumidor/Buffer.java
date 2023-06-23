package Monitores.ProductorConsumidor;

import java.util.ArrayList;
import java.util.List;

class Buffer {
    private int size;
    private List<Integer> buffer;
    private Object lock;

    public Buffer(int size) {
        this.size = size;
        this.buffer = new ArrayList<>();
        this.lock = new Object();
    }

    public void agregar(int numero) throws InterruptedException {
        synchronized (lock) {
            while (buffer.size() >= size) {
                lock.wait();
            }
            buffer.add(numero);
            System.out.println("Productor agregó: " + numero);
            lock.notifyAll();
        }
    }

    public int consumir() throws InterruptedException {
        synchronized (lock) {
            while (buffer.isEmpty()) {
                lock.wait();
            }
            int numero = buffer.remove(0);
            System.out.println("Consumidor eliminó: " + numero);
            lock.notifyAll();
            return numero;
        }
    }
}