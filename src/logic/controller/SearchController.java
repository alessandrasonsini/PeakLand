package logic.controller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import logic.model.DifficultyLevel;
import logic.view.bean.SimpleMountainPathBean;

public class SearchController {

	public List<SimpleMountainPathBean> searchMountainPath(String name) {
		
		List<SimpleMountainPathBean> list = new ArrayList<>();
		SimpleMountainPathBean bean = new SimpleMountainPathBean("Valle Dell'Orfento", "Caramanico", DifficultyLevel.E, LocalTime.of(1, 30), 4, 300);
		
		list.add(bean);
		
		return list;
	}
}
