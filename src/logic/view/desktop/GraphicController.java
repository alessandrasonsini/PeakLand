package logic.view.desktop;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class GraphicController {
	 
	protected Pane rootPane;
	protected String fileName; 
	private FXMLLoader loader;
	
	public GraphicController(String fileName) {
		this.fileName = fileName;
		this.rootPane = new VBox();
		this.loader = new FXMLLoader();
		this.loader.setController(this);
		setLayout();
	}
	
	public Pane getPane() {
		return rootPane;
	}
	
	protected void setLayout() {
		try {
			// path of the fxml file to load
			URL fileUrl = GraphicController.class.getResource("graphic_element/layout/" + fileName + ".fxml");
			
			if (fileUrl == null) {
				throw new java.io.FileNotFoundException("FXML file not found.");
			}
			
			this.loader.setLocation(fileUrl);
			Pane pane = this.loader.load();
			rootPane = pane;
			
		} catch (FileNotFoundException E) {
			// Handle the exception
			System.out.println("No page with name " + fileName);
			
		} catch (IOException E) {
			
			// Handle the exception
			System.out.println("load exception");
		}
		
	}
	
	public void switchPage(BorderPane mainPane) {
		mainPane.setCenter(rootPane);
	}
	
}
