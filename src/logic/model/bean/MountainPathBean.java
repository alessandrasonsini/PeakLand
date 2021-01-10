package logic.model.bean;


import java.util.List;

import logic.model.enums.GroundEnum;
import logic.model.enums.LandscapeEnum;

public class MountainPathBean extends SimpleMountainPathBean {
	
	private Integer altitude;
	private List<LandscapeEnum> landscape;
	private List<GroundEnum> ground;
	private Boolean cycleble;
	private Boolean historicalElements;
	private Boolean familySuitable;
	private Integer lenght;
	

	//private image
	
	public Integer getLenght() {
		return lenght;
	}

	public void setLenght(Integer lenght) {
		this.lenght = lenght;
	}
	
	public Integer getAltitude() {
		return altitude;
	}
	
	public void setAltitude(Integer altitude) {
		this.altitude = altitude;
	}
	
	public List<LandscapeEnum> getLandscape() {
		return landscape;
	}

	public void setLandscape(List<LandscapeEnum> landscape) {
		this.landscape = landscape;
	}

	public List<GroundEnum> getGround() {
		return ground;
	}

	public void setGround(List<GroundEnum> ground) {
		this.ground = ground;
	}

	public Boolean isCycleble() {
		return cycleble;
	}
	
	public void setCycle(Boolean cycleble) {
		this.cycleble = cycleble;
	}
	
	public Boolean isHistoricalElements() {
		return historicalElements;
	}
	
	public void setHistoricalElements(Boolean historicalElements) {
		this.historicalElements = historicalElements;
	}
	
	public Boolean isFamilySuitable() {
		return familySuitable;
	}
	
	public void setFamilySuitable(Boolean familySuitable) {
		this.familySuitable = familySuitable;
	}
	
}
