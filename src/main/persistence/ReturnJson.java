package persistence;

import org.json.JSONObject;

// Based on the JsonSerializationDemo example provided on the CPSC210 edX page
public interface ReturnJson {
    // EFFECTS: returns this as a JSON object
    JSONObject toJson();
}
