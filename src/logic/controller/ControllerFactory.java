package logic.controller;

import logic.model.exception.SystemException;

public class ControllerFactory {
	
	// Ritorna il controller relativo al tipo richiesto
	public Controller getController(String type) throws SystemException	 {
		Controller newController;
		
		switch(type) {
			case "View info": 
				newController = new ViewMountainPathInfoController();
				break;
			case "Add path": 
				newController = new AddNewMountainPathController();
				break;
			case "Search":
				newController = new SearchMountainPathController();
				break;
			case "Login":
				newController = new LoginController();
				break;
			case "Assisted research":
				newController = new AssistedResearchController();
				break;
			case "Add review":
				newController = new AddReviewController();
				break;
			case "Profile":
				newController = new ProfileController();
				break;
			case "Home":
				newController = new HomeController();
				break;	
			default: 
				throw new SystemException();
		}

		return newController;
	}
	
	public SearchMountainPathController getSearchMountainPathController() {
		return new SearchMountainPathController();
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
