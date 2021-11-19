package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionListTest {

    TransactionList testTransactionList;

    @BeforeEach
    public void runBefore() {
        testTransactionList = new TransactionList();
    }

    @Test
    public void testAddTransactionWhenAccountNotFull() {
        Transaction testTransaction;
        testTransaction = new Transaction(1, "a", "a", "a", "a", 10);
        Transaction testTransaction2 = new Transaction
                (2, "asdf", "sa", "Asdf", "asd", 2);

        assertEquals(0, testTransactionList.size());

        testTransactionList.addTransaction(testTransaction);
        assertEquals(1, testTransactionList.size());

        testTransactionList.addTransaction(testTransaction2);
        assertEquals(2, testTransactionList.size());

        testTransactionList.addTransaction(testTransaction);
        assertEquals(2, testTransactionList.size());


    }

    @Test
    public void testGetTransaction() {
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

        testTransactionList.addTransaction(transaction1);
        testTransactionList.addTransaction(transaction2);
        testTransactionList.addTransaction(transaction3);
        testTransactionList.addTransaction(transaction4);
        testTransactionList.addTransaction(transaction5);

        assertEquals(transaction1, testTransactionList.getTransaction(0));
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

            testTransactionList.addTransaction(transaction1);
            testTransactionList.addTransaction(transaction2);
            testTransactionList.addTransaction(transaction3);
            testTransactionList.addTransaction(transaction4);
            testTransactionList.addTransaction(transaction5);

        assertEquals(5, testTransactionList.size());

        testTransactionList.addTransaction(transaction6);
        assertEquals(5, testTransactionList.size());

    }

    @Test
    public void testRemoveTransactionWhenNotInAccount() {
        Transaction transaction1;
        transaction1 = new Transaction(1, "shipping expense", "me", "John", "a",
                10.00);
        Transaction transaction2;
        transaction2 = new Transaction(2, "shipping expense", "me", "John", "b",
                10.00);

        testTransactionList.addTransaction(transaction1);
        testTransactionList.addTransaction(transaction2);

        assertEquals(2, testTransactionList.size());
        testTransactionList.removeTransaction(3);
        assertEquals(2, testTransactionList.size());


    }

    @Test
    public void testRemoveTransactionWhenInAccountLast() {
        Transaction transaction1;
        transaction1 = new Transaction(1, "shipping expense", "me", "John", "a",
                10.00);
        Transaction transaction2;
        transaction2 = new Transaction(2, "shipping expense", "me", "John", "b",
                10.00);

        testTransactionList.addTransaction(transaction1);
        testTransactionList.addTransaction(transaction2);

        assertEquals(2, testTransactionList.size());
        testTransactionList.removeTransaction(2);
        assertEquals(1, testTransactionList.size());
        assertEquals("Transaction not in transaction list", testTransactionList.viewTransaction(2));

    }

    @Test
    public void testRemoveTransactionWhenInAccountFirst() {
        Transaction transaction1;
        transaction1 = new Transaction(1, "shipping expense", "me", "John", "a",
                10.00);
        Transaction transaction2;
        transaction2 = new Transaction(2, "shipping expense", "me", "John", "b",
                10.00);

        testTransactionList.addTransaction(transaction1);
        testTransactionList.addTransaction(transaction2);

        assertEquals(2, testTransactionList.size());
        testTransactionList.removeTransaction(1);
        assertEquals(1, testTransactionList.size());
        assertEquals("Transaction not in transaction list", testTransactionList.viewTransaction(1));

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

        testTransactionList.addTransaction(transaction1);
        testTransactionList.addTransaction(transaction2);
        testTransactionList.addTransaction(transaction3);

        assertEquals(3, testTransactionList.size());
        testTransactionList.removeTransaction(2);
        assertEquals(2, testTransactionList.size());
        assertEquals("Transaction not in transaction list", testTransactionList.viewTransaction(2));

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

        testTransactionList.addTransaction(transaction1);
        testTransactionList.addTransaction(transaction2);
        testTransactionList.addTransaction(transaction3);

        assertEquals(3, testTransactionList.size());
        testTransactionList.removeTransaction(4);
        assertEquals(3, testTransactionList.size());

    }

    @Test
    public void testLastTransactionWhenTransactionListEmpty() {
        assertEquals("There are no transactions in the transaction list. Please add a transaction and try again.",
                testTransactionList.lastTransaction());
    }

    @Test
    public void testLastTransactionWhenTransactionListNotEmpty() {
        Transaction transaction1;
        transaction1 = new Transaction(1, "shipping expense", "me", "John", "a",
                10.00);
        Transaction transaction2;
        transaction2 = new Transaction(2, "shipping expense", "me", "John", "a",
                10.00);
        Transaction transaction3;
        transaction3 = new Transaction(3, "shipping expense", "me", "John", "a",
                10.00);

        testTransactionList.addTransaction(transaction1);
        testTransactionList.addTransaction(transaction2);
        testTransactionList.addTransaction(transaction3);

        assertEquals("Sender: me, Receiver: John, Amount: 10.0", testTransactionList.lastTransaction());
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

        testTransactionList.addTransaction(transaction1);
        testTransactionList.addTransaction(transaction2);
        testTransactionList.addTransaction(transaction3);

        assertEquals("Transaction not in transaction list", testTransactionList.viewTransaction(4));
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

        testTransactionList.addTransaction(transaction1);
        testTransactionList.addTransaction(transaction2);
        testTransactionList.addTransaction(transaction3);

        assertEquals("Funds sent from me to John, Amount: 10.0, Details: c", testTransactionList.viewTransaction(3));
    }

    @Test
    public void testSizeWhenAccountEmpty() {
        assertEquals(0, testTransactionList.size());

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

        assertEquals(0, testTransactionList.size());

        testTransactionList.addTransaction(transaction1);
        testTransactionList.addTransaction(transaction2);
        testTransactionList.addTransaction(transaction3);

        assertEquals(3, testTransactionList.size());
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

        testTransactionList.addTransaction(transaction1);
        testTransactionList.addTransaction(transaction2);
        testTransactionList.addTransaction(transaction3);
        testTransactionList.addTransaction(transaction4);
        testTransactionList.addTransaction(transaction5);

        assertEquals(5, testTransactionList.size());
    }
}