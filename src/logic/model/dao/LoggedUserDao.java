package logic.model.dao;

import java.util.HashMap;
import java.util.Map;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import logic.model.LoggedUser;
import logic.model.MountainPath;

public class LoggedUserDao extends Dao{
	
	private DatabaseReference dbReferenceLoggedUser;
	private LoggedUser result;
	
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

	@Override
	public void onSuccess(DataSnapshot dataSnapshot) {
		if (dataSnapshot.exists()) {
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {          	
            	result = snapshot.getValue(LoggedUser.class);
            }
        }
        else {
        	System.out.println("no matches for logged user");
        }
		
	}

	@Override
	protected String getChild() {
		return "Logged User";
	}
}
