package logic.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Query;

import logic.model.Credential;
import logic.model.exception.DatabaseException;

public class CredentialDao extends Dao{
	
	private List<Credential> credentialResults;
	
	public CredentialDao() {
		super();
		this.credentialResults = new ArrayList<>();
	}
	
	public Credential getCredentialFromDb(String username) {
		Query query = this.dbReference.orderByChild("username").equalTo(username);
		readData(query);
		return credentialResults.size()>0 ? credentialResults.get(0): null;
		
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
		credentialResults.clear();
		// TROVARE UN MODO DECENTE PER FARE STA COSA
		if(dataSnapshot.exists()) {
			for (DataSnapshot snapshot : dataSnapshot.getChildren()) { 
				credentialResults.add(snapshot.getValue(Credential.class));
            }
		}
	}

	@Override
	protected String getChild() {
		return "Credential";
	}
	
}
