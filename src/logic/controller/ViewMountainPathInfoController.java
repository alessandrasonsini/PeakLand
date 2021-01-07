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
		setNextStepId("Search path");
	}
	
	// Richiama il metodo del controllore applicativo Search per
	// effettuare la ricerca nel DB
	public List<SimpleMountainPathBean> searchMountainPathByName(String name) {
		setNextStepId("View info");
		
		// Elimina contenuti dalle liste di eventuali ricerche precedenti
		this.searchResults.clear();
		this.beanResults.clear();
		
		this.searchResults = this.searchController.searchMountainPathByName(name);
		
		SimpleMountainPathBeanFactory beanFactory = new SimpleMountainPathBeanFactory();
		
		// Converte le entity in bean per poterle passare al controllore grafico
		for(MountainPath path : searchResults) {
				this.beanResults.add(beanFactory.getSimpleMountainPath(path));
		}

		return this.beanResults;
	}
	
	// Ricorda quale MountainPath è stato selezionato
	// dall'utente per poi passare al controller grafico le informazioni relative 
	public void setSelectedItem(SimpleMountainPathBean bean) {
		this.selectedMountainPath = bean;
	}
	
	// Converte il mountain path selezionato dall'utente in una bean
	// per poterla passare al controllore grafico ViewInfo
	public MountainPathBean getMountainPathInfo() {
		setNextStepId("Search path");
		
		MountainPathBeanFactory beanFactory = new MountainPathBeanFactory();
		
		for (MountainPath path : searchResults) {
			if (path.getName().equals(selectedMountainPath.getName())) {
				return beanFactory.getMountainPathBean(path);
			}
		}
		
		return null;
	}
	
	// Ritrona al controllore grafico Search la lista di dei path col
	// nome precedentemente cercato dall'utente
	public List<SimpleMountainPathBean> getSearchResultsList() {
		setNextStepId("View info");
		return this.beanResults;
	}

	
}
