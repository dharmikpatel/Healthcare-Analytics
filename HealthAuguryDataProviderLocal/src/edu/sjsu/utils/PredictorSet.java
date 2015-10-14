package edu.sjsu.utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class PredictorSet {
	public static  ArrayList<Double> preparePredictorSet(int diseaseNumber,List<Map<String, String[]>> weeklyDataList,String stateName)
	{
		ArrayList<Double> weekValues = new ArrayList<Double>();
		for(Map<String, String[]> weekWise: weeklyDataList)
		{
			String diseaseData[] = weekWise.get(stateName);
			weekValues.add(Double.parseDouble(diseaseData[diseaseNumber]));
		}
		return weekValues;
	}
}
