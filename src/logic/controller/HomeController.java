package logic.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import logic.controller.utils.CurrentLoggedUsers;
import logic.model.LoggedUser;
import logic.model.MountainPath;
import logic.model.bean.LoggedUserBean;
import logic.model.bean.factory.LoggedUserBeanFactory;
import logic.model.bean.factory.SimpleMountainPathBeanFactory;
import logic.model.dao.MountainPathDao;
import logic.model.bean.SimpleMountainPathBean;

public class HomeController extends Controller {

	private MountainPathDao mountainPathDao;
	
	public HomeController() {
		super();
		this.mountainPathDao = new MountainPathDao();
	}
	
	/* COPIATO DA PROFILE CONTROLLER QUINDI CAPIRE COME FARE PER NON RIPETERLO */
	// Prende l'utente corrente
	public LoggedUserBean getCurrentUser(Integer id) {
		LoggedUser currentUser = CurrentLoggedUsers.getInstance().getCurrentLoggedUser(id);
		return new LoggedUserBeanFactory().getLoggedUserBean(currentUser);
	}
	
	
	// Restituisce i 10 percorsi con voti più alti
	public List<SimpleMountainPathBean> getTopTen() {
		List<SimpleMountainPathBean> topTenList = new ArrayList<>();
		List<MountainPath> results = mountainPathDao.getPaths();
		
		results.sort(Comparator.comparing(MountainPath::getNumberOfVotes).thenComparing(MountainPath::getVote).reversed());
		
		for (int i = 0; i < 10; i++) {
			topTenList.add(new SimpleMountainPathBeanFactory().getSimpleMountainPath(results.get(i)));
		}
		topTenList.sort(Comparator.comparing(SimpleMountainPathBean::getVote).reversed());
		
		return topTenList;
	}
	
	public List<SimpleMountainPathBean> getTopByFavorites() {
		List<SimpleMountainPathBean> topByFavoritesList = new ArrayList<>();
		List<MountainPath> results = mountainPathDao.getPaths();
		
		for (int i = 0; i < 10; i++) {
			topByFavoritesList.add(new SimpleMountainPathBeanFactory().getSimpleMountainPath(results.get(i)));
		}
		
		//dummy
		return topByFavoritesList;
	}
	
	@Override
	public void setNextPageId(String action) {
		
	}

}
