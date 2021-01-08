package logic.controller;

import logic.model.Credential;
import logic.model.CurrentLoggedUser;
import logic.model.LoggedUser;
import logic.model.bean.CredentialBean;
import logic.model.exception.CurrentUserNotFindException;
import logic.model.exception.InvalidCredentialException;
import logic.model.exception.InvalidUsernameException;
import logic.model.exception.WrongInputException;

public class LoginController extends Controller {

	// Ritorna vero se esiste un'istanza dello user loggato corrente
	public static boolean isLogged() {
		boolean check;
		try {
			CurrentLoggedUser.getInstance();
			check = true;
		}
		catch (CurrentUserNotFindException e) {
			// l'utente non è loggato
			check = false;
		}
		return check;
	} 
		
	public void loginAction(CredentialBean credentialBean) throws WrongInputException {
		Credential credential = new Credential(credentialBean.getUsername(),credentialBean.getPassword());
		// Verificare le credenziali
		if(!credential.verifyCredential()) {
			// Le credenziali non corrispondono, comunicare l'errore
			throw new InvalidCredentialException();
		}
		else {
			// Recupera le informazioni dell'utente che si è loggato
			LoggedUser currLoggedUser = LoggedUser.getLoggedUserInfo(credential.getUsername());
			
			try {
				// Setta l'istanza dell'utente corrente
				CurrentLoggedUser.getInstance(currLoggedUser);
				
			}catch(CurrentUserNotFindException e) {}

		}
	}
	
	public void signInAction(CredentialBean credentialBean) throws WrongInputException {
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
				try {
					CurrentLoggedUser.getInstance(currLoggedUser);
					
				}catch(CurrentUserNotFindException e) {}
			}
			else {
				// lo username inserito esiste già
				throw new InvalidUsernameException();
			}
			
		}
		else throw new WrongInputException();
	}
	
	@Override
	public void setup() {
		setNextStepId("Login");
		
	}

}
