package logic.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import logic.controller.utils.CurrentLoggedUsers;
import logic.model.Location;
import logic.model.MountainPath;
import logic.model.Time;
import logic.model.bean.MountainPathBean;
import logic.model.dao.MountainPathDao;
import logic.model.exception.DatabaseException;
import logic.model.exception.SystemException;
import logic.model.exception.TooManyImagesException;

public class AddNewMountainPathController extends Controller {
	
	private static final int MAXIMAGES = 5;
	
	private List<File> pathImages;
	
	public AddNewMountainPathController() {
		super();
		pathImages = new ArrayList<>();
	}
	
	// Controlla se il nome inserito è già esistente sul db
	public boolean checkName(String name) {
		
		String nameToSearch = name.replaceFirst(name.substring(0,0), name.substring(0,0).toUpperCase());
		// Chiama il metodo relativo del controller di search per controllare se il nome è già esistente
		return (new ControllerFactory().getSearchMountainPathController().searchMountainPathByName(nameToSearch) == null);
	}
	
	public void saveNewMountainPath(MountainPathBean newPathBean, Integer sessionId) throws DatabaseException, SystemException {
		// A partire dalla bean, costruisce l'entità mountain path da salvare
		MountainPath newMountainPath = new MountainPath();
		
		// Ci deve essere un modo più bello per fare sta schifezzaaa
		newMountainPath.setName(newPathBean.getName());
		newMountainPath.setAltitude(newPathBean.getAltitude());
		newMountainPath.setCycleble(newPathBean.isCycleble());
		newMountainPath.setFamilySuitable(newPathBean.isFamilySuitable());
		newMountainPath.setGroundFromString(newPathBean.getGround());
		newMountainPath.setHistoricalElements(newPathBean.isHistoricalElements());
		newMountainPath.setLandscapeFromString(newPathBean.getLandscape());
		newMountainPath.setLenght(newPathBean.getLenght());
		newMountainPath.setLevelFromString(newPathBean.getLevel());
		newMountainPath.setLocation(new Location(newPathBean.getRegion(),newPathBean.getProvince(),newPathBean.getCity()));
		newMountainPath.setTravelTime(new Time(newPathBean.getHours(), newPathBean.getMinutes()));
		
		// Invoca il metodo del dao per salvare il mountain path sul db e le immagini inserite
		MountainPathDao mountainPathDao = new MountainPathDao();
		mountainPathDao.saveNewMountainPathOnDB(newMountainPath);
		mountainPathDao.uploadImage(this.pathImages, "Mountain path/" + newMountainPath.getName() + "/" + CurrentLoggedUsers.getInstance().getCurrentLoggedUser(sessionId).getUsername());
		

	}
	
	public void setMountainPathImages(List<File> images) throws TooManyImagesException {
		if(images.size() > MAXIMAGES)
			throw new TooManyImagesException();
		else
			this.pathImages = images;
		
	}

	@Override
	public void setNextPageId(String action) {
		if(action.equals("init"))
			this.nextPageId = "Add path";
		else this.nextPageId = null;
	}

}
