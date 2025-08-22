package userInterface;

import Domain.OperationSign;
import service.CalculatorService;

import java.util.Scanner;

public class CalculatorUserInterface {

    private final CalculatorService calculatorService;

    public CalculatorUserInterface() {
        calculatorService = new CalculatorService();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Calculator!");

        while (true) {
            try {
                System.out.print("Enter the first number: ");
                double lhs = validateNumber(scanner);

                System.out.print("Enter operation (+, -, *, /): ");
                String operationInput = scanner.next();

                OperationSign operationSign = getOperationSign(operationInput);
                if (operationSign == null) {
                    System.out.println("Invalid operation. Please try again.");
                    continue;
                }

                System.out.print("Enter the second number: ");
                double rhs = validateNumber(scanner);

                double result = calculatorService.compute(lhs, rhs, operationSign);
                System.out.println("Result: " + result);

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.print("Do you want to perform another calculation? (yes/no): ");
            String answer = scanner.next();

            if (answer.equalsIgnoreCase("no")) {
                System.out.println("Thank you for using the Calculator!");
                scanner.close();
                return;
            } else if(!answer.equalsIgnoreCase("yes")) {
                System.out.println("Invalid answer. Please enter 'yes' or 'no'.");
            }
        }

    }

    private OperationSign getOperationSign(String input) {
        return switch (input) {
            case "+" -> OperationSign.ADDITION;
            case "-" -> OperationSign.SUBTRACTION;
            case "*" -> OperationSign.MULTIPLICATION;
            case "/" -> OperationSign.DIVISION;
            default -> null;
        };
    }

    private double validateNumber(Scanner scanner) {
        while (true) {
            try {
                // Attempt to read a double value
                return scanner.nextDouble();
            } catch (Exception e) {
                // Handle invalid input, clear the scanner, and prompt again
                System.out.print("Invalid number. Please enter a valid number: ");
                scanner.next(); // Clear the invalid input
            }
        }
    }
}