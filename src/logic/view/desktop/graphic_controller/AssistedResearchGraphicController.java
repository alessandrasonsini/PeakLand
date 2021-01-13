package logic.view.desktop.graphic_controller;

import logic.controller.Controller;

public class AssistedResearchGraphicController extends GraphicController{

	protected AssistedResearchGraphicController(Controller controller) {
		super(controller);
	}

	@Override
	protected String getFXMLFileName() {
		return "assistedResearchLayout";
	}

}
