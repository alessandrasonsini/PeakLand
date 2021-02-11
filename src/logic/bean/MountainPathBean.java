package logic.bean;

import java.lang.reflect.Field;

import org.apache.commons.lang3.ArrayUtils;

public class MountainPathBean extends SimpleMountainPathBean {
	
	private Integer altitude;
	private String[] landscape;
	private String[] ground;
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
	
	public String[] getLandscape() {
		return landscape;
	}

	public void setLandscape(String[] landscape) {
		this.landscape = landscape;
	}

	public String[] getGround() {
		return ground;
	}

	public void setGround(String[] ground) {
		this.ground = ground;
	}

	public Boolean isCycleble() {
		return cycleble;
	}
	
	public void setCycleble(Boolean cycleble) {
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
	
	public Field[] getFields() {
		return ArrayUtils.addAll(this.getClass().getDeclaredFields(), super.getFields());
	}
	
	public Object getFieldValue(Field f) throws IllegalArgumentException, IllegalAccessException {
		return f.get(this);
	}
}
