package logic.controller;

import java.util.ArrayList;
import java.util.List;

import logic.model.MountainPath;
import logic.model.bean.SimpleMountainPathBean;
import logic.model.bean.factory.SimpleMountainPathBeanFactory;

public class SearchMountainPathController extends Controller {
	
	List<MountainPath> searchResults;
	
	// Metodo che esegue la ricerca dei mountain path dato un nome parziale
	public List<SimpleMountainPathBean> searchMountainPathByName(String name) {
		
		// Chiama il metodo statico della entity che si occupa della ricerca
		searchResults = MountainPath.searchMountainPathByName(name);
		
		List<SimpleMountainPathBean> beanResults = new ArrayList<>();
		
		SimpleMountainPathBeanFactory beanFactory = new SimpleMountainPathBeanFactory();
		
		// Converte le entity in bean
		for(MountainPath path : searchResults) {
				beanResults.add(beanFactory.getSimpleMountainPath(path));
		}

		return beanResults;
	}


	@Override
	public void setup() {
		setNextStepId("Search path");
	}

	
}
