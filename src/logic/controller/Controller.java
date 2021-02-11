package logic.controller;

import logic.model.exception.SystemException;

public abstract class Controller {
	
	protected String nextPageId;
	
	protected Controller() {
		setNextPageId("init");
	}
	
	
	// Costruisce il prossimo controller da istanziare per eseguire l'azione
	// chiamata
	public Controller executeAction(String action) throws SystemException {
		Controller nextController;
		ControllerFactory controllerFactory = new ControllerFactory();

		nextController = controllerFactory.getController(action);
		
		return nextController;
	}

	public String getNextPageId() {
		return this.nextPageId;
	}
	
	public abstract void setNextPageId(String action);
	
}
