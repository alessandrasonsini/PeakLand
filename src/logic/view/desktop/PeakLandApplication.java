package logic.view.desktop;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import logic.view.desktop.graphic_controller.MainGraphicController;

public class PeakLandApplication extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		MainGraphicController mainGraphCtrl = MainGraphicController.getInstance();
		
		Parent root = mainGraphCtrl.getRootPane();
		Scene scene = new Scene(root);
		
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        //set Stage boundaries to visible bounds of the main screen
		primaryStage.setX(primaryScreenBounds.getMinX());
		primaryStage.setY(primaryScreenBounds.getMinY());
		primaryStage.setWidth(primaryScreenBounds.getWidth());
		primaryStage.setHeight(primaryScreenBounds.getHeight());
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
