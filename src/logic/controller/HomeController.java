package logic.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import logic.controller.utils.CurrentLoggedUsers;
import logic.model.LoggedUser;
import logic.model.MountainPath;
import logic.model.bean.factory.SimpleMountainPathBeanFactory;
import logic.model.dao.MountainPathDao;
import logic.model.bean.SimpleMountainPathBean;

public class HomeController extends Controller {

	private MountainPathDao mountainPathDao;
	private String currentUserName;
	
	public HomeController() {
		super();
		this.mountainPathDao = new MountainPathDao();
	}
	
	public String getCurrentUserName() {
		return this.currentUserName;
	}
	
	public List<SimpleMountainPathBean> getClassification(Integer userId){
		if(userId != null) {
			LoggedUser currentUser = CurrentLoggedUsers.getInstance().getCurrentLoggedUser(userId);
			if(currentUser != null) {
				this.currentUserName = currentUser.getName();
				return getTopByFavorites();
			}
		}
		return getTopTen();
	}
	
	// Restituisce i 10 percorsi con voti più alti
	private List<SimpleMountainPathBean> getTopTen() {
		List<SimpleMountainPathBean> topTenList = new ArrayList<>();
		List<MountainPath> results = mountainPathDao.getPaths();
		
		results.sort(Comparator.comparing(MountainPath::getVote).thenComparing(MountainPath::getNumberOfVotes).reversed());
		SimpleMountainPathBean bean;
		for (int i = 0; i < 10; i++) {
			bean = new SimpleMountainPathBeanFactory().getSimpleMountainPath(results.get(i));
			bean.setRankPosition(i+1);
			topTenList.add(bean);
		}
		
		return topTenList;
	}
	
	private List<SimpleMountainPathBean> getTopByFavorites() {
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
		if(action.equals("init"))
			this.nextPageId = "Home";
		else this.nextPageId = null;
	}

}
