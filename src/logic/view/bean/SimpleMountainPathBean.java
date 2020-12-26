package logic.view.bean;

import java.time.LocalTime;

import logic.model.DifficultyLevelEnum;

public class SimpleMountainPathBean {
	
	String name;
	String location;
	DifficultyLevelEnum level;
	LocalTime travelTime;
	Integer vote;
	Integer numberOfVotes;
	//image
	
	public SimpleMountainPathBean(String name, String location, DifficultyLevelEnum level, LocalTime travelTime, Integer vote, Integer numberOfVotes) {
		this.name = name;
		this.location = location;
		this.level = level;
		this.travelTime = travelTime;
		this.vote = vote;
		this.numberOfVotes = numberOfVotes;
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
