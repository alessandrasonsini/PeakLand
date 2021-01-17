package logic.controller;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import logic.model.Credential;
import logic.model.LoggedUser;
import logic.model.bean.CredentialBean;
import logic.model.exception.InvalidCredentialException;
import logic.model.exception.InvalidUsernameException;
import logic.model.exception.WrongInputException;

public class LoginController extends Controller {
	
	private static LoginController instance;
	
	// Mantiene gli id delle sessioni attualmente attive, ovvero gli utenti correntemente loggati
	private static HashMap<Integer, LoggedUser> currentLoggedUsers = new HashMap<> ();
	
	private LoginController() {
		super();
	}
	
	public static LoginController getInstance() {
		if(instance == null) {
			instance = new LoginController();
		}
		return instance;
	}
	
	// Ritorna vero se esiste un'istanza dello user loggato corrente
	public static boolean isLogged(Integer sessionId) {
		boolean check;
		if(sessionId != null) {
			// C'è un'istanza di utente attualmente loggato
			check = currentLoggedUsers.containsKey(sessionId);
		}
		else check = false;
		return check;
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
			return addCurrentLoggedUser(currLoggedUser);
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
				
				return addCurrentLoggedUser(currLoggedUser);
			}
			else {
				// lo username inserito esiste già
				throw new InvalidUsernameException();
			}
			
		}
		else throw new WrongInputException();
	}
	
	public Integer addCurrentLoggedUser(LoggedUser currLoggedUser) {
		Integer newSessionId;
		// Genera una nuova chiave che non esista già nella lista
		do {
			newSessionId = (Integer) ThreadLocalRandom.current().nextInt(1000);
		} while (currentLoggedUsers.containsKey(newSessionId));
		
		// Aggiunge la coppia all'hash map
		currentLoggedUsers.put(newSessionId, currLoggedUser);
		
		// Ritorna la nuova chiave generata
		return newSessionId;
		
	}

	@Override
	public String getNextPageId(String action) {
		String nextPageId;
		switch(action) {
			case "init":
				nextPageId = "Login";
				break;
			default: 
				nextPageId = null;
		}
		return nextPageId;
			
	}
}
