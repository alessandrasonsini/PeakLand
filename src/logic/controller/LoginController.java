package logic.controller;

import logic.controller.utils.CurrentLoggedUsers;
import logic.model.Credential;
import logic.model.LoggedUser;
import logic.model.bean.CredentialBean;
import logic.model.exception.InvalidCredentialException;
import logic.model.exception.InvalidUsernameException;
import logic.model.exception.WrongInputException;

public class LoginController extends Controller {
	
	public LoginController() {
		super();
	}
	
	// Ritorna vero se esiste un'istanza dello user loggato corrente
	public static boolean isLogged(Integer sessionId) {
		return CurrentLoggedUsers.getInstance().isCurrentlyLogged(sessionId);
	} 
		
	public Integer loginAction(CredentialBean credentialBean) throws WrongInputException {
		Credential credential = new Credential(credentialBean.getUsername(),credentialBean.getPassword());
		// Verificare le credenziali
		if(!credential.verifyCredential()) {
			// Le credenziali non corrispondono, comunicare l'errore
			throw new InvalidCredentialException();
		}
		else {
			// Recupera le informazioni dell'utente che si è loggato
			LoggedUser currLoggedUser = LoggedUser.getLoggedUserInfo(credential.getUsername());
			
			// Aggiunge la coppia sessionId - LoggedUser alla lista degli utenti correntemente loggati
			return CurrentLoggedUsers.getInstance().addCurrentLoggedUser(currLoggedUser);
		}
	}
	
	public Integer signInAction(CredentialBean credentialBean) throws WrongInputException {
		Credential credential = new Credential(credentialBean.getUsername(),credentialBean.getPassword());
		// Verifica se la password coincide con la password di conferma
		if(credentialBean.getConfirmPassword().equals(credential.getPassword())) {
			// Controllare che lo username esiste già
			if(Credential.getCredential(credential.getUsername()) == null) {
				// salvo le nuove credenziali 
				credential.saveNewCredential();
				
				// Setta l'istanza del nuovo utente creato
				LoggedUser currLoggedUser = new LoggedUser(credential.getUsername());
				currLoggedUser.saveLoggedUserOnDb();
				
				return CurrentLoggedUsers.getInstance().addCurrentLoggedUser(currLoggedUser);
			}
			else {
				// lo username inserito esiste già
				throw new InvalidUsernameException();
			}
		}
		else throw new WrongInputException();
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
