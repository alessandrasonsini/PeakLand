package logic.view.desktop.graphic_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ViewMountainPathInfoGraphicController {
	
	SearchMountainPathGraphicController searchController;
	
	public ViewMountainPathInfoGraphicController() {
		// TODO Auto-generated constructor stub
		searchController = new SearchMountainPathGraphicController();
	}

	@FXML
	private TextField txtSearch;
	
	@FXML
	private ImageView imgSearch; 
	
	@FXML
	public HBox mainChild;
	
	@FXML
    private void onSearchInputHandler(ActionEvent event) {
        searchController.onSearchRequest(event);
    }
}
