package scenarios;

import endpoint.ApiEndpoints;
import helper.LoginHelper;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import java.io.File;

public class Dashboard {

    ApiEndpoints apiEndpoints = new ApiEndpoints();
    LoginHelper loginHelper = new LoginHelper();

    @Test
    public void successGetDashboard() {

        String jsonSchemaFilePath = "src/test/java/schema/Dashboard/validGetDashboardResponseSchema.json";
        String token = loginHelper.getToken();

        RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .get(apiEndpoints.getDashboard())
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body(JsonSchemaValidator.matchesJsonSchema(new File(jsonSchemaFilePath)))
                .log().body()
                .extract()
                .response();
    }

}
