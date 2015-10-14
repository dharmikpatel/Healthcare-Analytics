package edu.sjsu.controller;
import java.io.File;
import java.util.List;
import java.util.Map;

import edu.sjsu.utils.CollectWeeklyData;
import edu.sjsu.utils.ComputeLRInput;


public class CsvToJson {
	
	public static void main(String[] args) {
		File fileLocation = new File("D:\\ProjectSampleData");
		
	 	//read and collect data from datasets
		List<Map<String, String[]>> weeklyDataList = CollectWeeklyData.readData(fileLocation.listFiles().length);
		
		//compute and predict results
		ComputeLRInput.computeValues(weeklyDataList);
	}
}
