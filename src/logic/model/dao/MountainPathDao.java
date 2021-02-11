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
import logic.model.StandardName;
import logic.model.exception.DatabaseException;

public class MountainPathDao extends Dao {
	
	private static final Logger LOGGER = Logger.getLogger(MountainPathDao.class.getName());

	private List<MountainPath> mountainPathResult;
	
	public MountainPathDao() {
		super();
		this.mountainPathResult = new ArrayList<>();
		this.directory = "Mountain path/";
	}
	
	public void saveNewMountainPathOnDB(MountainPath mountainPath) throws DatabaseException {
		Map<String, Object> paths = new HashMap<>();
		
		// Inserimento di ID (costituito dal nome) e dei dati del mountainPath
		String standardName = StandardName.standardizeName(mountainPath.getName());
		mountainPath.setName(standardName);
		paths.put(mountainPath.getName(), (Object)mountainPath);
		
		// Aggiunta del path al nodo MountainPath del DB
		if(!writeData(paths)) {
			// se fallisce
			throw new DatabaseException();
		}
	}
	
	public MountainPath searchMountainPathByName(String name) {
		Query query = this.dbReference.orderByChild("name").equalTo(StandardName.standardizeName(name));
		readData(query);
		return mountainPathResult.size()>0 ? mountainPathResult.get(0) : null;
	}
	
	public List<MountainPath> searchMountainPathByPartialName(String pathName) {
		pathName = StandardName.standardizeName(pathName);
		Query query = this.dbReference.orderByChild("name").startAt(pathName).endAt(pathName+"\uf8ff");
		readData(query);
		return mountainPathResult;
	}
	
	public List<MountainPath> searchMountainPathbyFilter(String filterName, String filterValue){
		String dbFilterName = convertFieldName(filterName);
		System.out.println("filtro: " + dbFilterName + "    filter value :" + filterValue);
		Query query = this.dbReference.orderByChild(dbFilterName).equalTo(filterValue);
		executeQuery(query);
		return mountainPathResult;
	}
	
	
	public List<MountainPath> searchMountainPathbyFilter(String filterName, Boolean filterValue){
		Query query = this.dbReference.orderByChild(filterName).equalTo(filterValue);
		executeQuery(query);	
		return mountainPathResult;
	}
	
	public List<MountainPath> searchMountainPathbyFilter(String filterName, Integer filterValue){
		Query query = this.dbReference.orderByChild(filterName).equalTo(filterValue);
		executeQuery(query);
		return mountainPathResult;
	}
	
	public List<MountainPath> getPaths() {
		Query query = this.dbReference;
		executeQuery(query);
		return mountainPathResult;
	}
	
	public void updateMountainPath(MountainPath path) {
		Map<String, Object> paths = new HashMap<>();
		
		paths.put(path.getName(), (Object)path);
		this.dbReference.updateChildrenAsync(paths);
	}
	
	private void executeQuery(Query query){
		mountainPathResult.clear();
		readData(query);
	}
	
	private String convertFieldName(String name) {
		String newName;
		switch(name) {
			case "cityLocation":
				newName = "location/city";
				break;
			case "regionLocation":
				newName = "location/region";
				break;	
			case "provinceLocation":
				newName = "location/province";
				break;	
			case "hours":
			case "minutes":
				newName = "travelTime" + name;
				break;
			default:
				newName = name;	
		}
		
		return newName;
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
	
	@Override
	protected String getChild() {
		return "Mountain Path";
	}

	@Override
	protected String getDirectory() {
		return this.directory;
	}

}
