package logic.model.utils;

import logic.bean.LoggedUserBean;
import logic.model.LoggedUser;
import logic.model.enums.UserLevel;

public class LoggedUserConverter {
	
	private LoggedUserConverter() {
		// Costruttore privato per classe di utils con solo metodi statici
	}
	
	public static LoggedUserBean getLoggedUserBean(LoggedUser user) {
		LoggedUserBean bean = new LoggedUserBean();
		bean.setName(user.getName());
		bean.setSurname(user.getSurname());
		bean.setLevel(user.getLevel().toString().toLowerCase());
		bean.setDescription(user.getDescription());
		bean.setPeakCoin(user.getPeakCoin());
		
		return bean;
	}
	
	public static LoggedUserBean getLoggedUserBean(String name, String surname, String description, String level) {
		LoggedUserBean bean = new LoggedUserBean();
		bean.setName(name);
		bean.setSurname(surname);
		bean.setDescription(description);
		bean.setLevel(level);
		
		return bean;
	}
	
	public static LoggedUser getLoggedUser(LoggedUserBean bean) {
		LoggedUser user = new LoggedUser();
		user.setName(bean.getName());
		user.setSurname(bean.getSurname());
		user.setLevel(UserLevel.valueOf(bean.getLevel().toUpperCase()));
		user.setDescription(bean.getDescription());
		
		return user;
	}
	
}
