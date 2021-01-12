package logic.model.dao;

import java.util.HashMap;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import logic.model.Credential;
import logic.model.exception.DatabaseException;

public class CredentialDao extends Dao{
	
	private Credential result;
	private DatabaseReference dbReferenceCredential;
	
	public CredentialDao() {
		super();
		this.dbReferenceCredential = getSpecificReference();
	}
	
	public Credential getCredentialFromDb(String username) {
		Query query = dbReferenceCredential.orderByChild("username").equalTo(username);
		readData(query);
		return result;
		
	}
	
	public void saveNewCredentialOnDb(Credential newCredential) throws DatabaseException {
		HashMap<String, Object> credential = new HashMap<>();
		credential.put(newCredential.getUsername(), newCredential);
		
		if(!writeData(credential)) {
			// se fallisce
			throw new DatabaseException();
		}

	}
	
	@Override
	public void onReadSuccess(DataSnapshot dataSnapshot) {
		// TROVARE UN MODO DECENTE PER FARE STA COSA
		if(dataSnapshot.exists()) {
			for (DataSnapshot snapshot : dataSnapshot.getChildren()) { 
            	result = snapshot.getValue(Credential.class);
            }
		}
		else result = null;
		
	}

	@Override
	protected String getChild() {
		return "Credential";
	}
	
}
