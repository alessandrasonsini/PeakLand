package logic.view.desktop.graphic_element;

import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import logic.bean.ReviewBean;
import logic.view.desktop.ShowableElement;

public class ReviewItem extends ShowableElement{
	
	@FXML
	private HBox itemBox;
	
	@FXML
	private Label lbAuthor;
	
	@FXML
	private ImageView imgVote1;
	
	@FXML
	private ImageView imgVote2;
	
	@FXML
	private ImageView imgVote3;
	
	@FXML
	private ImageView imgVote4;
	
	@FXML
	private ImageView imgVote5;
	
	@FXML
	private Label lbTitle;
	
	@FXML
	private TextArea txtComment;
	
	private List<ImageView> starImages = Arrays.asList(imgVote1,imgVote2,imgVote3,imgVote4,imgVote5);
	
	public HBox getBox() {
        return itemBox;
    }
	
	public void setInfo(ReviewBean bean) {
		lbAuthor.setText(bean.getAuthor());
		txtComment.setText(bean.getCommentAsText());
		lbTitle.setText(bean.getTitle());
		if(bean.getVote() > 0)
			setStarImages(bean.getVote());
	}
	
	private void setStarImages(Integer vote) {
		int count = 1;
		for(ImageView image : this.starImages) {
			image.setImage(new Image(this.getClass().getResourceAsStream("../graphic_element/images/star.png"),35,35,false,false));
			if(count == vote)
				break;
			else count++;
		}
	}
	
	@Override
	protected String getFXMLFileName() {
		return "reviewItemLayout";
	}

}
