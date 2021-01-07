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
		bean.setLevel(path.getLevel());
		bean.setHours((path.getTravelTime().getHours()));
		bean.setMinutes((path.getTravelTime().getMinutes()));
		bean.setVote(path.getVote());
		bean.setNumberOfVotes(path.getNumberOfVotes());
		bean.setAltitude(path.getAltitude());
		bean.setLandscape(path.getLandscape());
		bean.setGround(path.getGround()); 
		bean.setCycle(path.isCycleble());
		bean.setHistoricalElements(path.isHistoricalElements());
		bean.setFamilySuitable(path.isFamilySuitable());
		bean.setLenght(path.getLenght());
		
		return bean;
	}
}
