package logic.controller;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import logic.bean.MountainPathBean;
import logic.model.MountainPath;
import logic.model.StandardName;
import logic.model.dao.MountainPathDao;
import logic.model.exception.SystemException;

public class AssistedResearchController extends Controller {

	private MountainPathDao mountainPathDao;
	private List<String> intersectionResult;

	public AssistedResearchController() {
		super();
		this.mountainPathDao = new MountainPathDao();
	}

	
	public List<MountainPath> searchMountainPathByFilters(MountainPathBean wishPath) throws SystemException {
		this.intersectionResult = null;
		//String fieldName;
		List<MountainPath> firstResult = null;
		// Chiama il metodo della bean che restituisce tutti i suoi Fields tramite la reflection
		Field[] fields = wishPath.getFields();

		for (Field f : fields) {
			System.out.println("----- field ----- " + f.getName());
			if(this.intersectionResult != null && this.intersectionResult.isEmpty()) {
				// Se l'intersezione dei risultati ha già dato esito vuoto, interrompi il ciclo
				break;
			}
			else {
				try {
					System.out.println("   field value   " + wishPath.getFieldValue(f));
					Object obj = wishPath.getFieldValue(f);
					if (obj != null) {
						/*
						// Coverte il nome del field
						fieldName = convertFieldName(f.getName());
						*/
						// Prende il tipo del Field
						Class<?> type = f.getType();
						
						//List<MountainPath> returnValue = searchMountainPathByFilter(obj,fieldName,type);
						List<MountainPath> returnValue = searchMountainPathByFilter(obj, f.getName(), type);
						getResultsNameIntersection(returnValue);
						
						// Salva una sola volta il risultato, perchè comunque dovendo operare con l'intersezione di tutti 
						// i risultati, il necessario sta sicuramente anche nel primo
						if(firstResult == null) {
							firstResult = returnValue;
						}
					}
				} catch (NoSuchMethodException e) {
					System.out.println(1);
					throw new SystemException();
				} catch (SecurityException e) {
					System.out.println(2);
					throw new SystemException();
				} catch (IllegalArgumentException e) {
					System.out.println(3);
					throw new SystemException();
				} catch (IllegalAccessException e) {
					System.out.println(4);
					throw new SystemException();
				} catch (InvocationTargetException e) {
					System.out.println(5);
					throw new SystemException();
				} catch (SystemException e) {
					System.out.println(6);
					throw new SystemException();
				}
			}
			
		}
		return getMountainPathIntersection(firstResult);
	}

	
	@SuppressWarnings("unchecked")
	private List<MountainPath> searchMountainPathByFilter(Object obj, String fieldName, Class<?> type) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SystemException {
		List<MountainPath> returnValue;
		if (type == String[].class) {
			returnValue = searchFilterInAList((Object[]) obj, fieldName);
			System.out.println("tutto okay if");
		} else {
			// Prende il metodo del mountainPathDao per effettuare la ricerca per filtro
			Method m = mountainPathDao.getClass().getDeclaredMethod("searchMountainPathbyFilter",
					new Class<?>[] { String.class, type });
			// Invoca il metodo
			returnValue = (List<MountainPath>) m.invoke(mountainPathDao, fieldName, obj);
			System.out.println("tutto okay else");
		}
		return returnValue;

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
		List<MountainPath> intersection = new ArrayList<>();
		if(firstResult != null && this.intersectionResult != null) {
			for(String name : this.intersectionResult) {
				for(MountainPath path : firstResult) {
					if(path.getName().equals(name)) {
						intersection.add(path);
						firstResult.remove(path);
						break;
					}
				}
			}
		}	
		return intersection;
	}
	
	private List<MountainPath> searchFilterInAList(Object[] list, String filterName) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SystemException {
		List<MountainPath> firstResult = null;
		List<MountainPath> returnValue = new ArrayList<>();
		int size = getPossibilitiesSize(filterName);
		for (Object obj : list) {
			returnValue.clear(); 
			for (int i = 0; i < size; i++) {
				returnValue.addAll(mountainPathDao.searchMountainPathbyFilter(filterName + "/" + i, obj.toString()));
			}
			if(firstResult == null)
				firstResult = returnValue;
			getResultsNameIntersection(returnValue);
		}
		return firstResult;
	}
	
	// Ritorna il numero di possibili valori per l'enum associato al filterName
	private int getPossibilitiesSize(String filterName) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SystemException {
		String enumClassName = "logic.model.enums." + StandardName.standardizeName(filterName) + "Enum";
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
		      throw new SystemException();
		    }
		return size;
	}
	

	@Override
	public void setNextPageId(String action) {
		if(action.equals("init"))
			this.nextPageId = "Assisted research";
		else this.nextPageId = null;

	}

}
