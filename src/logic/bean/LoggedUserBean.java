package logic.bean;

import java.io.ByteArrayInputStream;

public class LoggedUserBean extends ItemBean {
	
	private String name;
	private String surname;
	private String level;
	private ByteArrayInputStream imageStream;
	private String description;
	private Integer peakCoin;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	public void setImageStream(ByteArrayInputStream stream) {
		this.imageStream = stream;
	}
	
	public ByteArrayInputStream getImageStream() {
		return this.imageStream;
	}
	
	public Integer getPeakCoin() {
		return this.peakCoin;
	}
	
	public void setPeakCoin(Integer num) {
		this.peakCoin = num;
	}
	
	
}
