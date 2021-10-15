package model;

import java.util.ArrayList;
import java.util.List;

// A transaction list is a list of transactions
public class TransactionList {
    private List<Transaction> transactionList;
    public static final int MAX_NUM_TRANSACTIONS = 5;

    public TransactionList() {
        transactionList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: if the transaction list size is less than MAX_NUM_TRANSACTIONS, adds t to transactionList, otherwise
    // does nothing
    public void addTransaction(Transaction t) {
        if (transactionList.size() < MAX_NUM_TRANSACTIONS) {
            transactionList.add(t);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes a transaction from the list with identifier i, if there is no transaction with
    // identifier i, does not do anything
    public void removeTransaction(int i) {
        for (int x = 0; x < transactionList.size(); x++) {
            if (transactionList.get(x).getNumber() == i) {
                transactionList.remove(x);
                break;
            }
        }
    }

    // REQUIRES: transaction list must not be empty
    // EFFECTS: returns each sender, receiver, and amount for each transaction in the transaction list
    public String lastTransaction() {
        return "Sender: " + transactionList.get(transactionList.size() - 1).getSender() + ", Receiver: "
                + transactionList.get(transactionList.size() - 1).getReceiver() + ", Amount: "
                + transactionList.get(transactionList.size() - 1).getAmount();
    }

    // EFFECTS: returns the sender, receiver, amount and description of transaction with identifier i, returns a string
    // saying "Transaction not in transaction list" if there is no transaction with identifier i in the transaction list
    public String viewTransaction(int i) {
        for (Transaction t : transactionList) {
            if (t.getNumber() == i) {
                return "Funds sent from " + t.getSender() + " to " + t.getReceiver() + ", Amount: "
                        + t.getAmount() + ", Details: " + t.getDescription();
            }
        }
        return "Transaction not in transaction list";
    }

    // EFFECTS: returns the number of transactions in transactionList
    public int size() {
        return transactionList.size();
    }
}
