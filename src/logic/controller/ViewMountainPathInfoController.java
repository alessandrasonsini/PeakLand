package logic.controller;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import logic.bean.MountainPathBean;
import logic.bean.ReviewBean;
import logic.bean.SimpleMountainPathBean;
import logic.model.MountainPath;
import logic.model.Review;
import logic.model.dao.MountainPathDao;
import logic.model.dao.ReviewDao;
import logic.model.enums.PageId;
import logic.model.exception.SystemException;
import logic.model.utils.MountainPathConverter;
import logic.model.utils.ReviewConverter;
import logic.model.utils.Sorter;
import logic.model.utils.StandardName;

public class ViewMountainPathInfoController extends Controller {
	
	private MountainPathBean selectedMountainPath;
	private AssistedResearchController assistedResearchController;
	private List<SimpleMountainPathBean> searchResults;
	private MountainPathDao mountainPathDao;
	private ReviewDao reviewDao;

	public ViewMountainPathInfoController() {
		super();
		// Prende l'istanza del controller che si occupa della search e che collabora nell'esecuzione del caso d'uso
		this.mountainPathDao = new MountainPathDao();
		this.reviewDao = new ReviewDao();
		this.assistedResearchController = new ControllerFactory().getAssistedResearchController();

	}

	// Richiama il metodo del controllore applicativo Search per
	// effettuare la ricerca nel DB
	public List<SimpleMountainPathBean> searchMountainPathByName(String name) {
		List<MountainPath> resultList = this.mountainPathDao.searchMountainPathByPartialName(StandardName.standardizeName(name));
		createResultList(resultList);
		return this.searchResults;
	}

	// Restituisce le info complete del mountain path selezionato
	public void setSelectedMountainPath(String selectedPath) {
		MountainPath searchResult = this.mountainPathDao.searchMountainPathByName(StandardName.standardizeName(selectedPath));
		
		if(searchResult != null) {
			// Converte la entity in bean e la memorizza, per poterla poi passare
			// alla view che si occupa di visualizzare tutte le info del path
			this.selectedMountainPath = MountainPathConverter.getMountainPathBean(searchResult);
		}
		else this.selectedMountainPath = null;
	
		setNextPageId("Item selected");
	}
	
	public List<ByteArrayInputStream> getImageStreams(){
		return this.mountainPathDao.getImagesStream(this.selectedMountainPath.getName());
	}
	
	public MountainPathBean getSelectedMountainPath() {
		return this.selectedMountainPath;
	}
	
	public void viewReviewsRequest() {
		setNextPageId("View review");
	}
	
	public List<ReviewBean> getPathReview(String pathName) {
		List<ReviewBean> reviewBeanList = new ArrayList<>();
		
		List<Review> reviewList = this.reviewDao.getReviewFromDb(pathName);
		for (Review review : reviewList) {
			reviewBeanList.add(ReviewConverter.getReviewBean(review));
		}
		
		return reviewBeanList;
	}
	
	public void onBackPressed() {
		setNextPageId("Back");
	}
	
	public List<SimpleMountainPathBean> getPreviousSearchResults(){
		return this.searchResults;
	}
	
	public void assistedResearchRequest() {
		setNextPageId("Next assisted research");
	}
	
	public List<SimpleMountainPathBean> searchMountainPathByAssistedResearch(MountainPathBean wishPath) throws SystemException {
		List<MountainPath> resultList;
		resultList = this.assistedResearchController.searchMountainPathByFilters(wishPath);

		createResultList(resultList);
		setNextPageId("Done assisted research");
		return this.searchResults;
	}
	
	private void createResultList(List<MountainPath> result){
		this.searchResults = new ArrayList<>();
		result = Sorter.sortByVote(result);
		SimpleMountainPathBean bean;
		for (MountainPath path : result) {
			bean = MountainPathConverter.getSimpleMountainPath(path);
			this.searchResults.add(bean);
			
		}
	}
	
	@Override
	public void setNextPageId(String action) {
		switch(action) {
			case "init":
				this.nextPageId = PageId.SEARCH;
				break;
			case "Item selected":
				this.nextPageId = PageId.VIEW_INFO;
				break;	
			case "Back":
				if(this.nextPageId.equals(PageId.VIEW_INFO))
					this.nextPageId = PageId.SEARCH;
				else
					this.nextPageId = PageId.VIEW_INFO;
				break;
			case "Next assisted research":
				this.nextPageId = assistedResearchController.getNextPageId();
				break;
			case "Done assisted research":
				this.nextPageId = PageId.SEARCH;
				break;
			case "View review":
				this.nextPageId = PageId.VIEW_REVIEWS;
				break;
			default:
				this.nextPageId = null;
		}
	}
}
