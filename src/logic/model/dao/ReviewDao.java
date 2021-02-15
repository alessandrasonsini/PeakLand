package logic.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Query;
import logic.model.Review;
import logic.model.exception.DatabaseException;
import logic.model.utils.StandardName;

public class ReviewDao extends Dao {

	private static final Logger LOGGER = Logger.getLogger(ReviewDao.class.getName());
	
	private List<Review> reviewResult;
	
	public ReviewDao() {
		super();
		this.reviewResult = new ArrayList<>();
	}

	public void saveNewReviewOnDb(Review review) throws DatabaseException {
		Map<String, Object> reviews = new HashMap<>();
		review.setPathName(StandardName.standardizeName(review.getPathName()));
		reviews.put(StandardName.standardizeName(review.getPathName())+"/"+review.getAuthor(), (Object)review);
		
		if(!writeData(reviews)) {
			throw new DatabaseException();
		}
	}
	
	public List<Review> getReviewFromDb(String pathName) {
		Query query = this.dbReference.child(pathName).orderByChild("pathName").equalTo(pathName);
		readData(query);
		return reviewResult;
	}
	
	@Override
	public void onReadSuccess(DataSnapshot dataSnapshot) {
		reviewResult.clear();
		if (dataSnapshot.exists()) {
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
            	Review review = snapshot.getValue(Review.class);
            	reviewResult.add(review);
            }
		}
        else {
        	LOGGER.log(Level.FINE,"No matches founded");
        }
	}

	@Override
	protected String getChild() {
		return "Review";
	}

	@Override
	protected String getDirectory() {
		return this.directory;
	}
}
