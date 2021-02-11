package logic.controller;

import java.util.ArrayList;
import java.util.Arrays;

import logic.model.enums.PageId;

public class MainController extends Controller {
	
	private static final ArrayList<PageId> noLoginAction = new ArrayList<>(Arrays.asList(PageId.VIEW_INFO, PageId.HOME));
	
	public MainController(){
		super();
	}
	
	// Controlla se è necessario che parta il login oppure no
	public PageId onActionRequired(PageId actionId, Integer sessionId) {
		PageId actionToExecute;
		// Controlla se è un'azione accessibile solo se loggati e se l'utente è loggato
		if((!noLoginAction.contains(actionId)) && (!LoginController.isLogged(sessionId))){
			actionToExecute = PageId.LOGIN ;
		}
		else {
			actionToExecute = actionId;
		}
		return actionToExecute;
	}

	@Override
	public void setNextPageId(String action) {
		this.nextPageId = PageId.HOME;
	}
	

}
