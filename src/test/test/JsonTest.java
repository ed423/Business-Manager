package test;

import model.Transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkTransaction(int number, String type, String sender, String receiver, String description,
                                    int amount, Transaction transaction) {
        assertEquals(number, transaction.getNumber());
        assertEquals(type, transaction.getType());
        assertEquals(sender, transaction.getSender());
        assertEquals(receiver, transaction.getReceiver());
        assertEquals(description, transaction.getDescription());
        assertEquals(amount, transaction.getAmount());
    }
}