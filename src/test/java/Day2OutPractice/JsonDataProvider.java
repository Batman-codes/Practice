package Day2OutPractice;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;



public class JsonDataProvider implements TestDataProvider{

	private File file;
	private ObjectMapper mapper;
	private Map<String, Object> testData;
	private JsonPojo myPojo;
	
	private static Logger logger = Logger.getLogger(JsonDataProvider.class.getName());
	
	@Override
	public Map<String, Object> getTestData(String fileName) {
		
		logger.info("Getting the file from the file location");
		
		file = new File("./src/test/resources/testData/" + fileName + ".json");
		mapper = new ObjectMapper();
		testData = new LinkedHashMap<>();
		
		logger.info("Putting the json values to a POJO class"); 
		
		try {
			myPojo = mapper.readValue(file, JsonPojo.class);
		} catch (StreamReadException e) {
			e.printStackTrace();
		} catch (DatabindException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		logger.info("Putting the Pojo class values to the Map");
		
		testData = mapper.convertValue(myPojo, new TypeReference<Map<String,Object>>() {});
		return testData;
	}
	
	
	
	
	
	

}
