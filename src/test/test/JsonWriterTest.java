package test;

import model.Transaction;
import model.TransactionList;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/filed\0esntExist.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            TransactionList transactionList = new TransactionList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTransactionList.json");
            writer.open();
            writer.write(transactionList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTransactionList.json");
            transactionList = reader.read();
            assertEquals(0, transactionList.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterNormalWorkroom() {
        try {
            TransactionList transactionList = new TransactionList();
            transactionList.addTransaction(new Transaction(1, "a", "a", "b", "s",
                    2));
            JsonWriter writer = new JsonWriter("./data/testWriterNormalTransactionList.json");
            writer.open();
            writer.write(transactionList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterNormalTransactionList.json");
            transactionList = reader.read();
            assertEquals(1, transactionList.size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
