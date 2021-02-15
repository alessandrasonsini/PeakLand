package logic.model.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import logic.model.MountainPath;

public class Sorter {
	
	private Sorter() {
		// Costruttore privato per classe di utils con solo metodi statici
	}
	
	public static List<MountainPath> sortByVoteAndNumber(List<MountainPath> pathList) {
		pathList.sort(Comparator.comparing(MountainPath::getVote).thenComparing(MountainPath::getNumberOfVotes).reversed());
		return pathList;
	}
	
	public static List<MountainPath> sortByFavorites(List<MountainPath> pathList) {
		// Metodo implementato in maniera dummy
		List<MountainPath> topByFavoritesList = new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			topByFavoritesList.add(pathList.get(i));
		}
		
		return topByFavoritesList;
	}
	
	public static List<MountainPath> sortByVote(List<MountainPath> pathList) {
		pathList.sort(Comparator.comparing(MountainPath::getVote).reversed());
		
		return pathList;
	}
	
}
