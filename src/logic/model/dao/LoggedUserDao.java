package logic.model.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import logic.model.LoggedUser;

public class LoggedUserDao extends Dao{
	
	private DatabaseReference dbReferenceLoggedUser;
	private LoggedUser result;
	
	private static final Logger LOGGER = Logger.getLogger(LoggedUserDao.class.getName());
	
	public LoggedUserDao() {
		super();
		this.dbReferenceLoggedUser = getSpecificReference();
	}
	
	public LoggedUser getLoggedUserInfoFromDb(String id) {
		Query query = dbReferenceLoggedUser.orderByChild("username").equalTo(id);
		readData(query);
		return result;
	}
	
	public void saveNewLoggedUserOnDb(LoggedUser user) {
		Map<String, Object> users = new HashMap<>();
		// Inserimento di ID (costituito dallo username) e dei dati dello user
		users.put(user.getUsername(), (Object)user);
		
		// Aggiunta dello user al nodo Logged User del DB
		this.dbReferenceLoggedUser.updateChildrenAsync(users);
	}
	
	//PER FARE LE COSE FATTE BENE COSTRUIRE LISTA DI RISULTATI PER IL RITORNO DELLA QUERY
	
	@Override
	public void onSuccess(DataSnapshot dataSnapshot) {
		if (dataSnapshot.exists()) {
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {          	
            	result = snapshot.getValue(LoggedUser.class);
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