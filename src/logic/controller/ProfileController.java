package logic.controller;

import java.io.InputStream;
import logic.controller.utils.CurrentLoggedUsers;
import logic.model.LoggedUser;
import logic.model.bean.LoggedUserBean;
import logic.model.bean.factory.LoggedUserBeanFactory;
import logic.model.dao.LoggedUserDao;
import logic.model.exception.DatabaseException;
import logic.model.exception.SystemException;

public class ProfileController extends Controller{

	private static final String directory = "Logged user/";
	
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
		LoggedUserBean bean = new LoggedUserBeanFactory().getLoggedUserBean(this.currentUser);
		bean.setImageStream(loggedUserDao.getImage(directory + this.currentUser.getUsername() + ".jpeg"));
		return bean;
	}
	
	public void setProfileImage(InputStream is) throws SystemException {
		//Chiama il metodo del dao per salvare l'immagine inserita
		loggedUserDao.updateUserImage(is, directory + currentUser.getUsername() +".jpeg");
	}
	
	public void updateUserInfo(LoggedUserBean userBean) throws DatabaseException {
		this.currentUser.setName(userBean.getName());
		this.currentUser.setSurname(userBean.getSurname());
		this.currentUser.setDescription(userBean.getDescription());
		
		this.updateOnDb();
	}
	
	
	private void updateOnDb() throws DatabaseException {
		loggedUserDao.saveNewLoggedUserOnDb(currentUser);
	}
	
	public void logOut() {
		CurrentLoggedUsers.getInstance().removeCurrentLoggedUser(this.userId);
		setNextPageId("Log out");
	}
	
	@Override
	public void setNextPageId(String action) {
		switch(action) {
			case "init":
				this.nextPageId = "Profile";
				break;
			case "Log out":
				this.nextPageId = "Login";
				break;
			default:	
				this.nextPageId = null;
		}
		
	}

}
