package ui;

import model.Transaction;
import model.TransactionList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Adapted functionality and methods from the TellerApp file on edX: https://github.students.cs.ubc.ca/CPSC210/TellerApp
public class BusinessManagerApp {
    private Transaction transaction;
    private TransactionList transactionList;
    private Scanner userInput;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/transactionlist.json";

    // EFFECTS: runs the business manager application
    public BusinessManagerApp() {
        runBusinessManagerApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runBusinessManagerApp() {
        boolean goNext = true;
        String instruction;

        start();

        while (goNext) {
            userOptions();
            instruction = userInput.next();
            instruction = instruction.toLowerCase();

            if (instruction.equals("q")) {
                goNext = false;
            } else {
                processCommand(instruction);
            }
        }

        System.out.println("\nHave a nice day!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command on the main menu
    private void processCommand(String command) {
        if (command.equals("a")) {
            addTransaction();
        } else if (command.equals("r")) {
            removeTransaction();
        } else if (command.equals("l")) {
            lastTransaction();
        } else if (command.equals("i")) {
            getInfo();
        } else if (command.equals("v")) {
            loadTransactionList();
        } else if (command.equals("s")) {
            saveTransactionList();
        } else {
            System.out.println("Please try again...");
        }
    }



    // MODIFIES: this
    // EFFECTS: initializes the list of transactions
    private void start() {
        transactionList = new TransactionList();
        userInput = new Scanner(System.in);
        userInput.useDelimiter("\n");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: displays possible options that the user can choose from
    private void userOptions() {
        System.out.println("\nSelect an option:");
        System.out.println("\ta -> Add a transaction");
        System.out.println("\tr -> Remove a transaction");
        System.out.println("\tl -> Most recent transaction");
        System.out.println("\ti -> More info about a transaction");
        System.out.println("\ts -> Save transaction list to file");
        System.out.println("\tv -> Load transaction list from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: creates a transaction with parameters and adds it to the transaction list
    private void addTransaction() {
        System.out.print("Enter ID of transaction to create:");
        int id = userInput.nextInt();
        System.out.println("Enter type of transaction");
        String type = userInput.next();
        System.out.println("Enter name of sender:");
        String sender = userInput.next();
        System.out.println("Enter name of receiver:");
        String receiver = userInput.next();
        System.out.println("Enter transaction description:");
        String description = userInput.next();
        System.out.println("Enter amount of transaction");
        double amount = userInput.nextDouble();

        transaction = new Transaction(id, type, sender, receiver, description, amount);

        transactionList.addTransaction(transaction);
    }

    // MODIFIES: this
    // EFFECTS: gives info about the last transaction
    private void lastTransaction() {
        System.out.print("Your most recent transaction was: " + transactionList.lastTransaction());
    }

    // MODIFIES: this
    // EFFECTS: removes a transaction from the list
    private void removeTransaction() {
        System.out.print("Enter ID of transaction to remove:");
        int x = userInput.nextInt();

        transactionList.removeTransaction(x);
        System.out.println("Transaction " + x + " has successfully been removed.");
    }

    // MODIFIES: this
    // EFFECTS: gives more information about a specific transaction
    private void getInfo() {
        System.out.println("Enter ID of transaction to view:");
        int x = userInput.nextInt();

        System.out.println("Details: " + transactionList.viewTransaction(x));
    }

    // EFFECTS: saves the transaction list to a file
    private void saveTransactionList() {
        try {
            jsonWriter.open();
            jsonWriter.write(transactionList);
            jsonWriter.close();
            System.out.println("Saved transaction list" + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadTransactionList() {
        try {
            transactionList = jsonReader.read();
            System.out.println("Loaded transaction list" + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}

