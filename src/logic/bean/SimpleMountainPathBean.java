package logic.bean;

import java.lang.reflect.Field;

public class SimpleMountainPathBean extends ItemBean {
	
	protected String name;
	protected String level;
	protected String region;
	protected String province;
	protected String city;
	protected Integer hours;
	protected Integer minutes;
	protected Integer vote;
	protected Integer numberOfVotes;
	protected Integer rankPosition; 
	//private image
	
	public SimpleMountainPathBean() {
	}
	
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

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
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
		if (vote == null)
			return 0;
		else
			return vote;
	}

	public void setVote(Integer vote) {
		this.vote = vote;
	}

	public Integer getNumberOfVotes() {
		if (numberOfVotes == null)
			return 0;
		else
			return numberOfVotes;
	}

	public void setNumberOfVotes(Integer numberOfVotes) {
		this.numberOfVotes = numberOfVotes;
	}
	
	public Integer getRankPosition() {
		return rankPosition;
	}
	
	public void setRankPosition(int i) {
		this.rankPosition = i;
	}
	
	public Field[] getFields() {
		return SimpleMountainPathBean.class.getDeclaredFields();
	}
	
	
}
