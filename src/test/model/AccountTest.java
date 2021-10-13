package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccountTest {

    Account testAccount;

    @BeforeEach
    public void runBefore() {
        testAccount = new Account();
    }

    @Test
    public void testAddTransactionWhenAccountNotFull() {
        Transaction testTransaction;
        testTransaction = new Transaction(1, "a", "a", "a", "a", 10);

        assertEquals(0, testAccount.size());

        testAccount.addTransaction(testTransaction);
        assertEquals(1, testAccount.size());

        testAccount.addTransaction(testTransaction);
        assertEquals(2, testAccount.size());

    }

    @Test
    public void testAddTransactionWhenAccountFull() {
        Transaction transaction1;
        transaction1 = new Transaction(1, "shipping expense", "me", "John", "a",
                10.00);
        Transaction transaction2;
        transaction2 = new Transaction(2, "shipping expense", "me", "John", "a",
                10.00);
        Transaction transaction3;
        transaction3 = new Transaction(3, "shipping expense", "me", "John", "a",
                10.00);
        Transaction transaction4;
        transaction4 = new Transaction(4, "shipping expense", "me", "John", "a",
                10.00);
        Transaction transaction5;
        transaction5 = new Transaction(5, "shipping expense", "me", "John", "a",
                10.00);
        Transaction transaction6;
        transaction6 = new Transaction(6, "shipping expense", "me", "John", "a",
                10.00);

            testAccount.addTransaction(transaction1);
            testAccount.addTransaction(transaction2);
            testAccount.addTransaction(transaction3);
            testAccount.addTransaction(transaction4);
            testAccount.addTransaction(transaction5);

        assertEquals(5, testAccount.size());

        testAccount.addTransaction(transaction6);
        assertEquals(5, testAccount.size());

    }

    @Test
    public void testRemoveTransactionWhenNotInAccount() {
        Transaction transaction1;
        transaction1 = new Transaction(1, "shipping expense", "me", "John", "a",
                10.00);
        Transaction transaction2;
        transaction2 = new Transaction(2, "shipping expense", "me", "John", "b",
                10.00);

        testAccount.addTransaction(transaction1);
        testAccount.addTransaction(transaction2);

        assertEquals(2, testAccount.size());
        testAccount.removeTransaction(3);
        assertEquals(2, testAccount.size());


    }

    @Test
    public void testRemoveTransactionWhenInAccountLast() {
        Transaction transaction1;
        transaction1 = new Transaction(1, "shipping expense", "me", "John", "a",
                10.00);
        Transaction transaction2;
        transaction2 = new Transaction(2, "shipping expense", "me", "John", "b",
                10.00);

        testAccount.addTransaction(transaction1);
        testAccount.addTransaction(transaction2);

        assertEquals(2, testAccount.size());
        testAccount.removeTransaction(2);
        assertEquals(1, testAccount.size());
        assertEquals("Transaction not in account", testAccount.viewTransaction(2));

    }

    @Test
    public void testRemoveTransactionWhenInAccountFirst() {
        Transaction transaction1;
        transaction1 = new Transaction(1, "shipping expense", "me", "John", "a",
                10.00);
        Transaction transaction2;
        transaction2 = new Transaction(2, "shipping expense", "me", "John", "b",
                10.00);

        testAccount.addTransaction(transaction1);
        testAccount.addTransaction(transaction2);

        assertEquals(2, testAccount.size());
        testAccount.removeTransaction(1);
        assertEquals(1, testAccount.size());
        assertEquals("Transaction not in account", testAccount.viewTransaction(1));

    }

    @Test
    public void testRemoveTransactionWhenInAccountMiddle() {
        Transaction transaction1;
        transaction1 = new Transaction(1, "shipping expense", "me", "John", "a",
                10.00);
        Transaction transaction2;
        transaction2 = new Transaction(2, "shipping expense", "me", "John", "b",
                10.00);
        Transaction transaction3;
        transaction3 = new Transaction(3, "shipping expense", "me", "John", "c",
                10.00);

        testAccount.addTransaction(transaction1);
        testAccount.addTransaction(transaction2);
        testAccount.addTransaction(transaction3);

        assertEquals(3, testAccount.size());
        testAccount.removeTransaction(2);
        assertEquals(2, testAccount.size());
        assertEquals("Transaction not in account", testAccount.viewTransaction(2));

    }

    @Test
    public void testRemoveTransactionWhenTransactionNotInAccount() {
        Transaction transaction1;
        transaction1 = new Transaction(1, "shipping expense", "me", "John", "a",
                10.00);
        Transaction transaction2;
        transaction2 = new Transaction(2, "shipping expense", "me", "John", "b",
                10.00);
        Transaction transaction3;
        transaction3 = new Transaction(3, "shipping expense", "me", "John", "c",
                10.00);

        testAccount.addTransaction(transaction1);
        testAccount.addTransaction(transaction2);
        testAccount.addTransaction(transaction3);

        assertEquals(3, testAccount.size());
        testAccount.removeTransaction(4);
        assertEquals(3, testAccount.size());

    }

    @Test
    public void testLastTransaction() {
        Transaction transaction1;
        transaction1 = new Transaction(1, "shipping expense", "me", "John", "a",
                10.00);
        Transaction transaction2;
        transaction2 = new Transaction(2, "shipping expense", "me", "John", "a",
                10.00);
        Transaction transaction3;
        transaction3 = new Transaction(3, "shipping expense", "me", "John", "a",
                10.00);

        testAccount.addTransaction(transaction1);
        testAccount.addTransaction(transaction2);
        testAccount.addTransaction(transaction3);

        assertEquals(testAccount.lastTransaction(), transaction3);
    }

    @Test
    public void testViewTransactionWhenIdentifierNotInAccount() {
        Transaction transaction1;
        transaction1 = new Transaction(1, "shipping expense", "me", "John", "a",
                10.00);
        Transaction transaction2;
        transaction2 = new Transaction(2, "shipping expense", "me", "John", "b",
                10.00);
        Transaction transaction3;
        transaction3 = new Transaction(3, "shipping expense", "me", "John", "c",
                10.00);

        testAccount.addTransaction(transaction1);
        testAccount.addTransaction(transaction2);
        testAccount.addTransaction(transaction3);

        assertEquals("Transaction not in account", testAccount.viewTransaction(4));
    }

    @Test
    public void testViewTransactionWhenIdentifierInAccount() {
        Transaction transaction1;
        transaction1 = new Transaction(1, "shipping expense", "me", "John", "a",
                10.00);
        Transaction transaction2;
        transaction2 = new Transaction(2, "shipping expense", "me", "John", "b",
                10.00);
        Transaction transaction3;
        transaction3 = new Transaction(3, "shipping expense", "me", "John", "c",
                10.00);

        testAccount.addTransaction(transaction1);
        testAccount.addTransaction(transaction2);
        testAccount.addTransaction(transaction3);

        assertEquals("c", testAccount.viewTransaction(3));
    }

    @Test
    public void testSizeWhenAccountEmpty() {
        assertEquals(0, testAccount.size());

    }

    @Test
    public void testSizeWhenAccountNotFull() {
        Transaction transaction1;
        transaction1 = new Transaction(1, "shipping expense", "me", "John", "a",
                10.00);
        Transaction transaction2;
        transaction2 = new Transaction(2, "shipping expense", "me", "John", "b",
                10.00);
        Transaction transaction3;
        transaction3 = new Transaction(3, "shipping expense", "me", "John", "c",
                10.00);

        testAccount.addTransaction(transaction1);
        testAccount.addTransaction(transaction2);
        testAccount.addTransaction(transaction3);

        assertEquals(3, testAccount.size());
    }

    @Test
    public void testSizeWhenAccountFull() {
        Transaction transaction1;
        transaction1 = new Transaction(1, "shipping expense", "me", "John", "a",
                10.00);
        Transaction transaction2;
        transaction2 = new Transaction(2, "shipping expense", "me", "John", "a",
                10.00);
        Transaction transaction3;
        transaction3 = new Transaction(3, "shipping expense", "me", "John", "a",
                10.00);
        Transaction transaction4;
        transaction4 = new Transaction(4, "shipping expense", "me", "John", "a",
                10.00);
        Transaction transaction5;
        transaction5 = new Transaction(5, "shipping expense", "me", "John", "a",
                10.00);

        testAccount.addTransaction(transaction1);
        testAccount.addTransaction(transaction2);
        testAccount.addTransaction(transaction3);
        testAccount.addTransaction(transaction4);
        testAccount.addTransaction(transaction5);

        assertEquals(5, testAccount.size());
    }
}