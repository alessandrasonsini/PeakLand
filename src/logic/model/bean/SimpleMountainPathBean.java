package logic.model.bean;

import java.io.Serializable;
import java.time.LocalTime;

import logic.model.enums.DifficultyLevelEnum;

public class SimpleMountainPathBean implements Serializable {
	
	private String name;
	private String locationRegion;
	private String locationCity;
	private DifficultyLevelEnum level;
	private LocalTime travelTime;
	private Integer vote;
	private Integer numberOfVotes;
	//private image

	public SimpleMountainPathBean() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public DifficultyLevelEnum getLevel() {
		return level;
	}

	public void setLevel(DifficultyLevelEnum level) {
		this.level = level;
	}

	public LocalTime getTravelTime() {
		return travelTime;
	}

	public void setTravelTime(LocalTime travelTime) {
		this.travelTime = travelTime;
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
}
