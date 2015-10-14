package edu.sjsu.beans;

public class DiseaseLowest {
	String stateName;
	double weeklyPrediction;
	
	public DiseaseLowest(){
		
	}

	public DiseaseLowest(String stateName, double weeklyPrediction) {
		super();
		this.stateName = stateName;
		this.weeklyPrediction = weeklyPrediction;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public double getWeeklyPrediction() {
		return weeklyPrediction;
	}

	public void setWeeklyPrediction(double weeklyPrediction) {
		this.weeklyPrediction = weeklyPrediction;
	}

	@Override
	public String toString() {
		return "DiseaseLowest [stateName=" + stateName + ", weeklyPrediction="
				+ weeklyPrediction + "]";
	}
	
}
