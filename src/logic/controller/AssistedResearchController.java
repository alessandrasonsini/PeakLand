package logic.controller;

public class AssistedResearchController extends Controller {
	
	public AssistedResearchController() {
		super();
	}

	@Override
	public void setNextPageId(String action) {
		String nextPageId;
		switch(action) {
			case "init":
				nextPageId = "Assisted research";
				break;
			default: 
				nextPageId = null;
		}
		this.nextPageId = nextPageId;
			
	}
	

}
