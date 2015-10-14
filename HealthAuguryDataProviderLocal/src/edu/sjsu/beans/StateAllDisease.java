package edu.sjsu.beans;

public class StateAllDisease {
	String diseaseName;
	double totalWeekly;
	double weeklyPrediction;
	double currentYear;
	double previousyear;
	double yearlyPrediction;
	
	public StateAllDisease(){
		
	}

	public StateAllDisease(String diseaseName, double totalWeekly,
			double weeklyPrediction, double currentYear, double previousyear,
			double yearlyPrediction) {
		super();
		this.diseaseName = diseaseName;
		this.totalWeekly = totalWeekly;
		this.weeklyPrediction = weeklyPrediction;
		this.currentYear = currentYear;
		this.previousyear = previousyear;
		this.yearlyPrediction = yearlyPrediction;
	}

	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	public double getTotalWeekly() {
		return totalWeekly;
	}

	public void setTotalWeekly(double totalWeekly) {
		this.totalWeekly = totalWeekly;
	}

	public double getWeeklyPrediction() {
		return weeklyPrediction;
	}

	public void setWeeklyPrediction(double weeklyPrediction) {
		this.weeklyPrediction = weeklyPrediction;
	}

	public double getCurrentYear() {
		return currentYear;
	}

	public void setCurrentYear(double currentYear) {
		this.currentYear = currentYear;
	}

	public double getPreviousyear() {
		return previousyear;
	}

	public void setPreviousyear(double previousyear) {
		this.previousyear = previousyear;
	}

	public double getYearlyPrediction() {
		return yearlyPrediction;
	}

	public void setYearlyPrediction(double yearlyPrediction) {
		this.yearlyPrediction = yearlyPrediction;
	}

	@Override
	public String toString() {
		return "StateAllDisease [diseaseName=" + diseaseName + ", totalWeekly="
				+ totalWeekly + ", weeklyPrediction=" + weeklyPrediction
				+ ", currentYear=" + currentYear + ", previousyear="
				+ previousyear + ", yearlyPrediction=" + yearlyPrediction + "]";
	}
	
}
