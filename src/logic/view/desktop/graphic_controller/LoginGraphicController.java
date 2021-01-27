package logic.view.desktop.graphic_controller;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import logic.controller.Controller;
import logic.controller.LoginController;
import logic.model.bean.CredentialBean;
import logic.model.exception.DatabaseException;
import logic.model.exception.EmptyMandatoryFieldsException;
import logic.model.exception.InvalidCredentialException;
import logic.model.exception.InvalidUsernameException;
import logic.model.exception.WrongInputException;

public class LoginGraphicController extends GraphicController {
	
	@FXML 
	Button btnLogin;
	
	@FXML
	Button btnSignIn;
	
	@FXML
	TextField txtUsername;
	
	@FXML
	PasswordField txtPassword;
	
	@FXML
	PasswordField txtConfirmPassword;
	
	private ArrayList<TextField> mandatoryFields;
	
	protected LoginGraphicController(Controller controller) {
		super(controller);
		this.mandatoryFields = new ArrayList<>(Arrays.asList(txtUsername, txtPassword));
	}
	
	@FXML
	public void onActionRequest(ActionEvent event) {
		CredentialBean credential = new CredentialBean();
		try {
			Integer id = null;
			if(event.getSource().equals(btnLogin)) {
				credential.setCredential(txtUsername.getText(),txtPassword.getText());
				// Chiama il metodo del controller applicativo per eseguire il login
				id = getLoginController().loginAction(credential);
			}
			else if(event.getSource().equals(btnSignIn)) {
				mandatoryFields.add(txtConfirmPassword);
				credential.setCredential(txtUsername.getText(),txtPassword.getText(),txtConfirmPassword.getText());
				// chiama il metodo applicativo per eseguire il sign in
				id = getLoginController().signInAction(credential);
			}
			MainGraphicController.getInstance().loginSucceded(id);
		}catch(EmptyMandatoryFieldsException e) {
			this.displayEmptyFieldError();
		}catch (InvalidUsernameException e) {
			showError("Login error", "Credentials are not valid");
		}catch (InvalidCredentialException e) {
			showError("Sign in error", "Username not available");
		}catch(WrongInputException e) {
			showError("Sign in error", "Password mismatch");
		}catch(DatabaseException e) {
			showDatabaseError();
		}
		
	}
	
	private void displayEmptyFieldError() {
		for(int i = 0; i < mandatoryFields.size(); i++)
			mandatoryFields.get(i).setStyle("-fx-border-color: FF0000;");
	}
	
	@Override
	protected String getFXMLFileName() {
		return "loginLayout";
	}
	
	private LoginController getLoginController() {
		return (LoginController)myController;
	}
	
	
}