package persistence;

import model.Transaction;
import model.TransactionList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads TransactionList from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads TransactionList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public TransactionList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTransactionList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses transactionList from JSON object and returns it
    private TransactionList parseTransactionList(JSONObject jsonObject) {
        Integer number = jsonObject.getInt("number");
        String type = jsonObject.getString("type");
        String sender = jsonObject.getString("sender");
        String receiver = jsonObject.getString("receiver");
        String description = jsonObject.getString("description");
        Integer amount = jsonObject.getInt("amount");
        TransactionList tl = new TransactionList();
        addTransactionList(tl, jsonObject);
        return tl;
    }

    // MODIFIES: tl
    // EFFECTS: parses transactionList from JSON object and adds them to transaction list
    private void addTransactionList(TransactionList tl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("transactionList");
        for (Object json : jsonArray) {
            JSONObject nextTransaction = (JSONObject) json;
            addTransaction(tl, nextTransaction);
        }
    }

    // MODIFIES: tl
    // EFFECTS: parses transaction from JSON object and adds it to transactionList
    private void addTransaction(TransactionList tl, JSONObject jsonObject) {
        Integer number = jsonObject.getInt("number");
        String type = jsonObject.getString("type");
        String sender = jsonObject.getString("sender");
        String receiver = jsonObject.getString("receiver");
        String description = jsonObject.getString("description");
        Integer amount = jsonObject.getInt("amount");
        Transaction transaction = new Transaction(number, type, sender, receiver, description, amount);
        tl.addTransaction(transaction);
    }
}

