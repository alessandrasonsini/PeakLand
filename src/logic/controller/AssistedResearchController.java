package logic.controller;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import logic.model.MountainPath;
import logic.model.bean.MountainPathBean;
import logic.model.dao.MountainPathDao;

public class AssistedResearchController extends Controller {

	private MountainPathDao mountainPathDao;
	private List<String> intersectionResult;

	public AssistedResearchController() {
		super();
		this.mountainPathDao = new MountainPathDao();
	}

	@SuppressWarnings("unchecked")
	public List<MountainPath> searchMountainPathByFilter(MountainPathBean wishPath) {
		this.intersectionResult = null;
		String fieldName;
		List<MountainPath> firstResult = null;
		// Chiama il metodo della bean che restituisce tutti i suoi Fields tramite la reflection
		Field[] fields = wishPath.getFields();

		for (Field f : fields) {
			f.setAccessible(true);
			if(this.intersectionResult != null && this.intersectionResult.isEmpty()) {
				// Se l'intersezione dei risultati ha già dato esito vuoto, interrompi il ciclo
				break;
			}
			else {
				try {
					Object obj = f.get(wishPath);
					if (obj != null) {
						// Coverte il nome del field
						fieldName = convertFieldName(f.getName());
						// Prende il tipo del Field
						Class<?> type = f.getType();
						List<MountainPath> returnValue;
						if (type == String[].class) {
							returnValue = searchFilterInAList((Object[]) obj, fieldName);
						} else {
							// Prende il metodo del mountainPathDao per effettuare la ricerca per filtro
							Method m = mountainPathDao.getClass().getDeclaredMethod("searchMountainPathbyFilter",
									new Class<?>[] { String.class, type });
							// Invoca il metodo
							returnValue = (List<MountainPath>) m.invoke(mountainPathDao, new Object[] { fieldName, obj });

							getResultsNameIntersection(returnValue);
												
						}
						// Salva una sola volta il risultato, perchè comunque dovendo operare con l'intersezione di tutti 
						// i risultati, il necessario sta sicuramente anche nel primo
						if(firstResult == null) {
							firstResult = returnValue;
						}	
					}
					
				} catch (NoSuchMethodException | SecurityException | IllegalArgumentException | IllegalAccessException
						| InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.print("Exception");
				}
			}
			
		}
		return getMountainPathIntersection(firstResult);
	}
	
	private void getResultsNameIntersection(List<MountainPath> newResult) {
		// Prende i nomi dei mountain path del nuovo risultato
		List<String> newResultName = new ArrayList<>();
		for(MountainPath path : newResult)
			newResultName.add(path.getName());
		if(this.intersectionResult == null) {
			// Inizializza la lista con il primo risultato
			this.intersectionResult = newResultName;
		}	
		else {
			// Mantiene solo i nomi comuni alle due liste
			this.intersectionResult.retainAll(newResultName);
		}
	}
	
	private List<MountainPath> getMountainPathIntersection(List<MountainPath> firstResult){
		List<MountainPath> intersectionResult = new ArrayList<>();
		if(firstResult != null && this.intersectionResult != null) {
			for(String name : this.intersectionResult) {
				for(MountainPath path : firstResult) {
					if(path.getName().equals(name)) {
						intersectionResult.add(path);
						firstResult.remove(path);
						break;
					}
						
				}
			}
		}	
		return intersectionResult;
	}
	
	private List<MountainPath> searchFilterInAList(Object[] list, String filterName) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<MountainPath> firstResult = null;
		List<MountainPath> returnValue = new ArrayList<>();
		int size = getPossibilitiesSize(filterName);
		for (Object obj : list) {
			returnValue.clear(); 
			for (int i = 0; i < size; i++) {
				returnValue.addAll(mountainPathDao.searchMountainPathbyFilter(filterName + "/" + String.valueOf(i), obj.toString()));
			}
			if(firstResult == null)
				firstResult = returnValue;
			getResultsNameIntersection(returnValue);
		}
		return firstResult;
	}
	
	// Ritorna il numero di possibili valori per l'enum associato al filterName
	private int getPossibilitiesSize(String filterName) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String enumClassName = "logic.model.enums." + filterName.substring(0,1).toUpperCase() + filterName.substring(1) + "Enum";
		int size = 0;
		try {
			// Prende l'Enum associato ad enumClassName
		     Class<?> enumClass = Class.forName(enumClassName);
		     // Invoca il metodo che ritorna tutti i valori dell'enum 
		     Method m = enumClass.getDeclaredMethod("values");
		     Object possibilities = m.invoke(enumClass,(Object[])null);
		     size = ((Object[])possibilities).length;
		      
		    }
		    catch (ClassNotFoundException e) {
		      e.printStackTrace();
		    }
		return size;
	}
	// NON SO SE E' CORRETTO QUI O DEVE ANDARE NEL DAO
	private String convertFieldName(String name) {
		String newName;
		if(name.equals("city") | name.equals("region") | name.equals("province"))
			newName = "location/" + name;
		else if(name.equals("hours") | name.equals("minutes"))
			newName = "travelTime/" + name;
		else newName = name;
		
		return newName;
	}

	@Override
	public void setNextPageId(String action) {
		if(action.equals("init"))
			this.nextPageId = "Assisted research";
		else this.nextPageId = null;

	}

}
