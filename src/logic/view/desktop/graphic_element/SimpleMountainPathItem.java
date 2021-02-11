package logic.view.desktop.graphic_element;

import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.view.desktop.ShowableElement;

public class SimpleMountainPathItem extends ShowableElement {
	
	@FXML
	private HBox itemBox;
	
	@FXML
	private ImageView imgPath;
	
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
	
	@FXML
	private Label lbVotes;
	
	@FXML
	private ImageView imgStar1;
	
	@FXML
	private ImageView imgStar2;
	
	@FXML
	private ImageView imgStar3;
	
	@FXML
	private ImageView imgStar4;
	
	@FXML
	private ImageView imgStar5;
	
	@FXML
	private Label lbRankPosition;
	
	private List<ImageView> starImages = Arrays.asList(imgStar1,imgStar2,imgStar3,imgStar4,imgStar5);
	
	public SimpleMountainPathItem() {
		super();
	}
	
	public void setInfo(String name, String locationRegion, String locationProvince, 
			String locationCity, String diffLevel, String travelTime, Integer vote, Integer numberOfVotes, Integer position) {
        txtName.setText(name);
        txtLocationRegion.setText(locationRegion);
        txtLocationProvince.setText(locationProvince);
        txtLocationCity.setText(locationCity);
        txtDifficultyLevel.setText(diffLevel);
        txtTravelTime.setText(travelTime);
        lbVotes.setText(numberOfVotes.toString());
        if(vote > 0)
        	setStarVote(vote);
        if(position != null)
        	lbRankPosition.setText(position.toString() +")");
    }

	private void setStarVote(Integer vote) {
		int count = 1;
		for(ImageView image : this.starImages) {
			image.setImage(new Image(this.getClass().getResourceAsStream("../graphic_element/images/star.png"),35,35,false,false));
			if(count == vote)
				break;
			else count++;
		}
	}
	
    public HBox getBox() {
        return itemBox;
    }

	@Override
	protected String getFXMLFileName() {
		return "simpleMountainPathItemLayout";
	}
}
