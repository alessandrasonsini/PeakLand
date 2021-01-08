package logic.view.desktop.graphic_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import logic.controller.Controller;
import logic.controller.MainController;

public class MainGraphicController extends GraphicController {
	@FXML
	private Button btnSearch;

	@FXML
	private Button btnAddPath;
	
	@FXML
	private Button btnProfile;

	@FXML
	private BorderPane mainChild;
	
	// Button precedentemente premuto
	Button prevPressed;
	
	String defaultStyle = "-fx-background-color: FBC9A8; -fx-border-color: F69155;";

	private static MainGraphicController instance = null;;

	private MainGraphicController(Controller controller) {
		super(controller);
	}

	public static MainGraphicController getInstance() {
		if (instance == null)
			instance = new MainGraphicController(new MainController());
		return instance;
	}

	@FXML
	private void buttonHandler(ActionEvent event) {
		Button button = (Button) event.getSource();
		this.setPressed(button);
		this.executeAction(getMainController().onActionRequired(button.getId()));
	}

	@Override
	protected String getFXMLFileName() {
		return "mainLayout";
	}

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
	
	public String getLastPressed() {
		return prevPressed.getId();
	}
	
	private MainController getMainController() {
		return (MainController)myController;
	}
}
