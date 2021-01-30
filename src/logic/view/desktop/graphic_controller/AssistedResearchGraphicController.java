package logic.view.desktop.graphic_controller;

import logic.controller.Controller;
import logic.controller.ViewMountainPathInfoController;
import logic.model.bean.MountainPathBean;
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
	private TextField txtRegion;
	
	@FXML
	private TextField txtCity;
	
	@FXML
	private TextField txtProvince;
	
	@FXML
	private RadioButton radioBtnT;
	
	@FXML
	private RadioButton radioBtnE;
	
	@FXML
	private RadioButton radioBtnEE;
	
	@FXML
	private RadioButton radioBtnEEA;
	
	@FXML
	private CheckBox ckBoxMountain;
	
	@FXML
	private CheckBox ckBoxLake;
	
	@FXML
	private CheckBox ckBoxSea;
	
	@FXML
	private CheckBox ckBoxRock;
	
	@FXML
	private CheckBox ckBoxGrass;
	
	@FXML
	private CheckBox ckBoxWood;
	
	@FXML
	private RadioButton radioBtnCycleY;
	
	@FXML
	private RadioButton radioBtnCycleN;
	
	@FXML
	private RadioButton radioBtnHistY;
	
	@FXML
	private RadioButton radioBtnHistN;
	
	@FXML
	private RadioButton radioBtnFamY;
	
	@FXML
	private RadioButton radioBtnFamN;
	
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
		ground = new ArrayList<>(Arrays.asList(ckBoxGrass,ckBoxRock,ckBoxWood));
		landscape = new ArrayList<>(Arrays.asList(ckBoxMountain,ckBoxSea,ckBoxLake));
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
		if(txtRegion != null && !txtRegion.getText().isEmpty())
			wishMountainPath.setRegion(txtRegion.getText());
		if(txtProvince != null && !txtProvince.getText().isEmpty())	
			wishMountainPath.setProvince(txtProvince.getText());
		if(txtCity != null && !txtCity.getText().isEmpty())		
			wishMountainPath.setCity(txtCity.getText());
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
	
	private String[] checkSelectedBox(List<CheckBox> checkBoxs) {
		List<String> selected = new ArrayList<>();
		for(CheckBox box : checkBoxs) {
			if(box.isSelected())
				selected.add(box.getText().toUpperCase());
		}
		if(selected.isEmpty())
			return (String[])null;
		else
			return Arrays.copyOf(selected.toArray(), selected.size(), String[].class);
	}
	
	public ViewMountainPathInfoController getViewMountainPathInfoController() {
		return (ViewMountainPathInfoController) myController;
	}
	
	@Override
	protected String getFXMLFileName() {
		return "assistedResearchLayout";
	}
	
}
