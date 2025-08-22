package service;

import Domain.OperationSign;
import Domain.ZeroDivision;

public class CalculatorService {

    public CalculatorService() {}

    public double compute(double lhs, double rhs, OperationSign sign) throws Exception {
        return switch (sign) {
            case OperationSign.ADDITION -> addition(lhs, rhs);
            case OperationSign.SUBTRACTION -> subtraction(lhs, rhs);
            case OperationSign.MULTIPLICATION -> multiplication(lhs, rhs);
            case OperationSign.DIVISION -> division(lhs, rhs);
        };
    }

    private double addition(double lhs, double rhs) {
        return lhs + rhs;
    }

    private double subtraction(double lhs, double rhs) {
        return lhs - rhs;
    }

    private double multiplication(double lhs, double rhs) {
        return lhs * rhs;
    }

    private double division(double lhs, double rhs) throws ZeroDivision {
        if (rhs == 0) {
            throw new ZeroDivision("Zero division");
        }
        return lhs / rhs;
    }
}
