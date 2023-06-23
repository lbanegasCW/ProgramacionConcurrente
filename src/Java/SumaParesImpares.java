package Java;

import java.util.Scanner;

public class SumaParesImpares {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese un número: ");
        int numero = scanner.nextInt();

        int sumaPares = 0;
        int sumaImpares = 0;

        for (int i = 1; i <= numero; i++) {
            if (i % 2 == 0) {
                sumaPares += i;
            } else {
                sumaImpares += i;
            }
        }

        System.out.println("La suma de los números pares hasta " + numero + " es: " + sumaPares);
        System.out.println("La suma de los números impares hasta " + numero + " es: " + sumaImpares);
    }
}