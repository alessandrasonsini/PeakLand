package logic.view.desktop.graphicController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import logic.view.desktop.Page;

public class MainGraphicController  {
	
	@FXML
	private Button btnSearch;
	
	@FXML
	private BorderPane mainPane;
	
	@FXML
	private void buttonHandler(ActionEvent event) {
		Page page = new Page();
		Pane view = page.getPage("searchPathLayout");
		
		mainPane.setCenter(view); 
	}
	
	@FXML
	VBox root; 
	

}
