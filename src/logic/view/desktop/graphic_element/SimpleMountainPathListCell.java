package logic.view.desktop.graphic_element;

import javafx.scene.control.ListCell;
import logic.bean.SimpleMountainPathBean;

public class SimpleMountainPathListCell extends ListCell<SimpleMountainPathBean> {
	
	@Override
	public void updateItem(SimpleMountainPathBean itemBean, boolean empty) {
		super.updateItem(itemBean, empty);
		if(itemBean != null) {
			SimpleMountainPathItem newItem = new SimpleMountainPathItem();
			newItem.setInfo(itemBean);
			setGraphic(newItem.getBox());
			
		}
	}
}
	
