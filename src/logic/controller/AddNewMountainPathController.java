package logic.controller;

import logic.model.bean.MountainPathBean;

public class AddNewMountainPathController extends Controller {

	MountainPathBean newPathBean;
	
	public AddNewMountainPathController() {
		newPathBean = new MountainPathBean();
	}
	
	public void saveNewMountainPath(MountainPathBean newPathBean) {
		
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
	}

	@Override
	public void setup() {
		// TODO Auto-generated method stub
		setNextStepId("Add path");
	}

}
