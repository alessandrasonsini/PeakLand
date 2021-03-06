package logic.model.utils;

import logic.bean.MountainPathBean;
import logic.bean.SimpleMountainPathBean;
import logic.model.Location;
import logic.model.MountainPath;
import logic.model.Time;
import logic.model.enums.DifficultyLevelEnum;

public class MountainPathConverter {
	
	private MountainPathConverter() {
		// Costruttore privato per classe di utils con solo metodi statici
	}

	public static MountainPathBean getMountainPathBean(MountainPath path) {
		MountainPathBean bean = new MountainPathBean();
		
		bean.setRegion(path.getLocation().getRegion());
		bean.setName(path.getName());
		bean.setCity(path.getLocation().getCity());
		bean.setProvince(path.getLocation().getProvince());
		bean.setAltitude(path.getAltitude());
		bean.setHours((path.getTravelTime().getHours()));
		bean.setMinutes((path.getTravelTime().getMinutes()));
		bean.setVote(path.getVote());
		bean.setNumberOfVotes(path.getNumberOfVotes());
		
		if (path.getLevel() != null)
			bean.setLevel(path.getLevel().toString());
		
		String[] landscape = new String[path.getLandscape().size()];
		for (int i = 0; i < path.getLandscape().size(); i++)
			landscape[i] = path.getLandscape().get(i).toString();
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
	
	public static SimpleMountainPathBean getSimpleMountainPath(MountainPath path) {
		SimpleMountainPathBean bean = new SimpleMountainPathBean();
				
		bean.setName(path.getName());
		bean.setRegion(path.getLocation().getRegion());
		bean.setProvince(path.getLocation().getProvince());
		bean.setCity(path.getLocation().getCity());
		if (path.getLevel() != null)
			bean.setLevel(path.getLevel().toString());
		bean.setHours((path.getTravelTime().getHours()));
		bean.setMinutes((path.getTravelTime().getMinutes()));
		bean.setVote(path.getVote());
		bean.setNumberOfVotes(path.getNumberOfVotes());
		
		return bean;
	}
	
	public static MountainPath getMountainPath(MountainPathBean bean) {
		MountainPath path = new MountainPath();
		path.setName(bean.getName());
		
		if (path.getAltitude() != null)
			path.setAltitude(bean.getAltitude());
		
		Location loc = new Location();
		if (bean.getRegion() != null)
			loc.setRegion(StandardName.standardizeName(bean.getRegion()));
		if (bean.getProvince() != null)
			loc.setProvince(StandardName.standardizeName(bean.getProvince()));
		if (bean.getCity() != null)
			loc.setCity(StandardName.standardizeName(bean.getCity()));
		path.setLocation(loc);
		
		if (bean.getLenght() != null)
			path.setLenght(bean.getLenght());
		
		if (bean.getLevel() != null)
			path.setLevel(DifficultyLevelEnum.valueOf(bean.getLevel()));
		
		if (bean.getLandscape() != null)
			path.setLandscapeFromString(bean.getLandscape());
		
		if (bean.getGround() != null)
			path.setGroundFromString(bean.getGround());
		
		if (bean.isCycleble() != null)
			path.setCycleble(bean.isCycleble());
		if (bean.isHistoricalElements() != null)
				path.setHistoricalElements(bean.isHistoricalElements());
		if (bean.isFamilySuitable() != null)
			path.setFamilySuitable(bean.isFamilySuitable());
		
		Time time = new Time();
		if (bean.getMinutes() != null)
			time.setMinutes(bean.getMinutes());
		if (bean.getHours() != null)
			time.setHours(bean.getHours());
		path.setTravelTime(time);
		
		if (bean.getNumberOfVotes() != null)
			path.setNumberOfVotes(bean.getNumberOfVotes());
		if (bean.getVote() != null)
			path.setVote(bean.getVote());
		
		return path;
	}
	
}
