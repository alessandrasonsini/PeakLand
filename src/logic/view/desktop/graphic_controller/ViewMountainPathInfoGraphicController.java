package logic.view.desktop.graphic_controller;

import logic.controller.Controller;
import logic.view.desktop.GraphicController;

public class ViewMountainPathInfoGraphicController extends GraphicController{
	
	public ViewMountainPathInfoGraphicController(Controller controller) {
		super(controller);
	}

	@Override
	protected String getFXMLFileName() {
		return "viewMountainPathInfoLayout";
	}
	
}
