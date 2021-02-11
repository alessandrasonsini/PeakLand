package logic.model;

import java.util.ArrayList;
import java.util.List;
import logic.model.enums.UserLevel;


public class LoggedUser {
	/**
	 * Classe che modella l'utente loggato dell'applicazione
	 */
	
	private String username;
	private String name;
	private String surname;
	private String description;
	private UserLevel level;
	private Integer peakCoin;
	private List<String> favouritesMountainPath;
	
	public LoggedUser() {
		this.level = UserLevel.SOFAMAN;
		this.favouritesMountainPath = new ArrayList<>();
		this.name = "";
		this.surname = "";
		this.description = "";
		this.username = "";
		this.peakCoin = 0;
	}
	
	public LoggedUser(String username) {
		this();
		this.username = username;
	}
	
	public Integer getPeakCoin() {
		return peakCoin;
	}
	
	public void setPeakCoin(int num) {
		this.peakCoin = num;
	}
	
	public void addPeakCoin() {
		peakCoin++;
		this.levelUp();
	}
	
	private void levelUp() {
		if(this.peakCoin < 6)
			this.level = UserLevel.SOFAMAN;
		else if(this.peakCoin < 16)
			this.level = UserLevel.BOYSCOUT;
		else if(this.peakCoin < 31)
			this.level = UserLevel.EXPLORER;
		else this.level = UserLevel.RANGER;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public List<String> getFavouritesMountainPath() {
		return favouritesMountainPath;
	}
	public void setFavouritesMountainPath(List<String> favouritesMountainPath) {
		this.favouritesMountainPath = favouritesMountainPath;
	}
	public void addToFavourites(String newPath) {
		this.favouritesMountainPath.add(newPath);
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UserLevel getLevel() {
		return level;
	}

	public void setLevel(UserLevel level) {
		this.level = level;
	}

}
