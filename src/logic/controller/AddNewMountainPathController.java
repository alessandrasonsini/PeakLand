package logic.controller;

import logic.model.Location;
import logic.model.MountainPath;
import logic.model.Time;
import logic.model.bean.MountainPathBean;

public class AddNewMountainPathController extends Controller {

	//MountainPathBean newPathBean;
	
	public AddNewMountainPathController() {
		super();
		//newPathBean = new MountainPathBean();
	}
	
	// Controlla se il nome inserito è già esistente sul db
	public boolean checkName(String name) {
		// Chiama il metodo relativo del controller di search
		if(new ControllerFactory().getSearchMountainPathController().searchMountainPathByName(name) != null) {
			// Gestisci l'errore
			return false;
		}
		else {
			// Vai avanti con l'inserimento
			return true;
			
		}
		
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
	public void setup() {
		// TODO Auto-generated method stub
		setNextStepId("Add path");
	}

}
