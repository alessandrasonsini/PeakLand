package logic.model.bean;

import logic.model.exception.EmptyMandatoryFieldsException;

public class CredentialBean {
	
	private String username;
	private String password;
	private String confirmPassword;
	
	public void setCredential(String username, String password, String confirmPassword) throws EmptyMandatoryFieldsException{
		if(confirmPassword.isEmpty()) {
			throw new EmptyMandatoryFieldsException();
		}
		else {
			setConfirmPassword(confirmPassword);
			setCredential(username, password);
		}
		
	}
	
	public void setCredential(String username, String password) throws EmptyMandatoryFieldsException{
		if(username.isEmpty() || password.isEmpty())
			throw new EmptyMandatoryFieldsException();
		else {
			setPassword(password);
			setUsername(username);
		}
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	

}
