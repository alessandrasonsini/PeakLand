package logic.controller;

import java.util.ArrayList;
import java.util.List;
import logic.bean.SimpleMountainPathBean;
import logic.controller.utils.CurrentLoggedUsers;
import logic.model.LoggedUser;
import logic.model.MountainPath;
import logic.model.Sorter;
import logic.model.dao.MountainPathDao;
import logic.model.utils.MountainPathConverter;

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
		SimpleMountainPathBean bean;
		List<MountainPath> sorterResult = Sorter.sortByVoteAndNumber(mountainPathDao.getPaths());
		for (int i = 0; i < 10; i++ ) {
			bean = MountainPathConverter.getSimpleMountainPath(sorterResult.get(i));
			bean.setRankPosition(i+1);
			topTenList.add(bean);
		}
		
		return topTenList;
	}
	
	private List<SimpleMountainPathBean> getTopByFavorites() {
		List<SimpleMountainPathBean> topByFavoritesList = new ArrayList<>();
		SimpleMountainPathBean bean;
		List<MountainPath> sorterResult = Sorter.sortByFavorites(mountainPathDao.getPaths());
		for (int i = 0; i < 10; i++ ) {
			bean = MountainPathConverter.getSimpleMountainPath(sorterResult.get(i));
			bean.setRankPosition(i+1);
			topByFavoritesList.add(bean);
		}
		
		return topByFavoritesList;
	}
	
	@Override
	public void setNextPageId(String action) {
		if(action.equals("init"))
			this.nextPageId = "Home";
		else this.nextPageId = null;
	}

}
