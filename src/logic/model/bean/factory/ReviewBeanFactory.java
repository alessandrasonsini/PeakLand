package logic.model.bean.factory;

import logic.model.Review;
import logic.model.bean.ReviewBean;

public class ReviewBeanFactory {

	public ReviewBean getReviewBean(Review review) {
		ReviewBean bean = new ReviewBean();
		
		bean.setPathName(review.getPathName());
		bean.setAuthor(review.getAuthor());
		bean.setTitle(review.getTitle());
		bean.setVote(review.getVote());
		bean.setComment(review.getComment());
		
		return bean;
	}
}
