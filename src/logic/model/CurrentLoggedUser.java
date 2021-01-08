package logic.model;

import logic.model.exception.CurrentUserNotFindException;

public class CurrentLoggedUser{
	
	// Classe che mantiene l'istanza dell'utente corrente che sta utilizzando l'applicazione
	
	private static CurrentLoggedUser instance = null;
	private static LoggedUser currentLoggedUser;
	
	private CurrentLoggedUser(LoggedUser user) {
		currentLoggedUser = user;
		
	}
	
	public static CurrentLoggedUser getInstance(LoggedUser user) throws CurrentUserNotFindException{
		if(user == null) {
			if(instance == null) {
				throw new CurrentUserNotFindException("Current logged user instance not found");
			}
		}
		else {
			if(instance == null) {
				instance = new CurrentLoggedUser(user);
			}
		}	
		return instance;
	}
	
	public static void resetInstance() {
		instance = null;
	}
	
	public static CurrentLoggedUser getInstance() throws CurrentUserNotFindException {
		return getInstance(null);
	}
	
	public static LoggedUser getCurrentUser() {
		return currentLoggedUser;
	}
	
	
	

}
