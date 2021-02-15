package logic.controller;

import logic.model.enums.PageId;
import logic.model.exception.SystemException;

public class ControllerFactory {
	
	// Ritorna il controller relativo al tipo richiesto
	public Controller getController(PageId type) throws SystemException	 {
		Controller newController;
		switch(type) {
			case VIEW_INFO: 
				newController = new ViewMountainPathInfoController();
				break;
			case ADD_PATH: 
				newController = new AddNewMountainPathController();
				break;
			case LOGIN:
				newController = new LoginController();
				break;
			case PROFILE:
				newController = new ProfileController();
				break;
			case HOME:
				newController = new HomeController();
				break;	
			default: 
				throw new SystemException();
		}

		return newController;
	}
	
	public MainController getMainController() {
		return new MainController();
	}
	
	public LoginController getLoginController() {
		return new LoginController();		
	}
	
	public AssistedResearchController getAssistedResearchController() {
		return new AssistedResearchController();
	}
	
	public AddReviewController getAddReviewController() {
		return new AddReviewController();
	}
	
}
