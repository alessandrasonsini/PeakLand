package logic.view.desktop.graphicController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import logic.view.desktop.graphic_element.Page;

public class MainGraphicController  {
	
	@FXML
	private Button btnSearch;
	
	@FXML
	private BorderPane mainPane;
	
	@FXML
	private void buttonHandler(ActionEvent event) {
		//Page page = new Page();
		//Pane view = page.getPage("searchPathLayout");
		//Pane view = Page.getPage("searchPathLayout");
		//mainPane.setCenter(view); 
		
		switchPage("searchPathLayout");
	}
	
	@FXML
	VBox root; 
	
	
	private void switchPage(String newPage) {
		
		//Carico la view della nuova pagina
		Pane newPageView = Page.getPage(newPage);
		
		mainPane.setCenter(newPageView);
	}

}
