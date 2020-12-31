package logic.view.desktop.graphic_controller;

import logic.controller.Controller;
import logic.controller.HomeController;

public class HomeGraphicController extends GraphicController {

	public HomeGraphicController(Controller controller) {
		super(controller);
	}

	@Override
	protected String getFXMLFileName() {
		return "homeLayout";
	}
	
	public HomeController getHomeGraphicController() {
		return (HomeController) myController;
	}

}
