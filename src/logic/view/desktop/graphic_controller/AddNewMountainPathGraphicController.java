package logic.view.desktop.graphic_controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import logic.controller.AddNewMountainPathController;
import logic.controller.Controller;
import logic.model.bean.MountainPathBean;
import logic.model.exception.DatabaseException;
import logic.model.exception.SystemException;
import logic.model.exception.TooManyImagesException;

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
	private Label lbLoad;
	
	@FXML
	private AnchorPane panePathInfo;
	
	private List<CheckBox> ground;
	private List<CheckBox> landscape;

	public AddNewMountainPathGraphicController(Controller controller) {
		super(controller);
		panePathInfo.setDisable(true);
		ground = new ArrayList<>(Arrays.asList(ckBoxGrass,ckBoxRock,ckBoxWood));
		landscape = new ArrayList<>(Arrays.asList(ckBoxMountain,ckBoxSea,ckBoxLake));
		lbLoad.setText("");
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
	public void onLoadPhoto(ActionEvent event) {
		List<File> selectedFileList = new FileChooser().showOpenMultipleDialog(null);
		try {
			List<InputStream> selectedFileListInputStream = new ArrayList<>();
			for (File f : selectedFileList) {
				selectedFileListInputStream.add(new FileInputStream(f));
			}
			getAddNewMountainPathController().setMountainPathImages(selectedFileListInputStream);
			lbLoad.setText("Images loaded");
		} catch (TooManyImagesException e) {
			this.showError("Load error", "Too many images selected");
		} catch (FileNotFoundException e) {
			showSystemError();
		}
	}
	
	@FXML
	public void onAddReview(ActionEvent event) {
		System.out.println("dentro onAddReview");
		getAddNewMountainPathController().addReviewRequest();
		this.executeAction(this.myController);
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
		newPathBean.setLevel(((RadioButton) levelGroup.getSelectedToggle()).getText().toUpperCase());
		newPathBean.setLandscape(checkSelectedBox(landscape));
		newPathBean.setGround(checkSelectedBox(ground));
		newPathBean.setCycleble(( (RadioButton) cycleGroup.getSelectedToggle()).getText().equals("Yes"));
		newPathBean.setHistoricalElements(( (RadioButton) histGroup.getSelectedToggle()).getText().equals("Yes"));
		newPathBean.setFamilySuitable(( (RadioButton) famGroup.getSelectedToggle()).getText().equals("Yes"));
		newPathBean.setHours(Integer.parseInt(txtHours.getText()));
		newPathBean.setMinutes(Integer.parseInt(txtMinutes.getText()));
		
		// Chiama il metodo del controller applicativo per il salvataggio del nuovo mountain path
		try {
			getAddNewMountainPathController().saveNewMountainPath(newPathBean, MainGraphicController.getInstance().getSessionId());
			// Mostra il messaggio di avvenuto salvataggio
			showMessage("Add succeded", "The new mountain path is now on the system ", AlertType.INFORMATION);
		} catch (DatabaseException e) {
			showDatabaseError();
		} catch(SystemException e) {
			showSystemError();
		}
		
	}
	
	@Override
	protected String getFXMLFileName() {
		return "addNewMountainPathLayout";
	}
	
	private String[] checkSelectedBox(List<CheckBox> checkBoxs) {
		List<String> selected = new ArrayList<>();
		for(CheckBox box : checkBoxs) {
			if(box.isSelected())
				selected.add(box.getText().toUpperCase());
		}
		return Arrays.copyOf(selected.toArray(), selected.size(), String[].class);
	}
	
	public AddNewMountainPathController getAddNewMountainPathController() {
		return (AddNewMountainPathController) myController;
	}
	
}
