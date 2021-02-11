package logic.model.utils;

import logic.bean.ReviewBean;
import logic.model.Review;

public class ReviewConverter {
	
	private ReviewConverter() {
		// Costruttore privato per classe di utils con solo metodi statici
	}

	public static ReviewBean getReviewBean(Review review) {
		ReviewBean bean = new ReviewBean();
		
		bean.setPathName(review.getPathName());
		bean.setAuthor(review.getAuthor());
		bean.setTitle(review.getTitle());
		bean.setVote(review.getVote());
		bean.setComment(review.getComment());
		
		return bean;
	}
	
	public static Review getReview(ReviewBean bean) {
		Review review = new Review();
		
		review.setPathName(bean.getPathName());
		review.setAuthor(bean.getAuthor());
		review.setTitle(bean.getTitle());
		review.setVote(bean.getVote());
		review.setComment(bean.getComment());
		
		return review;
	}
}
