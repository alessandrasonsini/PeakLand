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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import logic.controller.Controller;
import logic.controller.ProfileController;
import logic.model.bean.LoggedUserBean;
import logic.model.bean.factory.LoggedUserBeanFactory;
import logic.model.exception.DatabaseException;
import logic.model.exception.SystemException;

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
	TextArea txtDescription;
	
	private static final int max_chars = 200;

	private LoggedUserBean currentUser;
	private List<TextInputControl> editableText; 
	
	protected ProfileGraphicController(Controller controller) {
		super(controller);
		this.editableText = Arrays.asList(txtDescription,txtName,txtSurname);
		txtDescription.setTextFormatter(new TextFormatter<String>(change -> change.getControlNewText().length() <= max_chars ? change : null));
		this.currentUser = this.getProfileController().getCurrentUser(MainGraphicController.getInstance().getSessionId());
		this.setupLayout();
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
		LoggedUserBean userBean = new LoggedUserBeanFactory().getLoggedUserBean(txtName.getText(),txtSurname.getText(),txtDescription.getText());
		try {
			this.getProfileController().updateUserInfo(userBean);
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
	
	private void setupLayout() {
		if(currentUser.getName()!= null)
			txtName.setText(currentUser.getName());
		if(currentUser.getSurname()!= null)
			txtSurname.setText(currentUser.getSurname());
		lbLevel.setText(currentUser.getLevel());
		if(currentUser.getImageStream() != null)
			imgProfile.setImage(new Image(currentUser.getImageStream()));
		if(currentUser.getDescription() != null)
			txtDescription.setText(currentUser.getDescription());
		
	}
	
	private ProfileController getProfileController() {
		return (ProfileController)this.myController;
	}
	
	@Override
	protected String getFXMLFileName() {
		return "profileLayout";
	}

}
