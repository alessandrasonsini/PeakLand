package logic.controller;

public class ControllerFactory {
	
	// Ritorna il controller relativo al tipo richiesto
	public Controller getController(String type) throws Exception {
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
			default: 
				throw new Exception("Invalid type : " + type);
		}
		
		return newController;
	}
	
	
	// NON SO SE QUESTI METODI HANNO SENSO
	public SearchMountainPathController getSearchMountainPathController() {
		return new SearchMountainPathController();
	}
	
	public LoginController getLoginController() {
		return new LoginController();		
	}
	
	public AssistedResearchController getAssistedResearchController() {
		return new AssistedResearchController()
;	}
	
}
