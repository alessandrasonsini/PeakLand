package logic.controller;


public abstract class Controller {
	
	public Controller(){
		setup();
	}
	
	// Id del prossimo step da eseguire
	private String nextStepId;

	public String getNextStepId() {
		return nextStepId;
	}

	public void setNextStepId(String newStep) {
		this.nextStepId = newStep;
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
			// Non so se è meglio metterlo direttamente nella factory
			nextController = new ErrorController();
		}
		
		return nextController;
	}
	
	// Metodo che fa il setup alla creazione del controller
	public abstract void setup();
	
	// Metodo che esegue l'azione principale del controller
	public abstract void execute() throws Exception;

}
