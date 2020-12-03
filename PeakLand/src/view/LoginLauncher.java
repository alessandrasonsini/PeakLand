package view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.graphicController.LoginGraphicController;

public class LoginLauncher extends Application {
	 	
		@Override
	    public void start(Stage primaryStage) throws IOException {
			
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("ui/loginLayout.fxml"));
	        Parent root = loader.load();
			
	        //LoginGraphicController controller = loader.getController();
	        //controller.setScreenDim(primaryStage);
	        
	        Scene scene = new Scene(root);
	        primaryStage.setScene(scene);
	        primaryStage.setMaximized(true);
	        //primaryStage.setFullScreen(true);
	        primaryStage.show();
	    }

	    public static void main(String[] args) {
	        launch(args);
	    }
}
