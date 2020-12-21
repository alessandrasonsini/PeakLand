package logic.view.desktop;

import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.view.bean.SimpleMountainPathBean;

public class CustomListCell extends ListCell<SimpleMountainPathBean> {
	
	@FXML
	HBox itemBox;
	
	@FXML
	ImageView image;
	
	@FXML
	VBox info;
	
	@FXML
	TextField name;
	
	@FXML
	TextField location;
	
	@FXML
	TextField difficultyLevel;
	
	@FXML
	TextField travelTime;
	

	
	public CustomListCell() {
		super();
		
	}
}
