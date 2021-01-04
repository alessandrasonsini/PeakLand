package logic.view.desktop.graphic_controller;


import logic.controller.Controller;
import logic.view.desktop.ShowableElement;

public abstract class GraphicController extends ShowableElement {
	
	// Controller applicativo associato 
	protected Controller myController;
	
	protected GraphicController(Controller controller) {
		super();
		this.myController = controller;
		setLayout();
	}
	
	//protected abstract String getFXMLFileName();
	
	protected void executeAction(String action) {
		//Recupera l'istanza di controller applicativo necessaria
		Controller nextController = myController.executeAction(action);

		GraphicControllerFactory factory = new GraphicControllerFactory();
		// Costruisce il prossimo graphic controller da eseguire in base alla action
		GraphicController nextGraphicController = factory.getGraphicController(nextController);
		MainGraphicController.getInstance().switchPage(nextGraphicController.getRootPane());
	}
	
	
	
}
