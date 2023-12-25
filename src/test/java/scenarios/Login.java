package scenarios;

import data.DataAuth;
import endpoint.ApiEndpoints;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import io.restassured.module.jsv.JsonSchemaValidator;

public class Login {

    ApiEndpoints apiEndpoints = new ApiEndpoints();
    DataAuth dataAuth = new DataAuth();

    @Test
    public void successLogin() {

        String jsonSchemaFilePath = "src/test/java/schema/Authentications/validLoginResponseSchema.json";
        JSONObject payload = dataAuth.login();

        Response response = (Response) RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(payload.toJSONString())
                .post(apiEndpoints.getAuthentications())
                .then()
                .statusCode(201)
                .contentType("application/json")
                .body(JsonSchemaValidator.matchesJsonSchema(new File(jsonSchemaFilePath)))
                .log().body()
                .extract()
                .response();

        JsonPath responseParsed = response.jsonPath();
        String userEmail = (String) payload.get("email");
        Assert.assertEquals(responseParsed.getString("data.user.email"), userEmail);

    }

}
