package logic.view.desktop.graphic_controller;

import logic.controller.Controller;
import logic.view.desktop.GraphicController;

public class HomeGraphicController extends GraphicController {

	public HomeGraphicController(Controller controller) {
		super(controller);
	}

	@Override
	protected String getFXMLFileName() {
		// TODO Auto-generated method stub
		return "homeLayout";
	}

}
