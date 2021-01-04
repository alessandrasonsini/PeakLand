package logic.model.bean;


import java.time.LocalTime;
import java.util.List;

import logic.model.enums.DifficultyLevelEnum;
import logic.model.enums.GroundEnum;
import logic.model.enums.LandscapeEnum;

public class MountainPathBean extends ItemBean {
	
	private String name;
	private Integer altitude;
	private String locationRegion;		//nell'MVC abbiamo messo una sola stringa
	private String locationCity;		//ma mi sembrava più completo metterci anche la regione (eventualmente cambiare anche in SimpleMountainPathBean)
	private Integer lenght;
	private DifficultyLevelEnum level;
	private List<LandscapeEnum> landscape;
	private List<GroundEnum> ground;
	private Boolean cycleble;
	private Boolean historicalElements;
	private Boolean familySuitable;
	private LocalTime travelTime;
	//private image
	
	public MountainPathBean() {
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getAltitude() {
		return altitude;
	}
	
	public void setAltitude(Integer altitude) {
		this.altitude = altitude;
	}
	
	public String getLocationRegion() {
		return locationRegion;
	}
	
	public void setLocationRegion(String locationRegion) {
		this.locationRegion = locationRegion;
	}
	
	public String getLocationCity() {
		return locationCity;
	}
	
	public void setLocationCity(String locationCity) {
		this.locationCity = locationCity;
	}
	
	public Integer getLenght() {
		return lenght;
	}
	
	public void setLenght(Integer lenght) {
		this.lenght = lenght;
	}
	
	public DifficultyLevelEnum getLevel() {
		return level;
	}
	
	public void setLevel(DifficultyLevelEnum level) {
		this.level = level;
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
	
	public LocalTime getTravelTime() {
		return travelTime;
	}
	
	public void setTravelTime(LocalTime travelTime) {
		this.travelTime = travelTime;
	}
}
