package logic.view.desktop.graphic_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import logic.controller.Controller;
import logic.controller.ControllerFactory;
import logic.controller.MainController;
import logic.model.enums.PageId;


public class MainGraphicController extends GraphicController {
	@FXML
	private Button btnViewInfo;

	@FXML
	private Button btnAddPath;
	
	@FXML
	private Button btnProfile;
	
	@FXML
	private Button btnHome;

	@FXML
	private BorderPane mainChild;
	
	private Integer sessionId;
	
	// Button precedentemente premuto
	private Button prevPressed;

	String defaultStyle = "-fx-background-color: FBC9A8; -fx-border-color: F69155;";

	private static MainGraphicController instance = null;

	private MainGraphicController(Controller controller) {
		super(controller);
		
	}
	
	@Override
	protected void setupLayout() {
		// Questo metodo è vuoto perchè attualmente non ci sono oggetti grafici di cui fare il setup
	}

	public static MainGraphicController getInstance() {
		if (instance == null) {
			instance = new MainGraphicController(new ControllerFactory().getMainController());
			instance.setup();
		}
			
		return instance;
	}
	
	private void setup() {
		this.setPressed(btnHome);
		this.executeAction(getMainController().getNextPageId());
	}

	@FXML
	private void buttonHandler(ActionEvent event) {
		Button pressedButton = (Button) event.getSource();
		this.setPressed(pressedButton);
		this.executeAction(getMainController().onActionRequired(convertAction(pressedButton.getId()),sessionId));
	}
	
	// Converte l'id dell'azione richiesta nell'interfaccia nell'id dell'azione che deve eseguire il sistema
	private PageId convertAction(String actionId) {
		PageId newActionId;
		switch(actionId) {
			case "btnViewInfo":
				newActionId = PageId.VIEW_INFO;
				break;
			case "btnAddPath":
				newActionId = PageId.ADD_PATH;
				break;
			case "btnProfile":
				newActionId = PageId.PROFILE;
				break;
			case "btnHome":
				newActionId = PageId.HOME;
				break;
			default: 
				newActionId = null;
				break;				
		}
		return newActionId;
	}

	@Override
	protected String getFXMLFileName() {
		return "mainLayout";
	}
	
	@Override
	public void switchPage(Pane paneToSwitch) {
		mainChild.setCenter(paneToSwitch);
	}
	
	// Imposta il colore del button premuto e setta i valori di default di quello precedentemente premuto
	private void setPressed(Button currPressed) {
		currPressed.setStyle("-fx-background-color: F69155;");
		if(prevPressed != null) {
			prevPressed.setStyle(defaultStyle);
		}
		prevPressed = currPressed;
	}
	
	// Setta il valore del session id e ritorna alla pagina richiesta prima della comparsa del login
	public void loginSucceded(Integer id) {
		this.sessionId = id;
		this.executeAction(convertAction(prevPressed.getId()));
	}
	
	public Integer getSessionId() {
		return this.sessionId;
	}
	
	private MainController getMainController() {
		return (MainController)myController;
	}

}
