package logic.view.desktop.graphic_controller;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import logic.bean.SimpleMountainPathBean;
import logic.controller.Controller;
import logic.controller.HomeController;
import logic.view.desktop.graphic_element.SimpleMountainPathListCell;

public class HomeGraphicController extends GraphicController {
	
	@FXML
	private Text txtWelcome;
	
	@FXML
	private Text txtTopTen;
	
	@FXML
	private Text txtMessage;
	
	@FXML
	private ListView<SimpleMountainPathBean> listViewClassification;

	public HomeGraphicController(Controller controller) {
		super(controller);
	}
	
	@Override
	protected void setupLayout() {
		List<SimpleMountainPathBean> classificationList = getHomeController().getClassification(MainGraphicController.getInstance().getSessionId());
		this.setWelcomeText();
		
		ObservableList<SimpleMountainPathBean> list = FXCollections.observableArrayList();
		list.addAll(classificationList);
		this.fillListView(list);
	}
	
	private void setWelcomeText() {
		String name = getHomeController().getCurrentUserName();
		if(name == null) {
			txtWelcome.setText("Welcome on Peakland!");
			txtTopTen.setText("Top ten on Peakland:");
			txtMessage.setText("Join our community to discover all the most beautiful mountain paths in Italy, "
					+ "plan trips with your friends and invite them through our platform!");
		}
		else {
			txtWelcome.setText("Welcome back "+ name);
			txtTopTen.setText("Suggested for you based on yout favourite paths:");
			txtMessage.setText("Are you ready for new adventures?");
		}
	}

	@Override
	protected String getFXMLFileName() {
		return "homeLayout";
	}
	
	public HomeController getHomeController() {
		return (HomeController) myController;
	}
	
	// Metodo che si occupa del riempimento della list view
	private void fillListView(ObservableList<SimpleMountainPathBean> list) {
		listViewClassification.setItems(list);
		listViewClassification.setCellFactory(new Callback<ListView<SimpleMountainPathBean>, ListCell<SimpleMountainPathBean>>() {
            @Override
            public ListCell<SimpleMountainPathBean> call(ListView<SimpleMountainPathBean> listViewMountainPath) {
                return new SimpleMountainPathListCell();
            }
        });
	}
	
	@Override
	public void switchPage(Pane paneToSwitch) {
		MainGraphicController.getInstance().switchPage(paneToSwitch);
		
	}
}
