package logic.controller;


public abstract class Controller {
	
	protected String nextPageId;
	
	protected Controller() {
		setNextPageId("init");
	}
	
	// Costruisce il prossimo controller da istanziare per eseguire l'azione
	// chiamata
	public Controller executeAction(String action) {
		Controller nextController;
		ControllerFactory controllerFactory = new ControllerFactory();

		try {
			nextController = controllerFactory.getController(action);
		} catch (Exception e) {
			// Gestione pagina di errore
			
			nextController = new ErrorController();
		}
		
		return nextController;
	}

	public String getNextPageId() {
		return this.nextPageId;
	}
	
	public abstract void setNextPageId(String action);
	
}
