package logic.controller;

import java.util.ArrayList;
import java.util.List;

import logic.model.MountainPath;
import logic.model.bean.SimpleMountainPathBean;

public class SearchMountainPathController extends Controller {

	public List<SimpleMountainPathBean> searchMountainPathByName(String name) {
		
		List<MountainPath> results = MountainPath.searchMountainPathByName(name);
		
		List<SimpleMountainPathBean> beanResults = new ArrayList<>();
		
		for(MountainPath path : results) {
			SimpleMountainPathBean bean = new SimpleMountainPathBean();
			bean.setName(path.getName());
			bean.setLocationRegion(path.getLocationRegion());
			bean.setLocationCity(path.getLocationCity());
			bean.setLevel(path.getLevel());
			bean.setTravelTime(path.getTravelTime());
			
			// ------------ capire come inserire questi due campi
			//bean.setVote(4);
			//bean.setNumberOfVotes(300);
			
			beanResults.add(bean);
		}
		
		/*List<SimpleMountainPathBean> list = new ArrayList<>();
		SimpleMountainPathBean bean = new SimpleMountainPathBean();
		bean.setName("Valle Dell'Orfento");
		bean.setLocation("Caramanico");
		bean.setLevel(DifficultyLevelEnum.E);
		bean.setTravelTime(LocalTime.of(1, 30));
		bean.setVote(4);
		bean.setNumberOfVotes(300);
		
		list.add(bean);
		
		return list;*/
		
		return beanResults;
	}

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setup() {
		setNextStepId("Search path");
	}

	
}
