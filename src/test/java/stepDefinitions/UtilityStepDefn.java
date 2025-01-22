package stepDefinitions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.json.simple.parser.ParseException;

import APIFramework.org.APIFramework.CommonAPIFunctions;
import APIFramework.org.APIFramework.FileReaderUtility;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class UtilityStepDefn extends FileReaderUtility{

	public UtilityStepDefn() throws IOException {
		super();
	}

	CommonAPIFunctions commFun = new CommonAPIFunctions();
	
	Properties prop = FileReaderUtility.readPropertiesFile("property.properties");
			
	 @Given("User post a request to add Location")
	    public void postAPIRequest() throws FileNotFoundException, IOException, ParseException {
		 commFun.postAPIResponse(FileReaderUtility.getPayload("postLocation.json"),prop.getProperty("addAPI"));
	    }
	
	 @Given("User updates the {} in json request")
	    public void updateAPIRequest(String address) throws FileNotFoundException, IOException, ParseException {
		 commFun.updateAPIResponse(FileReaderUtility.updatePayload("updateLocation.json",CommonAPIFunctions.getPlaceId().toString(), address),prop.getProperty("updateAPI"));
	    }
	 
	 @Given("User get the API response")
	    public void getResponse() throws FileNotFoundException, IOException, ParseException {
		 commFun.getAPIResponse(prop.getProperty("getAPI"));
	    }
	 @Then("Verify the response after Adding Location")
	    public void postAPIResponseValidation() throws FileNotFoundException, IOException, ParseException {
		 commFun.postResponseValidation("addLocation");
	    }
	 
	 @Then("Verify the response of PUT API")
	    public void putAPIResponseValidation() throws FileNotFoundException, IOException, ParseException {
		 commFun.addrressUpdateValidation("updateLocation");
	    }
	 
	 @Then("Verify the response of GET API")
	    public void getAPIResponseValidation() throws FileNotFoundException, IOException, ParseException {
		 commFun.getAddressValidation("LocationDataFetch");
	    }
}
