package logic.view.desktop.graphic_controller;


import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import logic.controller.Controller;
import logic.model.enums.PageId;
import logic.model.exception.SystemException;
import logic.view.desktop.ShowableElement;

public abstract class GraphicController extends ShowableElement {
	
	// Controller applicativo associato 
	protected Controller myController;
	
	protected GraphicController(Controller controller) {
		super();
		this.myController = controller;
	}
	
	protected void executeAction(PageId action) {
		// Istanzia controller applicativo del prossimo caso d'uso da eseguire
		Controller nextController;
		try {
			nextController = myController.executeAction(action);
			executeAction(nextController);
		} catch (SystemException e) {
			showSystemError();
		}
	}
	
	public void executeAction(Controller appController) {
		GraphicControllerFactory factory = new GraphicControllerFactory();
		// Costruisce il prossimo graphic controller da eseguire in base alla action
		GraphicController nextGraphicController = factory.getGraphicController(appController);
		MainGraphicController.getInstance().switchPage(nextGraphicController.getRootPane());
	}
	
	// Metodo che mostra all'utente un messaggio
	protected void showMessage(String header, String message, AlertType type) {
		Alert alert = new Alert(type);
		alert.setHeaderText(header);
		alert.setContentText(message);

		alert.showAndWait();
	}

	// Metodo che mostra all'utente l'errore che si è verificato
	protected void showError(String header, String message) {
		showMessage(header, message, AlertType.ERROR);
	}
	
	protected void showDatabaseError() {
		showError("Database error", "Ops, there was an error connecting to database. Retry later");
	}
	
	protected void showSystemError() {
		showError("System error", "Ops, there was a system error. Retry later");
	}
	
}
