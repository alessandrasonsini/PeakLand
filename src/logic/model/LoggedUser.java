package logic.model;

import java.util.ArrayList;
import java.util.List;

import logic.model.dao.LoggedUserDao;
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
	private List<String> favouritesMountainPath;
	
	private static LoggedUserDao loggedUserDao = new LoggedUserDao();
	
	// Istanza dell'utente correntemente loggato
	private static LoggedUser currentLoggedUser;
	
	public LoggedUser() {
		this.level = UserLevel.SOFAMAN;
		this.favouritesMountainPath = new ArrayList<>();
	}
	
	public LoggedUser(String username) {
		this();
		this.username = username;
	}
	
	public static LoggedUser getCurrentLoggedUser() {
		return currentLoggedUser;
	}
	
	public static void setCurrentLoggedUser(LoggedUser user) {
		currentLoggedUser = user;
	}
	
	public static LoggedUser getLoggedUserInfo(String id) {
		return loggedUserDao.getLoggedUserInfoFromDb(id);
	}
	
	public void saveLoggedUserOnDb() {
		loggedUserDao.saveNewLoggedUserOnDb(this);
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