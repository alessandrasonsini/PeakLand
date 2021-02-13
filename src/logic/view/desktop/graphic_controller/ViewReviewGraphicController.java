package logic.view.desktop.graphic_controller;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import logic.bean.ReviewBean;
import logic.controller.Controller;
import logic.controller.ViewMountainPathInfoController;
import logic.view.desktop.graphic_element.ReviewListCell;

public class ViewReviewGraphicController extends GraphicController {
	
	@FXML
	private Label lbPathName;
	
	@FXML
	private Label lbNoResults;
	
	@FXML
	private ListView<ReviewBean> listViewReviews;
	
	@FXML
	private Button btnBack;
	
	protected ViewReviewGraphicController(Controller controller) {
		super(controller);
	}
	
	@FXML
	private void onBackPressed(ActionEvent event) {
		getViewMountainPathInfoController().onBackPressed();
		this.executeAction(this.myController);
	}
	
	@Override
	protected void setupLayout() {
		String pathName = getViewMountainPathInfoController().getSelectedMountainPath().getName();
		lbPathName.setText(pathName);
		List<ReviewBean> reviewList = getViewMountainPathInfoController().getPathReview(pathName);
		if(reviewList.size() == 0) {
			lbNoResults.setText("No reviews for this mountain path");
		}
		else {
			ObservableList<ReviewBean> reviews = FXCollections.observableArrayList();
			reviews.addAll(reviewList);
			fillListView(reviews);
		}
		
	}
	
	
	private void fillListView(ObservableList<ReviewBean> list) {
		listViewReviews.setItems(list);
		listViewReviews.setCellFactory(new Callback<ListView<ReviewBean>, ListCell<ReviewBean>>() {
            @Override
            public ListCell<ReviewBean> call(ListView<ReviewBean> listViewReviews) {
                return new ReviewListCell();
            }
        });
	}

	@Override
	protected String getFXMLFileName() {
		return "viewReviewLayout";
	}
	
	public ViewMountainPathInfoController getViewMountainPathInfoController() {
		return (ViewMountainPathInfoController) myController;
	}

}
