package logic.controller;

import java.util.List;
import logic.model.MountainPath;

public class SearchMountainPathController extends Controller {
	
	// Metodo che esegue la ricerca dei mountain path dato un nome parziale
	public List<MountainPath> searchMountainPathByPartialName(String name) {
	
		// Chiama il metodo statico della entity che si occupa della ricerca
		return MountainPath.searchMountainPathByPartialName(name);
	}
	
	public MountainPath searchMountainPathByName(String name) {
		List<MountainPath> searchResults = MountainPath.searchMountainPathByName(name);
		// Ritorna null se la lista è vuota, altrimenti il primo elemento della lista
		// che, poichè il nome è univoco, è anche l'unico elemento della lista
		return searchResults.isEmpty()? null : searchResults.get(0);
		 
	}


	@Override
	public void setup() {
		//setNextStepId("Search path");
	}

	
}
