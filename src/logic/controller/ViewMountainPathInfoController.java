package logic.controller;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import logic.model.MountainPath;
import logic.model.bean.MountainPathBean;
import logic.model.bean.SimpleMountainPathBean;
import logic.model.bean.factory.MountainPathBeanFactory;
import logic.model.bean.factory.SimpleMountainPathBeanFactory;
import logic.model.dao.MountainPathDao;

public class ViewMountainPathInfoController extends Controller {
	
	private MountainPathBean selectedMountainPath;
	private SearchMountainPathController searchController;
	private AssistedResearchController assistedResearchController;
	private List<SimpleMountainPathBean> searchResults;

	public ViewMountainPathInfoController() {
		super();
		// Prende l'istanza del controller che si occupa della search e che collabora nell'esecuzione del caso d'uso
		this.searchController = new ControllerFactory().getSearchMountainPathController();
		this.assistedResearchController = new ControllerFactory().getAssistedResearchController();
		this.searchResults = null;
	}
	
	// Richiama il metodo del controllore applicativo Search per
	// effettuare la ricerca nel DB
	public List<SimpleMountainPathBean> searchMountainPathByName(String name) {
		this.searchResults = new ArrayList<>();
		
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
	
	public List<ByteArrayInputStream> getImageStreams(){
		return new MountainPathDao().getImagesStream("Mountain path/" + this.selectedMountainPath.getName());
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
	
	public void assistedResearchRequest() {
		setNextPageId("Next assisted research");
	}
	
	public List<SimpleMountainPathBean> searchMountainPathByAssistedResearch(MountainPathBean wishPath){		
		this.searchResults = new ArrayList<>();
		List<MountainPath> resultList = new ArrayList<>();
		resultList = this.assistedResearchController.searchMountainPathByFilter(wishPath);
		for(MountainPath path : resultList) {
			this.searchResults.add(new SimpleMountainPathBeanFactory().getSimpleMountainPath(path));
		}
		setNextPageId("Done assisted research");
		return this.searchResults;
	}
	
	@Override
	public void setNextPageId(String action) {
		switch(action) {
			case "init":
				this.nextPageId = "Search path";
				break;
			case "Item selected":
				this.nextPageId = "View info";
				break;	
			case "Back":
				this.nextPageId = searchController.getNextPageId();
				break;
			case "Next assisted research":
				this.nextPageId = assistedResearchController.getNextPageId();
				break;
			case "Done assisted research":
				this.nextPageId = searchController.getNextPageId();
				break;
			default:
				this.nextPageId = null;
		}
	}
	
	
	
}
