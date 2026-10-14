package StepDefinition;

import io.cucumber.java.Before;
import java.io.IOException;

public class Hooks {
    @Before("@DeletePlaceAPI")
    public void beforeScenario() throws IOException {
        // in this method we will get place id by using add place api but as we know in step definition we are already call add placeAPI. so, we will use that again

        //execute this code only when placeID is null
        //write a code that will give you placeID

        // Create an object of Step Definition file so, that we can access all method
        // we can call static by className also
        if(StepDefinition.placeID == null) {
            StepDefinition definition = new StepDefinition();
            definition.add_place_payload_is_prepared_with_and("Punchh Divyanshu", "Hindi", "Noida, India");
            definition.user_calls_with_http_request("addPlaceApi", "POST");
            definition.place_id_is_generated_and_it_maps_to_when_we_call("Punchh Divyanshu", "getPlaceApi");
        }

    }
}
