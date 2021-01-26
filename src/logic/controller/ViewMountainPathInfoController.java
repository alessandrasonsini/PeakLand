package logic.controller;

import java.util.ArrayList;
import java.util.List;
import logic.model.MountainPath;
import logic.model.bean.MountainPathBean;
import logic.model.bean.SimpleMountainPathBean;
import logic.model.bean.factory.MountainPathBeanFactory;
import logic.model.bean.factory.SimpleMountainPathBeanFactory;

public class ViewMountainPathInfoController extends Controller {
	
	private MountainPathBean selectedMountainPath;
	private SearchMountainPathController searchController;
	private List<SimpleMountainPathBean> searchResults;

	public ViewMountainPathInfoController() {
		super();
		// Prende l'istanza del controller che si occupa della search e che collabora nell'esecuzione del caso d'uso
		this.searchController = new ControllerFactory().getSearchMountainPathController();
		this.searchResults = new ArrayList<>();
	}
	
	// Richiama il metodo del controllore applicativo Search per
	// effettuare la ricerca nel DB
	public List<SimpleMountainPathBean> searchMountainPathByName(String name) {
		this.searchResults.clear();
		
		List<MountainPath> searchResults = searchController.searchMountainPathByPartialName(name);
		
		SimpleMountainPathBeanFactory beanFactory = new SimpleMountainPathBeanFactory();
		
		// Converte le entity in bean per poterle passare al controllore grafico
		for(MountainPath path : searchResults) {
				this.searchResults.add(beanFactory.getSimpleMountainPath(path));
		}
		sortResultsByVote(this.searchResults);
		return this.searchResults;
	}
	
	// Restituisce le info complete del mountain path selezionato
	public void setSelectedMountainPath(String selectedPath) {
		
		MountainPath searchResult = searchController.searchMountainPathByName(selectedPath);
		
		if(searchResult != null) {
			// Converte la entity in bean e la memorizza, per poterla poi passare alla view che si occupa di visualizzare
			// tutte le info del path
			this.selectedMountainPath = new MountainPathBeanFactory().getMountainPathBean(searchResult);
		}
		else this.selectedMountainPath = null;
		
		// Aggiorno il nextPageId
		setNextPageId("Item selected");
	}
	
	public MountainPathBean getSelectedMountainPath() {
		return this.selectedMountainPath;
	}
	
	public void onBackPressed() {
		setNextPageId("Back");
	}
	
	public List<SimpleMountainPathBean> getPreviousSearchResults(){
		return this.searchResults;
	}
	
	private List<SimpleMountainPathBean> sortResultsByVote(List<SimpleMountainPathBean> results){
		// dummy
		return results;
	}
	
	public List<SimpleMountainPathBean> searchMountainPathByAssistedResearch(MountainPathBean wishPath){
		wishPath = new MountainPathBean();
		//wishPath.setFamilySuitable(true);
		//wishPath.setCycleble(true);
		//String[] ground = {"ROCK","GRASS"};
		//wishPath.setGround(ground);
		wishPath.setRegion("Abruzzo");		
		List<MountainPath> list = new ArrayList<>();
		list = new ControllerFactory().getAssistedResearchController().searchMountainPathByFilter(wishPath);
		for(MountainPath path : list) {
			this.searchResults.add(new SimpleMountainPathBeanFactory().getSimpleMountainPath(path));
		}
		return this.searchResults;
	}
	
	@Override
	public void setNextPageId(String action) {
		String nextPageId;
		switch(action) {
			case "init":
				nextPageId = "Search path";
				break;
			case "Item selected":
				nextPageId = "View info";
				break;	
			case "Back":
				nextPageId = "Search path";
				break;		
			default:
				nextPageId = null;
		}
		this.nextPageId = nextPageId;
	}
	
	
	
}
