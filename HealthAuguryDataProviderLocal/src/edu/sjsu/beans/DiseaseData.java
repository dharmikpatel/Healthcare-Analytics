package edu.sjsu.beans;
import java.util.List;


public class DiseaseData {
	String DiseaseName;
	double currentYear;
	double previousYear;
	double nextYear;
	List<StateWiseData> stateWiseData;
	List<DiseaseHighest> diseaseHighest;
	List<DiseaseLowest> diseaseLowest;
	
	public DiseaseData(){
		
	}

	public DiseaseData(String diseaseName, double currentYear,
			double previousYear, double nextYear,
			List<StateWiseData> stateWiseData,
			List<DiseaseHighest> diseaseHighest,
			List<DiseaseLowest> diseaseLowest) {
		super();
		DiseaseName = diseaseName;
		this.currentYear = currentYear;
		this.previousYear = previousYear;
		this.nextYear = nextYear;
		this.stateWiseData = stateWiseData;
		this.diseaseHighest = diseaseHighest;
		this.diseaseLowest = diseaseLowest;
	}

	public String getDiseaseName() {
		return DiseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		DiseaseName = diseaseName;
	}

	public double getCurrentYear() {
		return currentYear;
	}

	public void setCurrentYear(double currentYear) {
		this.currentYear = currentYear;
	}

	public double getPreviousYear() {
		return previousYear;
	}

	public void setPreviousYear(double previousYear) {
		this.previousYear = previousYear;
	}

	public double getNextYear() {
		return nextYear;
	}

	public void setNextYear(double nextYear) {
		this.nextYear = nextYear;
	}

	public List<StateWiseData> getStateWiseData() {
		return stateWiseData;
	}

	public void setStateWiseData(List<StateWiseData> stateWiseData) {
		this.stateWiseData = stateWiseData;
	}

	public List<DiseaseHighest> getDiseaseHighest() {
		return diseaseHighest;
	}

	public void setDiseaseHighest(List<DiseaseHighest> diseaseHighest) {
		this.diseaseHighest = diseaseHighest;
	}

	public List<DiseaseLowest> getDiseaseLowest() {
		return diseaseLowest;
	}

	public void setDiseaseLowest(List<DiseaseLowest> diseaseLowest) {
		this.diseaseLowest = diseaseLowest;
	}

	@Override
	public String toString() {
		return "DiseaseData [DiseaseName=" + DiseaseName + ", currentYear="
				+ currentYear + ", previousYear=" + previousYear
				+ ", nextYear=" + nextYear + ", stateWiseData=" + stateWiseData
				+ ", diseaseHighest=" + diseaseHighest + ", diseaseLowest="
				+ diseaseLowest + "]";
	}

}
