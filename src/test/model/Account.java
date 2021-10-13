package model;

import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// An account is a list of transactions
public class Account {
    private List<Transaction> account;
    public static final int MAX_NUM_TRANSACTIONS = 5;

    public Account() {
        account = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: if the account size is less than MAX_NUM_TRANSACTIONS, adds t to account and returns true, returns false
    // if account size is equal to MAX_NUM_TRANSACTIONS
    public boolean addTransaction(Transaction t) {
        if (account.size() < MAX_NUM_TRANSACTIONS) {
            account.add(t);
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: removes a transaction from the list with identifier i, if there is no transaction with
    // identifier i, does not do anything
    public void removeTransaction(int i) {
        for (int x = 0; x < account.size(); x++) {
            if (account.get(x).getNumber() == i) {
                account.remove(x);
                break;
            }
        }
    }

    // REQUIRES: account must not be empty
    // EFFECTS: returns each sender, receiver, and amount for each transaction in the account
    public Transaction lastTransaction() {
        return account.get(account.size()-1);
    }

    // EFFECTS: returns the sender, receiver, amount and description of transaction with identifier i, returns a string
    // saying "Transaction not in account" if there is no transaction with identifier i in the account
    public String viewTransaction(int i) {
        for (Transaction t : account) {
            if (t.getNumber() == i) {
                return t.getDescription();
            }
        }
        return "Transaction not in account";
    }

    // EFFECTS: returns the number of transactions in account
    public int size() {
        int a = 0;
        for (Transaction t : account) {
            a++;
        }
        return a;
    }
}
