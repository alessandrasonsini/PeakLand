package logic.controller;

import java.util.List;
import logic.model.MountainPath;
import logic.model.Review;
import logic.model.bean.ReviewBean;
import logic.model.dao.MountainPathDao;
import logic.model.dao.ReviewDao;
import logic.model.exception.DatabaseException;

public class AddReviewController extends Controller {

	private ReviewDao reviewDao;
	private MountainPathDao mountainPathDao;
	
	public AddReviewController() {
		super();
		this.reviewDao = new ReviewDao();
		this.mountainPathDao = new MountainPathDao();
	}
	
	public void saveReview(ReviewBean reviewBean) throws DatabaseException {
		Review newReview = new Review();
		
		newReview.setPathName(reviewBean.getPathName());
		newReview.setVote(reviewBean.getVote());
		newReview.setTitle(reviewBean.getTitle());
		newReview.setComment(reviewBean.getComment());
		newReview.setAuthor(reviewBean.getAuthor());
		
		reviewDao.saveNewReviewOnDb(newReview);
		
		updateMountainPathVote(newReview.getPathName());
	}
	
	private void updateMountainPathVote(String pathName) {
		MountainPath path = mountainPathDao.searchMountainPathByName(pathName);
		List<Review> reviewList = reviewDao.getReviewFromDb(pathName);
		
		if (path.getNumberOfVotes() != null)
			path.setNumberOfVotes(path.getNumberOfVotes() + 1);
		else
			path.setNumberOfVotes(1);
		
		Integer sum = 0;
		for (Review review : reviewList) {
			sum += review.getVote();
		}
	
		path.setVote(sum / path.getNumberOfVotes());
		mountainPathDao.updateMountainPath(path);
	}
	
	@Override
	public void setNextPageId(String action) {
		if(action.equals("init"))
			this.nextPageId = "Add path";
		else 
			this.nextPageId = null;
	}
	
}
