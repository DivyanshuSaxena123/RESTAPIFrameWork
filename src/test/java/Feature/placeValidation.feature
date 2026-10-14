  # Now as we can see that we have multiple scenarios in our feature file, we can use tags to categorize our scenarios and run them accordingly. For example, we can use @AddPlaceAPI tag for all the scenarios related to Add Place API and @DeletePlaceAPI tag for all the scenarios related to Delete Place API. This way we can easily run the scenarios related to a specific API without having to run all the scenarios in the feature file.and pass the tag in runner file to run the specific scenarios.
  # But there is problem with tags approach, as we know that for Delete Place API we need to have place_id generated from Add Place API, so if we run the scenarios with @DeletePlaceAPI tag then it will fail because it will not have the place_id generated from Add Place API, so to avoid this we can use hooks in cucumber to run the scenarios in a specific order. We can use @Before and @After hooks to run the scenarios in a specific order. For example, we can use @Before hook to run the scenarios with @AddPlaceAPI tag before the scenarios with @DeletePlaceAPI tag. This way we can ensure that the place_id is generated before we run the scenarios for Delete Place API.
    # We can also multiple tags for a single scenario, for example, we can use @AddPlaceAPI and @Regression tags for the same scenario, this way we can run the scenario using jenkins by creating a job and passing the tags in the job configuration to run the specific scenarios. This way we can easily run the scenarios related to a specific API or a specific type of testing (like regression testing) without having to run all the scenarios in the feature file.
Feature: Validating Place API's
  @AddPlaceAPI @Regression
  Scenario Outline: Verify if place is being successfully added using Add Place API
    #As you can see in the above scenario, we are using BDD approach to write our test cases. We have used Given, When, Then and And keywords to make our test case more readable and understandable.
    #But let assume we have to validate delete api as well and we have to write another scenario for that, then we will have to repeat the same steps again which is not a good practice. So to avoid this we can use or make "Add Place Payload" as a reusable method and we can call that method in our test case whenever we need to use it. This way we can avoid code duplication and make our test cases more maintainable.
    #Same we will apply for "status" in response body is "OK" as well, we can make it as a reusable method and call it whenever we need to validate the status in response body.
    # Given Add Place Payload - previously this is Given method which is used to prepare the payload for Add Place API, Now, since we have change the "Given" definition therefore it syntax in StepDefinition file will be changed
    Given Add Place Payload is prepared with "<name>", "<language>" and "<address>"
    When User calls "addPlaceApi" with "POST" http request
    Then API call is successful with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And place_id is generated and it maps to "<name>" when we call "getPlaceApi"

    Examples:
      | name         | language | address          |
      | Test Place 1 | English  | 123 Test Street  |
   #   | Test Place 2 | French   | 456 Test Avenue  |

    #This below scenario is used to understand enum in cucumber, as you can see in the above scenario we have used "POST" http request to call the API, but what if we want to test the same API with "GET" http request, then we can use enum to define the http request method and then we can use that enum in our test case. This way we can avoid hardcoding the http request method in our test case and make it more flexible.

    # When user calls "Get Place API" with "GET" http request
    # Then API call is successful with status code 200
    # And "status" in response body is "OK"

  #Delete Place API validation scenario
  @DeletePlaceAPI @Regression
  Scenario: Delete Place API validation
    Given Delete Place Payload is prepared with place_id generated from Add Place API
    When User calls "deletePlaceApi" with "POST" http request
    Then API call is successful with status code 200
    And "status" in response body is "OK"

