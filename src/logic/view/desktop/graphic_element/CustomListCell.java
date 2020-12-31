package logic.view.desktop.graphic_element;


import javafx.scene.control.ListCell;
import logic.model.bean.SimpleMountainPathBean;

public class CustomListCell extends ListCell<SimpleMountainPathBean> {
	
	
	@Override
	public void updateItem(SimpleMountainPathBean item, boolean empty) {
		super.updateItem(item, empty);
		if(item != null) {
			SimpleMountainPathItem newItem = new SimpleMountainPathItem();
			newItem.setInfo(item.getName(), item.getLocationRegion(), item.getLocationCity(), item.getLevel().toString(), item.getTravelTime().toString());
			setGraphic(newItem.getBox());
		}
	}
}
	