package logic.controller;

import java.util.ArrayList;
import java.util.List;
import logic.model.MountainPath;
import logic.model.bean.MountainPathBean;
import logic.model.bean.SimpleMountainPathBean;
import logic.model.bean.factory.MountainPathBeanFactory;
import logic.model.bean.factory.SimpleMountainPathBeanFactory;

public class ViewMountainPathInfoController extends Controller {
	
	private static ViewMountainPathInfoController instance;

	private ViewMountainPathInfoController() {
		super();
	}
	
	public static ViewMountainPathInfoController getInstance() {
		if(instance == null) {
			instance = new ViewMountainPathInfoController();
		}
		return instance;
	}
	
	// Richiama il metodo del controllore applicativo Search per
	// effettuare la ricerca nel DB
	public List<SimpleMountainPathBean> searchMountainPathByName(String name) {
		
		// Prende l'istanza del controller che si occupa della search
		SearchMountainPathController searchController = new ControllerFactory().getSearchMountainPathController();
		
		List<MountainPath> searchResults = searchController.searchMountainPathByPartialName(name);
		
		SimpleMountainPathBeanFactory beanFactory = new SimpleMountainPathBeanFactory();
		
		List<SimpleMountainPathBean> beanResults= new ArrayList<>();
		// Converte le entity in bean per poterle passare al controllore grafico
		for(MountainPath path : searchResults) {
				beanResults.add(beanFactory.getSimpleMountainPath(path));
		}
		return beanResults;
	}
	
	// Restituisce le info complete del mountain path selezionato
	public MountainPathBean getMountainPathInfo(String selectedPath) {
		
		// Prende l'istanza del controller che si occupa della search
		SearchMountainPathController searchController = new ControllerFactory().getSearchMountainPathController();
		
		MountainPath searchResult = searchController.searchMountainPathByName(selectedPath);
		
		MountainPathBean selectedPathBean = null;
		if(searchResult != null) {
			// Converte la entity in bean per poterla passare al controller grafico
			selectedPathBean = new MountainPathBeanFactory().getMountainPathBean(searchResult);
		}
		 return selectedPathBean;
	}
	
	@Override
	public String getNextPageId(String action) {
		String nextPageId;
		switch(action) {
			case "init":
				nextPageId = "Search path";
				break;
			case "Item selected":
				nextPageId = "View info";
				break;	
			case "Back":
				nextPageId = "Search path";
				break;		
			default:
				nextPageId = null;
		}
		return nextPageId;
	}
	
	
	
}
