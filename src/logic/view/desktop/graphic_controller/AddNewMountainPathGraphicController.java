package logic.view.desktop.graphic_controller;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import logic.controller.AddNewMountainPathController;
import logic.controller.Controller;
import logic.model.bean.MountainPathBean;
import logic.model.enums.DifficultyLevelEnum;
import logic.model.enums.GroundEnum;
import logic.model.enums.LandscapeEnum;
import logic.model.exception.DatabaseException;

public class AddNewMountainPathGraphicController extends GraphicController {
	
	@FXML
	private TextField txtName;
	
	@FXML
	private TextField txtAltitude;
	
	@FXML
	private TextField txtRegion;
	
	@FXML
	private TextField txtCity;
	
	@FXML
	private TextField txtLenght;
	
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
	private TextField txtHours;
	
	@FXML
	private TextField txtMinutes;
	
	@FXML
	private Button btnLoadPhoto;
	
	@FXML
	private Button btnSavePath;
	
	@FXML
	private ToggleGroup levelGroup;
	
	@FXML
	private ToggleGroup cycleGroup;
	
	@FXML
	private ToggleGroup histGroup;
	
	@FXML
	private ToggleGroup famGroup;
	
	@FXML
	private TextField txtProvince;
	
	@FXML
	private AnchorPane panePathInfo;
	
	public AddNewMountainPathGraphicController(Controller controller) {
		super(controller);
		panePathInfo.setDisable(true);
	}

	@FXML 
	public void onNameEntered(ActionEvent event) {
		// Chiama il metodo del controller applicativo
		if(getAddNewMountainPathController().checkName(txtName.getText())){
			// Permette l'inserimento delle altre info
			panePathInfo.setDisable(false);
		}
		else {
			// Disabilita l'inserimento di altre info
			panePathInfo.setDisable(true);
			// Mostra il messaggio di errore
			showError("Add new path error", "Mountain path with entered name already exists");
		}
	}
	
	@FXML
	public void onSavePath(ActionEvent event) {
		// Inizializza la bean con tutti i campi inseriti
		MountainPathBean newPathBean = new MountainPathBean();
		newPathBean.setName(txtName.getText());
		newPathBean.setAltitude(Integer.parseInt(txtAltitude.getText()));
		newPathBean.setRegion(txtRegion.getText());
		newPathBean.setProvince(txtProvince.getText());
		newPathBean.setCity(txtCity.getText());
		newPathBean.setLenght(Integer.parseInt(txtLenght.getText()));
		newPathBean.setLevel(DifficultyLevelEnum.valueOf(( (RadioButton) levelGroup.getSelectedToggle()).getText().toUpperCase()));
		newPathBean.setLandscape(checkSelectedLandscape());
		newPathBean.setGround(checkSelectedGround());
		newPathBean.setCycle(( (RadioButton) cycleGroup.getSelectedToggle()).getText().equals("Yes"));
		newPathBean.setHistoricalElements(( (RadioButton) histGroup.getSelectedToggle()).getText().equals("Yes"));
		newPathBean.setFamilySuitable(( (RadioButton) famGroup.getSelectedToggle()).getText().equals("Yes"));
		newPathBean.setHours(Integer.parseInt(txtHours.getText()));
		newPathBean.setMinutes(Integer.parseInt(txtMinutes.getText()));
		//gestire salvataggio immagine
		
		// Chiama il metodo del controller applicativo per il salvataggio del nuovo mountain path
		try {
			getAddNewMountainPathController().saveNewMountainPath(newPathBean);
		} catch (DatabaseException e) {
			showDatabaseError();
		}
	}
	
	@Override
	protected String getFXMLFileName() {
		return "addNewMountainPathLayout";
	}
	
	//trovare il modo di farlo più elegantemente
	private List<LandscapeEnum> checkSelectedLandscape(){
		List<LandscapeEnum> checked = new ArrayList<>();
		
		if (ckBoxMountain.isSelected()) {
			checked.add(LandscapeEnum.valueOf(ckBoxMountain.getText().toUpperCase()));
		}
		if (ckBoxLake.isSelected()) {
			checked.add(LandscapeEnum.valueOf(ckBoxLake.getText().toUpperCase()));
		}
		if (ckBoxSea.isSelected()) {
			checked.add(LandscapeEnum.valueOf(ckBoxSea.getText().toUpperCase()));
		}
		
		return checked;
	}
	
	//trovare il modo di farlo più elegantemente
	private List<GroundEnum> checkSelectedGround(){
		List<GroundEnum> checked = new ArrayList<>();
		
		if (ckBoxRock.isSelected()) {
			checked.add(GroundEnum.valueOf(ckBoxRock.getText().toUpperCase()));
		}
		if (ckBoxGrass.isSelected()) {
			checked.add(GroundEnum.valueOf(ckBoxGrass.getText().toUpperCase()));
		}
		if (ckBoxWood.isSelected()) {
			checked.add(GroundEnum.valueOf(ckBoxWood.getText().toUpperCase()));
		}
		
		return checked;
	}
	
	public AddNewMountainPathController getAddNewMountainPathController() {
		return (AddNewMountainPathController) myController;
	}
	
}
