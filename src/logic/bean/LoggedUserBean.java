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
	
	public String getDescriptionAsText() {
		return convertToText(this.description);
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	
	public String getNameAsText() {
		return convertToText(this.name);
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	
	public String getSurnameAsText() {
		return convertToText(this.surname);
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getLevel() {
		return level;
	}
	
	public String getLevelAsText() {
		return convertToText(this.level);
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
	
	public String getPeakCoinAsText() {
		return convertToText(this.peakCoin);
	}
	
	public void setPeakCoin(Integer num) {
		this.peakCoin = num;
	}
	
	
}
