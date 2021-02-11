package logic.model.dao;

import java.io.InputStream;
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
		this.directory = "Logged user/";
	}
	
	public LoggedUser getLoggedUserInfoFromDb(String id) {
		Query query = this.dbReference.orderByChild("username").equalTo(id);
		readData(query);
		return loggedUserResults.size()>0 ? loggedUserResults.get(0): null;
	}
	
	public void saveLoggedUserOnDb(LoggedUser user) throws DatabaseException {
		Map<String, Object> users = new HashMap<>();
		// Inserimento di ID (costituito dallo username) e dei dati dello user
		users.put(user.getUsername(), (Object)user);
		
		if(!writeData(users)) {
			// se fallisce
			throw new DatabaseException();
		}
	}

	
	public void updateUserImage(InputStream imageStream, String name) {
		// Controlla se è già presenta una foto del profilo dell'utente
		if(getImage(name) != null) {
			// se è presente la cancella
			deleteImage(name);
		}
		uploadImage(imageStream, name);
	}

	
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

	@Override
	protected String getDirectory() {
		return this.directory;
	}
	
	
}
