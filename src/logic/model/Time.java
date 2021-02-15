package logic.model;


public class Time{
	
	private Integer hours;
	private Integer minutes;
	
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
