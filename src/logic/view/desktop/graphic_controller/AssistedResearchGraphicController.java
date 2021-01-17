package logic.view.desktop.graphic_controller;

import logic.controller.Controller;
import logic.model.enums.DifficultyLevelEnum;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.controlsfx.control.CheckComboBox;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class AssistedResearchGraphicController extends GraphicController{
	
	@FXML
	CheckComboBox<String> boxDifficultyLevel;

	protected AssistedResearchGraphicController(Controller controller) {
		super(controller);
		
		// Solo per testare la grafica
		//provaLib();
	}

	@Override
	protected String getFXMLFileName() {
		return "assistedResearchLayout";
	}
	
	// Solo per testare la grafica
	private void provaLib() {
		// create the data to show in the CheckComboBox 
		 final ObservableList<String> strings = FXCollections.observableArrayList();
		 for (int i = 0; i <= 100; i++) {
		     strings.add("Item " + i);
		 }
		 
		 boxDifficultyLevel.getItems().addAll(EnumSet.allOf(DifficultyLevelEnum.class).stream().map(DifficultyLevelEnum::name).collect(Collectors.toList()));
		 System.out.println("Eccomi");
		 // and listen to the relevant events (e.g. when the selected indices or 
		 // selected items change).
		 boxDifficultyLevel.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
		     public void onChanged(ListChangeListener.Change<? extends String> c) {
		          while(c.next()) {
		              //do something with changes here
		          }
		          System.out.println(boxDifficultyLevel.getCheckModel().getCheckedItems());
		     }
		 });
		 
	}

}
