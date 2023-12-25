package data;

import org.json.simple.JSONObject;

public class DataAuth {

    public JSONObject login() {

        JSONObject payload = new JSONObject();
        payload.put("email", "test_selenium@gmail.com");
        payload.put("password", "pass_test_selenium");

        return payload;

    }

}
