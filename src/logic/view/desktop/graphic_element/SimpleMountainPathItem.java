package logic.view.desktop.graphic_element;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.view.desktop.ShowableElement;

public class SimpleMountainPathItem extends ShowableElement {
	
	@FXML
	private HBox itemBox;
	
	@FXML
	private ImageView image;
	
	@FXML
	private VBox vbInfo;
	
	@FXML
	private Label txtName;
	
	@FXML
	private Label txtLocationRegion;
	
	@FXML
	private Label txtLocationProvince;
	
	@FXML
	private Label txtLocationCity;
	
	@FXML
	private Label txtDifficultyLevel;
	
	@FXML
	private Label txtTravelTime;
	
	public SimpleMountainPathItem() {
		super();
	}
	
	public void setInfo(String name, String locationRegion, String locationProvince, String locationCity, String diffLevel, String travelTime) {
        txtName.setText(name);
        txtLocationRegion.setText(locationRegion);
        txtLocationProvince.setText(locationProvince);
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
