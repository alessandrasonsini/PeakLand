package logic.controller.utils;

import java.security.SecureRandom;
import java.util.HashMap;

import logic.model.LoggedUser;

// Classe che mantiene il riferimento a tutti gli utenti correntemente loggati nell'applicazione
public class CurrentLoggedUsers {
	
	private static CurrentLoggedUsers instance = null;
	private HashMap<Integer, LoggedUser> loggedUsers;
	
	private CurrentLoggedUsers() {
		this.loggedUsers = new HashMap<>();
	}
	
	public static CurrentLoggedUsers getInstance() {
		if(instance == null) {
			instance = new CurrentLoggedUsers();
		}
		return instance;
	}
	
	// Controlla se l'utente è correntemente loggato
	public boolean isCurrentlyLogged(Integer id) {
		return loggedUsers.containsKey(id);
	}
	
	// Aggiunge un nuovo utente correntemente loggato assegnandogli un id univoco
	public Integer addCurrentLoggedUser(LoggedUser currLoggedUser) {
		Integer newSessionId;
		SecureRandom secureRandom = new SecureRandom();
		// Genera una nuova chiave che non esista già nella lista
		do {
			newSessionId = (Integer) secureRandom.nextInt(1000);
		} while (this.loggedUsers.containsKey(newSessionId));
		
		// Aggiunge la coppia all'hash map
		this.loggedUsers.put(newSessionId, currLoggedUser);
		
		// Ritorna la nuova chiave generata
		return newSessionId;
		
	}
	
	public void removeCurrentLoggedUser(Integer id) {
		this.loggedUsers.remove(id);
	}
	
	public LoggedUser getCurrentLoggedUser(Integer id) {
		return this.loggedUsers.get(id);
	}

}
