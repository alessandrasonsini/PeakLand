package logic.view.desktop.graphic_element;

import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.bean.SimpleMountainPathBean;
import logic.view.desktop.ShowableElement;

public class SimpleMountainPathItem extends ShowableElement {
	
	@FXML
	private HBox itemBox;
	
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
	
	public void setInfo(SimpleMountainPathBean bean) {
        txtName.setText(bean.getNameAsText());
        txtLocationRegion.setText(bean.getRegionAsText());
        txtLocationProvince.setText(bean.getProvinceAsText());
        txtLocationCity.setText(bean.getCity());
        txtDifficultyLevel.setText(bean.getLevelAsText());
        txtTravelTime.setText(bean.getHoursAsText() + ":" + bean.getMinutesAsText());
        lbVotes.setText(bean.getNumberOfVotesAsText());
        if(bean.getVote() > 0)
        	setStarVote(bean.getVote());
        if(bean.getRankPosition() != null)
        	lbRankPosition.setText(bean.getRankPosition().toString() +")");
        	
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
