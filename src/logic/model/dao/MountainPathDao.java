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

//VEDERE SE IL METODO CHE STANDARDIZZA I NOMI LO VOGLIAMO LASCIARE QUI, FORSE SAREBBE MEGLIO SE LO FACESSE
//IL CONTROLLER, IL PROBLEMA E' CHE VIENE CHIAMATO DA PIU' CONTROLLER DIVERSI
public class MountainPathDao extends Dao {
	
	private static final Logger LOGGER = Logger.getLogger(MountainPathDao.class.getName());
	
	private List<MountainPath> mountainPathResult;
	
	public MountainPathDao() {
		this.mountainPathResult = new ArrayList<>();
	}
	
	public void saveNewMountainPathOnDB(MountainPath mountainPath) throws DatabaseException {
		Map<String, Object> paths = new HashMap<>();
		
		// Inserimento di ID (costituito dal nome) e dei dati del mountainPath
		String standardName = standardizeName(mountainPath.getName());
		mountainPath.setName(standardName);
		paths.put(mountainPath.getName(), (Object)mountainPath);
		
		// Aggiunta del path al nodo MountainPath del DB
		if(!writeData(paths)) {
			// se fallisce
			throw new DatabaseException();
		}
		
	}
	
	public MountainPath searchMountainPathByName(String name) {
		Query query = this.dbReference.orderByChild("name").equalTo(standardizeName(name));
		readData(query);
		return mountainPathResult.size()>0 ? mountainPathResult.get(0) : null;
	}
	
	public List<MountainPath> searchMountainPathByPartialName(String pathName) {
		//Creo la query al database
		pathName = standardizeName(pathName);
		Query query = this.dbReference.orderByChild("name").startAt(pathName).endAt(pathName+"\uf8ff");
		readData(query);
		return mountainPathResult;
	}
	
	public List<MountainPath> searchMountainPathbyFilter(String filterName, String filterValue){
		Query query= this.dbReference.orderByChild(filterName).equalTo(filterValue);
		executeQuery(query);
		return mountainPathResult;
	}
	
	public List<MountainPath> searchMountainPathbyFilter(String filterName, Boolean filterValue){
		Query query= this.dbReference.orderByChild(filterName).equalTo(filterValue);
		executeQuery(query);	
		return mountainPathResult;
	}
	
	public List<MountainPath> searchMountainPathbyFilter(String filterName, Integer filterValue){
		Query query= this.dbReference.orderByChild(filterName).equalTo(filterValue);
		executeQuery(query);
		return mountainPathResult;
	}
	
	private void executeQuery(Query query){
		mountainPathResult.clear();
		readData(query);
		System.out.println(mountainPathResult.size());
	}
	
	@Override
	public void onReadSuccess(DataSnapshot dataSnapshot) {
		mountainPathResult.clear();
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
	
	// Standardizza i nomi dei mountain path rendendo maiuscola la prima lettera prima di operare sul db
	private String standardizeName(String name) {
		String standardName = name;
		if(name != null && name.length() > 0) {
			standardName = name.substring(0,1).toUpperCase();
			if(name.length() > 1)
				standardName += name.substring(1); 
		}
		return standardName;
	}
	
	@Override
	protected String getChild() {
		return "Mountain Path";
	}

}
