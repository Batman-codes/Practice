package Day2OutPractice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider implements TestDataProvider{
	
	private static Logger logger = Logger.getLogger(ExcelDataProvider.class.getName());

	@Override
	public Map<String, Object> getTestData(String fileName) {

		FileInputStream inputFile;
		File file;
		XSSFWorkbook wb;
		XSSFSheet ws ;
		int rowNum;
		int cellNum;
		Map<String, Object> testData = new LinkedHashMap<>();
		
		//Get the file from the file location
		logger.info("getting the file from location");
		file = new File("./src/test/resources/testData/" + fileName + ".xlsx");
		
		//Create workbook to read the data
		logger.info("Creating workbook to read the data");
		try {
			inputFile = new FileInputStream(file);
			wb = new XSSFWorkbook(inputFile);
			ws = wb.getSheet("sheet1");

			//Get the number of rows and cells in the sheet
			logger.info("Getting the number of rows and cells in the sheet");


			rowNum = ws.getLastRowNum();
			cellNum = ws.getRow(0).getLastCellNum();

			//Initialize map
			testData = new LinkedHashMap<>();

			//Run for loops to get data from excel and put it in map
			logger.info("Running for loops to get data from excel and put it in map");
			for(int i = 0 ; i < rowNum ; i++) {
				for(int j = 0 ; j < cellNum ; j++) {

					testData.put(ws.getRow(0).getCell(j).toString(), ws.getRow(i+1).getCell(j).toString());
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return testData;
	}
	
	

}
