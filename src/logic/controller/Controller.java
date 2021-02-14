package logic.controller;

import logic.model.enums.PageId;
import logic.model.exception.SystemException;

public abstract class Controller {
	
	protected PageId nextPageId;
	
	protected Controller() {
		setNextPageId("init");
	}
	
	// Costruisce il prossimo controller da istanziare per eseguire l'azione
	// chiamata
	public Controller executeAction(PageId action) throws SystemException {
		Controller nextController;
		ControllerFactory controllerFactory = new ControllerFactory();

		nextController = controllerFactory.getController(action);
	
		return nextController;
	}

	public PageId getNextPageId() {
		return this.nextPageId;
	}
	
	protected abstract void setNextPageId(String action);
	
}
