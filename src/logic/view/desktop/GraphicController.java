package logic.view.desktop;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import logic.controller.Controller;
import logic.view.desktop.graphic_controller.GraphicControllerFactory;
import logic.view.desktop.graphic_controller.MainGraphicController;

public abstract class GraphicController {
	 
	protected Pane rootPane;
	private FXMLLoader loader;
	protected Controller myController;
	
	public GraphicController(Controller controller) {
		this.rootPane = new VBox();
		this.loader = new FXMLLoader();
		this.loader.setController(this);
		this.myController = controller;
		setLayout();
	}
	
	public Pane getRootPane() {
		return rootPane;
	}
	
	protected abstract String getFXMLFileName();
	
	protected void setLayout() {
		
		try {
			URL fileUrl = GraphicController.class.getResource("graphic_element/layout/" + getFXMLFileName() + ".fxml");
			
			if (fileUrl == null) {
				throw new java.io.FileNotFoundException("FXML file not found.");
			}
			
			this.loader.setLocation(fileUrl);
			Pane pane = this.loader.load();
			rootPane = pane;
			
		} catch (FileNotFoundException E) {
			// Handle the exception
			System.out.println("No page with name " + getFXMLFileName());
			
		} catch (IOException E) {
			
			// Handle the exception
			System.out.println("load exception");
		}
		
	}
	
	protected void executeAction(String action) {
		
		//Recupera l'istanza di controller necessaria
		Controller nextController = myController.executeAction(action);

		GraphicControllerFactory factory = new GraphicControllerFactory();
		// Costruisce il prossimo graphic controller da eseguire in base alla action
		GraphicController nextGraphicController = factory.getGraphicController(nextController);
		MainGraphicController.getInstance().switchPage(nextGraphicController.getRootPane());
		
	}
	
}
