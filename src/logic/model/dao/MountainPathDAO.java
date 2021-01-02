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

public class MountainPathDAO extends Dao {
	
	private DatabaseConnection dbConnection;
	private DatabaseReference dbReferenceMountainPath;
	private List<MountainPath> mountainPathResult;
	
	public MountainPathDAO() {
		this.dbConnection = DatabaseConnection.getInstance();
		this.dbReferenceMountainPath = this.dbConnection.getDatabaseReference().child("Mountain Path");
	}
	
	public void saveNewMountainPathOnDB(MountainPath mountainPath) {
		Map<String, MountainPath> paths = new HashMap<>();
		// Inserimento di ID (costituito dal nome) e dei dati del mountainPath
		paths.put(mountainPath.getName(), mountainPath);
		
		// Aggiunta del path al nodo MountainPath del DB
		dbReferenceMountainPath.setValueAsync(paths);
	}
	
	public List<MountainPath> searchMountainPathByName(String pathName) {
		this.mountainPathResult = new ArrayList<>();
		//Creo la query al database
		Query query = dbReferenceMountainPath.orderByChild("name").startAt(pathName).endAt(pathName+"\uf8ff");
		readData(query);
		
		return mountainPathResult;
	}

	@Override
	public void onSuccess(DataSnapshot dataSnapshot) {
		if (dataSnapshot.exists()) {
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                MountainPath mountainPath = snapshot.getValue(MountainPath.class);
        		mountainPathResult.add(mountainPath);
        		System.out.println("Thread");
            }
        }
        else {
        	System.out.println("no matches for path with name");
        }
		
	}
	
	
}
