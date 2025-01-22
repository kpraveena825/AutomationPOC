package APIFramework.org.APIFramework;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.asserts.SoftAssert;
import org.testng.log4testng.Logger;

public class CommonAPIFunctions {
	private static String placeId, address;
	static Logger logger = Logger.getLogger(CommonAPIFunctions.class.getClass());
	JsonPath js;
	SoftAssert softAssert = new SoftAssert();
	JSONObject jsonObject; 
	LinkedHashMap<String, String> data;

	public JsonPath postAPIResponse(Object payload, String resources)
			throws FileNotFoundException, IOException, ParseException {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(payload).when().post(resources).then().assertThat().statusCode(200).body("scope", equalTo("APP"))
				.header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		System.out.println("\n\n***********************\n" + response);
		js = new JsonPath(response);
		System.out.println("\n\n***********************\n" + js.get());

		setPlaceId(js.getString("place_id"));
		return js;
	}

	public JsonPath updateAPIResponse(Object payload, String resources) throws FileNotFoundException, IOException, ParseException {
		
		String updateOutput = given().log().all().queryParam("key", "qaclick123")
				.header("Content-Type", "application/json").body(payload).when().put(resources).then().assertThat()
				.statusCode(200).extract().response().asString();
		js = new JsonPath(updateOutput);
		System.out.println("\nUpdate data ------> \n" + updateOutput);
		return js;

	}

	public JsonPath getAPIResponse(String resources) {
		String responseOutput = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", getPlaceId())
				.header("Content-Type", "application/json").body("").when().get(resources).then().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.print("\nGet data ------> \n" + responseOutput);
		js = new JsonPath(responseOutput);
		return js;
	}

	public void postResponseValidation(String responseFile) throws IOException {
		FileReaderUtility.writeJson(js,responseFile);
		data =new LinkedHashMap<String, String>();
		data = FileReaderUtility.getStaticMap(responseFile);
		softAssert.assertEquals(js.getString("scope"), data.get("SCOPE"));
		softAssert.assertEquals(js.getString("status"), data.get("STATUS"));
		softAssert.assertAll();

	}

	public void getAddressValidation(String responseFile) throws IOException {
	    FileReaderUtility.writeJson(js,responseFile);
	    data =new LinkedHashMap<String, String>();
		data = FileReaderUtility.getStaticMap(responseFile);
		softAssert.assertEquals(js.getString("address"), data.get("ADDRESS"));
		softAssert.assertEquals(js.getString("website"), data.get("WEBSITE"));
		softAssert.assertAll();
	}

	public void addrressUpdateValidation(String responseFile) throws IOException {
		FileReaderUtility.writeJson(js,responseFile);
		 data =new LinkedHashMap<String, String>();
			data = FileReaderUtility.getStaticMap(responseFile);
		softAssert.assertEquals(js.getString("msg"), data.get("MSG"));
		softAssert.assertAll();
	}

	public static String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		CommonAPIFunctions.placeId = placeId;
	}
	
	public static JSONObject setJsonObject(String address) throws FileNotFoundException, IOException, ParseException {
		Object obj = new JSONParser().parse(new FileReader(ReUsableMethods.getFiles("RequestPayloads\\postLocation.json")));
        JSONObject jsonObj = (JSONObject) obj;
        jsonObj.put("address", address);
//        try {
//			address = (String) jsonObj.get("address");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return jsonObj;
	}

}
