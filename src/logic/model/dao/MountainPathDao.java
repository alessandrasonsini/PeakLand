package logic.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Query;
import logic.model.MountainPath;
import logic.model.exception.DatabaseException;

public class MountainPathDao extends Dao {
	
	private static final Logger LOGGER = Logger.getLogger(MountainPathDao.class.getName());
	
	//private DatabaseReference dbReferenceMountainPath;
	private List<MountainPath> mountainPathResult;
	
	public MountainPathDao() {
		//this.dbReferenceMountainPath = getSpecificReference();
		this.mountainPathResult = new ArrayList<>();
	}
	
	public void saveNewMountainPathOnDB(MountainPath mountainPath) throws DatabaseException {
		Map<String, Object> paths = new HashMap<>();
		// Inserimento di ID (costituito dal nome) e dei dati del mountainPath
		paths.put(mountainPath.getName(), (Object)mountainPath);
		// Aggiunta del path al nodo MountainPath del DB
		if(!writeData(paths)) {
			// se fallisce
			throw new DatabaseException();
		}

	}
	
	public List<MountainPath> searchMountainPathByName(String name) {
		mountainPathResult.clear();
		Query query = this.dbReference.orderByChild("name").equalTo(name);
		readData(query);
		return mountainPathResult;
	}
	
	public List<MountainPath> searchMountainPathByPartialName(String pathName) {
		mountainPathResult.clear();
		//Creo la query al database
		Query query = this.dbReference.orderByChild("name").startAt(pathName).endAt(pathName+"\uf8ff");
		readData(query);
		return mountainPathResult;
	}
	
	@Override
	public void onReadSuccess(DataSnapshot dataSnapshot) {
		if (dataSnapshot.exists()) {
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {          	
            	MountainPath mountainPath = snapshot.getValue(MountainPath.class);
        		mountainPathResult.add(mountainPath);
            }
		}
        else {
        	LOGGER.log(Level.FINE,"No matches founded");
        }    
		
	}

	@Override
	protected String getChild() {
		return "Mountain Path";
	}

}
