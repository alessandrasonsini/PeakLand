package logic.view.desktop;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import logic.model.dao.MountainPathDao;
import logic.view.desktop.graphic_controller.GraphicController;

public abstract class ShowableElement {
	
	private static final Logger LOGGER = Logger.getLogger(ShowableElement.class.getName());
	
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
			LOGGER.log(Level.SEVERE, E.toString(), E);
		} catch (IOException E) {
			// Handle the exception
			LOGGER.log(Level.SEVERE, E.toString(), E);
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
