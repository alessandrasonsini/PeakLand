package logic.view.bean;

import java.time.LocalTime;
import java.util.List;

import logic.model.DifficultyLevelEnum;
import logic.model.GroundEnum;
import logic.model.LandscapeEnum;

public class MountainPathBean {
	
	String name;
	Integer altitude;
	String locationRegion;		//nell'MVC abbiamo messo una sola stringa
	String locationCity;		//ma mi sembrava più completo metterci anche la regione (eventualmente cambiare anche in SimpleMountainPathBean)
	Integer lenght;
	DifficultyLevelEnum level;
	List<LandscapeEnum> landscape;
	List<GroundEnum> ground;
	Boolean cycle;
	Boolean historicalElements;
	Boolean familySuitable;
	LocalTime travelTime;
	//image
	

	//non so se inserire costruttore che prenda parametri (verrebbe lunghissimo) 
	//o inserirli tramite setter
	
	
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

	public Boolean getCycle() {
		return cycle;
	}
	
	public void setCycle(Boolean cycle) {
		this.cycle = cycle;
	}
	
	public Boolean getHistoricalElements() {
		return historicalElements;
	}
	
	public void setHistoricalElements(Boolean historicalElements) {
		this.historicalElements = historicalElements;
	}
	
	public Boolean getFamilySuitable() {
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
