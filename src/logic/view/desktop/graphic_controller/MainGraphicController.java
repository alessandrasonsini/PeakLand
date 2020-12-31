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
	private BorderPane mainChild;

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
		this.executeAction(button.getId());

	}

	@Override
	protected String getFXMLFileName() {
		return "mainLayout";
	}

	public void switchPage(Pane paneToSwitch) {
		mainChild.setCenter(paneToSwitch);
	}

}
