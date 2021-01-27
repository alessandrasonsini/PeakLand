package logic.controller;

import logic.controller.utils.CurrentLoggedUsers;
import logic.model.Credential;
import logic.model.LoggedUser;
import logic.model.bean.CredentialBean;
import logic.model.dao.CredentialDao;
import logic.model.dao.LoggedUserDao;
import logic.model.exception.DatabaseException;
import logic.model.exception.InvalidCredentialException;
import logic.model.exception.InvalidUsernameException;
import logic.model.exception.WrongInputException;

public class LoginController extends Controller {
	
	private CredentialDao credentialDao;
	private LoggedUserDao loggedUserDao;
	
	public LoginController() {
		super();
		this.credentialDao = new CredentialDao();
		this.loggedUserDao = new LoggedUserDao();
	}
	
	// Ritorna vero se esiste un'istanza dello user loggato corrente
	public static boolean isLogged(Integer sessionId) {
		return CurrentLoggedUsers.getInstance().isCurrentlyLogged(sessionId);
	} 
		
	public Integer loginAction(CredentialBean credentialBean) throws WrongInputException {
		Credential credential = new Credential(credentialBean.getUsername(),credentialBean.getPassword());
		// Verificare le credenziali
		if(!verifyCredential(credential)) {
			// Le credenziali non corrispondono, comunicare l'errore
			throw new InvalidCredentialException();
		}
		else {
			// Recupera le informazioni dell'utente che si è loggato
			LoggedUser currLoggedUser = loggedUserDao.getLoggedUserInfoFromDb(credential.getUsername());
			
			// Aggiunge la coppia sessionId - LoggedUser alla lista degli utenti correntemente loggati
			return CurrentLoggedUsers.getInstance().addCurrentLoggedUser(currLoggedUser);
		}
	}
	
	public Integer signInAction(CredentialBean credentialBean) throws WrongInputException, DatabaseException {
		Credential credential = new Credential(credentialBean.getUsername(),credentialBean.getPassword());
		// Verifica se la password coincide con la password di conferma
		if(credentialBean.getConfirmPassword().equals(credential.getPassword())) {
			// Controllare che lo username esiste già
			if(credentialDao.getCredentialFromDb(credential.getUsername()) == null) {
				// salvo le nuove credenziali 
				credentialDao.saveNewCredentialOnDb(credential);
				
				// Setta l'istanza del nuovo utente creato
				LoggedUser currLoggedUser = new LoggedUser(credential.getUsername());
				loggedUserDao.saveNewLoggedUserOnDb(currLoggedUser);
				
				return CurrentLoggedUsers.getInstance().addCurrentLoggedUser(currLoggedUser);
			}
			else {
				// lo username inserito esiste già
				throw new InvalidUsernameException();
			}
		}
		else throw new WrongInputException();
	}
	
	// Controlla che le credenziali inserite siano corrette recuperando dal db tramite la dao le credenziali associate allo username
	public boolean verifyCredential(Credential insertedCred) {
		Boolean verified;
		Credential credential = credentialDao.getCredentialFromDb(insertedCred.getUsername());
		// Controlla se lo username esiste
		if(credential != null) {
			// Controlla se la password corrisponde
			if(insertedCred.getPassword().equals(credential.getPassword()))
				verified = true;
			else verified = false;
		}
		else verified = false;
		
		return verified;
	}
	
	@Override
	public void setNextPageId(String action) {
		String nextPageId;
		switch(action) {
			case "init":
				nextPageId = "Login";
				break;
			default: 
				nextPageId = null;
		}
		this.nextPageId = nextPageId;
			
	}
}
