package edu.sjsu.beans;


public class StateWiseData {
	String stateName;
	double weeklyData;
	double stateWeekPrediction;
	double stateCurrentYear;
	double statePreviousYear;
	double stateYearPrediction;
	String id;
	String longitude;
	String latitude;
	
	public StateWiseData(){
		
	}

	public StateWiseData(String stateName, double weeklyData,
			double stateWeekPrediction, double stateCurrentYear,
			double statePreviousYear, double stateYearPrediction, String id,
			String longitude, String latitude) {
		super();
		this.stateName = stateName;
		this.weeklyData = weeklyData;
		this.stateWeekPrediction = stateWeekPrediction;
		this.stateCurrentYear = stateCurrentYear;
		this.statePreviousYear = statePreviousYear;
		this.stateYearPrediction = stateYearPrediction;
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public double getWeeklyData() {
		return weeklyData;
	}

	public void setWeeklyData(double weeklyData) {
		this.weeklyData = weeklyData;
	}

	public double getStateWeekPrediction() {
		return stateWeekPrediction;
	}

	public void setStateWeekPrediction(double stateWeekPrediction) {
		this.stateWeekPrediction = stateWeekPrediction;
	}

	public double getStateCurrentYear() {
		return stateCurrentYear;
	}

	public void setStateCurrentYear(double stateCurrentYear) {
		this.stateCurrentYear = stateCurrentYear;
	}

	public double getStatePreviousYear() {
		return statePreviousYear;
	}

	public void setStatePreviousYear(double statePreviousYear) {
		this.statePreviousYear = statePreviousYear;
	}

	public double getStateYearPrediction() {
		return stateYearPrediction;
	}

	public void setStateYearPrediction(double stateYearPrediction) {
		this.stateYearPrediction = stateYearPrediction;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Override
	public String toString() {
		return "StateWiseData [stateName=" + stateName + ", weeklyData="
				+ weeklyData + ", stateWeekPrediction=" + stateWeekPrediction
				+ ", stateCurrentYear=" + stateCurrentYear
				+ ", statePreviousYear=" + statePreviousYear
				+ ", stateYearPrediction=" + stateYearPrediction + ", id=" + id
				+ ", longitude=" + longitude + ", latitude=" + latitude + "]";
	}

}
