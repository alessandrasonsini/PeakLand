package logic.view.desktop.graphic_controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import logic.controller.AddNewMountainPathController;
import logic.controller.Controller;

public class AddReviewGraphicController extends GraphicController{

	@FXML
	private ImageView imageEmptyStar1;
	
	@FXML
	private ImageView imageEmptyStar2;
	
	@FXML
	private ImageView imageEmptyStar3;
	
	@FXML
	private ImageView imageEmptyStar4;
	
	@FXML
	private ImageView imageEmptyStar5;
	
	@FXML
	private TextArea txtReview;
	
	@FXML
	private TextField txtReviewTitle;
	
	@FXML
	private Button btnAddReview;
	
	public AddReviewGraphicController(Controller controller) {
		super(controller);
		System.out.println("dentro addReview grafico");
	}

	@Override
	protected String getFXMLFileName() {
		return "addReview";
	}
	
	public AddNewMountainPathController getAddNewMountainPathController() {
		return (AddNewMountainPathController) myController;
	}
	
	
}
