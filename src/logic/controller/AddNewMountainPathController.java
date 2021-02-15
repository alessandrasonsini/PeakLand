package logic.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import logic.bean.MountainPathBean;
import logic.bean.ReviewBean;
import logic.controller.utils.CurrentLoggedUsers;
import logic.model.LoggedUser;
import logic.model.MountainPath;
import logic.model.dao.LoggedUserDao;
import logic.model.dao.MountainPathDao;
import logic.model.enums.PageId;
import logic.model.exception.DatabaseException;
import logic.model.exception.SystemException;
import logic.model.exception.TooManyImagesException;
import logic.model.utils.MountainPathConverter;
import logic.model.utils.StandardName;

public class AddNewMountainPathController extends Controller {
	
	private AddReviewController addReviewController;
	
	private static final int MAXIMAGES = 5;
	
	private List<InputStream> pathImages;
	private String newPathName;
	private MountainPathDao mountainPathDao;
	private LoggedUserDao loggedUserDao;
	private LoggedUser author;
	
	public AddNewMountainPathController() {
		super();
		this.addReviewController = new ControllerFactory().getAddReviewController();
		this.pathImages = new ArrayList<>();
		this.mountainPathDao = new MountainPathDao();
		this.loggedUserDao = new LoggedUserDao();
	}

	// Controlla se il nome inserito è già esistente sul db
	public boolean checkName(String name) {
		// Chiama il metodo relativo del controller di search per controllare se il nome è già esistente
		return (this.mountainPathDao.searchMountainPathByName(StandardName.standardizeName(name)) == null);
	}

	public void saveReview(ReviewBean reviewBean) throws DatabaseException {
		reviewBean.setPathName(this.newPathName);
	
		reviewBean.setAuthor(this.author.getUsername());
		addReviewController.saveReview(reviewBean);
		this.updateUserPeakCoin();
		
	}

	public void saveNewMountainPath(MountainPathBean newPathBean, Integer userId) throws DatabaseException, SystemException {
		// A partire dalla bean, prende l'entità mountain path da salvare
		MountainPath newMountainPath = MountainPathConverter.getMountainPath(newPathBean);
	
		// Invoca il metodo del dao per salvare il mountain path sul db e le immagini inserite
		this.mountainPathDao.saveNewMountainPathOnDB(newMountainPath);
		
		this.author = CurrentLoggedUsers.getInstance().getCurrentLoggedUser(userId);
		this.mountainPathDao.uploadImages(this.pathImages, newMountainPath.getName(), this.author.getUsername());
	
		this.newPathName = newMountainPath.getName();
		this.updateUserPeakCoin();
	}
	
	private void updateUserPeakCoin() throws DatabaseException {
		// Aggiorna il numero di PeakCoin dell'utente e il suo stato sul db
		this.author.addPeakCoin();
		this.loggedUserDao.saveLoggedUserOnDb(author);
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
				this.nextPageId = PageId.ADD_PATH;
				break;
			case "New review":
				this.nextPageId = this.addReviewController.getNextPageId();
				break;
			case "Back":
				this.nextPageId = PageId.ADD_PATH;
				break;
			default:
				this.nextPageId = null;
		}
	}

}
