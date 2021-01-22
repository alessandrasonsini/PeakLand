package logic.controller;

import logic.model.Location;
import logic.model.MountainPath;
import logic.model.Time;
import logic.model.bean.MountainPathBean;

public class AddNewMountainPathController extends Controller {
	
	public AddNewMountainPathController() {
		super();
	}
	
	// Controlla se il nome inserito è già esistente sul db
	public boolean checkName(String name) {
		// Chiama il metodo relativo del controller di search per controllare se il nome è già esistente
		return (new ControllerFactory().getSearchMountainPathController().searchMountainPathByName(name) == null);
	}
	
	public void saveNewMountainPath(MountainPathBean newPathBean) {
		// A partire dalla bean, costruisce l'entità mountain path da salvare
		MountainPath newMountainPath = new MountainPath();
		
		// Ci deve essere un modo più bello per fare sta schifezzaaa
		newMountainPath.setName(newPathBean.getName());
		newMountainPath.setAltitude(newPathBean.getAltitude());
		newMountainPath.setCycleble(newPathBean.isCycleble());
		newMountainPath.setFamilySuitable(newPathBean.isFamilySuitable());
		newMountainPath.setGround(newPathBean.getGround());
		newMountainPath.setHistoricalElements(newPathBean.isHistoricalElements());
		newMountainPath.setLandscape(newPathBean.getLandscape());
		newMountainPath.setLenght(newPathBean.getLenght());
		newMountainPath.setLevel(newPathBean.getLevel());
		newMountainPath.setLocation(new Location(newPathBean.getRegion(),newPathBean.getProvince(),newPathBean.getCity()));
		newMountainPath.setTravelTime(new Time(newPathBean.getHours(), newPathBean.getMinutes()));
		
		// Invoca il metodo della entity che si occupa di salvare sul db
		newMountainPath.saveMountainPathOnDb();
	}

	@Override
	public void setNextPageId(String action) {
		String nextPageId;
		switch(action) {
			case "init":
				nextPageId = "Add path";
				break;
			default: 
				nextPageId = null;
		}
		this.nextPageId = nextPageId;
			
	}

}
