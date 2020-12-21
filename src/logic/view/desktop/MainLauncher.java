package logic.view.desktop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainLauncher extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
        Parent root = FXMLLoader.load(getClass().getResource("graphic_element/layout/mainLayout.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
