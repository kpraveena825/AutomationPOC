package APIFramework.org.APIFramework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.log4testng.Logger;

public class ReUsableMethods {

	private static final String RESOURCE_PATH = System.getProperty("user.dir") + "\\src\\test\\java\\resources\\";

	static Logger logger = Logger.getLogger(ReUsableMethods.class.getClass());
	static String address;

	public static File getFiles(String fileType) {
		String path = RESOURCE_PATH + fileType;
		return new File(path);

	}
	
	public static List<JSONObject> readJson(String fileType) throws FileNotFoundException{
		List<JSONObject> jsonList = new ArrayList<>();
		File file = getFiles(fileType);
		String line;
		JSONObject jsonObj = new JSONObject();
		if(file==null) {
			throw new FileNotFoundException("File not found");
		}
		logger.info(file.getName());
		
		FileReader freader = new FileReader(file);
		try(BufferedReader bf = new BufferedReader(freader)){
			while((line = bf.readLine())!=null) {
				jsonObj = (JSONObject)new JSONParser().parse(line);
				jsonList.add(jsonObj);
			}
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(""+jsonList);
		return jsonList;
		
	}
}
