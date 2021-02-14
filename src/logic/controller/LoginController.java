package logic.controller;

import logic.bean.CredentialBean;
import logic.controller.utils.CurrentLoggedUsers;
import logic.model.Credential;
import logic.model.LoggedUser;
import logic.model.dao.CredentialDao;
import logic.model.dao.LoggedUserDao;
import logic.model.enums.PageId;
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
		Credential dBcredential = this.credentialDao.getCredentialFromDb(credential.getUsername());
	
		// Verificare le credenziali
		if(!credential.verifyCredential(dBcredential)) {
			// Le credenziali non corrispondono, comunicare l'errore
			throw new InvalidCredentialException();
		}
		else {
			// Recupera le informazioni dell'utente che si è loggato
			LoggedUser currLoggedUser = this.loggedUserDao.getLoggedUserInfoFromDb(credential.getUsername());
			
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
				loggedUserDao.saveLoggedUserOnDb(currLoggedUser);
				
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
		if(action.equals("init"))
			this.nextPageId = PageId.LOGIN;
		else this.nextPageId = null;
			
	}
}
