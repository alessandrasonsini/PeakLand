package logic.controller;

import java.util.List;
import logic.model.MountainPath;
import logic.model.dao.MountainPathDao;

// MI STANNO VENENDO I DUBBI SU QUESTA CLASSE, E' VERAMENTE NECESSARIA?

public class SearchMountainPathController extends Controller {
	
	private MountainPathDao mountainPathDao;
	
	public SearchMountainPathController() {
		super();
		this.mountainPathDao = new MountainPathDao();
	}
	
	// Metodo che esegue la ricerca dei mountain path dato un nome parziale
	public List<MountainPath> searchMountainPathByPartialName(String name) {
		// Chiama il metodo del dao che si occupa di effettuare la ricerca per nome parziale sul db
		return this.mountainPathDao.searchMountainPathByPartialName(name);
	}
	
	public MountainPath searchMountainPathByName(String name) {
		// Chiama il metodo del dao che si occupa di recuperare il mountain path dato il nome intero dal db
		return this.mountainPathDao.searchMountainPathByName(name);
	}

	@Override
	public void setNextPageId(String action) {
		if(action.equals("init"))
			this.nextPageId = "Search path";
		else this.nextPageId = null;
	}
	
	
	
}
