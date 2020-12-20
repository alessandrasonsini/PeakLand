package logic.view.desktop.graphicController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ViewMountainPathInfoGraphicController {

	@FXML
	private TextField txtSearch;
	
	@FXML
	private ImageView imgSearch; 
	
	@FXML
	public HBox mainChild;
	
	@FXML
    private void onSearchInputHandler(ActionEvent event) {
        txtSearch.setText("ok");
    }
}
