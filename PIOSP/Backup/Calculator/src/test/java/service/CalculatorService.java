package service;

import Domain.OperationSign;
import Domain.ZeroDivision;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorServiceTest {
    private CalculatorService calculatorService;

    @BeforeEach
    void setUp() {
        calculatorService = new CalculatorService();
    }

    @Test
    void testAddition() throws Exception {
        double result = calculatorService.compute(5.0, 3.0, OperationSign.ADDITION);
        assertEquals(8.0, result);
    }

    @Test
    void testSubtraction() throws Exception {
        double result = calculatorService.compute(5.0, 3.0, OperationSign.SUBTRACTION);
        assertEquals(2.0, result);
    }

    @Test
    void testMultiplication() throws Exception {
        double result = calculatorService.compute(5.0, 3.0, OperationSign.MULTIPLICATION);
        assertEquals(15.0, result);
    }

    @Test
    void testDivision() throws Exception {
        double result = calculatorService.compute(6.0, 3.0, OperationSign.DIVISION);
        assertEquals(2.0, result);
    }

    @Test
    void testDivisionByZero() {
        Exception exception = assertThrows(ZeroDivision.class, () -> {
            calculatorService.compute(6.0, 0.0, OperationSign.DIVISION);
        });
        assertEquals("Zero division", exception.getMessage());
    }
}
