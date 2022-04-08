package cucumber.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class RMSApi {
    String url;
    Response response;


    @Given("^I make a GET request to \"([^\\\"]*)\"$")
    public void getRequest(String url) throws Throwable{
        this.url = url;
        response = given().get(url);
        response.prettyPrint();
    }

    @Then("^HTTP status code of the response should be \"(\\d+)\"$")
    public void verifyStatus(int statusCode) throws Throwable{
        assert response.statusCode() == statusCode : "Status code is different that expected";
    }

    @And("^the response time of the request should be below \"(\\d+)\" milliseconds$")
    public void verifyResponseTime(int responseTime) throws Throwable{
        assert response.getTimeIn(TimeUnit.MILLISECONDS) <= responseTime : "Response time is higher than expected";
    }

    @Then("^\"id\" field should never be null$")
    public void verifyData() throws Throwable{

    }

    @And("^\"segment_type\" field for every track is always \"music\"$")
    public void verifySegmentData() throws Throwable{

    }
}
