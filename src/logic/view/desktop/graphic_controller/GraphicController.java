package logic.view.desktop.graphic_controller;


import logic.controller.Controller;
import logic.view.desktop.ShowableElement;

public abstract class GraphicController extends ShowableElement {
	
	// Controller applicativo associato 
	protected Controller myController;
	
	protected GraphicController(Controller controller) {
		super();
		this.myController = controller;
	}
	
	protected void executeAction(String action) {
		// Istanzia controller applicativo del prossimo caso d'uso da eseguire
		Controller nextController = myController.executeAction(action);
		
		this.switchPage(nextController);
		
	}
	
	public void switchPage(Controller appController) {
		GraphicControllerFactory factory = new GraphicControllerFactory();
		// Costruisce il prossimo graphic controller da eseguire in base alla action
		GraphicController nextGraphicController = factory.getGraphicController(appController);
		MainGraphicController.getInstance().switchPage(nextGraphicController.getRootPane());
	}
	
	
	
	
}
