package logic.controller;

import logic.model.bean.MountainPathBean;

public class AddNewMountainPathController extends Controller {

	MountainPathBean newPathBean;
	
	public AddNewMountainPathController() {
		super();
		newPathBean = new MountainPathBean();
	}
	
	public void saveNewMountainPath(MountainPathBean newPathBean) {
		// TODO 
	}

	@Override
	public void setup() {
		// TODO Auto-generated method stub
		setNextStepId("Add path");
	}

}
