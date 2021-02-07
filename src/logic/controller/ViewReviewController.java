package logic.controller;

import logic.model.dao.ReviewDao;
import java.util.List;
import logic.model.Review;

public class ViewReviewController extends Controller {

	private ReviewDao reviewDao;
	
	public ViewReviewController() {
		super();
		this.reviewDao = new ReviewDao();
	}
	
	public List<Review> getReview(String pathName) {
		return this.reviewDao.getReviewFromDb(pathName);
	}
	
	public Review getOneReview(String pathName) {
		return this.reviewDao.getOneReviewFromDb(pathName);
	}
	
	@Override
	public void setNextPageId(String action) {
		if(action.equals("init"))
			this.nextPageId = "View Review";
		else this.nextPageId = null;
	}

}
