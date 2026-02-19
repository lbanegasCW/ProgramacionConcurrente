package Java;

import java.util.Scanner;

/**
 * Ejercicio base de Java orientado a operaciones con hilos para practicar estructura de ejecución concurrente.
 */
public class Calculadora {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el primer operando: ");
        int operando1 = scanner.nextInt();

        System.out.print("Ingrese el segundo operando: ");
        int operando2 = scanner.nextInt();

        System.out.print("Ingrese el signo aritmético (+, -, *, /): ");
        String signo = scanner.next();

        double resultado = 0.0;

        switch (signo) {
            case "+":
                resultado = operando1 + operando2;
                break;
            case "-":
                resultado = operando1 - operando2;
                break;
            case "*":
                resultado = operando1 * operando2;
                break;
            case "/":
                resultado = (double) operando1 / operando2;
                break;
            default:
                System.out.println("Signo aritmético no válido.");
                return;
        }

        System.out.println("El resultado es: " + resultado);
    }
}
