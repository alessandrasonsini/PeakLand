package logic.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Query;

import logic.model.LoggedUser;
import logic.model.exception.DatabaseException;

public class LoggedUserDao extends Dao{
	
	private List<LoggedUser> loggedUserResults;
	
	private static final Logger LOGGER = Logger.getLogger(LoggedUserDao.class.getName());
	
	public LoggedUserDao() {
		super();
		this.loggedUserResults = new ArrayList<>();
	}
	
	public LoggedUser getLoggedUserInfoFromDb(String id) {
		Query query = this.dbReference.orderByChild("username").equalTo(id);
		readData(query);
		return loggedUserResults.size()>0 ? loggedUserResults.get(0): null;
	}
	
	public void saveNewLoggedUserOnDb(LoggedUser user) throws DatabaseException {
		Map<String, Object> users = new HashMap<>();
		// Inserimento di ID (costituito dallo username) e dei dati dello user
		users.put(user.getUsername(), (Object)user);
		
		if(!writeData(users)) {
			// se fallisce
			throw new DatabaseException();
		}}
	
	@Override
	public void onReadSuccess(DataSnapshot dataSnapshot) {
		loggedUserResults.clear();
		if (dataSnapshot.exists()) {
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {          	
            	loggedUserResults.add(snapshot.getValue(LoggedUser.class));
            }
        }
        else {
        	LOGGER.log(Level.FINE, "No matches founded");
        }
		
	}

	@Override
	protected String getChild() {
		return "Logged User";
	}
}
