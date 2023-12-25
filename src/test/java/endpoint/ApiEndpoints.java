package endpoint;

import config.TestConfig;

public class ApiEndpoints {

    TestConfig config = new TestConfig();
    String baseURL = config.getBaseUrl();

    public String getAuthentications() {
        return baseURL + "/authentications";
    }

    public String getDashboard() {
        return baseURL + "/dashboard";
    }

}
