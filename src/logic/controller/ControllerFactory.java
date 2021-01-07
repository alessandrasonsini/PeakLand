package logic.controller;

public class ControllerFactory {
	
	// Ritorna il controller relativo al tipo richiesto
	public Controller getController(String type) throws Exception {
		Controller newController;
		
		switch(type) {
			case "btnSearch": 
				//newController = new SearchMountainPathController();
				newController = new ViewMountainPathInfoController();
				break;
			case "btnAddPath": 
				newController = new AddNewMountainPathController();
				break;
			case "search":
				newController = new SearchMountainPathController();
				break;
			//case "View info":
				//newController = new ViewMountainPathInfoController();
				//break;
			default: 
				throw new Exception("Invalid type : " + type);
		}
		
		return newController;
	}
	
	public SearchMountainPathController getSearchMountainPathController() {
		return new SearchMountainPathController();
	}
}