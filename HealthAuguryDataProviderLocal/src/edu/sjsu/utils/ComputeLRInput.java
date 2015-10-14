package edu.sjsu.utils;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.lang.ArrayUtils;

import com.google.gson.Gson;

import edu.sjsu.beans.DiseaseData;
import edu.sjsu.beans.DiseaseHighest;
import edu.sjsu.beans.DiseaseLowest;
import edu.sjsu.beans.StateAllDisease;
import edu.sjsu.beans.StateWiseData;


public class ComputeLRInput {
	public static void computeValues(List<Map<String, String[]>> weeklyDataList)
	{
		int counter = 0;
		String usYearData = CollectWeeklyData.USData;
		String splitUSData[] = usYearData.split(",");
		for(String disease : CollectWeeklyData.diseaseNames)
		{
			counter++;
				if(counter==1)
				{
					//USA yearly prediction
					double[][] yearData = {{1,Double.parseDouble(splitUSData[4].replaceAll("[^\\dA-Za-z ]", "0").replaceAll("N", "0").replaceAll("U", "0"))},
							{2,Double.parseDouble(splitUSData[5].replaceAll("[^\\dA-Za-z ]", "0").replaceAll("N", "0").replaceAll("U", "0"))}};
					double yearPrediction = PredictValue.predictVaules(yearData);
					
					ComputeLRInput.stateWeekValueCompute(disease,Double.parseDouble(splitUSData[4]),Double.parseDouble(splitUSData[5]),counter,weeklyDataList,yearPrediction);
				}
				if(counter==2)
				{
					double[][] yearData = {{1,Double.parseDouble(splitUSData[9].replaceAll("[^\\dA-Za-z ]", "0").replaceAll("N", "0").replaceAll("U", "0"))},
							{2,Double.parseDouble(splitUSData[10].replaceAll("[^\\dA-Za-z ]", "0").replaceAll("N", "0").replaceAll("U", "0"))}};
					double yearPrediction = PredictValue.predictVaules(yearData);
					
					ComputeLRInput.stateWeekValueCompute(disease,Double.parseDouble(splitUSData[9]),Double.parseDouble(splitUSData[10]),counter,weeklyDataList,yearPrediction);
				}
				if(counter==3)
				{
					double[][] yearData = {{1,Double.parseDouble(splitUSData[14].replaceAll("[^\\dA-Za-z ]", "0").replaceAll("N", "0").replaceAll("U", "0"))},
							{2,Double.parseDouble(splitUSData[15].replaceAll("[^\\dA-Za-z ]", "0").replaceAll("N", "0").replaceAll("U", "0"))}};
					double yearPrediction = PredictValue.predictVaules(yearData);
					
					ComputeLRInput.stateWeekValueCompute(disease,Double.parseDouble(splitUSData[14]),Double.parseDouble(splitUSData[15]),counter,weeklyDataList,yearPrediction);
				}	
		}
	}
	
	public static void stateWeekValueCompute(String disease,double currentYear,double previousYear,int counter,List<Map<String, String[]>> weeklyDataList,double yearPrediction)
	{
		Properties prop = new Properties();
    	InputStream input = null;
    	String filename = "edu//sjsu//controller//config.properties";
		input = ComputeLRInput.class.getClassLoader().getResourceAsStream(filename);
		try {
		prop.load(input);
		DiseaseData diseaseData = null;
		List<StateWiseData> stateWiseDataList = new ArrayList<StateWiseData>();
		Map<String, Double> weekHighLow = new HashMap<String, Double>();
		for(String stateName : CollectWeeklyData.stateNames)
		{
			//state wise weekly data set preparation and prediction
			int weekCounter = 1;
			ArrayList<Double> stateWeekData = PredictorSet.preparePredictorSet(counter-1, weeklyDataList,stateName);
			double[] weekValueArr = ArrayUtils.toPrimitive(stateWeekData.toArray(new Double[stateWeekData.size()]));
			double[][] inputWeekValues = new double[weekValueArr.length][2];
			for(int i=0;i<weekValueArr.length;i++)
			{
				for(int j=0;j<2;j++)
				{
					if(j==1)
					{inputWeekValues[i][j]=weekValueArr[i];}
					else
					{inputWeekValues[i][j]=weekCounter;}
				}
				weekCounter++;
			}
			
			double stateWeekPrediction =  PredictValue.predictVaules(inputWeekValues);
			double totalOfTenWeeks = 0.0;
			for(int i=0;i<weekValueArr.length;i++)
			{
				totalOfTenWeeks = totalOfTenWeeks+weekValueArr[i];
			}
			//state wise yearly prediction
			Map<String, String[]> stateYearData = CollectWeeklyData.yearWise;
			String[] yearDataArr = stateYearData.get(stateName);
			
			if(counter == 1)
			{
				double[][] inputYearData = {{1,Double.parseDouble(yearDataArr[0])},{2,Double.parseDouble(yearDataArr[1])}};
				double stateYearPrediction = PredictValue.predictVaules(inputYearData);
				if(prop.getProperty(stateName.replace(" ", "_"))!=null)
				{
					/*stateCounter++;
					System.out.println(disease+"  "+stateName+" "+stateCounter);*/
					String propValues = prop.getProperty(stateName.replace(" ", "_"));
					String parts[] = propValues.split(",");
					
					StateWiseData stateWiseData = new StateWiseData(stateName, totalOfTenWeeks, stateWeekPrediction, Double.parseDouble(yearDataArr[0]), Double.parseDouble(yearDataArr[1]), stateYearPrediction,parts[2].trim(),parts[0],parts[1]);
					StateAllDisease stateAllDisease = new StateAllDisease(disease, totalOfTenWeeks, stateWeekPrediction, Double.parseDouble(yearDataArr[0]), Double.parseDouble(yearDataArr[1]), stateYearPrediction );
					MongoDbUpsert.addDiseaseState(stateName,stateAllDisease);
					stateWiseDataList.add(stateWiseData);
					weekHighLow.put(stateName, stateWeekPrediction);
				}
			}
			else if(counter == 2)
			{
				double[][] inputYearData = {{1,Double.parseDouble(yearDataArr[2])},{2,Double.parseDouble(yearDataArr[3])}};
				double stateYearPrediction = PredictValue.predictVaules(inputYearData);
				if(prop.getProperty(stateName.replace(" ", "_"))!=null)
				{
					/*stateCounter++;
					System.out.println(disease+"  "+stateName+" "+stateCounter);*/
					String propValues = prop.getProperty(stateName.replace(" ", "_"));
					String parts[] = propValues.split(",");
					
					StateWiseData stateWiseData = new StateWiseData(stateName, totalOfTenWeeks, stateWeekPrediction, Double.parseDouble(yearDataArr[2]), Double.parseDouble(yearDataArr[3]), stateYearPrediction,parts[2].trim(),parts[0],parts[1]);
					StateAllDisease stateAllDisease = new StateAllDisease(disease, totalOfTenWeeks, stateWeekPrediction, Double.parseDouble(yearDataArr[2]), Double.parseDouble(yearDataArr[3]), stateYearPrediction );
					MongoDbUpsert.addDiseaseState(stateName,stateAllDisease);
					stateWiseDataList.add(stateWiseData);
					weekHighLow.put(stateName, stateWeekPrediction);
				}
				
			}
			else if(counter == 3)
			{
				
				double[][] inputYearData = {{1,Double.parseDouble(yearDataArr[4])},{2,Double.parseDouble(yearDataArr[5])}};
				double stateYearPrediction = PredictValue.predictVaules(inputYearData);
				if(prop.getProperty(stateName.replace(" ", "_"))!=null)
				{
					/*stateCounter++;
					System.out.println(disease+"  "+stateName+" "+stateCounter);*/
					String propValues = prop.getProperty(stateName.replace(" ", "_"));
					String parts[] = propValues.split(",");
					
					StateWiseData stateWiseData = new StateWiseData(stateName, totalOfTenWeeks, stateWeekPrediction, Double.parseDouble(yearDataArr[4]), Double.parseDouble(yearDataArr[5]), stateYearPrediction,parts[2].trim(),parts[0],parts[1]);
					StateAllDisease stateAllDisease = new StateAllDisease(disease, totalOfTenWeeks, stateWeekPrediction, Double.parseDouble(yearDataArr[4]), Double.parseDouble(yearDataArr[5]), stateYearPrediction );
					MongoDbUpsert.addDiseaseState(stateName,stateAllDisease);
					stateWiseDataList.add(stateWiseData);
					weekHighLow.put(stateName, stateWeekPrediction);
				}
			}	
		}
		List<DiseaseHighest> highValueStates = new ArrayList<DiseaseHighest>();
		List<DiseaseLowest> lowestValueStates = new ArrayList<DiseaseLowest>();
		
		double highVal = (Collections.max(weekHighLow.values()));
		double lowVal = (Collections.min(weekHighLow.values()));
		//System.out.println(highVal+"    "+lowVal);
		for (Entry<String, Double> entry : weekHighLow.entrySet()) {  
            if (entry.getValue()==highVal) {
                DiseaseHighest highest = new DiseaseHighest(entry.getKey(),highVal);
                highValueStates.add(highest);
                
            }
            if(entry.getValue()==lowVal){
            	DiseaseLowest lowest = new DiseaseLowest(entry.getKey(),lowVal);
            	lowestValueStates.add(lowest);
            }
        }
		
		diseaseData = new DiseaseData(disease, currentYear, previousYear, yearPrediction, stateWiseDataList, highValueStates, lowestValueStates);
		//convert java obj to json format
		Gson gson = new Gson();
		String json = gson.toJson(diseaseData);
		//System.out.println(json);
		MongoDBInsert.insertDataToMongo(json,disease);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
