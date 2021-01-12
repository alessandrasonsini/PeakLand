package logic.model;

import java.util.logging.Level;
import java.util.logging.Logger;

import logic.model.dao.CredentialDao;
import logic.model.exception.DatabaseException;

// NON VEDO TANTO IL SENSO DI STA CLASSE, MI SERVIVA PIU' CHE ALTRO PER CREARE LA DAO ASSOCIATA
// SE CAPIAMO CHE SI PUO' CREARE LA DAO ANCHE SENZA ENTITA' ASSOCIATA POSSIAMO TOGLIERLA

public class Credential {
	private String username;
	private String password;
	
	private static CredentialDao credentialDao = new CredentialDao();
	
	private static final Logger LOGGER = Logger.getLogger(Credential.class.getName());
	
	public Credential() {
		
	}
	
	public Credential(String username, String password) {
		this.password = password;
		this.username = username;
	}
	
	public boolean verifyCredential() {
		Boolean verified;
		Credential credential = getCredential(this.username);
		// Controlla se lo username esiste
		if(credential != null) {
			// Controlla se la password corrisponde
			if(this.password.equals(credential.getPassword()))
				verified = true;
			else verified = false;
		}
		else verified = false;
		
		return verified;
	}
	
	public static Credential getCredential(String username) {
		// Recupera dal db le credenziali associate allo username
		return credentialDao.getCredentialFromDb(username);
	}
	
	public void saveNewCredential() {
		//PROPAGARE
		try {
			credentialDao.saveNewCredentialOnDb(this);
		} catch (DatabaseException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
		}
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
