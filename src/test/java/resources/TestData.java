package resources;

import POJOClasses.DeleteLocation.DeleteLocation;
import POJOClasses.Serialization.Location;
import POJOClasses.Serialization.Serialization;
import POJOClasses.UpdateLocation.UpdateLocation;
import io.cucumber.java.hu.De;


import java.util.ArrayList;

public class TestData {
    public Serialization addLocationPayload(String name, String language, String address){
        // Now as we know we are passing data is hardcoded which is wrong therefore let say we , will pass name, language and address
        Serialization insertValue = new Serialization();
        //In order to set lat and lon we will fist make object of Location class
        Location locData   = new Location();
        locData.setLat("-38.383494");
        locData.setLng("33.427362");

        insertValue.setLocation(locData);

        insertValue.setAccuracy("50");
        insertValue.setName(name);
        insertValue.setPhone_number("(+91) 983 893 3937");
        insertValue.setAddress(address);

        ArrayList<String> t = new ArrayList<String>();
        t.add("Shoe Park");
        t.add("Shop");

        insertValue.setTypes(t);

        insertValue.setWebsite("https://google.com");
        insertValue.setLanguage(language);
        return insertValue;
    }

    public UpdateLocation  updatePlacePayload(String placeID, String address, String key){
        UpdateLocation updateValue = new UpdateLocation();
        updateValue.setPlace_id(placeID);
        updateValue.setAddress(address);
        updateValue.setKey(key);
        return updateValue;
    }

    public DeleteLocation deletePlacePayload(String placeID){
        DeleteLocation deleteValue = new DeleteLocation();
        deleteValue.setPlace_id(placeID);
        return deleteValue;
    }
}
