package logic.view.desktop.graphic_element;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.view.desktop.ShowableElement;

public class SimpleMountainPathItem extends ShowableElement {
	
	@FXML
	HBox itemBox;
	
	@FXML
	ImageView image;
	
	@FXML
	VBox vbInfo;
	
	@FXML
	Label txtName;
	
	@FXML
	Label txtLocationRegion;
	
	@FXML
	Label txtLocationCity;
	
	@FXML
	Label txtDifficultyLevel;
	
	@FXML
	Label txtTravelTime;
	
	public SimpleMountainPathItem() {
		super();
	}
	
	public void setInfo(String name, String locationRegion, String locationCity, String diffLevel, String travelTime) {
        txtName.setText(name);
        txtLocationRegion.setText(locationRegion);
        txtLocationCity.setText(locationCity);
        txtDifficultyLevel.setText(diffLevel);
        txtTravelTime.setText(travelTime);
    }

    public HBox getBox() {
        return itemBox;
    }

	@Override
	protected String getFXMLFileName() {
		return "simpleMountainPathItemLayout";
	}
}
