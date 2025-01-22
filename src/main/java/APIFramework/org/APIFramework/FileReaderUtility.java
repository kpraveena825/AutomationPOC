package APIFramework.org.APIFramework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import io.restassured.path.json.JsonPath;

public class FileReaderUtility {
	private static Properties prop=null;
	static JSONParser parser = new JSONParser();
	private static final String RESOURCE_PATH = System.getProperty("user.dir") + "\\src\\test\\java\\resources\\";
	
	public FileReaderUtility() throws IOException {
		
	}
	
	public static JsonPath rawToJson(String response) {
		JsonPath js = new JsonPath(response);
		return js;
	}
	
	public static Properties readPropertiesFile(String fileName) throws IOException {
		FileInputStream fis = null;
	         try {
				fis = new FileInputStream(RESOURCE_PATH+fileName);
				 setProp(new Properties());
		         getProp().load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				fis.close();
			}
	     return getProp();    
	}
	
	public static Object jsonReader(String fileName) throws FileNotFoundException, IOException, ParseException {
		Object obj = parser.parse(new FileReader(RESOURCE_PATH+"RequestPayloads\\"+fileName+""));
		return obj;
	}
	
	public static String getPayload(String fileName) throws FileNotFoundException, IOException, ParseException{
		JSONObject jsonObject = (JSONObject)jsonReader(fileName);
		return jsonObject.toString();
	}
	
	public static Object updatePayload(String fileName, String variable, String address) throws FileNotFoundException, IOException, ParseException{
		
		JSONObject jsonObject = (JSONObject)jsonReader(fileName);
	    jsonObject.put("place_id", variable);
	    jsonObject.put("address", address);
		return jsonObject.toString();
	}
	
	public static LinkedHashMap<String, String>getStaticMap(String fileName) throws IOException {
		LinkedHashMap<String, String> data = new LinkedHashMap<String, String>();
		
		FileInputStream fis = new FileInputStream(RESOURCE_PATH+"staticMap.xlsx");
		XSSFWorkbook wb;
		try {
			wb = new XSSFWorkbook(fis);
			Sheet sheet = wb.getSheet(fileName);
		    int rowCount = sheet.getPhysicalNumberOfRows();
		    
			for(int i=1;i<rowCount;i++) {
				Row r = sheet.getRow(i);
				String fieldName = r.getCell(0).getStringCellValue().trim();
				String fieldValue = r.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
				data.put(fieldName, fieldValue);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			fis.close();

		}		
		return data;		
	}
	
	public static void writeJson(JsonPath js,String fileName) throws IOException {

		FileWriter file = new FileWriter(RESOURCE_PATH+"ResponseEvidance\\"+fileName+"response.json");
        file.write(js.get().toString());
        file.close();
	}
	
	public static String AddPlace()
	{
		return "{\r\n" + 
				"  \"location\": {\r\n" + 
				"    \"lat\": -38.383494,\r\n" + 
				"    \"lng\": 33.427362\r\n" + 
				"  },\r\n" + 
				"  \"accuracy\": 50,\r\n" + 
				"  \"name\": \"Rahul Shetty Academy\",\r\n" + 
				"  \"phone_number\": \"(+91) 983 893 3937\",\r\n" + 
				"  \"address\": \"29, side layout, cohen 09\",\r\n" + 
				"  \"types\": [\r\n" + 
				"    \"shoe park\",\r\n" + 
				"    \"shop\"\r\n" + 
				"  ],\r\n" + 
				"  \"website\": \"http://rahulshettyacademy.com\",\r\n" + 
				"  \"language\": \"French-IN\"\r\n" + 
				"}\r\n" + 
				"";
	}

	public static  Properties getProp() {
		return prop;
	}

	public static void setProp(Properties prop) {
		try {
			FileReaderUtility.prop = prop;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
