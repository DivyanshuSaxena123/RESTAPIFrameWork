package resources;

import io.cucumber.java.bs.A;

/*
 * This class is used to store all the API resources (endpoints) that we will be using in our tests.
 * It helps to keep our code organized and maintainable. Whenever we need to change an endpoint, we can do it here without having to search through all our test files.
 We will make this call "enum" . Enum is a special class in which java has collection of constants or methods
 */
public enum APIResources {
    /**
     * Here we will define all the API resources (endpoints) that we will be using in our tests. For example, if we have an endpoint for adding a place, we can define it like this:
     * Purpose of doing this is that suppose in future if there is change in end point so, we have to make change in single place instead of searching in all test files and making change there. It will make our code more maintainable and cleaner.
     * Current we have hardcoded this
     */
    addPlaceApi("/maps/api/place/add/json"),
    getPlaceApi("/maps/api/place/get/json"),
    updatePlaceApi("/maps/api/place/update/json"),
    deletePlaceApi("maps/api/place/delete/json");
    private String resource;

    //create constructor
    APIResources(String resource) {

        this.resource=resource;
    }

    public String getResource() {
        //this "resource" is declared in class
        return resource;
    }
}
