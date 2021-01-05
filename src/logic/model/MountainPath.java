package logic.model;

import java.util.List;

import logic.model.dao.MountainPathDAO;
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
	
	private static MountainPathDAO mountainPathDAO = new MountainPathDAO();
	
	public MountainPath() {
		this.location = new Location();
		this.travelTime = new Time();
	}
	
	public static List<MountainPath> searchMountainPathByName(String name) {
		return mountainPathDAO.searchMountainPathByName(name);
	}
	
	public void saveMountainPathOnDb() {
		mountainPathDAO.saveNewMountainPathOnDB(this);
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
	
	public void setRegion(String region) {
		this.location.setRegion(region);
	}
	
	public void setProvince(String province) {
		this.location.setProvince(province);
	}
	
	public void setCity(String city) {
		this.location.setCity(city);
	}
	
//	public String getRegion() {
//		return this.location.getRegion();
//	}
//	
//	public String getProvince() {
//		return this.location.getProvince();
//	}
//	
//	public String getCity() {
//		return this.location.getCity();
//	}

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


	public List<LandscapeEnum> getLandscape() {
		return landscape;
	}


	public void setLandscape(List<LandscapeEnum> landscape) {
		this.landscape = landscape;
	}


	public List<GroundEnum> getGround() {
		return ground;
	}


	public void setGround(List<GroundEnum> ground) {
		this.ground = ground;
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
	
//	public Integer getHours() {
//		return this.travelTime.getHours();
//	}
//	
//	public Integer getMinutes() {
//		return this.travelTime.getMinutes();
//	}

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
