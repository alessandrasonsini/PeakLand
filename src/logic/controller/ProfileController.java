package logic.controller;

import java.io.InputStream;
import logic.bean.LoggedUserBean;
import logic.controller.utils.CurrentLoggedUsers;
import logic.model.LoggedUser;
import logic.model.dao.LoggedUserDao;
import logic.model.enums.PageId;
import logic.model.exception.DatabaseException;
import logic.model.exception.SystemException;
import logic.model.utils.LoggedUserConverter;

public class ProfileController extends Controller{

	private LoggedUserDao loggedUserDao;
	private Integer userId;
	private LoggedUser currentUser;
	
	public ProfileController() {
		super();
		this.loggedUserDao = new LoggedUserDao();
	}
	
	// Prende l'utente corrente
	public LoggedUserBean getCurrentUser(Integer id) {
		this.userId = id;
		this.currentUser = CurrentLoggedUsers.getInstance().getCurrentLoggedUser(id);
		LoggedUserBean bean = LoggedUserConverter.getLoggedUserBean(this.currentUser);
		bean.setImageStream(loggedUserDao.getImage(this.currentUser.getUsername()));
		return bean;
	}
	
	public void setProfileImage(InputStream is) throws SystemException {
		//Chiama il metodo del dao per salvare l'immagine inserita
		loggedUserDao.updateUserImage(is, currentUser.getUsername());
	}
	
	
	public void updateUserInfo(LoggedUserBean userBean) throws DatabaseException {
		this.currentUser = LoggedUserConverter.getLoggedUser(userBean);
		this.updateOnDb();
	}
	
	
	private void updateOnDb() throws DatabaseException {
		loggedUserDao.saveLoggedUserOnDb(currentUser);
	}
	
	public void logOut() {
		CurrentLoggedUsers.getInstance().removeCurrentLoggedUser(this.userId);
		setNextPageId("Log out");
	}
	
	@Override
	public void setNextPageId(String action) {
		switch(action) {
			case "init":
				this.nextPageId = PageId.PROFILE;
				break;
			case "Log out":
				this.nextPageId = PageId.LOGIN;
				break;
			default:	
				this.nextPageId = null;
		}
		
	}

}
