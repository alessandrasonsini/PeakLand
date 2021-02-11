package logic.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import logic.bean.SimpleMountainPathBean;
import logic.model.utils.MountainPathConverter;

public class Sorter {

	// PRENDE IN INPUT LISTE DI MOUNTAIN PATH E LI CONVERTE LUI IN BEAN.. SBAGLIATO ??
	
	public static List<SimpleMountainPathBean> sortByVoteAndNumber(List<MountainPath> pathList) {
		List<SimpleMountainPathBean> topTenList = new ArrayList<>();
		pathList.sort(Comparator.comparing(MountainPath::getVote).thenComparing(MountainPath::getNumberOfVotes).reversed());
		SimpleMountainPathBean bean;
		for (int i = 0; i < 10; i++) {
			bean = MountainPathConverter.getSimpleMountainPath(pathList.get(i));
			bean.setRankPosition(i+1);
			topTenList.add(bean);
		}
		
		return topTenList;
	}
	
	public static List<SimpleMountainPathBean> sortByFavorites(List<MountainPath> pathList) {
		// Metodo implementato in maniera dummy
		List<SimpleMountainPathBean> topByFavoritesList = new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			topByFavoritesList.add(MountainPathConverter.getSimpleMountainPath(pathList.get(i)));
		}
		
		return topByFavoritesList;
	}
	
	public static List<SimpleMountainPathBean> sortByVote(List<MountainPath> pathList) {
		List<SimpleMountainPathBean> sorted = new ArrayList<>();
		pathList.sort(Comparator.comparing(MountainPath::getVote).reversed());
		for (MountainPath path : pathList) {
			sorted.add(MountainPathConverter.getSimpleMountainPath(path));
		}
		
		return sorted;
	}
	
}
