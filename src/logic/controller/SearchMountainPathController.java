package logic.controller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import logic.model.DifficultyLevelEnum;
import logic.model.bean.SimpleMountainPathBean;

public class SearchMountainPathController {

	public List<SimpleMountainPathBean> searchMountainPath(String name) {
		
		List<SimpleMountainPathBean> list = new ArrayList<>();
		SimpleMountainPathBean bean = new SimpleMountainPathBean();
		bean.setName("Valle Dell'Orfento");
		bean.setLocation("Caramanico");
		bean.setLevel(DifficultyLevelEnum.E);
		bean.setTravelTime(LocalTime.of(1, 30));
		bean.setVote(4);
		bean.setNumberOfVotes(300);
		
		list.add(bean);
		
		return list;
	}
}
