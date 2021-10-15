package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionTest {
    Transaction testTransaction;

    @BeforeEach
    public void runBefore() {
        testTransaction = new Transaction(1, "a", "b", "c", "d", 100);
    }

    @Test
    public void testTransactionConstructor() {
        assertEquals(1, testTransaction.getNumber());
        assertEquals("a", testTransaction.getType());
        assertEquals("b", testTransaction.getSender());
        assertEquals("c", testTransaction.getReceiver());
        assertEquals("d", testTransaction.getDescription());
        assertEquals(100, testTransaction.getAmount());
    }

    @Test
    public void testGetNumber() {
        assertEquals(1, testTransaction.getNumber());
    }

    @Test
    public void testGetType() {
        assertEquals("a", testTransaction.getType());
    }

    @Test
    public void testGetSender() {
        assertEquals("b", testTransaction.getSender());
    }

    @Test
    public void testGetReceiver() {
        assertEquals("c", testTransaction.getReceiver());
    }

    @Test
    public void testGetDescription() {
        assertEquals("d", testTransaction.getDescription());
    }

    @Test
    public void testGetAmount() {
        assertEquals(100, testTransaction.getAmount());
    }

}
