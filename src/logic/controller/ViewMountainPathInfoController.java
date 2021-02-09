package logic.controller;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import logic.model.MountainPath;
import logic.model.Review;
import logic.model.bean.MountainPathBean;
import logic.model.bean.ReviewBean;
import logic.model.bean.SimpleMountainPathBean;
import logic.model.bean.factory.MountainPathBeanFactory;
import logic.model.bean.factory.ReviewBeanFactory;
import logic.model.bean.factory.SimpleMountainPathBeanFactory;
import logic.model.dao.MountainPathDao;
import logic.model.dao.ReviewDao;
import logic.model.exception.SystemException;

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
		
		List<MountainPath> resultList = searchController.searchMountainPathByPartialName(name);
		
		SimpleMountainPathBeanFactory beanFactory = new SimpleMountainPathBeanFactory();
		
		// Converte le entity in bean per poterle passare al controllore grafico
		for(MountainPath path : resultList) {
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
	
	public void viewReviewsRequest() {
		setNextPageId("View reviews");
	}
	
	public List<ReviewBean> getPathReview(String pathName) {
		List<ReviewBean> reviewBeanList = new ArrayList<>();
		
		List<Review> reviewList = new ReviewDao().getReviewFromDb(pathName);;
		for (Review review : reviewList) {
			reviewBeanList.add(new ReviewBeanFactory().getReviewBean(review));
		}
		
		return reviewBeanList;
	}
	
	public void onBackPressed() {
		setNextPageId("Back");
	}
	
	public List<SimpleMountainPathBean> getPreviousSearchResults(){
		return this.searchResults;
	}
	
	private List<SimpleMountainPathBean> sortResultsByVote(List<SimpleMountainPathBean> results){
		results.sort(Comparator.comparing(SimpleMountainPathBean::getVote).reversed());
		
		return results;
	}
	
	public void assistedResearchRequest() {
		setNextPageId("Next assisted research");
	}
	
	public List<SimpleMountainPathBean> searchMountainPathByAssistedResearch(MountainPathBean wishPath) throws SystemException{		
		this.searchResults = new ArrayList<>();
		List<MountainPath> resultList;
		resultList = this.assistedResearchController.searchMountainPathByFilters(wishPath);
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
				if(this.nextPageId.equals("View info"))
					this.nextPageId = searchController.getNextPageId();
				else this.nextPageId = "View info";
				break;
			case "Next assisted research":
				this.nextPageId = assistedResearchController.getNextPageId();
				break;
			case "Done assisted research":
				this.nextPageId = searchController.getNextPageId();
				break;
			case "View reviews":
				this.nextPageId = "View reviews";
				break;
			default:
				this.nextPageId = null;
		}
	}
}
