package logic.model.bean.factory;

import logic.model.MountainPath;
import logic.model.bean.MountainPathBean;

public class MountainPathBeanFactory {

	public MountainPathBean getMountainPathBean(MountainPath path) {
		MountainPathBean bean = new MountainPathBean();
		
		bean.setName(path.getName());
		bean.setRegion(path.getLocation().getRegion());
		bean.setProvince(path.getLocation().getProvince());
		bean.setCity(path.getLocation().getCity());
		bean.setLevel(path.getLevel().toString());
		bean.setHours((path.getTravelTime().getHours()));
		bean.setMinutes((path.getTravelTime().getMinutes()));
		bean.setVote(path.getVote());
		bean.setNumberOfVotes(path.getNumberOfVotes());
		bean.setAltitude(path.getAltitude());
		
		String[] landscape = new String[path.getLandscape().size()];
		for (int i = 0; i < path.getLandscape().size(); i++) {
			landscape[i] = path.getLandscape().get(i).toString();
		}
		bean.setLandscape(landscape);
		
		String[] ground = new String[path.getGround().size()];
		for (int i = 0; i < path.getGround().size(); i++) {
			ground[i] = path.getGround().get(i).toString();
		}
		bean.setGround(ground); 
		
		bean.setCycleble(path.isCycleble());
		bean.setHistoricalElements(path.isHistoricalElements());
		bean.setFamilySuitable(path.isFamilySuitable());
		bean.setLenght(path.getLenght());
		
		return bean;
	}
}
