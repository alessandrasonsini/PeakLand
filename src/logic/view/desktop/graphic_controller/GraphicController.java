package logic.view.desktop.graphic_controller;


import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
		
		executeAction(nextController);
	}
	
	public void executeAction(Controller appController) {
		GraphicControllerFactory factory = new GraphicControllerFactory();
		// Costruisce il prossimo graphic controller da eseguire in base alla action
		GraphicController nextGraphicController = factory.getGraphicController(appController);
		MainGraphicController.getInstance().switchPage(nextGraphicController.getRootPane());
	}
	
	// Metodo che mostra all'utente l'errore che si è verificato
	protected void showError(String header, String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText(header);
		alert.setContentText(message);

		alert.showAndWait();
	}
	
	protected void showDatabaseError() {
		showError("Database error", "Ops, there was an error connecting to database. Retry later");
	}
	
}
