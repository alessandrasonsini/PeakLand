package logic.model.bean;

import java.lang.reflect.Field;

import logic.model.enums.DifficultyLevelEnum;

public class SimpleMountainPathBean extends ItemBean {
	
	private String name;
	private DifficultyLevelEnum level;
	private String region;
	private String province;
	private String city;
	private Integer hours;
	private Integer minutes;
	private Integer vote;
	private Integer numberOfVotes;
	//private image

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DifficultyLevelEnum getLevel() {
		return level;
	}

	public void setLevel(DifficultyLevelEnum level) {
		this.level = level;
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

	public Integer getVote() {
		return vote;
	}

	public void setVote(Integer vote) {
		this.vote = vote;
	}

	public Integer getNumberOfVotes() {
		return numberOfVotes;
	}

	public void setNumberOfVotes(Integer numberOfVotes) {
		this.numberOfVotes = numberOfVotes;
	}
	
	public Field[] getFields() {
		return SimpleMountainPathBean.class.getDeclaredFields();
	}
}
