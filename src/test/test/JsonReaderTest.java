package test;

import model.Transaction;
import model.TransactionList;
import model.Transaction;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/fileDoesntExist.json");
        try {
            TransactionList transactionList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyTransactionList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTransactionList.json");
        try {
            TransactionList transactionList = reader.read();
            assertEquals(0, transactionList.size());
        } catch (IOException e) {
            fail("File was not readable, please try again");
        }
    }

    @Test
    void testReaderNormalTransactionList() {
        JsonReader reader = new JsonReader("./data/testReaderNormalTransactionList.json");
        try {
            TransactionList transactionList = reader.read();
            assertEquals(1, transactionList.size());
            checkTransaction(1, "a", "a", "a", "a", 1,
                    transactionList.getTransaction(0));
        } catch (IOException e) {
            fail("File was not readable, please try again");
        }
    }
}

