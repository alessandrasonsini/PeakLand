package logic.model;

import logic.model.exception.EmptyMandatoryFieldsException;

public class Credential {
	private String username;
	private String password;
	
	public Credential() {
	}
	
	public Credential(String username, String password) {
		this.password = password;
		this.username = username;
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
	
	public boolean verifyCredential(Credential credential) throws EmptyMandatoryFieldsException {
		Boolean verified;
		try {
			// Controlla se lo username esiste
			if(credential != null) {
				// Controlla se la password corrisponde
				if(this.password.equals(credential.getPassword()))
					verified = true;
				else verified = false;
			}
			else verified = false;
			
			return verified;
		} catch (Exception e) {
			throw new EmptyMandatoryFieldsException();
		}
	}
	
}
