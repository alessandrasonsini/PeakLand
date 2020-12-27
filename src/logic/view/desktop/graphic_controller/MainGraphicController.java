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
		SearchMountainPathGraphicController searchCtrl = new SearchMountainPathGraphicController();
		searchCtrl.switchPage(mainChild);
	}
	
	@FXML
	private void addPathButtonHandler(ActionEvent event) {
		AddNewMountainPathGraphicController addCtrl = new AddNewMountainPathGraphicController();
		addCtrl.switchPage(mainChild);
	}
	
}
