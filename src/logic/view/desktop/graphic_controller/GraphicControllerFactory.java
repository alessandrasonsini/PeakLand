package logic.view.desktop.graphic_controller;

import logic.controller.Controller;

public class GraphicControllerFactory {
	
	public GraphicController getGraphicController(Controller controller) {
		GraphicController newGraphicController;
		switch(controller.getNextPageId()) {
			case "Search path": 
				newGraphicController =  new SearchMountainPathGraphicController(controller);
				break;
			case "Add path": 
				newGraphicController =  new AddNewMountainPathGraphicController(controller);
				break;	
			case "View info": 
				newGraphicController =  new ViewMountainPathInfoGraphicController(controller);			
				break;		
			case "Login":
				newGraphicController = new LoginGraphicController(controller);
				break;
			case "Assisted research":
				newGraphicController = new AssistedResearchGraphicController(controller);
				break;
			default: newGraphicController = null;
		}
		return newGraphicController;
	}
	
	
}
