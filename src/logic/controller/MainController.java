package logic.controller;

import java.util.ArrayList;
import java.util.Arrays;

public class MainController extends Controller {
	
	private static final ArrayList<String> noLoginAction = new ArrayList<>(Arrays.asList("btnSearch"));
	
	public MainController(){
		super();
	}
	
	public String onActionRequired(String actionId) {
		String actionToExecute;
		// Controlla se è un'azione accessibile solo se loggati e se l'utente è loggato
		if((!noLoginAction.contains(actionId)) & (!LoginController.isLogged())) {
			actionToExecute = "login";
		}
		else {
			actionToExecute = actionId;
		}
		return actionToExecute;
		
	}
	
	
	@Override
	public void setup() {
		// TODO Auto-generated method stub
		
	}


}
