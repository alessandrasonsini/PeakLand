package logic.view.desktop;

import java.net.URL;


import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class Page {
	private Pane view;
	
	public Pane getPage(String fileName) {
		try {
			URL file = MainLauncher.class.getResource("ui/" + fileName + ".fxml");
			
			if (file == null) {
				throw new java.io.FileNotFoundException("FXML file not found.");
			}
			
			new FXMLLoader();
			
			view = FXMLLoader.load(file);
		} catch (Exception e) {
			System.out.println("No page with name " + fileName);
		}
		
		return view;
	}
}