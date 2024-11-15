package Day2OutPractice;

import java.util.logging.Logger;

public class DataProviderFactory {

	private static Logger logger = Logger.getLogger(DataProviderFactory.class.getName());

	public TestDataProvider getTestData(DataSources fileType){

		logger.info("Getting the dataProvider based on the file");
		switch(fileType) {
		
		case excel : 
			return new ExcelDataProvider();
		case json :
			return new JsonDataProvider();
		default :
			throw new IllegalArgumentException("Please provide the correct file type");
		}

	}
}
