package logic.view.desktop.graphic_controller;

import logic.controller.Controller;

public class GraphicControllerFactory {
	
	public GraphicController getGraphicController(Controller controller) {
		GraphicController newGraphicController;
		switch(controller.getNextPageId()) {
			case SEARCH: 
				newGraphicController =  new SearchMountainPathGraphicController(controller);
				break;
			case ADD_PATH: 
				newGraphicController =  new AddNewMountainPathGraphicController(controller);
				break;	
			case VIEW_INFO: 
				newGraphicController =  new ViewMountainPathInfoGraphicController(controller);			
				break;		
			case LOGIN:
				newGraphicController = new LoginGraphicController(controller);
				break;
			case ASSISTED_RESEARCH:
				newGraphicController = new AssistedResearchGraphicController(controller);
				break;
			case PROFILE:
				newGraphicController = new ProfileGraphicController(controller);
				break;	
			case ADD_REVIEW:
				newGraphicController = new AddReviewGraphicController(controller);
				break;
			case VIEW_REVIEWS:
				newGraphicController = new ViewReviewGraphicController(controller);
				break;
			case HOME:
				newGraphicController = new HomeGraphicController(controller);
				break;
			default: newGraphicController = null;
		}
		return newGraphicController;
	}

	
}
