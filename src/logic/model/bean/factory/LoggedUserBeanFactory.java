package logic.model.bean.factory;

import logic.model.LoggedUser;
import logic.model.bean.LoggedUserBean;

public class LoggedUserBeanFactory {
	
	public LoggedUserBean getLoggedUserBean(LoggedUser user) {
		LoggedUserBean bean = new LoggedUserBean();
		bean.setName(user.getName());
		bean.setSurname(user.getSurname());
		bean.setLevel(user.getLevel().toString().toLowerCase());
		bean.setDescription(user.getDescription());
		
		return bean;
	}
	
	public LoggedUserBean getLoggedUserBean(String name, String surname, String description) {
		LoggedUserBean bean = new LoggedUserBean();
		bean.setName(name);
		bean.setSurname(surname);
		bean.setDescription(description);
		
		return bean;
	}
}
