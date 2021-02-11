package logic.view.desktop.graphic_controller;

import logic.bean.MountainPathBean;
import logic.controller.Controller;
import logic.controller.ViewMountainPathInfoController;
import logic.model.exception.SystemException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class AssistedResearchGraphicController extends GraphicController{
	
	@FXML
	private TextField txtRegionFilter;
	
	@FXML
	private TextField txtCityFilter;
	
	@FXML
	private TextField txtProvinceFilter;
	
	@FXML
	private RadioButton radioBtnTFilter;
	
	@FXML
	private RadioButton radioBtnEFilter;
	
	@FXML
	private RadioButton radioBtnEEFilter;
	
	@FXML
	private RadioButton radioBtnEEAFilter;
	
	@FXML
	private CheckBox ckBoxMountainFilter;
	
	@FXML
	private CheckBox ckBoxLakeFilter;
	
	@FXML
	private CheckBox ckBoxSeaFilter;
	
	@FXML
	private CheckBox ckBoxRockFilter;
	
	@FXML
	private CheckBox ckBoxGrassFilter;
	
	@FXML
	private CheckBox ckBoxWoodFilter;
	
	@FXML
	private RadioButton radioBtnCycleYFilter;
	
	@FXML
	private RadioButton radioBtnCycleNFilter;
	
	@FXML
	private RadioButton radioBtnHistYFilter;
	
	@FXML
	private RadioButton radioBtnHistNFilter;
	
	@FXML
	private RadioButton radioBtnFamYFilter;
	
	@FXML
	private RadioButton radioBtnFamNFilter;
	
	@FXML
	private Button btnSearch;
	
	@FXML
	private ToggleGroup levelGroup;
	
	@FXML
	private ToggleGroup cycleGroup;
	
	@FXML
	private ToggleGroup histGroup;
	
	@FXML
	private ToggleGroup famGroup;
	
	private List<CheckBox> ground;
	private List<CheckBox> landscape;
	
	protected AssistedResearchGraphicController(Controller controller) {
		super(controller);
		ground = new ArrayList<>(Arrays.asList(ckBoxGrassFilter,ckBoxRockFilter,ckBoxWoodFilter));
		landscape = new ArrayList<>(Arrays.asList(ckBoxMountainFilter,ckBoxSeaFilter,ckBoxLakeFilter));
	}

	@FXML
	private void onBackPressed(ActionEvent event) {
		getViewMountainPathInfoController().onBackPressed();
		this.executeAction(this.myController);
	}
	
	@FXML
	private void onSearchPressed(ActionEvent event) {
		MountainPathBean wishMountainPath = new MountainPathBean();
		// Costruisce la bean con tutti i filtri inseriti
		if(txtRegionFilter != null && !txtRegionFilter.getText().isEmpty())
			wishMountainPath.setRegion(txtRegionFilter.getText());
		if(txtProvinceFilter != null && !txtProvinceFilter.getText().isEmpty())	
			wishMountainPath.setProvince(txtProvinceFilter.getText());
		if(txtCityFilter != null && !txtCityFilter.getText().isEmpty())		
			wishMountainPath.setCity(txtCityFilter.getText());
		RadioButton radioButton = ((RadioButton) levelGroup.getSelectedToggle());
		if(radioButton != null)
			wishMountainPath.setLevel(radioButton.getText().toUpperCase());
		wishMountainPath.setLandscape(checkSelectedBox(landscape));
		wishMountainPath.setGround(checkSelectedBox(ground));
		radioButton = ((RadioButton) cycleGroup.getSelectedToggle());
		if(radioButton != null)
			wishMountainPath.setCycleble(radioButton.getText().equals("Yes"));
		radioButton = ((RadioButton) histGroup.getSelectedToggle());
		if(radioButton != null)
			wishMountainPath.setHistoricalElements(radioButton.getText().equals("Yes"));
		radioButton = ((RadioButton) famGroup.getSelectedToggle());
		if(radioButton != null)
			wishMountainPath.setFamilySuitable(radioButton.getText().equals("Yes"));
		
		//Chiama il metodo del controller applicativo
		try {
			getViewMountainPathInfoController().searchMountainPathByAssistedResearch(wishMountainPath);
			this.executeAction(this.myController);
		} catch (SystemException e) {
			showSystemError();
		}
		
	}
	
	// HO DOVUTO MODIFICARE IL RITORNO IN CASO DI LISTA VUOTA CAUSA SONARCLOUD 
	// QUIDNI RIVEDERE LA CREAZIONE DELLA BEAN
	private String[] checkSelectedBox(List<CheckBox> checkBoxes) {
		List<String> selected = new ArrayList<>();
		for(CheckBox box : checkBoxes) {
			if(box.isSelected())
				selected.add(box.getText().toUpperCase());
		}
		String[] resultArray = Arrays.copyOf(selected.toArray(), selected.size(), String[].class);
		if(resultArray.length == 0) {
			resultArray = null;
		}
		return resultArray;
	}
	
	public ViewMountainPathInfoController getViewMountainPathInfoController() {
		return (ViewMountainPathInfoController) myController;
	}
	
	@Override
	protected String getFXMLFileName() {
		return "assistedResearchLayout";
	}
	
}
