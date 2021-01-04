package logic.controller;

import logic.model.MountainPath;
import logic.model.bean.MountainPathBean;

public class AddNewMountainPathController extends Controller {

	//MountainPathBean newPathBean;
	
	public AddNewMountainPathController() {
		super();
		//newPathBean = new MountainPathBean();
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
		newMountainPath.setLocationCity(newPathBean.getLocationCity());
		newMountainPath.setLocationRegion(newPathBean.getLocationRegion());
		newMountainPath.setTravelTime(newPathBean.getTravelTime());
		
		// Invoca il metodo della entity che si occupa di salvare sul db
		newMountainPath.saveMountainPathOnDb();
	}

	@Override
	public void setup() {
		// TODO Auto-generated method stub
		setNextStepId("Add path");
	}

}
