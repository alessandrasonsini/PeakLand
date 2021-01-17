package logic.controller;

public class AssistedResearchController extends Controller {
	
	private static AssistedResearchController instance;
	
	private AssistedResearchController() {
		super();
	}
	
	public static AssistedResearchController getInstance() {
		if(instance == null) {
			instance = new AssistedResearchController();
		}
		return instance;
	}

	@Override
	public String getNextPageId(String action) {
		String nextPageId;
		switch(action) {
			case "init":
				nextPageId = "Assisted research";
				break;
			default: 
				nextPageId = null;
		}
		return nextPageId;
			
	}
	

}
