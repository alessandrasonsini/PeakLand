package logic.view.desktop.graphicController;

import javafx.fxml.FXML;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class LoginGraphicController {
	@FXML 
	private Screen screen; 
	
	public void initialize() {
	}
	
	public void setScreenDim(Stage stage) {
		//Rectangle2D screenBounds = screen.getVisualBounds();
		//stage.setHeight(screenBounds.getHeight());
		//stage.setWidth(screenBounds.getWidth());
		stage.setMaximized(true);
		//stage.setX(screenBounds.getMinX());
		//stage.setY(screenBounds.getMinY());
		/*GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		stage.setWidth(width);
		stage.setHeight(height);
		stage.setx
		*/
	}

}