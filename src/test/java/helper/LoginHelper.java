package helper;

import data.DataAuth;
import endpoint.ApiEndpoints;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

public class LoginHelper {

    ApiEndpoints apiEndpoints = new ApiEndpoints();
    DataAuth dataAuth = new DataAuth();

    public String getToken() {

        JSONObject payload = dataAuth.login();

        Response response = (Response) RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(payload.toJSONString())
                .post(apiEndpoints.getAuthentications())
                .then()
                .extract()
                .response();

        JsonPath responseParsed = response.jsonPath();

        return responseParsed.getString("data.accessToken");
    }

}
