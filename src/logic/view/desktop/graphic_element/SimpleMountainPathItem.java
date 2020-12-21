package logic.view.desktop.graphic_element;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SimpleMountainPathItem {
	
	@FXML
	HBox itemBox;
	
	@FXML
	ImageView image;
	
	@FXML
	VBox vbInfo;
	
	@FXML
	TextField txtName;
	
	@FXML
	TextField txtLocation;
	
	@FXML
	TextField txtDifficultyLevel;
	
	@FXML
	TextField txtTravelTime;
	
	public SimpleMountainPathItem() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("layout/simpleMountainPathItemLayout.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
	}
	
	public void setInfo(String name, String location, String diffLevel, String travelTime)
    {
        txtName.setText(name);
        txtLocation.setText(location);
        txtDifficultyLevel.setText(diffLevel);
        txtTravelTime.setText(travelTime);
    }

    public HBox getBox()
    {
        return itemBox;
    }
}
