package logic.bean;

import java.lang.reflect.Field;

import org.apache.commons.lang3.ArrayUtils;

import logic.model.exception.WrongInputException;

public class MountainPathBean extends SimpleMountainPathBean {
	
	private Integer altitude;
	private String[] landscape;
	private String[] ground;
	private Boolean cycleble;
	private Boolean historicalElements;
	private Boolean familySuitable;
	private Integer lenght;
	
	public Integer getLenght() {
		return lenght;
	}
	
	public String getLenghtAsText() {
		return convertToTextWithUnitOfMeasure(this.lenght, "km");
	}

	public void setLenght(Integer lenght) {
		this.lenght = lenght;
	}
	
	public void setLenght(String lenght) throws WrongInputException {
		try {
			this.lenght = Integer.parseInt(lenght);
		}catch(NumberFormatException e) {
			throw new WrongInputException();
		}
	}
	
	public void setAltitude(String altitude) throws WrongInputException {
		try {
			this.altitude = Integer.parseInt(altitude);
		}catch(NumberFormatException e) {
			throw new WrongInputException();
		}
	}
	
	public Integer getAltitude() {
		return altitude;
	}
	
	public String getAltitudeAsText() {
		return convertToTextWithUnitOfMeasure(this.altitude, "m");
	}
	
	public void setAltitude(Integer altitude) {
		this.altitude = altitude;
	}
	
	public String[] getLandscape() {
		return landscape;
	}
	
	public String getLandscapeAsText() {
		return convertToText(this.landscape);
	}

	public void setLandscape(String[] landscape) {
		this.landscape = landscape;
	}

	public String[] getGround() {
		return ground;
	}
	
	public String getGroundAsText() {
		return convertToText(this.ground);
	}

	public void setGround(String[] ground) {
		this.ground = ground;
	}

	public Boolean isCycleble() {
		return cycleble;
	}
	
	public String getCyclableAsText() {
		return convertToText(this.cycleble);
	}
	
	public void setCycleble(Boolean cycleble) {
		this.cycleble = cycleble;
	}
	
	public Boolean isHistoricalElements() {
		return historicalElements;
	}
	
	public String getHistoricalElementsAsText() {
		return convertToText(this.historicalElements);
	}
	
	public void setHistoricalElements(Boolean historicalElements) {
		this.historicalElements = historicalElements;
	}
	
	public Boolean isFamilySuitable() {
		return familySuitable;
	}
	
	public String getFamilySuitableAsText() {
		return convertToText(this.familySuitable);
	}
	
	public void setFamilySuitable(Boolean familySuitable) {
		this.familySuitable = familySuitable;
	}
	
	public Field[] getFields() {
		return ArrayUtils.addAll(this.getClass().getDeclaredFields(), super.getFields());
	}
	
	public Object getFieldValue(Field f) throws IllegalArgumentException, IllegalAccessException {
		return f.get(this);
	}
}
