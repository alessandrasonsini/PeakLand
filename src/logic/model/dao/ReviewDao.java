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

public class ReviewDao extends Dao {

	private static final Logger LOGGER = Logger.getLogger(ReviewDao.class.getName());
	
	private List<Review> reviewResult;
	
	public ReviewDao() {
		super();
		this.reviewResult = new ArrayList<>();
	}

	public void saveNewReviewOnDb(Review review) throws DatabaseException {
		Map<String, Object> reviews = new HashMap<>();
		review.setPathName(standardizeName(review.getPathName()));
		reviews.put(standardizeName(review.getPathName())+"/"+review.getAuthor(), (Object)review);
		
		if(!writeData(reviews)) {
			throw new DatabaseException();
		}
	}
	
	public List<Review> getReviewFromDb(String pathName) {
		Query query = this.dbReference.child(pathName).orderByChild("pathName").equalTo(pathName);
		readData(query);
		return reviewResult;
	}
	
	public Review getOneReviewFromDb(String pathName) {
		Query query = this.dbReference.child(pathName).orderByChild("pathName").equalTo(pathName);
		readData(query);
		return reviewResult.size()>0 ? reviewResult.get(0) : null;
	}
	
	private String standardizeName(String name) {
		String standardName = name;
		if(name != null && name.length() > 0) {
			standardName = name.substring(0,1).toUpperCase();
			if(name.length() > 1)
				standardName += name.substring(1); 
		}
		return standardName;
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
}
