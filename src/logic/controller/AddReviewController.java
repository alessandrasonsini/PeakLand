package logic.controller;

import logic.bean.ReviewBean;
import logic.model.MountainPath;
import logic.model.Review;
import logic.model.dao.MountainPathDao;
import logic.model.dao.ReviewDao;
import logic.model.enums.PageId;
import logic.model.exception.DatabaseException;
import logic.model.utils.ReviewConverter;

public class AddReviewController extends Controller {

	private ReviewDao reviewDao;
	private MountainPathDao mountainPathDao;
	
	public AddReviewController() {
		super();
		this.reviewDao = new ReviewDao();
		this.mountainPathDao = new MountainPathDao();
	}

	public void saveReview(ReviewBean reviewBean) throws DatabaseException {
		Review review = ReviewConverter.getReview(reviewBean);
		reviewDao.saveNewReviewOnDb(review);
		updateMountainPathVote(review.getPathName(), review.getVote());
	}
	
	private void updateMountainPathVote(String pathName, Integer vote) {
		MountainPath path = mountainPathDao.searchMountainPathByName(pathName);
		path.updateVote(vote);
		
		mountainPathDao.updateMountainPath(path);
	}
	
	@Override
	public void setNextPageId(String action) {
		if(action.equals("init"))
			this.nextPageId = PageId.ADD_REVIEW;
		else 
			this.nextPageId = null;
	}
	
}
