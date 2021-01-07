package logic.controller;

import java.util.ArrayList;
import java.util.List;

import logic.model.MountainPath;
import logic.model.bean.MountainPathBean;
import logic.model.bean.SimpleMountainPathBean;
import logic.model.bean.factory.MountainPathBeanFactory;
import logic.model.bean.factory.SimpleMountainPathBeanFactory;

public class ViewMountainPathInfoController extends Controller {

	SearchMountainPathController searchController;
	ControllerFactory controllerFactory;
	List<SimpleMountainPathBean> beanResults;
	List<MountainPath> searchResults;
	SimpleMountainPathBean selectedMountainPath;
	
	@Override
	public void setup() {
		
		this.beanResults = new ArrayList<>();
		this.searchResults = new ArrayList<>();
		this.controllerFactory = new ControllerFactory();
		
		// Crea riferimento al controller applicativo SearchMountainPath
		// per poter eseguire le ricerche nel DB
		try {
			this.searchController = (SearchMountainPathController) controllerFactory.getController("search");
		} catch (Exception e) {
			System.out.println("Impossibile istanziare controllore");
			// Gestione pagina di errore
			// capire come metterlo perché c'è mismatch dei tipi di controller
			//this.searchController = new ErrorController();
		}
		
		//setNextStepId("View info");
		setNextStepId("Search path");
	}
	
	public List<SimpleMountainPathBean> searchMountainPathByName(String name) {
		setNextStepId("View info");
		
		this.searchResults = this.searchController.searchMountainPathByName(name);
		
		SimpleMountainPathBeanFactory beanFactory = new SimpleMountainPathBeanFactory();
		
		// Converte le entity in bean
		for(MountainPath path : searchResults) {
				this.beanResults.add(beanFactory.getSimpleMountainPath(path));
		}

		return this.beanResults;
	}
	
	// Mantiene l'informazione su quale MountainPath è stato selezionato
	// dall'utente per poi passare al controller grafico le informazioni relative 
	public void setSelectedItem(SimpleMountainPathBean bean) {
		this.selectedMountainPath = bean;
	}
	
	public MountainPathBean getMountainPathInfo() {
		MountainPathBeanFactory beanFactory = new MountainPathBeanFactory();
		
		// INSERIRE ANCHE RECUPERO DELLE REVIEWS	
		
		for (MountainPath path : searchResults) {
			if (path.getName().equals(selectedMountainPath.getName())) {
				return beanFactory.getMountainPathBean(path);
			}
		}
		
		return null;
	}

	
}
