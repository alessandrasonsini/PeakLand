package logic.view.desktop;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.view.desktop.graphic_controller.MainGraphicController;

public class MainLauncher extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		MainGraphicController mainCtrl = new MainGraphicController();
		
		Parent root = mainCtrl.getPane();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
