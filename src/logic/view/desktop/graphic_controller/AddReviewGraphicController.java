package logic.view.desktop.graphic_controller;


import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import logic.bean.ReviewBean;
import logic.controller.AddNewMountainPathController;
import logic.controller.Controller;
import logic.model.exception.DatabaseException;

public class AddReviewGraphicController extends GraphicController{

	@FXML
	private Button btnVote1;
	
	@FXML
	private Button btnVote2;
	
	@FXML
	private Button btnVote3;
	
	@FXML
	private Button btnVote4;
	
	@FXML
	private Button btnVote5;
	
	@FXML
	private TextArea txtReview;
	
	@FXML
	private TextField txtReviewTitle;
	
	@FXML
	private Button btnAddReview;
	
	@FXML
	private Button back;
	
	private List<Button> btnVotes;
	private Integer votes;
	
	public AddReviewGraphicController(Controller controller) {
		super(controller);
	}
	
	@Override
	protected void setupLayout() {
		this.btnVotes = Arrays.asList(btnVote1,btnVote2,btnVote3,btnVote4,btnVote5);
		btnAddReview.setDisable(true);
		txtReviewTitle.setDisable(true);
		txtReview.setDisable(true);
		
	}
	
	@FXML
	public void onAddingVoteRequest(ActionEvent event) {
		btnAddReview.setDisable(false);
		txtReviewTitle.setDisable(false);
		txtReview.setDisable(false);
		int count = 0;
		String filePath = "../graphic_element/images/star.png";
		for(Button vote : btnVotes) {
			count++;
			vote.setGraphic(new ImageView(new Image(this.getClass().getResourceAsStream(filePath),35,35,false,false)));
			if(vote == (Button)event.getSource()) {
				filePath = "../graphic_element/images/emptystar.png";
				this.votes = Integer.valueOf(count);
			}	
		}
	}

	@FXML
	public void onSaveReview(ActionEvent event) {
		ReviewBean review = new ReviewBean();
		review.setVote(this.votes);
		review.setTitle(txtReviewTitle.getText());
		review.setComment(txtReview.getText());
		try {
			getAddNewMountainPathController().saveReview(review);
			showMessage("Review added successfully", "You gain one peakCoin!", AlertType.INFORMATION);
			btnAddReview.setDisable(true);
		} catch (DatabaseException e) {
			showDatabaseError();
		}
	}
	
	@FXML
	public void onBackPressed(ActionEvent event) {
		getAddNewMountainPathController().onBackPressed();
		this.executeAction(this.myController);
	}
	
	@Override
	protected String getFXMLFileName() {
		return "addReview";
	}
	
	public AddNewMountainPathController getAddNewMountainPathController() {
		return (AddNewMountainPathController) myController;
	}
	
	@Override
	public void switchPage(Pane paneToSwitch) {
		MainGraphicController.getInstance().switchPage(paneToSwitch);
		
	}
	
	
}
