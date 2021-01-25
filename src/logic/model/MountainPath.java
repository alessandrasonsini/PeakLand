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
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		System.out.println("setName : " + name);
		this.name = name;
	}

	public Integer getAltitude() {
		return altitude;
	}

	public void setAltitude(Integer altitude) {
		System.out.println("setAltitude : " + altitude);
		this.altitude = altitude;
	}

	public Location getLocation() {
		return this.location;
	}
	
	public void setLocation(Location loc) {
		System.out.println("setLoc : " + loc);
		this.location = loc;
	}

	public Integer getLenght() {
		return lenght;
	}


	public void setLenght(Integer lenght) {
		System.out.println("setLenght : " + lenght);
		this.lenght = lenght;
	}

	public DifficultyLevelEnum getLevel() {
		return level;
	}

	public void setLevel(String level) {
		System.out.println("setLevel : " + level);
		this.level = DifficultyLevelEnum.valueOf(level);
	}

	public List<LandscapeEnum> getLandscape() {
		return landscape;
	}

	public void setLandscape(String[] landscape) {
		this.landscape = new ArrayList<LandscapeEnum>();
		for (String str : landscape) {
			System.out.println("setLandscape : " + str);
			this.landscape.add(LandscapeEnum.valueOf(str));
		}
	}

	public List<GroundEnum> getGround() {
		return ground;
	}

	public void setGround(String[] ground) {
		this.ground = new ArrayList<GroundEnum>();
		for (String str : ground) {
			System.out.println("setGround : " + str);
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
		return numberOfVotes;
	}

	public void setNumberOfVotes(Integer numberOfVotes) {
		this.numberOfVotes = numberOfVotes;
	}

	public Integer getVote() {
		return vote;
	}

	public void setVote(Integer vote) {
		this.vote = vote;
	}
}
