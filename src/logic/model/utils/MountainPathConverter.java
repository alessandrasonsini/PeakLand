package logic.model.utils;

import java.util.ArrayList;
import java.util.List;

import logic.bean.MountainPathBean;
import logic.bean.SimpleMountainPathBean;
import logic.model.Location;
import logic.model.MountainPath;
import logic.model.Time;
import logic.model.enums.DifficultyLevelEnum;
import logic.model.enums.GroundEnum;
import logic.model.enums.LandscapeEnum;

public class MountainPathConverter {
	
	private MountainPathConverter() {
		// Costruttore privato per classe di utils con solo metodi statici
	}

	public static MountainPathBean getMountainPathBean(MountainPath path) {
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
	
	public static SimpleMountainPathBean getSimpleMountainPath(MountainPath path) {
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
	
	public static MountainPath getMountainPath(MountainPathBean bean) {
		MountainPath path = new MountainPath();
		path.setName(bean.getName());
		path.setAltitude(bean.getAltitude());
		path.setLocation(new Location(bean.getRegion(), bean.getProvince(), bean.getCity()));
		path.setLenght(bean.getLenght());
		path.setLevel(DifficultyLevelEnum.valueOf(bean.getLevel()));
		
		List<LandscapeEnum> landscape = new ArrayList<>();
		for (String str : bean.getLandscape()) {
			landscape.add(LandscapeEnum.valueOf(str));
		}
		path.setLandscape(landscape);
		
		List<GroundEnum> ground = new ArrayList<>();
		for (String str : bean.getGround()) {
			ground.add(GroundEnum.valueOf(str));
		}
		path.setGround(ground);
		
		path.setCycleble(bean.isCycleble());
		path.setHistoricalElements(bean.isHistoricalElements());
		path.setFamilySuitable(bean.isFamilySuitable());
		path.setTravelTime(new Time(bean.getHours(), bean.getMinutes()));
		path.setNumberOfVotes(bean.getNumberOfVotes());
		path.setVote(bean.getVote());
		
		return path;
	}
	
}
