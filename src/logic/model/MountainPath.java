package logic.model;

import java.util.ArrayList;
import java.util.List;
import logic.model.enums.DifficultyLevelEnum;
import logic.model.enums.GroundEnum;
import logic.model.enums.LandscapeEnum;

public class MountainPath {
	private String name;
	private Integer altitude;
	private Location location;
	private Integer lenght;
	private DifficultyLevelEnum level;
	private List<LandscapeEnum> landscape;
	private List<GroundEnum> ground;
	private Boolean cycleble;
	private Boolean historicalElements;
	private Boolean familySuitable;
	private Time travelTime; 
	// private image
	private Integer numberOfVotes;
	private Integer vote;
	
	public MountainPath() {
		this.location = new Location();
		this.travelTime = new Time();
		this.landscape = new ArrayList<>();
		this.ground = new ArrayList<>();
		this.numberOfVotes = 0;
		this.vote = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAltitude() {
		return altitude;
	}

	public void setAltitude(Integer altitude) {
		this.altitude = altitude;
	}

	public Location getLocation() {
		return this.location;
	}
	
	public void setLocation(Location loc) {
		this.location = loc;
	}

	public Integer getLenght() {
		return lenght;
	}

	public void setLenght(Integer lenght) {
		this.lenght = lenght;
	}

	public DifficultyLevelEnum getLevel() {
		return level;
	}

	public void setLevel(DifficultyLevelEnum level) {
		this.level = level;
	}
	
	public void setLevelFromString(String level) {
		this.level = DifficultyLevelEnum.valueOf(level);
	}
	
	public List<LandscapeEnum> getLandscape() {
		return landscape;
	}
	
	public void setLandscape(List<LandscapeEnum> landscape) {
		this.landscape = landscape;
	}
	
	public void setLandscapeFromString(String[] landscape) {
		for (String str : landscape) {
			this.landscape.add(LandscapeEnum.valueOf(str));
		}
	}
	
	public List<GroundEnum> getGround() {
		return ground;
	}

	public void setGround(List<GroundEnum> ground) {
		this.ground = ground;
	}
	
	public void setGroundFromString(String[] ground) {
		for (String str : ground) {
			this.ground.add(GroundEnum.valueOf(str));
		}
	}
	
	public Boolean isCycleble() {
		return cycleble;
	}

	public void setCycleble(Boolean cycle) {
		this.cycleble = cycle;
	}

	public Boolean isHistoricalElements() {
		return historicalElements;
	}

	public void setHistoricalElements(Boolean historicalElements) {
		this.historicalElements = historicalElements;
	}

	public Boolean isFamilySuitable() {
		return familySuitable;
	}

	public void setFamilySuitable(Boolean familySuitable) {
		this.familySuitable = familySuitable;
	}

	public Time getTravelTime() {
		return travelTime;
	}

	public void setTravelTime(Time travelTime) {
		this.travelTime = travelTime;
	}
	
	public void setHours(Integer hours) {
		this.travelTime.setHours(hours);
	}
	
	public void setMinutes(Integer minutes) {
		this.travelTime.setMinutes(minutes);
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

	public Integer getVote() {
		if (vote == null)
			return 0;
		else
			return vote;
	}

	public void setVote(Integer vote) {
		this.vote = vote;
	}
	
	public void updateVote(Integer newVote) {
		this.numberOfVotes++;
		this.vote = (this.vote*(this.numberOfVotes - 1) + newVote) / this.numberOfVotes;
	}
}
