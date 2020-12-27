package logic.model.bean;

import java.io.Serializable;
import java.time.LocalTime;

import logic.model.DifficultyLevelEnum;

public class SimpleMountainPathBean implements Serializable {
	
	private String name;
	private String location;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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
