package logic.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import logic.bean.MountainPathBean;
import logic.bean.ReviewBean;
import logic.controller.utils.CurrentLoggedUsers;
import logic.model.MountainPath;
import logic.model.StandardName;
import logic.model.dao.MountainPathDao;
import logic.model.exception.DatabaseException;
import logic.model.exception.SystemException;
import logic.model.exception.TooManyImagesException;
import logic.model.utils.MountainPathConverter;

public class AddNewMountainPathController extends Controller {
	
	private AddReviewController addReviewController;
	
	private static final int MAXIMAGES = 5;
	
	private List<InputStream> pathImages;
	private String newPathName;
	
	public AddNewMountainPathController() {
		super();
		this.addReviewController = new ControllerFactory().getAddReviewController();
		pathImages = new ArrayList<>();
	}

	// Controlla se il nome inserito è già esistente sul db
	public boolean checkName(String name) {
		// Chiama il metodo relativo del controller di search per controllare se il nome è già esistente
		return (new ControllerFactory()
				.getSearchMountainPathController()
				.searchMountainPathByName(StandardName.standardizeName(name)) == null);
	}

	public void saveReview(ReviewBean reviewBean,Integer userId) throws DatabaseException {
		reviewBean.setPathName(this.newPathName);
		reviewBean.setAuthor(CurrentLoggedUsers.getInstance().getCurrentLoggedUser(userId).getUsername());
		addReviewController.saveReview(reviewBean);
	}

	public void saveNewMountainPath(MountainPathBean newPathBean, Integer sessionId) throws DatabaseException, SystemException {
		// A partire dalla bean, prende l'entità mountain path da salvare
		MountainPath newMountainPath = MountainPathConverter.getMountainPath(newPathBean);
		
		// Invoca il metodo del dao per salvare il mountain path sul db e le immagini inserite
		MountainPathDao mountainPathDao = new MountainPathDao();
		mountainPathDao.saveNewMountainPathOnDB(newMountainPath);
		mountainPathDao.uploadImage(this.pathImages, newMountainPath.getName(), CurrentLoggedUsers.getInstance().getCurrentLoggedUser(sessionId).getUsername());
	
		this.newPathName = newMountainPath.getName();
	}
	
	public void setMountainPathImages(List<InputStream> images) throws TooManyImagesException {
		if(images.size() > MAXIMAGES)
			throw new TooManyImagesException();
		else
			this.pathImages = images;
	}

	public void addReviewRequest() {
		setNextPageId("New review");
	}
	
	public void onBackPressed() {
		setNextPageId("Back");
	}
	
	@Override
	public void setNextPageId(String action) {
		switch(action){
			case "init":
				this.nextPageId = "Add path";
				break;
			case "New review":
				this.nextPageId = "Add review";
				break;
			case "Back":
				this.nextPageId = "Add path";
				break;
			default:
				this.nextPageId = null;
		}
	}

}
