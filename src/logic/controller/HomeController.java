package logic.controller;

import java.util.List;
import logic.bean.SimpleMountainPathBean;
import logic.controller.utils.CurrentLoggedUsers;
import logic.model.LoggedUser;
import logic.model.Sorter;
import logic.model.dao.MountainPathDao;

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
		return Sorter.sortByVoteAndNumber(mountainPathDao.getPaths());
	}
	
	private List<SimpleMountainPathBean> getTopByFavorites() {
		return Sorter.sortByFavorites(mountainPathDao.getPaths());
	}
	
	@Override
	public void setNextPageId(String action) {
		if(action.equals("init"))
			this.nextPageId = "Home";
		else this.nextPageId = null;
	}

}
