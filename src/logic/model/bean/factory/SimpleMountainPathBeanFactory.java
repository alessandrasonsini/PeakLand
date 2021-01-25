package logic.model.bean.factory;

import logic.model.MountainPath;
import logic.model.bean.SimpleMountainPathBean;

public class SimpleMountainPathBeanFactory {
	
	public SimpleMountainPathBean getSimpleMountainPath(MountainPath path) {
		SimpleMountainPathBean bean = new SimpleMountainPathBean();
				
		bean.setName(path.getName());
		bean.setRegion(path.getLocation().getRegion());
		bean.setProvince(path.getLocation().getProvince());
		bean.setCity(path.getLocation().getCity());
		bean.setLevel(path.getLevel().toString());
		bean.setHours((path.getTravelTime().getHours()));
		bean.setMinutes((path.getTravelTime().getMinutes()));
		bean.setVote(path.getVote());
		bean.setNumberOfVotes(path.getNumberOfVotes());
		
		return bean;
	}

}
