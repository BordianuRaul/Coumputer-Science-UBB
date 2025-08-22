package testDomain;

import Domain.OperationSign;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperationSignTest {

    @Test
    void testFromStringValidSigns() {
        Assertions.assertEquals(OperationSign.ADDITION.toString(), OperationSign.ADDITION.fromString("+"));
        assertEquals(OperationSign.SUBTRACTION.toString(), OperationSign.SUBTRACTION.fromString("-"));
        assertEquals(OperationSign.MULTIPLICATION.toString(), OperationSign.MULTIPLICATION.fromString("*"));
        assertEquals(OperationSign.DIVISION.toString(), OperationSign.DIVISION.fromString("/"));
    }

    @Test
    void testFromStringInvalidSign() {
        assertNull(OperationSign.ADDITION.fromString("%"));
        assertNull(OperationSign.ADDITION.fromString("random"));
        assertNull(OperationSign.ADDITION.fromString(""));
        assertNull(OperationSign.ADDITION.fromString(null));
    }

    @Test
    void testEnumValues() {
        OperationSign[] expected = {OperationSign.DIVISION, OperationSign.MULTIPLICATION, OperationSign.SUBTRACTION, OperationSign.ADDITION};
        assertArrayEquals(expected, OperationSign.values());
    }
}
