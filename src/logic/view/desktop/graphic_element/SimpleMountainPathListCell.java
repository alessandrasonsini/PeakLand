package logic.view.desktop.graphic_element;

import javafx.scene.control.ListCell;
import logic.model.bean.SimpleMountainPathBean;

public class SimpleMountainPathListCell extends ListCell<SimpleMountainPathBean> {
	
	@Override
	public void updateItem(SimpleMountainPathBean itemBean, boolean empty) {
		super.updateItem(itemBean, empty);
		if(itemBean != null) {
			SimpleMountainPathItem newItem = new SimpleMountainPathItem();
			newItem.setInfo(itemBean.convertToText(itemBean.getName()), itemBean.convertToText(itemBean.getRegion()), 
					itemBean.convertToText(itemBean.getProvince()), itemBean.convertToText(itemBean.getCity()), 
					itemBean.convertToText(itemBean.getLevel()), itemBean.convertToText(itemBean.getHours()) + ":" + itemBean.convertToText(itemBean.getMinutes()),
					itemBean.getVote(), itemBean.getNumberOfVotes(), itemBean.getRankPosition());
			setGraphic(newItem.getBox());
		}
	}
}
	
