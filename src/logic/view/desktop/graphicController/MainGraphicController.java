package logic.view.desktop.graphicController;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class MainGraphicController  {
	
	@FXML
	private Button btnSearch;
	
	@FXML
    private void buttonHandler(ActionEvent event) throws Exception {
        btnSearch.setText("ok");
        changePage();
    }
	
	@FXML
	VBox root; 
	
	private void changePage() throws Exception{
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ui/searchPathLayout.fxml"));
		Parent parent = loader.load();
		ViewMountainPathInfoGraphicController ctrl = loader.getController();
		//ViewMountainPathInfoGraphicController ctrl = new ViewMountainPathInfoGraphicController(); 
        root.getChildren().add(ctrl.mainChild);
	}
	

}
