package StepDefinition;

import io.cucumber.java.an.E;
import org.junit.Assert;
import resources.APIResources;
import resources.TestData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
//import org.testng.Assert;
import resources.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.http.WebSocket;

import static io.restassured.RestAssured.given;

public class StepDefinition extends Utils  {
    RequestSpecification res;
    ResponseSpecification respSpec;
    Response response;
    static String placeID;

    TestData locationPayload = new TestData();

    //---------------------------------Since "Given definition is changed in feature this Given will not work------------------------------------------------


    //@Given("Add Place Payload")
    //public void add_place_payload() throws IOException {
        // Write code here that turns the phrase above into concrete actions

        /*
        Now we will optimize out by removing the payload from this to resource file
        ------------------------------------------------------------------------------
        Serialization insertValue = new Serialization();
        //In order to set lat and lon we will fist make object of Location class
        Location locData   = new Location();
        locData.setLat("-38.383494");
        locData.setLng("33.427362");

        insertValue.setLocation(locData);

        insertValue.setAccuracy("50");
        insertValue.setName("Frontline house");
        insertValue.setPhone_number("(+91) 983 893 3937");
        insertValue.setAddress("29, side layout, cohen 09");

        ArrayList<String> t = new ArrayList<String>();
        t.add("Shoe Park");
        t.add("Shop");

        insertValue.setTypes(t);

        insertValue.setWebsite("https://google.com");
        insertValue.setLanguage("French-IN");
        ---------------------------------------------------------
         */

        /*
        Note -  All the code for "given()...." will be tagged to "Give" in BDD
        and all the code for "when()....." will be tagged to "When" in BDD
        and all the code for "then()...." will be tagged to "Then" in BDD
         */

        /*
        As we know this below is common for all "ADD Location, Delete Location" api we will move this code Utils
        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key", "qaclick123")
                .addHeader("Content-Type", "application/json")
                .build();
                -------------------------------------------------
                */

        /*
        This code will be shifted to When()
        ---------------------------------------------
        respSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
         */

        //res = given().spec(requestSpecification()).body(locationPayload.addLocationPayload());

        /* This code will go under "When"
        Response response = res.when().post("/maps/api/place/add/json")
                .then().spec(respSpec).extract().response();

         */

    //}

    //-----------------------------------------------------------------------------------------------------

    @Given("Add Place Payload is prepared with {string}, {string} and {string}")
    public void add_place_payload_is_prepared_with_and(String name, String language, String address) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        res = given().spec(requestSpecification()).body(locationPayload.addLocationPayload(name, language, address));
    }

    @When("User calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String method) {
        // Write code here that turns the phrase above into concrete actions
        // we can directly call constructor of "APIResource" class using "valueOf()" method will execute constructor
        APIResources resouceApi = APIResources.valueOf(resource); // For enum classes valueof() method directly invoke the constructor
        resouceApi.getResource();
        System.out.println("resource: " + resouceApi.getResource());
        respSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();

        if(method.equalsIgnoreCase("POST")){
            response = res.when().post(resouceApi.getResource());
                    /*
                    ".then().spec(respSpec).extract().response();" -

                    we will not write this code here because we want to
                    validate status code in next step and if we write this code here then it will automatically
                    validate the status code and if status code is not 200 then it will fail at this step only
                    but we want to validate status code in next step so we will write this code in next step
                     */
        }else if(method.equalsIgnoreCase("GET")){
            response = res.when().get(resouceApi.getResource());
        }else if(method.equalsIgnoreCase("PUT")){
            response = res.when().put(resouceApi.getResource());
        }

    }
    @Then("API call is successful with status code {int}")
    public void api_call_is_successful_with_status_code(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(200, response.getStatusCode());
    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String ExpectedValue) {
        // Write code here that turns the phrase above into concrete actions
        // In this we will verify that in output response we are getting "OK" for this we have to convert output response into string

        //Assert.assertEquals(jsonPath.get("status").toString(), "success"); - we can write it in this form as well
        Assert.assertEquals(getJsonPath(response, keyValue), ExpectedValue);
        //For last validation present in feature we dont have to make separate "@Then" we can use the same "@Then" and write another assertion in that
            Assert.assertEquals(getJsonPath(response, keyValue), ExpectedValue);
    }

    @Then("place_id is generated and it maps to {string} when we call {string}")
    public void place_id_is_generated_and_it_maps_to_when_we_call(String expectedName, String resource) throws IOException {
        placeID = getJsonPath(response, "place_id");
        res = given().spec(requestSpecification()).queryParam("place_id", placeID);

        // Now we,don't have declared a separate method to call the api. we can use the first that we call to "AddPlaceApi"
        user_calls_with_http_request(resource, "GET");
        //String name = getJsonPath(response, "name");
        Assert.assertEquals(getJsonPath(response, "name"), expectedName);

    }





    @Given("Delete Place Payload is prepared with place_id generated from Add Place API")
    public void delete_place_payload_is_prepared_with_place_id_generated_from_add_place_api() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        res = given().spec(requestSpecification()).body(locationPayload.deletePlacePayload(placeID));

    }


}
