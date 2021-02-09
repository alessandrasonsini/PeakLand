package logic.view.desktop.graphic_element;

import javafx.scene.control.ListCell;
import logic.model.bean.ReviewBean;

public class ReviewListCell extends ListCell<ReviewBean> {
	@Override
	public void updateItem(ReviewBean itemBean, boolean empty) {
		super.updateItem(itemBean, empty);
		if(itemBean != null) {
			ReviewItem item = new ReviewItem();
			item.setInfo(itemBean.getVote(), itemBean.convertToText(itemBean.getAuthor()), itemBean.convertToText(itemBean.getComment()), itemBean.convertToText(itemBean.getTitle()));
			setGraphic(item.getBox());
		}
	}
}
