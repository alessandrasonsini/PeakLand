package logic.model;

import java.time.LocalTime;
import java.util.List;

import logic.model.dao.MountainPathDAO;
import logic.model.enums.DifficultyLevelEnum;
import logic.model.enums.GroundEnum;
import logic.model.enums.LandscapeEnum;

public class MountainPath {
	private String name;
	private Integer altitude;
	private String locationRegion;
	private String locationCity;
	private Integer lenght;
	private DifficultyLevelEnum level;
	private List<LandscapeEnum> landscape;
	private List<GroundEnum> ground;
	private Boolean cycle;
	private Boolean historicalElements;
	private Boolean familySuitable;
	private LocalTime travelTime;
	// private image
	private Integer numberOfVotes;
	private Integer vote;
	
	private static MountainPathDAO mountainPathDAO;
	
	public MountainPath() {
	}
	
	public static List<MountainPath> searchMountainPathByName(String name) {
		mountainPathDAO = new MountainPathDAO();
		return mountainPathDAO.searchMountainPathByName(name);
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


	public Boolean getCycle() {
		return cycle;
	}


	public void setCycle(Boolean cycle) {
		this.cycle = cycle;
	}


	public Boolean getHistoricalElements() {
		return historicalElements;
	}


	public void setHistoricalElements(Boolean historicalElements) {
		this.historicalElements = historicalElements;
	}


	public Boolean getFamilySuitable() {
		return familySuitable;
	}


	public void setFamilySuitable(Boolean familySuitable) {
		this.familySuitable = familySuitable;
	}

	public LocalTime getTravelTime() {
		return travelTime;
	}


	public void setTravelTime(LocalTime travelTime) {
		this.travelTime = travelTime;
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
