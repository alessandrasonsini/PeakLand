package logic.bean;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;

import logic.model.exception.WrongInputException;

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
	protected ByteArrayInputStream image;

	public ByteArrayInputStream getImage() {
		return image;
	}

	public void setImage(ByteArrayInputStream image) {
		this.image = image;
	}

	public String getProvince() {
		return province;
	}
	
	public String getProvinceAsText() {
		return convertToText(this.province);
	}

	public void setProvince(String province) {
		this.province = province;
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

	public String getLevel() {
		return level;
	}
	
	public String getLevelAsText() {
		return convertToText(this.level);
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Integer getHours() {
		return hours;
	}
	
	public String getHoursAsText() {
		return convertToText(this.hours);
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}

	public String getRegion() {
		return region;
	}
	
	public String getRegionAsText() {
		return convertToText(this.region);
	}

	public void setRegion(String region) {
		this.region = region;
	}
	
	public void setMinutes(String minutes) throws WrongInputException {
		try {
			Integer min = Integer.parseInt(minutes);
			if(min < 0 || min > 59) throw new WrongInputException();
			this.minutes = min;
		}catch(NumberFormatException e) {
			throw new WrongInputException();
		}
	}
	
	public void setHours(String hours) throws WrongInputException {
		try {
			this.minutes = Integer.parseInt(hours);
		}catch(NumberFormatException e) {
			throw new WrongInputException();
		}
	}
	
	public Integer getMinutes() {
		return minutes;
	}
	
	public String getMinutesAsText() {
		return convertToText(this.minutes);
	}
	
	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}
	
	public String getVoteAsText() {
		return convertToText(getVote());
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
	
	public String getNumberOfVotesAsText() {
		return convertToText(this.getNumberOfVotes());
	}
	
	public String getCity() {
		return city;
	}
	
	public String getCityAsText() {
		return convertToText(this.city);
	}

	public void setCity(String city) {
		this.city = city;
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
