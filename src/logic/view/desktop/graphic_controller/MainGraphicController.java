package logic.view.desktop.graphic_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import logic.view.desktop.graphic_element.Page;

public class MainGraphicController  {
	
	@FXML
	private Button btnSearch;
	
	@FXML
	private BorderPane mainPane;
	
	@FXML
	private void buttonHandler(ActionEvent event) {
		switchPage("searchPathLayout");
	}
	
	private void switchPage(String newPage) {
		//Carico la view della nuova pagina
		Pane newPageView = Page.getPage(newPage);
		mainPane.setCenter(newPageView);
	}

}
