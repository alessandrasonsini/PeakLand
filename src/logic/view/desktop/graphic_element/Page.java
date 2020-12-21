package logic.view.desktop.graphic_element;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import logic.view.desktop.MainLauncher;

public class Page {
	
	private static Pane view;
	
	public static Pane getPage(String fileName) {
		try {
			URL file = MainLauncher.class.getResource("graphic_element/layout/" + fileName + ".fxml");
			System.out.println("graphic_element/layout/" + fileName + ".fxml");
			
			if (file == null) {
				throw new java.io.FileNotFoundException("FXML file not found.");
			}
					
			view = FXMLLoader.load(file);
			
		} catch (Exception e) {
			System.out.println("No page with name " + fileName);
		}
		
		return view;
	}
}