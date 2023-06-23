package Java;

import java.util.Scanner;

public class SumadorConArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numeros = new int[5];

        for (int i = 0; i < 5; i++) {
            System.out.print("Ingrese el número " + (i + 1) + ": ");
            numeros[i] = scanner.nextInt();
        }

        int suma = 0;
        for (int i = 0; i < 5; i++) {
            suma += numeros[i];
        }

        System.out.println("La suma de los números es: " + suma);

        // Mostrar los números en orden inverso
        System.out.print("Los números en orden inverso son: ");
        for (int i = 4; i >= 0; i--) {
            System.out.print(numeros[i] + " ");
        }
    }
}