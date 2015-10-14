package edu.sjsu.utils;
import org.apache.commons.math3.stat.regression.SimpleRegression;


public class PredictValue {
	
	public static double predictVaules(double[][] data)
	{
		SimpleRegression regression = new SimpleRegression();
		regression.addData(data);
		//System.out.println(regression.predict(1.5d));
		return regression.predict(1.5d);
	}
}

