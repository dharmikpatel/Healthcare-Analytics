package edu.sjsu.utils;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CollectWeeklyData {
	public static String diseaseNames[];
	public static Map<String, String[]> yearWise = new HashMap<String, String[]>();
	public static List<String> stateNames = new ArrayList<String>();
	public static String USData;
	
	public static List<Map<String, String[]>> readData(int totalFiles)
	{
		List<Map<String, String[]>> weeklyDataList = new ArrayList<Map<String,String[]>>();
		for(int fileIterator=1;fileIterator<=totalFiles;fileIterator++)
		{
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader("D:\\ProjectSampleData\\week"+fileIterator+".csv"));
				String line = "";
				int counter =0;
				Map<String, String[]> weekWise = new HashMap<String, String[]>();
				while((line = reader.readLine())!=null)
				{
					if(counter==0)
					{
						if(fileIterator==totalFiles)
						{	diseaseNames = line.split(",");	}
					}
					else if(counter==1)
					{
						if(fileIterator==totalFiles)
						{	USData= line;	}
					}
					else
					{
						String stateDataArr[] = line.split(",");
						String[] dataArr = {stateDataArr[1].replaceAll("[^\\dA-Za-z ]", "0").replaceAll("N", "0").replaceAll("U", "0"),
								stateDataArr[6].replaceAll("[^\\dA-Za-z ]", "0").replaceAll("N", "0").replaceAll("U", "0"),
								stateDataArr[11].replaceAll("[^\\dA-Za-z ]", "0").replaceAll("N", "0").replaceAll("U", "0")};
						weekWise.put(stateDataArr[0], dataArr);
						
						if(fileIterator==totalFiles)
						{
							String[] yearDataArr = {stateDataArr[4].replaceAll("[^\\dA-Za-z ]", "0").replaceAll("N", "0").replaceAll("U", "0")
									,stateDataArr[5].replaceAll("[^\\dA-Za-z ]", "0").replaceAll("N", "0").replaceAll("U", "0")
									,stateDataArr[9].replaceAll("[^\\dA-Za-z ]", "0").replaceAll("N", "0").replaceAll("U", "0")
									,stateDataArr[10].replaceAll("[^\\dA-Za-z ]", "0").replaceAll("N", "0").replaceAll("U", "0")
									,stateDataArr[14].replaceAll("[^\\dA-Za-z ]", "0").replaceAll("N", "0").replaceAll("U", "0")
									,stateDataArr[15].replaceAll("[^\\dA-Za-z ]", "0").replaceAll("N", "0").replaceAll("U", "0") };
							yearWise.put(stateDataArr[0], yearDataArr);
							stateNames.add(stateDataArr[0]);
						}
					}
					counter++;
				}
				weeklyDataList.add(weekWise);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return weeklyDataList;
	}
}
