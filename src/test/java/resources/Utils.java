package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {

    // we have make this variable static because we want to use it for multiple test cases, so that it does not make copy of it for each test case
    public static RequestSpecification reqSpec;


    public RequestSpecification requestSpecification() throws IOException {
        // This method can be used to set up common request specifications for API testing

        /*
        As we know that we use give().log().all() for each test cases that is not good instead we will use
        addFilter()
         */

        PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
        /*
        The purpose of adding this if condition is that if we run test with multiple data
        sets then in "logging.txt" it only records last run test bcz it again create "RespectBuilder" for second test cases
         */
        if (reqSpec == null) {
            reqSpec = new RequestSpecBuilder()
                    // initially we have hardcoded baseUrl
                    .setBaseUri(getGlobalValue("baseUrl"))
                    .addQueryParam("key", "qaclick123")
                    .addHeader("Content-Type", "application/json")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .build();
            return reqSpec;
        }
         return reqSpec;
    }



    // We will create a method that will help in extracting global properties
    public static String getGlobalValue(String key) throws IOException {
        // We will create object of "Propertis" class
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("/Users/punchh_divyanshu/Desktop/REST-APIFramework/src/test/java/resources/global.properties");
        prop.load(fis);
        return prop.getProperty(key);
    }

    public String getJsonPath(Response response, String keyValue){
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        return js.getString(keyValue);
    }

}
