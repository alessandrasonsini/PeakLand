package logic.controller;

import java.util.List;
import logic.model.MountainPath;

public class SearchMountainPathController extends Controller {
	
	// Metodo che esegue la ricerca dei mountain path dato un nome parziale
	public List<MountainPath> searchMountainPathByName(String name) {
	
		// Chiama il metodo statico della entity che si occupa della ricerca
		List<MountainPath> searchResults = MountainPath.searchMountainPathByName(name);
		
		
		/*List<SimpleMountainPathBean> beanResults = new ArrayList<>();
		
		SimpleMountainPathBeanFactory beanFactory = new SimpleMountainPathBeanFactory();
		
		// Converte le entity in bean
		for(MountainPath path : searchResults) {
				beanResults.add(beanFactory.getSimpleMountainPath(path));
		}

		return beanResults;*/
		
		return searchResults;
	}


	@Override
	public void setup() {
		//setNextStepId("Search path");
	}

	
}
