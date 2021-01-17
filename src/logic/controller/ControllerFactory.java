package logic.controller;

public class ControllerFactory {
	
	// Ritorna il controller relativo al tipo richiesto
	public Controller getController(String type) throws Exception {
		Controller newController;
		
		switch(type) {
			case "View info": 
				newController = ViewMountainPathInfoController.getInstance();
				break;
			case "Add path": 
				newController = AddNewMountainPathController.getInstance();
				break;
			case "Search":
				newController = SearchMountainPathController.getInstance();
				break;
			case "Login":
				newController = LoginController.getInstance();
				break;
			case "Assisted research":
				newController = AssistedResearchController.getInstance();
				break;
			default: 
				throw new Exception("Invalid type : " + type);
		}
		
		return newController;
	}
	
	
	// NON SO SE QUESTI METODI HANNO SENSO
	public SearchMountainPathController getSearchMountainPathController() {
		return SearchMountainPathController.getInstance();
	}
	
	public LoginController getLoginController() {
		return LoginController.getInstance();
				
	}
}
