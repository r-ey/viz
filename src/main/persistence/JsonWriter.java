package persistence;

import model.component.Storage;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Code influenced by the JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/tree/master/src
// class to write to JSON file
public class JsonWriter {

    private static final int TAB = 4;
    private PrintWriter writer;
    private final String destination;

    // EFFECTS : construct writer to write to JSON file
    public JsonWriter(String d) {
        destination = d;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(destination);
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of storage to file
    public void write(Storage s) {
        JSONObject json = s.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
