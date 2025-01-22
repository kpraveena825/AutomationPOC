package options;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features/", 
         glue = "stepDefinitions", 
         plugin = {"json:target/jsonReports/cucumber-report.json"}, 
         tags = "@test", dryRun = false
         )
public class TestRunner {

}
