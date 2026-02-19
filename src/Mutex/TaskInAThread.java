package Mutex;

/**
 * Hilo de trabajo que ejecuta movimientos sobre SharedMemory para demostrar la protección por mutex.
 */
class TaskInAThread extends Thread {
    private SharedMemory sharedMemory;
    private String nombreHilo;

    public TaskInAThread(SharedMemory sharedMemory, String nombreHilo){
        this.sharedMemory = sharedMemory;
        this.nombreHilo = nombreHilo;
    }

    @Override
    public void run() {
        super.run();
        try {
            for (int i = 0; i < 1; i++) {
                this.sharedMemory.doMovement();
                System.out.println(nombreHilo + " -> " + this.sharedMemory.getMovements());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
