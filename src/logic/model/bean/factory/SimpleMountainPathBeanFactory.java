package logic.model.bean.factory;

import logic.model.MountainPath;
import logic.model.bean.SimpleMountainPathBean;

public class SimpleMountainPathBeanFactory {
	
	public SimpleMountainPathBean getSimpleMountainPath(MountainPath path) {
		SimpleMountainPathBean bean = new SimpleMountainPathBean();
				
		bean.setName(path.getName());
		bean.setLocationRegion(path.getLocationRegion());
		bean.setLocationCity(path.getLocationCity());
		bean.setLevel(path.getLevel());
		bean.setTravelTime(path.getTravelTime());
		bean.setVote(path.getVote());
		bean.setNumberOfVotes(path.getNumberOfVotes());
		
		return bean;
	}

}
