package model;

import java.util.LinkedList;

// An account is a list of transactions
public class Account {
    private LinkedList<Transaction> account;
    public static final int MAX_NUM_TRANSACTIONS = 15;

    public Account() {
        account = new LinkedList<Transaction>();
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
    // EFFECTS: removes a transaction from the list with identifier i and returns true, if there is no transaction with
    // identifier i, returns false
    public boolean removeTransaction(int i) {
        int a = 0;
        for (Transaction t : account)
            if (t.getNumber() == i) {
                account.remove(a);
                return true;
            } else {
                a++;
            }
        return false;
    }

    // EFFECTS: returns each sender, receiver, and amount for each transaction in the account, returns string "Account
    // empty" if there are no transactions in the account
    public String viewAccount() {
        for (Transaction t : account) {
            return t.getSender() + t.getReceiver() + t.getAmount() + "\n";
        }
        return "List of transactions completed";
    }

    // EFFECTS: returns the sender, receiver, amount and description of transaction with identifier i, returns a string
    // saying "Transaction not in account" if there is no transaction with identifier i in the account
    public String viewTransaction(int i) {
        for (Transaction t : account) {
            if (t.getNumber() == i) {
                return t.getDescription();
            }
        }
        return "done";
    }

    public int size() {
        int a = 0;
        for (Transaction t : account) {
            a++;
        }
        return a;
    }
}
