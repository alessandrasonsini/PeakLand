package logic.controller;

public class ControllerFactory {
	
	// Ritorna il controller relativo al tipo richiesto
	public Controller getController(String type) throws Exception {
		Controller newController;
		
		switch(type) {
			case "btnViewInfo": 
				newController = new ViewMountainPathInfoController();
				break;
			case "btnAddPath": 
				newController = new AddNewMountainPathController();
				break;
			case "search":
				newController = new SearchMountainPathController();
				break;
			case "login":
				newController = new LoginController();
				break;
			default: 
				throw new Exception("Invalid type : " + type);
		}
		
		return newController;
	}
	
	public SearchMountainPathController getSearchMountainPathController() {
		return new SearchMountainPathController();
	}
	
	public LoginController getLoginController() {
		return new LoginController();
				
	}
}
