package model;

// Referred to Teller example given on edX for formatting

import org.json.JSONObject;
import persistence.ReturnJson;

// Represents a transaction. A transaction has a type, sender, receiver, description, and dollar amount
public class Transaction implements ReturnJson {
    private int number;                     // transaction identifier number, unique for each transaction
    private String type;                    // type of transaction, whether it be an expense, revenue etc.
    private String sender;                  // name of sender
    private String receiver;                // name of receiver
    private String description;             // short description of the transaction
    private double amount;                  // dollar amount of the transaction

    // REQUIRES: type, sender, receiver and description must have non-zero lengths
    // EFFECTS: Transaction has given type, sender, receiver, description and amount
    public Transaction(int num, String type, String sender, String receiver, String description, double amount) {
        this.number = num;
        this.type = type;
        this.sender = sender;
        this.receiver = receiver;
        this.description = description;
        this.amount = amount;
    }

    // EFFECTS: returns transaction number
    public int getNumber() {
        return number;
    }

    // EFFECTS: returns type
    public String getType() {
        return type;
    }

    // EFFECTS: returns sender
    public String getSender() {
        return sender;
    }

    // EFFECTS: returns receiver
    public String getReceiver() {
        return receiver;
    }

    // EFFECTS: returns description
    public String getDescription() {
        return description;
    }

    // EFFECTS: returns amount
    public double getAmount() {
        return amount;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("number", number);
        json.put("type", type);
        json.put("sender", sender);
        json.put("receiver", receiver);
        json.put("description", description);
        json.put("amount", amount);
        return json;
    }

    @Override
    public String toString() {
        return String.valueOf(this.number);
    }
}
