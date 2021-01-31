package logic.controller;

import logic.model.Review;
import logic.model.bean.ReviewBean;
import logic.model.dao.ReviewDao;
import logic.model.exception.DatabaseException;

public class AddReviewController extends Controller {

	
	public AddReviewController() {
		super();
	}
	
	public void saveReview(ReviewBean reviewBean) throws DatabaseException {
		Review newReview = new Review();
		
		newReview.setPathName(reviewBean.getPathName());
		newReview.setVote(reviewBean.getVote());
		newReview.setTitle(reviewBean.getTitle());
		newReview.setComment(reviewBean.getComment());
		newReview.setAuthor(reviewBean.getAuthor());
		
		ReviewDao reviewDao = new ReviewDao();
		reviewDao.saveNewReviewOnDb(newReview);
	}
	
	@Override
	public void setNextPageId(String action) {
		if(action.equals("init"))
			this.nextPageId = "Add path";
		else 
			this.nextPageId = null;
	}
	
}
