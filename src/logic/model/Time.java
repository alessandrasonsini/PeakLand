package logic.model;

import java.io.Serializable;

public class Time implements Serializable {
	
	Integer hours;
	Integer minutes;
	
	public Time(Integer hours, Integer minutes) {
		super();
		this.hours = hours;
		this.minutes = minutes;
	}
	
	public Time() {
		
	}
	
	public Integer getHours() {
		return hours;
	}
	
	public void setHours(Integer hours) {
		this.hours = hours;
	}
	
	public Integer getMinutes() {
		return minutes;
	}
	
	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}
}
