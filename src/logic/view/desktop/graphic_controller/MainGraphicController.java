package logic.view.desktop.graphic_controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import logic.view.desktop.GraphicController;

public class MainGraphicController extends GraphicController  {
	@FXML
	private Button btnSearch;
	
	@Override
	protected String getFXMLFileName() {
		return "mainLayout";
	}

	@FXML
	private Button btnAddPath;
	
	@FXML
	private BorderPane mainChild;
	
	public MainGraphicController() {
		super();
	}	
	
	@FXML
	private void buttonHandler(ActionEvent event) {
		Object button = event.getSource();
		GraphicController controller;
		if (button.equals(btnSearch))
			controller = new SearchMountainPathGraphicController();
		else if(button.equals(btnAddPath))
			controller = new AddNewMountainPathGraphicController();
		else {
			controller = new HomeGraphicController();
		}
		controller.switchPage(mainChild);
	}
	
}
