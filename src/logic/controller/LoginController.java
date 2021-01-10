package logic.controller;

import logic.model.Credential;
import logic.model.LoggedUser;
import logic.model.bean.CredentialBean;
import logic.model.exception.InvalidCredentialException;
import logic.model.exception.InvalidUsernameException;
import logic.model.exception.WrongInputException;

public class LoginController extends Controller {

	// Ritorna vero se esiste un'istanza dello user loggato corrente
	public static boolean isLogged() {
		boolean check;
		if(LoggedUser.getCurrentLoggedUser() != null) {
			// C'è un'istanza di utente attualmente loggato
			check = true;
		}
		else check = false;
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
			
			// Setta l'istanza dell'utente corrente
			LoggedUser.setCurrentLoggedUser(currLoggedUser);

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
				
				LoggedUser.setCurrentLoggedUser(currLoggedUser);
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
