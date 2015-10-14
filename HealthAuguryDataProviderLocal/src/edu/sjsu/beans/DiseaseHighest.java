package edu.sjsu.beans;

public class DiseaseHighest {
	String stateName;
	double weeklyPrediction;
	
	public DiseaseHighest(){
		
	}

	public DiseaseHighest(String stateName, double weeklyPrediction) {
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
		return "DiseaseHighest [stateName=" + stateName + ", weeklyPrediction="
				+ weeklyPrediction + "]";
	}
	
}
