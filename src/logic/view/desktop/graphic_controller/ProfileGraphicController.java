package logic.view.desktop.graphic_controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import logic.bean.LoggedUserBean;
import logic.controller.Controller;
import logic.controller.ProfileController;
import logic.model.exception.DatabaseException;
import logic.model.exception.SystemException;
import logic.model.utils.LoggedUserConverter;

public class ProfileGraphicController extends GraphicController {
	
	@FXML
	Button btnEditImage;
	
	@FXML
	ImageView imgProfile;
	
	@FXML
	Button btnLogOut;
	
	@FXML
	TextField txtName;
	
	@FXML
	TextField txtSurname;
	
	@FXML
	Label lbLevel;
	
	@FXML
	Button btnEdit;
	
	@FXML
	Button btnSave;
	
	@FXML
	Label lbPeakCoin;
	
	@FXML
	TextArea txtDescription;
	
	private static final int MAX_CHARS = 200;

	private List<TextInputControl> editableText; 
	
	protected ProfileGraphicController(Controller controller) {
		super(controller);
	}
	
	//Abilita le text field per poter modificare gli elementi
	@FXML
	public void onEditRequest(ActionEvent event) {
		for(TextInputControl text : this.editableText) {
			text.setEditable(true);
			text.requestFocus();
		}
		
		btnEdit.setDisable(true);
		btnSave.setDisable(false);
	}
	
	@FXML
	public void onSaveModification(ActionEvent event) {
		LoggedUserBean userBean = LoggedUserConverter.getLoggedUserBean(txtName.getText(),txtSurname.getText(),txtDescription.getText(),lbLevel.getText());
		try {
			this.getProfileController().updateUserInfo(userBean);
			showMessage("Update succeded", "Your dates was updated successfully", AlertType.INFORMATION);
		} catch (DatabaseException e) {
			showDatabaseError();
		}
		
		for(TextInputControl text : this.editableText) {
			text.setEditable(false);
		}
		
		btnEdit.setDisable(false);
		btnSave.setDisable(true);
		
	}
	
	@FXML
	public void onEditProfile(ActionEvent event) {
		File selectedFile = new FileChooser().showOpenDialog(null);
		try {
			InputStream selectedFileInputStream = new FileInputStream(selectedFile);
			this.getProfileController().setProfileImage(selectedFileInputStream);
			// fa il refresh della pagina
			executeAction(this.myController);
		} catch (SystemException e) {
			showSystemError();
		} catch (FileNotFoundException e) {
			showSystemError();
		}
	}
	
	@FXML
	public void onLogOutRequest(ActionEvent event) {
		this.getProfileController().logOut();
		executeAction(this.myController);
	}
	
	@Override
	protected void setupLayout() {
		this.editableText = Arrays.asList(txtDescription,txtName,txtSurname);
		LoggedUserBean currentUser = this.getProfileController().getCurrentUser(MainGraphicController.getInstance().getSessionId());
	
		txtDescription.setTextFormatter(new TextFormatter<String>(change -> change.getControlNewText().length() <= MAX_CHARS ? change : null));
		txtName.setText(currentUser.getNameAsText());
		txtSurname.setText(currentUser.getSurnameAsText());
		txtDescription.setText(currentUser.getDescriptionAsText());
		lbPeakCoin.setText(currentUser.getPeakCoinAsText());
		lbLevel.setText(currentUser.getLevelAsText());
		if(currentUser.getImageStream() != null)
			imgProfile.setImage(new Image(currentUser.getImageStream()));

	}
	
	private ProfileController getProfileController() {
		return (ProfileController)this.myController;
	}
	
	@Override
	protected String getFXMLFileName() {
		return "profileLayout";
	}

}
