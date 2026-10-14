package cucumber.Options;


import io.cucumber.core.backend.StepDefinition;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/*
This file will help us in running the code as we know that we are using JUnit as our test runner and we have to specify the path of the feature file,
and we can also do via giving step definition file path but here we will use "glue{"packageName"}".

To generate a html report of test we need a plugin in html code but that plugin take "json" report as an input file then only it will generate HTML report
 */

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/Feature", glue={"StepDefinition"}, plugin = "json:target/jsonReports/cucumber-report.json", tags = "@DeletePlaceAPI")
public class TestRunner {
    /*
    Run this test without defining stepDefinitions we will get error but in console we will also
    get all syntaxes that we need to implement in stepDefinitions so, copy from console and paste in stepDefinition file

    Note - Here we are Junit 4 bcz this most compatible and widely used there are other version as well Junit 5 and Junit 6 as well. Cucumber
    does not support Junit 6. Just FYI, only test runner file will change if we use Junit 5
    */



}
