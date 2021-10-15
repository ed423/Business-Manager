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
    // EFFECTS: adds t to the list of transactions if the transaction list size is less than MAX_NUM_TRANSACTIONS
    public void addTransaction(Transaction t) {
        if (transactionList.size() < MAX_NUM_TRANSACTIONS) {
            transactionList.add(t);
        }
    }

    // MODIFIES: this
    // EFFECTS: if there exists a transaction with identifier i in the transaction list, removes the transaction from
    // the list
    public void removeTransaction(int i) {
        for (int x = 0; x < transactionList.size(); x++) {
            if (transactionList.get(x).getNumber() == i) {
                transactionList.remove(x);
                break;
            }
        }
    }

    // EFFECTS: if the transaction list is empty, returns a warning string. Otherwise,
    // returns the sender, receiver, and amount of the most recently added transaction in the transaction list
    public String lastTransaction() {
        if (transactionList.isEmpty()) {
            return "There are no transactions in the transaction list. Please add a transaction and try again.";
        }
        return "Sender: " + transactionList.get(transactionList.size() - 1).getSender() + ", Receiver: "
                + transactionList.get(transactionList.size() - 1).getReceiver() + ", Amount: "
                + transactionList.get(transactionList.size() - 1).getAmount();
    }

    // EFFECTS: if there is a transaction with identifier i in the transaction list, returns the sender, receiver,
    // amount and description of the transaction, otherwise returns a warning string
    public String viewTransaction(int i) {
        for (Transaction t : transactionList) {
            if (t.getNumber() == i) {
                return "Funds sent from " + t.getSender() + " to " + t.getReceiver() + ", Amount: "
                        + t.getAmount() + ", Details: " + t.getDescription();
            }
        }
        return "Transaction not in transaction list";
    }

    // EFFECTS: returns the number of transactions in the transaction list
    public int size() {
        return transactionList.size();
    }
}
