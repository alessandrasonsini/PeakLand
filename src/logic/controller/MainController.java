package logic.controller;

import java.util.ArrayList;
import java.util.Arrays;

public class MainController extends Controller {
	
	private static final ArrayList<String> noLoginAction = new ArrayList<>(Arrays.asList("View info"));
	
	public MainController(){
		super();
	}
	
	// Controlla se è necessario che parta il login oppure no
	public String onActionRequired(String actionId, Integer sessionId) {
		String actionToExecute;
		// Controlla se è un'azione accessibile solo se loggati e se l'utente è loggato
		if((!noLoginAction.contains(actionId)) && (!LoginController.isLogged(sessionId))){
			actionToExecute = "Login";
		}
		else {
			actionToExecute = actionId;
		}
		return actionToExecute;
		
	}

	@Override
	public void setNextPageId(String action) {

	}
	

}
