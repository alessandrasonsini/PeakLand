package logic.model.dao;

import java.util.HashMap;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import logic.model.Credential;
import logic.model.MountainPath;

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
	
	public void saveNewCredentialOnDb(Credential newCredential) {
		HashMap<String, Object> credential = new HashMap<>();
		credential.put(newCredential.getUsername(), newCredential);
		
		// NON MI FA IMPAZZIRE CHE NON FACCIAMO CONTROLLI SE IL SALVATAGGIO E' ANDATO BENE
		this.dbReferenceCredential.updateChildrenAsync(credential);
		
	}
	
	@Override
	public void onSuccess(DataSnapshot dataSnapshot) {
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
