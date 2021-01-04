package logic.view.desktop;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import logic.view.desktop.graphic_controller.GraphicController;

public abstract class ShowableElement {
	
	protected FXMLLoader loader;
	
	// Contenitore contenente tutta la grafica da mostrare
	protected Pane rootPane;
	
	protected ShowableElement() {
		this.rootPane = new Pane();
		this.loader = new FXMLLoader();
		this.loader.setController(this);
		setLayout();
	}
	
	protected void setLayout() {
		// Carica il file fxml associato
		try {
			URL fileUrl = ShowableElement.class.getResource("graphic_element/layout/" + getFXMLFileName() + ".fxml");
			
			if (fileUrl == null) {
				throw new java.io.FileNotFoundException("FXML file not found.");
			}
			
			this.loader.setLocation(fileUrl);
			rootPane = this.loader.load();
			
		} catch (FileNotFoundException E) {
			// Handle the exception
			System.out.println("No page with name " + getFXMLFileName());
			
		} catch (IOException E) {
			
			// Handle the exception
			System.out.println("load exception");
		}
		
	}
	
	protected Pane getRootPane() {
		return rootPane;
	}
	
	protected void setGraphicController(GraphicController controller) {
		this.loader.setController(controller);
	}
	
	protected abstract String getFXMLFileName(); 
}
