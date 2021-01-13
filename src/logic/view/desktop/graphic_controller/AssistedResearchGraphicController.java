package logic.view.desktop.graphic_controller;

import logic.controller.Controller;
import org.controlsfx.control.CheckComboBox;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class AssistedResearchGraphicController extends GraphicController{

	protected AssistedResearchGraphicController(Controller controller) {
		super(controller);
		
		provaLib();
	}

	@Override
	protected String getFXMLFileName() {
		return "assistedResearchLayout";
	}
	
	private void provaLib() {
		// create the data to show in the CheckComboBox 
		 final ObservableList<String> strings = FXCollections.observableArrayList();
		 for (int i = 0; i <= 100; i++) {
		     strings.add("Item " + i);
		 }
		 
		 // Create the CheckComboBox with the data 
		 final CheckComboBox<String> checkComboBox = new CheckComboBox<String>(strings);
		 
		 // and listen to the relevant events (e.g. when the selected indices or 
		 // selected items change).
		 checkComboBox.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
		     public void onChanged(ListChangeListener.Change<? extends String> c) {
		          while(c.next()) {
		              //do something with changes here
		          }
		          System.out.println(checkComboBox.getCheckModel().getCheckedItems());
		     }
		 });
		 
	}

}
