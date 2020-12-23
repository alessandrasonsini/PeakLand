package logic.view.desktop.graphic_controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import logic.view.desktop.GraphicController;

public class MainGraphicController extends GraphicController  {
	@FXML
	private Button btnSearch;
	
	@FXML
	private BorderPane mainChild;
	
	public MainGraphicController() {
		super("mainLayout");
	}	
	
	@FXML
	private void buttonHandler(ActionEvent event) {
		SearchMountainPathGraphicController searchCtrl = new SearchMountainPathGraphicController();
		searchCtrl.switchPage(mainChild);
		
	}
	
}
