package logic.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import logic.model.MountainPath;

public class MountainPathDAO {
	
	private DatabaseConnection dbConnection;
	private FirebaseDatabase firebaseDb;
	private DatabaseReference dbReference;
	
	public MountainPathDAO() {
		this.dbConnection = DatabaseConnection.getInstance();
		this.firebaseDb = dbConnection.getDB();
		this.dbReference = firebaseDb.getReference("MountainPath");
	}
	
	public void saveNewMountainPathOnDB(MountainPath mountainPath) {
		Map<String, MountainPath> paths = new HashMap<>();
		// Inserimento di ID (costituito dal nome) e dei dati del mountainPath
		paths.put(mountainPath.getName(), mountainPath);
		
		// Aggiunta del path al nodo MountainPath del DB
		dbReference.setValueAsync(paths);
	}
	
	public List<MountainPath> searchMountainPathByName(String pathName) {
		List<MountainPath> mountainPathList = new ArrayList<>();
		Query query = dbReference.orderByChild("name").startAt(pathName).endAt(pathName+"\uf8ff");
		
		query.addValueEventListener(new ValueEventListener() {
	        @Override
	        public void onDataChange(DataSnapshot dataSnapshot) {
	            if (dataSnapshot.exists()) {
	                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
	                    MountainPath mountainPath = snapshot.getValue(MountainPath.class);
	            		mountainPathList.add(mountainPath);
	                }
	            }
	            else {
	            	System.out.println("no matches for path with name '" + pathName + "'!");
	            }
	        }
	
	        @Override
	        public void onCancelled(DatabaseError databaseError) {
	        	//non so cosa metterci
	        }
		});
		
		return mountainPathList;
	}
}
