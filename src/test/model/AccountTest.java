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

        assertTrue(testAccount.size() == 0);

        testAccount.addTransaction(testTransaction);
        assertTrue(testAccount.size() == 1);

        testAccount.addTransaction(testTransaction);
        assertTrue(testAccount.size() == 2);

    }

    @Test
    public void testAddTransactionWhenAccountFull() {



    }
    @Test
    public void testRemoveTransactionWhenNotInAccount() {
        //stub
    }

    @Test
    public void testRemoveTransactionWhenInAccount() {
        //stub
    }

    @Test
    public void testViewAccountWhenAccountEmpty() {
        //stub
    }

    @Test
    public void testViewAccountWhenAccountNotEmpty() {
        //stub
    }

    @Test
    public void testViewTransactionWhenIdentifierNotInAccount() {
        //stub
    }

    @Test
    public void testViewTransactionWhenIdentifierInAccount() {
        //stub
    }

    @Test
    public void testSizeWhenAccountEmpty() {
        //stub
    }

    @Test
    public void testSizeWhenAccountNotFull() {
        //stub
    }

    @Test
    public void testSizeWhenAccountFull() {
        //stub
    }
}