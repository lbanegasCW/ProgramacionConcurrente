package Mutex;

class SharedMemory {
    private Integer movements;

    public SharedMemory() {
        this.movements = 0;
    }

    public synchronized void doMovement() {
        this.movements++;
    }

    public Integer getMovements() {
        return this.movements;
    }
}
