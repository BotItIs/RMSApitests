package cucumber.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.DateUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class RMSApiTestSteps {
    String url;
    Response response;
    JsonPath jsonResponse;
    String rmsApi = "https://testapi.io/api/TruptiRath/api/ottplatform/media";

    @Given("^I make a GET request to RMSApi$")
    public void getRequest() throws Throwable {
        response = given().get(rmsApi);
        jsonResponse = response.jsonPath();
    }

    @Then("^HTTP status code of the response should be (\\d+)$")
    public void verifyStatus(int statusCode) throws Throwable {
        assertTrue("Http status code ("+response.statusCode()+") should be 200",response.statusCode() == statusCode);
    }

    @And("^Response should match the expected response schema$")
    public void validateResponseSchema(){
        String schemaPath = System.getProperty("user.dir") + "/src/test/resources/schema/response-schema.json";
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(schemaPath)));
    }

    @And("^the response time of the request should be below (\\d+) milliseconds$")
    public void verifyResponseTime(long responseTime) throws Throwable {
        long actualResponseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
        assertTrue("Response time (" + actualResponseTime + ") should be less than (" + responseTime + ")", actualResponseTime < responseTime);
    }

    @Then("^\"id\" field should never be null for all (\\d+) items present in the data array$")
    public void verifyData(int totalIds) throws Throwable {
        List<String> ids = jsonResponse.getList("data.id");
        assertTrue("Total elements should be 14", ids.size()==totalIds);
        for(String id:ids)
        {
            assertTrue("id("+id+") should not be null",id != null);
        }
    }

    @And("^\"segment_type\" field for every track is always \"music\"$")
    public void verifySegmentData() throws Throwable {
        List<String> segmentTypes = jsonResponse.getList("data.segment_type");
        for(String segmentType:segmentTypes)
        {
            assertTrue("segment_type("+segmentType+") should be music",Objects.equals(segmentType, "music"));
        }
    }

    @Then("^\"primary\" field in \"title_list\" is never null or empty\\(\"\"\\) for any track$")
    public void verifyPrimary() throws Throwable {
        List<String> primaries = jsonResponse.getList("data.title_list.primary");
        for(String primary:primaries){
            assertTrue("primary can never be empty or null", primary != null && !primary.equals(""));
        }
    }

    @Then("^only one track in the list has \"now_playing\" field in \"offset\" as true$")
    public void verifyOffset() throws Throwable {
        List<Boolean> nowPlayings = jsonResponse.getList("data.offset.now_playing");
        ArrayList<Boolean> playing = new ArrayList<Boolean>();
        for (Boolean nowPlaying:nowPlayings){
            if(nowPlaying){
                playing.add(nowPlaying);
            }
        }
        assertTrue("There should be one track playing now", playing.size()==1);
    }

    @Then("^response headers should contain \"Date\" header$")
    public void verifyDateHeader() throws Throwable {
        DateUtil dateUtil = new DateUtil();
        String currentDate = dateUtil.getCurrentDate("EEE, dd MMM yyyy");
        String dateString = response.header("Date");
        assertTrue("Date header should be part of response header", dateString != null && !dateString.equals(""));
        assertTrue("Date header should have current date", dateString.contains(currentDate));  
    }
}
 